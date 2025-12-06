package com.monitoring.collector.service

import com.monitoring.collector.model.Issue
import com.monitoring.collector.repository.metadata.IssueRepository
import org.slf4j.LoggerFactory
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class IssueService(
    private val issueRepository: IssueRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getOpenIssues(): List<Issue> {
        return issueRepository.findByResolved(false)
    }

    fun getAllIssues(): List<Issue> {
        return issueRepository.findAll()
    }

    @Retryable(
        value = [OptimisticLockingFailureException::class],
        maxAttempts = 5,
        backoff = Backoff(delay = 100)
    )
    fun resolveIssue(issueId: String, resolvedBy: String): Issue {
        val issue = issueRepository.findById(issueId)
            .orElseThrow { IllegalArgumentException("Issue not found: $issueId") }

        if (issue.resolved) {
            throw IllegalStateException("Issue is already resolved")
        }

        val resolvedIssue = issue.copy(
            resolved = true,
            resolvedBy = resolvedBy,
            resolvedAt = Instant.now()
        )

        return issueRepository.save(resolvedIssue)
    }

    @Recover
    fun recoverResolveIssue(e: OptimisticLockingFailureException, issueId: String, resolvedBy: String): Issue {
        logger.warn("Failed to resolve issue $issueId after retries due to concurrent modification")
        throw IllegalStateException("Issue is being modified by another user. Please try again.")
    }

    fun getIssueCount(): Map<String, Long> {
        val allIssues = issueRepository.findAll()
        return mapOf(
            "total" to allIssues.size.toLong(),
            "open" to allIssues.count { !it.resolved }.toLong(),
            "resolved" to allIssues.count { it.resolved }.toLong()
        )
    }
}

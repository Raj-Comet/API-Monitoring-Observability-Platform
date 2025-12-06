package com.monitoring.collector.repository.metadata

import com.monitoring.collector.model.Issue
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface IssueRepository : MongoRepository<Issue, String> {
    fun findByResolved(resolved: Boolean): List<Issue>
    fun findByServiceNameAndEndpoint(serviceName: String, endpoint: String): List<Issue>
    fun countByResolved(resolved: Boolean): Long
}

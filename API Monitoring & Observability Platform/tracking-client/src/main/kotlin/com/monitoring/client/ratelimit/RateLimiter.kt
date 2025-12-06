package com.monitoring.client.ratelimit

import io.github.bucket4j.Bandwidth
import io.github.bucket4j.Bucket
import io.github.bucket4j.Refill
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap

class RateLimiter(private val requestsPerSecond: Int) {

    private val buckets = ConcurrentHashMap<String, Bucket>()

    fun tryConsume(key: String): Boolean {
        val bucket = buckets.computeIfAbsent(key) { createBucket() }
        return bucket.tryConsume(1)
    }

    private fun createBucket(): Bucket {
        val refill = Refill.intervally(requestsPerSecond.toLong(), Duration.ofSeconds(1))
        val limit = Bandwidth.classic(requestsPerSecond.toLong(), refill)
        return Bucket.builder()
            .addLimit(limit)
            .build()
    }

    fun getCurrentRate(key: String): Int {
        val bucket = buckets[key] ?: return 0
        val available = bucket.availableTokens
        return (requestsPerSecond - available).toInt()
    }
}

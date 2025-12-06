package com.monitoring.collector.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "alerts")
data class Alert(
    @Id
    val id: String? = null,
    val serviceName: String,
    val endpoint: String,
    val alertType: String, // HIGH_LATENCY, SERVER_ERROR, RATE_LIMIT_EXCEEDED
    val message: String,
    val severity: String, // LOW, MEDIUM, HIGH, CRITICAL
    val timestamp: Instant,
    val acknowledged: Boolean = false
)

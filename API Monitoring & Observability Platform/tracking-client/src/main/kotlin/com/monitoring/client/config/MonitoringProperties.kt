package com.monitoring.client.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "monitoring")
data class MonitoringProperties(
    var enabled: Boolean = true,
    var collectorUrl: String = "http://localhost:8080/api/logs",
    var serviceName: String = "unknown-service",
    var rateLimit: RateLimitConfig = RateLimitConfig()
)

data class RateLimitConfig(
    var enabled: Boolean = true,
    var limit: Int = 100, // requests per second
    var service: String = ""
)

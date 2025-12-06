package com.monitoring.client.interceptor

import com.monitoring.client.config.MonitoringProperties
import com.monitoring.client.model.ApiLogDto
import com.monitoring.client.model.RateLimitEventDto
import com.monitoring.client.ratelimit.RateLimiter
import com.monitoring.client.sender.LogSender
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor
import java.time.Instant

class ApiTrackingInterceptor(
    private val properties: MonitoringProperties,
    private val rateLimiter: RateLimiter,
    private val logSender: LogSender
) : HandlerInterceptor {

    companion object {
        private const val START_TIME_ATTR = "startTime"
    }

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        if (!properties.enabled) return true

        request.setAttribute(START_TIME_ATTR, System.currentTimeMillis())

        // Check rate limit
        if (properties.rateLimit.enabled) {
            val endpoint = request.requestURI
            val allowed = rateLimiter.tryConsume(endpoint)

            if (!allowed) {
                // Log rate limit hit but allow request to proceed
                val event = RateLimitEventDto(
                    serviceName = properties.serviceName,
                    endpoint = endpoint,
                    timestamp = Instant.now(),
                    currentRate = rateLimiter.getCurrentRate(endpoint),
                    limit = properties.rateLimit.limit
                )
                logSender.sendRateLimitEvent(event)
            }
        }

        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        if (!properties.enabled) return

        val startTime = request.getAttribute(START_TIME_ATTR) as? Long ?: return
        val latency = System.currentTimeMillis() - startTime

        val log = ApiLogDto(
            serviceName = properties.serviceName,
            endpoint = request.requestURI,
            method = request.method,
            statusCode = response.status,
            latency = latency,
            requestSize = request.contentLengthLong.takeIf { it >= 0 } ?: 0L,
            responseSize = 0L, // Response size tracking would need a wrapper
            timestamp = Instant.now()
        )

        logSender.sendLog(log)
    }
}

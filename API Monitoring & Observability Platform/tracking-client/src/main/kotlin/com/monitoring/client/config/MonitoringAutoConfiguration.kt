package com.monitoring.client.config

import com.monitoring.client.interceptor.ApiTrackingInterceptor
import com.monitoring.client.ratelimit.RateLimiter
import com.monitoring.client.sender.LogSender
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableConfigurationProperties(MonitoringProperties::class)
@ConditionalOnProperty(prefix = "monitoring", name = ["enabled"], havingValue = "true", matchIfMissing = true)
class MonitoringAutoConfiguration(
    private val properties: MonitoringProperties
) : WebMvcConfigurer {

    @Bean
    fun rateLimiter(): RateLimiter {
        return RateLimiter(properties.rateLimit.limit)
    }

    @Bean
    fun logSender(): LogSender {
        return LogSender(properties)
    }

    @Bean
    fun apiTrackingInterceptor(rateLimiter: RateLimiter, logSender: LogSender): ApiTrackingInterceptor {
        return ApiTrackingInterceptor(properties, rateLimiter, logSender)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(apiTrackingInterceptor(rateLimiter(), logSender()))
            .addPathPatterns("/api/**")
    }
}

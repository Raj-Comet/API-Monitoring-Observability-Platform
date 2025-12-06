package com.monitoring.collector.controller

import com.monitoring.collector.model.ApiLogDto
import com.monitoring.collector.model.RateLimitEventDto
import com.monitoring.collector.service.LogCollectorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class LogCollectorController(
    private val logCollectorService: LogCollectorService
) {

    @PostMapping("/logs")
    fun collectLog(@RequestBody log: ApiLogDto): ResponseEntity<Map<String, String>> {
        logCollectorService.saveApiLog(log)
        return ResponseEntity.ok(mapOf("status" to "success"))
    }

    @PostMapping("/rate-limit-events")
    fun collectRateLimitEvent(@RequestBody event: RateLimitEventDto): ResponseEntity<Map<String, String>> {
        logCollectorService.saveRateLimitEvent(event)
        return ResponseEntity.ok(mapOf("status" to "success"))
    }
}

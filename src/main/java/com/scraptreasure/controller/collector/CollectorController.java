package com.scraptreasure.controller.collector;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collector")
public class CollectorController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('COLLECTOR')")
    public String collectorDashboard() {
        return "Collector dashboard access granted";
    }
}


package com.scraptreasure.controller.client;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('CLIENT')")
    public String clientDashboard() {
        return "Client dashboard access granted";
    }
}
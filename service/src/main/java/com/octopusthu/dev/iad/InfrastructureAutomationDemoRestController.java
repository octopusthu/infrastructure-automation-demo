package com.octopusthu.dev.iad;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author octopusthu@gmail.com
 */
@RestController
public class InfrastructureAutomationDemoRestController {
    @GetMapping("/api/demo")
    public String hello() {
        return "Hello, world! Let's generate a new UUID: " + UUID.randomUUID();
    }

}

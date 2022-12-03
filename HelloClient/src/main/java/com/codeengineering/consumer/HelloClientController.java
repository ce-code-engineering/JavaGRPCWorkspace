package com.codeengineering.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloClientController {
    @Autowired
    private HelloClientService service;

    @RequestMapping("/")
    public String printMessage(
        @RequestParam(defaultValue = "welcome") final String input) {
        return service.printMessage(input);
    }
}

package com.noteplus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class WelcomeController {

    // inject via application.properties
   // @Value("${welcome.message:Hello}")
    // private String message = "Hello World";

    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        //model.put("message", this.message);
        return "index";
    }

}

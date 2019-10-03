package phorest.techtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value="/")
public class ClientController {

    @GetMapping
    public String index(){
        return "client";
    }
}

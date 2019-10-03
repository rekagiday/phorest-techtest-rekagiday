package phorest.techtest.controller;

import kong.unirest.GetRequest;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

@Controller(value="/")
public class ClientController {

    @GetMapping
    public String index(){
        return "client";
    }

    @GetMapping(value = "/search")
    public String searchClients(@RequestParam String email, @RequestParam String phone){
        GetRequest query = Unirest.get("http://api-gateway-dev.phorest.com/third-party-api-server/api/business/")
                .basicAuth(System.getenv("Username"), System.getenv("Password"));

        if (!StringUtils.isEmpty(email)) {
            query.queryString("email", email);
        }
        if (StringUtils.isEmpty(phone)) {
            query.queryString("phone", phone);
        }
        query.asString();

        return "client";
    }
}

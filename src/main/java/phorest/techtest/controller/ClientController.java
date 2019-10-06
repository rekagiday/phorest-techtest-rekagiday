package phorest.techtest.controller;

import kong.unirest.GetRequest;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;
import phorest.techtest.model.Client;
import phorest.techtest.model.ClientResponse;

import java.util.ArrayList;
import java.util.List;

@Controller(value="/")
public class ClientController {

    private String url = "http://api-gateway-dev.phorest.com/third-party-api-server/api/business/" + System.getenv("businessId");

    @GetMapping
    public ModelAndView index(@RequestParam(required = false) String voucherAddedMessage){
        List<Client> clients = new ArrayList<>();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("clients", clients);
        mav.addObject("success", voucherAddedMessage);
        return mav;
    }

    @GetMapping(value = "/client")
    public ModelAndView searchClients(@RequestParam(required = false) String email, @RequestParam(required = false) String phone){

        List<Client> clients = new ArrayList<>();

        GetRequest query = Unirest.get(url + "/client")
                .basicAuth(System.getenv("Username"), System.getenv("Password"));

        if (!StringUtils.isEmpty(email)) {
            query = query.queryString("email", email);

        }
        if (!StringUtils.isEmpty(phone)) {
            query = query.queryString("phone", phone);
        }
        ClientResponse response = query.asObject(ClientResponse.class)
                .getBody();

        if (response != null && response.get_embedded() != null) {
            clients = response.get_embedded().getClients();
        }

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("clients", clients);

        return mav;
    }

}

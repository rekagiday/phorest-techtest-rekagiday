package phorest.techtest.controller;

import kong.unirest.GetRequest;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;
import phorest.techtest.model.Client;
import phorest.techtest.model.ClientResponse;
import phorest.techtest.model.Voucher;

import java.util.ArrayList;
import java.util.List;

@Controller(value="/")
public class ClientController {

    private List<Client> clients;

    @GetMapping
    public String index(){
        return "client";
    }

    @GetMapping(value = "/search")
    public ModelAndView searchClients(@RequestParam(required = false) String email, @RequestParam(required = false) String phone){

        clients = new ArrayList<>();

        GetRequest query = Unirest.get("http://api-gateway-dev.phorest.com/third-party-api-server/api/business/eTC3QY5W3p_HmGHezKfxJw/client")
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
        ModelAndView mav = new ModelAndView("client");
        mav.addObject("clients", clients);

        return mav;
    }

    @PostMapping(value = "/addvoucher")
    public void addVoucher(String clientId, double value) {

        Voucher voucher = new Voucher(clientId, value);

        

    }
}

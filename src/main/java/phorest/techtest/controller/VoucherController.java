package phorest.techtest.controller;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phorest.techtest.model.Client;
import phorest.techtest.model.NewVoucherDTO;
import phorest.techtest.model.Voucher;

@Controller
public class VoucherController {

    private String url = "http://api-gateway-dev.phorest.com/third-party-api-server/api/business/" + System.getenv("businessId");

    @GetMapping(value = "/voucher/client")
    public ModelAndView index(@RequestParam String clientId) {

        Client client = Unirest.get(url + "/client/"+ clientId)
                .basicAuth(System.getenv("Username"), System.getenv("Password"))
                .queryString("clientId", clientId).asObject(Client.class).getBody();

        ModelAndView mav = new ModelAndView("voucher");
        mav.addObject("voucherDTO", new NewVoucherDTO());
        mav.addObject("client", client);
        return mav;
    }

    @PostMapping(value = "/voucher")
    public String addVoucher(@ModelAttribute("voucherDTO") NewVoucherDTO voucherDTO, @RequestParam String clientId){
        Voucher voucher = new Voucher(voucherDTO.getClientId(), voucherDTO.getAmount(), System.getenv("branchId"));

        HttpResponse<JsonNode> response = Unirest.post(url + "/voucher")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .basicAuth(System.getenv("Username"), System.getenv("Password"))
                .body(voucher)
                .asJson();

        return "redirect:/";

    }

}

package phorest.techtest.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Voucher {

    private String clientId;
    private String creatingBranchId;
    private String issueDate;
    private String expiryDate;
    private double originalBalance;
    private List<Link> links;

    public Voucher(String clientId, double originalBalance, String branchId) {
        this.clientId = clientId;
        this.originalBalance = originalBalance;
        this.creatingBranchId = branchId;
        this.issueDate = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
        this.expiryDate = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now().plusMonths(1)); // 1 month expiry
        this.links = new ArrayList<>();
        links.add(new Link("string","string", true));
    }
}

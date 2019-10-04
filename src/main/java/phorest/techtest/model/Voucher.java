package phorest.techtest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Voucher {

    private String clientId;
    private String creatingBranchId;
    private Date issueDate;
    private Date expiryDate;
    private double originalBalance;
    private List<Link> links;

    public Voucher(String clientId, double originalBalance, String branchId) {
        this.clientId = clientId;
        this.originalBalance = originalBalance;
        this.creatingBranchId = branchId;
        this.issueDate = new Date();
        this.expiryDate = new Date(System.currentTimeMillis() + 2592000000L);  // 30 days expiry
        this.links = new ArrayList<>();
    }
}

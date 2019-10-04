package phorest.techtest.model;

import java.util.Date;
import java.util.HashMap;

public class Voucher {

    private String clientId;
    private String creatingBranchId;
    private Date issueDate;
    private Date expiryDate;
    private double originalBalance;
    private HashMap links;

    public Voucher(String clientId, double originalBalance) {
        this.clientId = clientId;
        this.originalBalance = originalBalance;
        this.creatingBranchId = System.getenv("branchId");
        this.issueDate = new Date();
        this.expiryDate = new Date(System.currentTimeMillis() + 2592000000L);  // 30 days expiry
        this.links = new HashMap();
        links.put("href", "string");
        links.put("rel", "string");
        links.put("templated", true);
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setCreatingBranchId(String creatingBranchId) {
        this.creatingBranchId = creatingBranchId;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setOriginalBalance(double originalBalance) {
        this.originalBalance = originalBalance;
    }
}

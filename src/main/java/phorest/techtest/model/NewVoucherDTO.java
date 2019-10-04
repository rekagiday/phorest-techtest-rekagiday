package phorest.techtest.model;

public class NewVoucherDTO {

    private String clientId;
    private double amount;

    public String getClientId() {
        return clientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

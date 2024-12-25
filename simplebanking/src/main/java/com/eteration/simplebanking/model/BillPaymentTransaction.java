package com.eteration.simplebanking.model;

public class BillPaymentTransaction extends Transaction {
    private String payee;

    public BillPaymentTransaction(Double amount, String payee) {
        super(amount);
        this.payee = payee;
    }

    @Override
    public String toString() {
        return "BillPaymentTransaction{" +
                "payee='" + payee + '\'' +
                '}';
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }
}

package com.eteration.simplebanking.model;

public class PhoneBillPaymentTransaction extends BillPaymentTransaction{
    public String payee;
    public String phone;
    public Double payment;

    public PhoneBillPaymentTransaction(String payee, String phone, Double payment) {
        super((payment) * -1, payee);
        this.payee = payee;
        this.phone = phone;
        this.payment = payment;
    }
}

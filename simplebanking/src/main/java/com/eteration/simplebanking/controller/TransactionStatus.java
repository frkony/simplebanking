package com.eteration.simplebanking.controller;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.model.Transaction;

public class TransactionStatus {
    public String status;
    public String approvalCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}

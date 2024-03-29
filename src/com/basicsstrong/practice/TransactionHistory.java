package com.basicsstrong.practice;

import com.basicsstrong.annotation.Column;
import com.basicsstrong.annotation.PrimaryKey;

public class TransactionHistory {

    @PrimaryKey
    private long transactionId;

    @Column
    private int accountNumber;

    @Column
    private String name;

    @Column
    private String transactionType;

    @Column
    private int amount;

    public TransactionHistory() {

    }

    public TransactionHistory(int accountNumber, String name, String transactionType, int amount) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionHistory {" + "\n" +
                "   transactionId: " + transactionId + "\n" +
                "   amount: " + amount + "\n" +
                "   accountNumber: " + accountNumber + "\n" +
                "   name: " + name + "\n" +
                "   transactionType: " + transactionType + "\n" +
                "}";
    }
}

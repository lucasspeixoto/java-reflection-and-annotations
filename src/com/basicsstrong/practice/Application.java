package com.basicsstrong.practice;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws Exception {
        TransactionHistory sangeeta = new TransactionHistory(15331, "Sangeeta", "Credit", 10000);
        TransactionHistory neha = new TransactionHistory(9895, "Neha", "Credit", 7000);
        TransactionHistory drake = new TransactionHistory(19553, "Drake", "Debit", 2000);
        TransactionHistory josh = new TransactionHistory(10054, "Neha", "Debit", 9000);

        Hibernate<TransactionHistory> hibernate = Hibernate.getConnection();

        //hibernate.write(sangeeta);
        //hibernate.write(neha);
        //hibernate.write(drake);
        //hibernate.write(josh);

        TransactionHistory obj1 = hibernate.read(TransactionHistory.class, 3L);
        System.out.println(obj1);

    }
}

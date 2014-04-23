package edu.sandbox.concurrency;

import javax.naming.InsufficientResourcesException;

/**
 * Created by Lena on 23.04.14.
 */
public class Operation {
    public static void main(String[] args) {
        final Account a = new Account(1000);
        final Account b = new Account(2000);


        new Thread( new Runnable() {
            @Override
            public void run() {

                try {
                    transfer( a, b, 500);
                } catch (InsufficientResourcesException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            transfer( b, a, 300);
        } catch (InsufficientResourcesException e) {
            e.printStackTrace();
        }
    }

    static void transfer(
            Account acc1, Account acc2, int amount)
            throws InsufficientResourcesException
    {
        if (acc1.getBalance() < amount)
            throw new InsufficientResourcesException();

        synchronized (acc1) {
            System.out.println("transfer of " + amount + ": synchronized (acc1)");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (acc2) {
                System.out.println("transfer of " + amount + ": synchronized (acc2)");
                acc1.withdraw(amount);
                acc2.deposit(amount);
            }
        }
        System.out.println("transfer: tranfer of " + amount + " is done");
    }
}

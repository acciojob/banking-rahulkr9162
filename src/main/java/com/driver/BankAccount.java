package com.driver;

import java.util.ArrayList;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;
    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception {
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        String ans = "";
        ArrayList<String> list = new ArrayList<>();
        AccountNumberGenerartion(digits, sum, sum, ans, list);
        if (list.size() == 0) {
            throw new Exception("Account Number can not be generated");
        } else {
            for (String accountnumber : list) {
                return accountnumber;

            }
        }
        return null;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance = this.balance + amount;

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        this.balance = this.balance - amount;
        if(this.balance < minBalance) throw new Exception("Insufficient Balance");

    }

    public void AccountNumberGenerartion(int digits, int sum,int target, String ans, ArrayList<String> list){
        if(target < 0) return;
        if(ans.length() == digits && digitssum(ans, sum)==true){
            list.add(new String(ans));
            return;
        }
        if(ans.length() == digits && digitssum(ans, sum)==false){
            return;
        }
        for(char i='0'; i<='9'; i++){
            AccountNumberGenerartion(digits, sum, target-(i-'0'), ans+i, list);

        }
    }
    public boolean digitssum(String ans, int sum){
        int sum1 = 0;
        int num = Integer.parseInt(ans);
        while(num > 0){
            int rem = num%10;
            sum1 = sum1+ rem;
            num = num/10;
        }
        if(sum1 == sum) return true;
        else return false;
    }

        }
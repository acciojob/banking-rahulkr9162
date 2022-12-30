package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean flag = false;
        for(int i=0; i<tradeLicenseId.length()-1; i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
                flag = true;
                break;
            }
        }
        if(flag == true){
            String res = RearrangeLicenseId(tradeLicenseId);
            if (res == "")
                throw new Exception("Valid License can not be generated");
            else
                this.tradeLicenseId = res;
        }

    }
    public String RearrangeLicenseId(String tradeLicenseId){
        if (tradeLicenseId.length() == 0)
            return "";

        int[] freq = new int[26];
        for (char ch : tradeLicenseId.toCharArray()) {
            freq[(int)ch - (int)'A']++;
        }
        char ch_max = getMaxCountChar(freq);
        int maxCount = freq[(int)ch_max - (int)'A'];

        if (maxCount > (tradeLicenseId.length() + 1) / 2)
            return "";

        String res = "";
        for (int i = 0; i < tradeLicenseId.length(); i++) {
            res += ' ';
        }

        int ind = 0;
        while (maxCount > 0) {
            res = res.substring(0, ind) + ch_max
                    + res.substring(ind + 1);
            ind = ind + 2;
            maxCount--;
        }
        freq[(int)ch_max - (int)'A'] = 0;

        for (int i = 0; i < 26; i++) {
            while (freq[i] > 0) {
                ind = (ind >= tradeLicenseId.length()) ? 1 : ind;
                res = res.substring(0, ind)
                        + (char)((int)'A' + i)
                        + res.substring(ind + 1);
                ind += 2;
                freq[i]--;
            }
        }
        return res;
    }

    public char getMaxCountChar(int[] freq)
    {
        int max = 0;
        char ch = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > max) {
                max = freq[i];
                ch = (char)((int)'A' + i);
            }
        }
        return ch;
    }
}

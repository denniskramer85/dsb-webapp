package dsb.web.domain;

import dsb.web.service.IbanService;

public class Iban {
    private static final int LENGTH_ACCOUNT_NUMBER = 10;
    private String countryCode;
    private int checkSum;
    private String bankCode;
    private int accountCode;

    public Iban(String countryCode, int checkSum, String bankCode, int accountCode) {
        this.countryCode = countryCode;
        this.checkSum = checkSum;
        this.bankCode = bankCode;
        this.accountCode = accountCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getCountryCodeAsInt() {
        return Integer.parseInt(countryCode);
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(int checkSum) {
        this.checkSum = checkSum;
    }

    public String getBankCode() {
        return bankCode;
    }

    public int getBankCodeAsInt() {
        return Integer.parseInt(bankCode);
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public int getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(int accountCode) {
        this.accountCode = accountCode;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.countryCode);
        result.append(String.format("%02d", this.checkSum));
        result.append(this.bankCode);
        for (int i = 0; i < LENGTH_ACCOUNT_NUMBER-Integer.toString(this.accountCode).length(); i++) {
            result.append(0);
        }
        result.append(this.accountCode);
        return result.toString().toUpperCase();
    }

    public String toNumericalString() {
        StringBuilder result = new StringBuilder();
        result.append(IbanService.stringToNumericalString(this.bankCode));
        for (int i = 0; i < LENGTH_ACCOUNT_NUMBER-Integer.toString(this.accountCode).length(); i++) {
            result.append("0");
        }
        result.append(this.accountCode);
        result.append(IbanService.stringToNumericalString(this.countryCode));
        result.append(String.format("%02d", this.checkSum));
        return result.toString().toUpperCase();
    }


}

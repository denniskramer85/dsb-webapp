package dsb.web.domain.domain_helpers;

import dsb.web.domain.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateAccountHoldersString {

    private static final String STRING_ETCETERA = " e.a.";

    private List<Customer> listHolders;
    private List<String> holderNames = new ArrayList<>();
    private int maxHoldersShown;


    public CreateAccountHoldersString(List<Customer> listHolders, int maxHoldersShown) {
        this.listHolders = listHolders;
        this.maxHoldersShown = maxHoldersShown;
    }

    public String createAccountHoldersString() {

        //cover edge case
        if (maxHoldersShown == 0) {
            return " - ";
        }

        //sort by surname
        Collections.sort(listHolders);

        //create unified name strings from Customer attributes
        createListHolderNames();

        //create final result
        return createTotalString();

    }

    private void createListHolderNames() {
        for (Customer c : listHolders) {
            holderNames.add(c.printWholeName());
        }
    }

    private String createTotalString() {
        StringBuilder stringBuilder = new StringBuilder();

        //prevent outOfBound
        int maxLoop = getMaxloop();

        //compose string with commas by appending
        for (int i = 0; i < maxLoop ; i++) {
            stringBuilder.append(holderNames.get(i)).append(", ");
        };

        //create final String and remove last comma
        String finalString = stringBuilder.toString();
        finalString = finalString.substring(0, finalString.length() - 2);

        //add "etc." if number of holders exceeds maxNrHoldersShown
        if (holderNames.size() > maxHoldersShown) {
            finalString = finalString + STRING_ETCETERA;
        };

        return finalString;
    }

    private int getMaxloop() {
        int maxLoop = maxHoldersShown;
        if (holderNames.size() < maxHoldersShown) {
            maxLoop = holderNames.size();
        };
        return maxLoop;
    }


}

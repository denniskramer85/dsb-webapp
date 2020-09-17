package dsb.web.controller.beans;

public class TransferNameBean {
    private boolean matchingName;
    private String suggestedName;

    public TransferNameBean(boolean matchingName, String suggestedName) {
        this.matchingName = matchingName;
        this.suggestedName = suggestedName;
    }

    public boolean isMatchingName() {
        return matchingName;
    }

    public void setMatchingName(boolean matchingName) {
        this.matchingName = matchingName;
    }

    public String getSuggestedName() {
        return suggestedName;
    }

    public void setSuggestedName(String suggestedName) {
        this.suggestedName = suggestedName;
    }
}

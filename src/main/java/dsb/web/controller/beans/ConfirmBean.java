package dsb.web.controller.beans;

import java.util.List;

public class ConfirmBean {
    private String title;
    private List<String> messages;
    private String url;

    public ConfirmBean(String title, List<String> messages, String url) {
        this.title = title;
        this.messages = messages;
        this.url = url;
    }

    public ConfirmBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ConfirmBean{" +
                "title='" + title + '\'' +
                ", messages=" + messages +
                ", url='" + url + '\'' +
                '}';
    }
}

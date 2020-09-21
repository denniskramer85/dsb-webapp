package dsb.web.controller.beans;

public class ConfirmBean {
    private String title;
    private String message;
    private String url;
    private String urlName;

    public ConfirmBean(String title, String message, String url, String urlName) {
        this.title = title;
        this.message = message;
        this.url = url;
        this.urlName = urlName;
    }

    public ConfirmBean(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public ConfirmBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    @Override
    public String toString() {
        return "ConfirmBean{" +
                "title='" + title + '\'' +
                ", message=" + message +
                ", url='" + url + '\'' +
                '}';
    }
}

package email;

public class BasicEmailContent extends Email {
    private String content;
    public BasicEmailContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

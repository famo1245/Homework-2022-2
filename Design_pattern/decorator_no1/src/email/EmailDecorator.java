package email;

public abstract class EmailDecorator extends Email {
    Email emailContent;
    public EmailDecorator(Email emailContent) {
        this.emailContent = emailContent;
    }

    @Override
    public String getContent() {
        return emailContent.getContent();
    }
}

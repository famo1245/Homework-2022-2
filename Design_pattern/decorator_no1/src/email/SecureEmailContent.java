package email;

public class SecureEmailContent extends EmailDecorator {
    public SecureEmailContent(Email emailContent) {
        super(emailContent);
    }

    @Override
    public String getContent() {
        return super.getContent() + " Encrypted";
    }
}

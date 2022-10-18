package email;

public class ExternalEmailContent extends EmailDecorator {
    public ExternalEmailContent(Email emailContent) {
        super(emailContent);
    }

    @Override
    public String getContent() {
        return super.getContent() + " Company Disclaimer";
    }
}

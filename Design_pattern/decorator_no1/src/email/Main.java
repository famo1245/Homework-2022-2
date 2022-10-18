package email;

public class Main {
    public static void main(String[] args) {
        Email emailContent = new BasicEmailContent("Hello");
        System.out.println(emailContent.getContent());
        emailContent = new ExternalEmailContent(emailContent);
        System.out.println(emailContent.getContent());
        emailContent = new SecureEmailContent(emailContent);
        System.out.println(emailContent.getContent());
    }
}

public class Main {
    public static void main(String[] args) {

        EmailBuilder builder = new EmailBuilder("prankster-resources/victims.txt", "prankster-resources/messages.txt");

        var mails = builder.buildEmails(50);
    }
}
public class Email {
    private final String sender;
    private final String[] recipients;
    private final String subject;
    private final String message;

    public Email(String sender, String[] recipients, String subject, String message) {
        this.sender = sender;
        this.recipients = recipients;
        this.subject = subject;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }
}
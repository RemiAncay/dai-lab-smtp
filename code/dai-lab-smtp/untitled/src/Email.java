public class Email {
    private String sender;
    private String[] recipients;
    private String subject;
    private String message;

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

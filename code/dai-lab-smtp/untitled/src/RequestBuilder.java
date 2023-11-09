public class RequestBuilder {
    public RequestBuilder(){

    }

    public String buildSMTPRequest(Email email){
        StringBuilder sb = new StringBuilder();

        sb.append("ehlo heig-vd.ch\n");
        sb.append("mail from :").append(email.getSender()).append("\n");
        sb.append("rcpt to :").append(email.getRecipients()).append("\n");
        sb.append("data\n");
        sb.append("From: ").append(email.getSender()).append("\n");
        sb.append("To: ").append(email.getRecipients()).append("\n");
        //sb.append("Date: ").
        sb.append("Subject: ").append(email.getSubject()).append("\n");
        sb.append(email.getMessage()).append("\n");
        sb.append(".\n");
        sb.append("quit\n");

        return sb.toString();
    }
}

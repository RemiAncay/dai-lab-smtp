import java.util.ArrayList;

public class RequestBuilder {
    public RequestBuilder(){

    }

    public String[] buildSMTPRequest(Email email){
        /*StringBuilder sb = new StringBuilder();

        sb.append("ehlo heig-vd.ch\n");
        sb.append("mail from :<").append(email.getSender()).append(">\n");
        for (String rcpt : email.getRecipients()) {
            sb.append("rcpt to :<").append(rcpt).append(">\n");
        }

        sb.append("data\n");
        sb.append("From: ").append(email.getSender()).append("\n");
        //sb.append("To: ").append(email.getRecipients()).append("\n");
        //sb.append("Date: ").
        sb.append("Subject: ").append(email.getSubject()).append("\n");
        sb.append(email.getMessage()).append("\n");
        sb.append(".\n");
        sb.append("quit\n");*/

        ArrayList<String> requests = new ArrayList<>();

        requests.add("ehlo heig-vd.ch\n");
        requests.add("mail from :<" + email.getSender() + ">\n");
        for (String rcpt : email.getRecipients()) {
            requests.add("rcpt to :<" + rcpt + ">\n");
        }

        requests.add("data\n");
        requests.add("From: " + email.getSender() + "\n");
        //sb.append("To: ").append(email.getRecipients()).append("\n");
        //sb.append("Date: ").
        requests.add("Subject: " + email.getSubject() + "\n");
        requests.add("\n" + email.getMessage() + "\n");
        requests.add("\r\n.\r\n");
        requests.add("quit\n");

        return requests.toArray(new String[0]);
    }
}

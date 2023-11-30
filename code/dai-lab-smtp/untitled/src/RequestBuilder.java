import java.util.ArrayList;

public class RequestBuilder {
    public RequestBuilder(){}

    public String[] buildSMTPRequest(Email email){
        ArrayList<String> requests = new ArrayList<>();

        requests.add("ehlo heig-vd.ch\n");
        requests.add("mail from :<" + email.getSender() + ">\n");
        for (String rcpt : email.getRecipients()) {
            requests.add("rcpt to :<" + rcpt + ">\n");
        }

        requests.add("data\n");
        requests.add("From: " + email.getSender() + "\n");
        requests.add("Subject: " + email.getSubject() + "\n");
        requests.add("\n" + email.getMessage() + "\n");
        requests.add("\r\n.\r\n");
        requests.add("quit\n");

        return requests.toArray(new String[0]);
    }
}

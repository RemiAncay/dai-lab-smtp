public class PranksterProgram {
    private final EmailBuilder emailBuilder;
    private final RequestBuilder requestBuilder;
    private final SMTPClient smtpClient;

    public PranksterProgram(String victimsPath, String messagesPath) {
        emailBuilder = new EmailBuilder(victimsPath, messagesPath);
        requestBuilder = new RequestBuilder();
        smtpClient = new SMTPClient();
    }

    public void runOnce(int groupsCount){
        var emails = emailBuilder.buildEmails(groupsCount);

        int count = 0;
        for(var mail : emails) {
            System.out.println("Email n° " + ++count + "/" + groupsCount);
            smtpClient.sendSMTPRequest(requestBuilder.buildSMTPRequest(mail));
        }
    }
}

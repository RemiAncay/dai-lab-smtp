public class PranksterProgram {


    public PranksterProgram(String victimsPath, String messagesPath) {
        emailBuilder = new EmailBuilder(victimsPath, messagesPath);
        requestBuilder = new RequestBuilder();
        smtpClient = new SMTPClient();
    }

    public void runOnce(int groupsCount){
        var emails = emailBuilder.buildEmails(groupsCount);

        for(var mail : emails)
            smtpClient.sendSMTPRequest(requestBuilder.buildSMTPRequest(mail));
    }

    private EmailBuilder emailBuilder;
    private RequestBuilder requestBuilder;
    private SMTPClient smtpClient;
}

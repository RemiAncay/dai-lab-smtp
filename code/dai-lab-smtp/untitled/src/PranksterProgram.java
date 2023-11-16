public class PranksterProgram {
    public PranksterProgram(String victimsPath, String messagesPath, int groupsCount) {
        //emailBuilder = new EmailBuilder(victimsPath, messagesPath, groupsCount);
        requestBuilder = new RequestBuilder();
        smtpClient = new SMTPClient();
    }

    public void runOnce(){
        //Email[] emails = emailBuilder.buildEmails();

        Email emailTest = new Email("titeuf.leBoss@heig-vd.ch", new String[] {"nadia.princesse@heig-vd.ch", "manu.leBro@heig-vd.ch"},"Fete chez Hugo", "Viens ça va être tro bi1 !");
        smtpClient.sendSMTPRequest(requestBuilder.buildSMTPRequest(emailTest));
    }

    private EmailBuilder emailBuilder;
    private RequestBuilder requestBuilder;
    private SMTPClient smtpClient;
}

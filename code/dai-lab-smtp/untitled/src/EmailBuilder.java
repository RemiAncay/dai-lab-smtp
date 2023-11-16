import java.util.ArrayList;
import java.util.Random;

public class EmailBuilder {
    static final int MIN_GROUP_SIZE_INCL = 2;
    static final int MAX_GROUP_SIZE_EXCL = 6;
    static int randomGroupSize() {
        var random = new Random();
        return random.nextInt(MIN_GROUP_SIZE_INCL, MAX_GROUP_SIZE_EXCL);
    }

    private ArrayList<Email> mails;
    private EmailList mailingList;
    private MessagesList messagesList;

    public EmailBuilder(String emailListPath, String messagesPath) {
        mails = new ArrayList<>();
        mailingList = new EmailList(emailListPath);
        messagesList = new MessagesList(messagesPath);
    }

    public ArrayList<Email> buildEmails(int numberOfGroups) {
        var mails = new ArrayList<Email>();
        for(int i = 0; i < numberOfGroups; ++i) {
            var group = mailingList.chooseRandomAddresses(randomGroupSize());

            var sender = group.get(0);
            var recipients = group.subList(1, group.size());

            var message = messagesList.chooseRandomMessage();

            Email mail = new Email(sender, recipients.toArray(new String[0]), "todo", message);
            mails.add(mail);
        }
        return mails;
    }
}

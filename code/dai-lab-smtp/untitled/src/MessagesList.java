import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

public class MessagesList {
    private static final String MESSAGES_SEPARATOR = ";";
    private static final String DEFAULT_MESSAGE = "Default message :(";

    private final String path;
    private ArrayList<String> messages;
    public MessagesList(String messagesPath) {
        messages = new ArrayList<>();
        this.path = messagesPath;
        loadMessageList();
    }

    private void loadMessageList() {
        String data;
        try {
            data = Files.readString(Path.of(path));
        }
        catch(IOException e) {
            return;
        }

        var tokens = data.split(MESSAGES_SEPARATOR);
        for(var tok : tokens)
            if(!tok.isEmpty())
                messages.add(tok);
    }

    public String chooseRandomMessage() {
        if(messages.isEmpty())
            return DEFAULT_MESSAGE;

        Random rand = new Random();
        int randomIndex = rand.nextInt(messages.size());
        return messages.get(randomIndex);
    }
}

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;


public class MessagesList {
    private static final String MESSAGES_SEPARATOR = ";";
    private static final String DEFAULT_MESSAGE = "Default message :(";

    private final String path;
    private ArrayList<JSONMessage> messages;
    public MessagesList(String messagesPath) {
        messages = new ArrayList<>();
        this.path = messagesPath;
        loadMessageList();
    }

    private void loadMessageList() {
        String data;
        try {
            data = Files.readString(Path.of(path), StandardCharsets.UTF_8);
        }
        catch(IOException e) {
            System.out.println("Messages list could not be read.");
            return;
        }

        var jsonMessages = new Gson().fromJson(data, JSONMessages.class);

        for(var msg : jsonMessages.messages)
            messages.add(msg);
    }

    public JSONMessage chooseRandomMessage() {
        if(messages.isEmpty())
            throw new RuntimeException("No messages to choose from");

        Random rand = new Random();
        int randomIndex = rand.nextInt(messages.size());
        return messages.get(randomIndex);
    }
}

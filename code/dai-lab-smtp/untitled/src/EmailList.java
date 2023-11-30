import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

public class EmailList {
    ArrayList<String> addresses;
    String path;

    public EmailList(String emailListPath) {
        addresses = new ArrayList<>();
        path = emailListPath;
        loadAddresses();
    }

    private void loadAddresses() {
        try(var reader = new FileReader(path, StandardCharsets.UTF_8);
            var bufferedReader = new BufferedReader(reader))
        {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                addresses.add(line);
            }
        }
        catch(IOException e) {
            System.out.println("Email list could not be read.");
            return;
        }
    }

    public String chooseRandomAddress() {
        if(addresses.isEmpty())
            throw new RuntimeException("No emails to choose from");

        Random rand = new Random();
        int randomIndex = rand.nextInt(addresses.size());
        return addresses.get(randomIndex);
    }

    public ArrayList<String> chooseRandomAddresses(int numberOfAdresses) {
        if(numberOfAdresses > addresses.size())
            throw new InvalidParameterException("More adresses requested than available");

        var list = new ArrayList<String>();
        while(list.size() < numberOfAdresses) {
            String a = chooseRandomAddress();
            if(!list.contains(a))
                list.add(a);
        }
        return list;
    }
}

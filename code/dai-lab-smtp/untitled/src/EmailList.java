import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EmailList {
    ArrayList<String> addresses;
    String path;

    public EmailList(String emailListPath) {
        addresses = new ArrayList<>();
        path = emailListPath;
        loadAddresses();
    }

    private void loadAddresses() {
        try(var reader = new FileReader(path);
            var bufferedReader = new BufferedReader(reader))
        {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                addresses.add(line);
            }
        }
        catch(IOException e) {
            return;
        }
    }

    public void chooseRandomAddress() {
        //todo
    }

    public void chooseRandomAddresses() {
        //todo
    }
}

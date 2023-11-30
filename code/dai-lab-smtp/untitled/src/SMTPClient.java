
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class SMTPClient {
    static final String SERVER_ADDRESS = "localhost";
    static final int SERVER_PORT = 1025;
    public SMTPClient() {

    }

    public void sendSMTPRequest(String[] request) {
        System.out.println("Connection to the server");
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)))
        {
            String returnString;
            returnString = in.readLine();

            out.write(request[0]);
            out.flush();

            while ((returnString = in.readLine()).startsWith("250-"))
            {
            }

            int i = 1;
            while (!request[i].startsWith("From:"))
            {
                //Write request
                out.write(request[i]);
                out.flush();
                returnString = in.readLine();
                i++;
            }

            while (!request[i].startsWith("\r\n.\r\n"))
            {
                //Write request
                out.write(request[i]);
                out.flush();
                i++;
            }

            for (; i < request.length; i++) {
                out.write(request[i]);
                out.flush();
                returnString = in.readLine();
            }

        System.out.println("Email sent");
        } catch (Exception e) {
            System.out.println("Client: exc.: " + e);
        }
        System.out.println("End of the connection\n");
    }
}


import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class SMTPClient {
    final String SERVER_ADDRESS = "localhost";
    final int SERVER_PORT = 1025;
    public SMTPClient() {

    }

    public void sendSMTPRequest(String[] request) {
        System.out.println("Connexion au serveur");
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)))
        {
            String returnString;
            //Connexion au serveur
            returnString = in.readLine();
            System.out.println(returnString);

            out.write(request[0]);
            out.flush();

            while ((returnString = in.readLine()).startsWith("250-"))
            {
                System.out.println(returnString);
            }


            //SENT REQUEST
            int i = 1;

            while (!request[i].startsWith("From:"))
            {
                //Write request
                out.write(request[i]);
                out.flush();
                System.out.println(request[i]);

                returnString = in.readLine();
                System.out.println(returnString);

                i++;
            }

            while (!request[i].startsWith("\r\n.\r\n"))
            {
                //Write request
                out.write(request[i]);
                out.flush();
                System.out.println(request[i]);

                i++;
            }

            for (; i < request.length; i++) {
                out.write(request[i]);
                out.flush();
                System.out.println(request[i]);

                returnString = in.readLine();
                System.out.println(returnString);
            }
        } catch (Exception e) {
            System.out.println("Client: exc.: " + e);
        }
        System.out.println("Fin de la connexion");
    }
}

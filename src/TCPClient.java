import java.io.*; // Import classes for handling input and output
import java.net.Socket; // Import the Socket class
import java.net.UnknownHostException;

public class TCPClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // The server's hostname
        int port = 65432; // The port number on which the server is listening

        // Create a socket to connect to the server
        try (Socket socket = new Socket(hostname, port);
             OutputStream output = socket.getOutputStream(); // Get the output stream from the socket
             PrintWriter writer = new PrintWriter(output, true); // Create a PrintWriter to write to the output stream
             InputStream input = socket.getInputStream(); // Get the input stream from the socket
             BufferedReader reader = new BufferedReader(new InputStreamReader(input)); // Create a BufferedReader to read from the input stream
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) { // Create a BufferedReader to read from the console

            String text;
            // Continuously read messages from the console and send them to the server
            while (true) {
                System.out.print("Enter message: ");
                text = consoleReader.readLine(); // Read input from the console

                writer.println(text); // Send the input to the server

                String response = reader.readLine(); // Read the server's response
                System.out.println(response); // Print the server's response to the console

                if ("bye".equalsIgnoreCase(text)) {
                    break; // Exit the loop if the user types "bye"
                }
            }

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}

import java.net.Socket;

public class Client {
    private Socket socket;

    public Client(String serverAddress, int port) {
        // Initialize socket connection

        //Takes in address and port and returns a new socket
        int CPort = port;
        String CAddress = serverAddress;

        //Socket
        socket = new Socket(CAddress, CPort);

        //Returns Socket
        return socket;
        
    }

    public void sendRequest(Object request) {
        // Send requests to the server
    }

    public Object receiveResponse() {
        // Receive responses from the server
        return null;
    }

    public void encryptPassword(String password) {
        // Encrypt the password using the server's public key
    }
}

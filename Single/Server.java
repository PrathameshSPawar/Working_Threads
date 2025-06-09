package Thread.Single;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws Exception{
        int port=8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while(true) {
            try {
                System.out.println("Server is listening on port" + port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection accept from client" + acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());//From server to client first convert into bitFormat
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("Hello From Server");
                acceptedConnection.close();
                toClient.close();
                fromClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try{
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package assignment4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

        public static void main(String[] args) throws IOException {

                try (ServerSocket serverSocket = new ServerSocket(1050);
                        Socket socket = serverSocket.accept();
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        OutputStream output = socket.getOutputStream();)
                {
                        String operands = br.readLine();
                        double op1 = Double.parseDouble(operands.split(" ")[0]);
                        double op2 = Double.parseDouble(operands.split(" ")[1]);
                        output.write((String.valueOf(op1 + op2) + "\r\n").getBytes());

                } catch (IOException iOException) {
                        System.out.println("Error in connection: " + iOException.getMessage());
                }
        }
}

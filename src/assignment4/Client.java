package assignment4;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

        public static void main(String[] args) throws IOException {

                try (Socket socket = new Socket("localhost", 1050);
                        OutputStream output = socket.getOutputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

                        Frame f = new Frame();
                        LayoutManager layout = new GridBagLayout();
                        f.setLayout(layout);
                        GridBagConstraints c;

                        Label lOp1 = new Label("First operand: ", Label.CENTER);
                        c = new GridBagConstraints();
                        c.gridx = 0;
                        c.gridy = 0;
                        f.add(lOp1, c);

                        Label lOp2 = new Label("Second operand: ", Label.CENTER);
                        c = new GridBagConstraints();
                        c.gridx = 1;
                        c.gridy = 0;
                        f.add(lOp2, c);

                        TextField tfOp1 = new TextField(10);
                        c = new GridBagConstraints();
                        c.gridx = 0;
                        c.gridy = 1;
                        f.add(tfOp1, c);

                        TextField tfOp2 = new TextField(10);
                        c = new GridBagConstraints();
                        c.gridx = 1;
                        c.gridy = 1;
                        f.add(tfOp2, c);

                        Button addButton = new Button("add");
                        c = new GridBagConstraints();
                        c.gridx = 2;
                        c.gridy = 1;
                        f.add(addButton, c);
                        addButton.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                        try {
                                                output.write((tfOp1.getText() + " " + tfOp2.getText() + "\r\n").getBytes());
                                        } catch (IOException ex) {
                                                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                }
                        });
                        
                        Label lResult = new Label("Enter operands, and press add button for results.", Label.CENTER);
                        c = new GridBagConstraints();
                        c.gridx = 0;
                        c.gridy = 3;
                        c.gridwidth = 3;
                        c.fill = GridBagConstraints.BOTH;
                        f.add(lResult, c);

                        f.setTitle("Client-Server assignment");
                        f.setSize(600, 400);
                        f.setVisible(true);
                        f.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosing(WindowEvent we) {
                                        System.exit(0);
                                }
                        });
                        
                        String result = br.readLine();
                        lResult.setText("Result: " + result);

                } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                }

        }

}

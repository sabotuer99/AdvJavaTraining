package examples;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUI extends JFrame {
  private static final long serialVersionUID = 1L;
  private JTextField hostField;
  private JTextField portField;
  private JTextArea sendArea;
  private JTextArea receiveArea;

  public ClientGUI() {
    setTitle("ClientGUI");

    JPanel north = new JPanel();
    JPanel center = new JPanel();
    JPanel south = new JPanel();

    north.add(new JLabel("Host:"));
    hostField = new JTextField("localhost", 20);
    north.add(hostField);
    north.add(new JLabel("Port:"));
    portField = new JTextField("7777", 5);
    north.add(portField);

    center.setLayout(new GridLayout(2, 1, 10, 10));
    sendArea = new JTextArea(5, 50);
    JScrollPane sendScroll = new JScrollPane(sendArea);
    center.add(sendScroll);
    receiveArea = new JTextArea(5, 50);
    JScrollPane receiveScroll = new JScrollPane(receiveArea);
    center.add(receiveScroll);

    JButton send = new JButton("Send");
    send.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String host = hostField.getText();
        int port = Integer.parseInt(portField.getText());
        String textToSend = sendArea.getText();

        String result = SocketHandler.echo(host, port, textToSend);
        receiveArea.setText(result);
      }
    });
    south.add(send);

    Container content = getContentPane();
    content.add(north, BorderLayout.NORTH);
    content.add(center, BorderLayout.CENTER);
    content.add(south, BorderLayout.SOUTH);

    setDefaultCloseOperation(EXIT_ON_CLOSE);

    pack();
    setVisible(true);
  }

  public static void main(String[] args) {
    new ClientGUI();
  }
}

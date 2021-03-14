package ru.khorolskiy.chat.client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    TextField msgField;
    @FXML
    TextArea msgArea;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private static final String EXIT = "/exit";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket("localhost", 8789);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread t = new Thread(() -> {
                int x;
                try {
                    while (true){
                        String msg = in.readUTF();
                        msgArea.appendText(msg + " \n");
                        if(msg.equals(EXIT)){
                            socket.close();
                        }
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();

        } catch (IOException e) {
            throw new RuntimeException("Unable to connect to server");
        }

    }

    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Невозможно отправить сообщение", ButtonType.OK);
            alert.showAndWait();
        }

    }
}

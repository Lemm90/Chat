package ru.khorolskiy.chat.server;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    private static int numberOfMessages;
    private static final String STAT = "/stat";
    private static final String EXIT = "/exit";
    public static void main(String[] args) throws Exception {
        try(ServerSocket serverSocket = new ServerSocket(8789)) {
            System.out.println("Сервер успешно запущен. Рабочий порт: 8789");
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutput out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Клиент подключился");

            while (true) {
                String msg = in.readUTF();
                if (msg.equals(STAT)) {
                    out.writeUTF("Количество сообщений: " + numberOfMessages);
                }
                else if (msg.equals(EXIT)) {
                    out.writeUTF(msg);
                    socket.close();
                } else {
                    System.out.print(msg + "\n");
                    numberOfMessages++;
                    out.writeUTF("ECHO:" + msg);
                }

            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}

package com.main;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.auxiliary.CollectionException;
import com.auxiliary.Message;
import com.auxiliary.TextColor;
import com.main.*;

public class Client {

    private static int PORT = 23909;
    private static String HOST;
    private static Listener listener;

    private static SocketChannel sc;
    public static boolean executing = false;


    private static SocketChannel find_socket() throws IOException {
        SocketChannel socket = SocketChannel.open();
        try {
            socket.connect(new InetSocketAddress("localhost", PORT));
            System.out.println("Server is ready");
            listener = new Listener();
            sc = socket;
            request(socket, listener.start());
        } catch (Exception ex){
            System.out.println("Connection refused...");
        }
        if(!socket.isConnected())
            return null;
        else return socket;
    }

    private static SocketChannel connect() throws InterruptedException, IOException {
        SocketChannel socket = null;
        while (socket == null){
            socket = find_socket();
            if(socket == null){
                TimeUnit.SECONDS.sleep(5);
            }
        }
        return socket;
    }

    public static void makeRequest(Data data) throws Exception {
        executing = true;
        if (request(sc, data)) {
            read(sc);
        }
    }

    public static Message requestWithAnswer(Data data) throws IOException, ClassNotFoundException {
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();


            sc.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
        } catch (IOException ex){
            return null;
        }

        byte[] info = new byte[2048];

        try {
            sc.read(ByteBuffer.wrap(info));
        } catch (IOException ex){
            return null;
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(info);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Message message = (Message) objectInputStream.readObject();
        return message;
    }

    private static boolean request(SocketChannel socket, Data data) throws Exception {
        if(data == null && !executing) {
            return request(socket, listener.listen());
        }
        else if(data == null && executing){
            return false;
        }
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();


            socket.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
        } catch (IOException ex){
            return false;
        }
        return true;
    }



    private static boolean read(SocketChannel socket) throws IOException, ClassNotFoundException {
        byte[] info = new byte[20048];

        try {
            socket.read(ByteBuffer.wrap(info));
        } catch (IOException ex){
            return false;
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(info);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Message message = (Message) objectInputStream.readObject();

        if(message.isException && message.exception.getClass() == CollectionException.class){
            System.out.println(TextColor.ANSI_YELLOW + message.text + TextColor.ANSI_RESET);
        } else {
            System.out.println(message.text);
        }
        return true;
    }


    public static void main(String[] args) throws Exception {
        if (args.length != 2){
            System.out.println(TextColor.ANSI_YELLOW + "Please add a port and a host as an argument" + TextColor.ANSI_RESET);
            System.exit(0);
        } else {
            PORT = Integer.parseInt(args[0]);
            HOST = args[1];
        }
        SocketChannel socket = connect();
        while (true){
            if (!read(socket) || !request(socket, listener.listen())){
                socket = connect();
            }
        }
    }
}

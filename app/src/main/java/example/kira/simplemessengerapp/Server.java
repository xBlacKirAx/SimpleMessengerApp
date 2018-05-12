package example.kira.simplemessengerapp;

import android.support.annotation.MainThread;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private int port;
    public Server(int port) {
        this.port=port;

    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket=new ServerSocket(port);
            while(true){
                Socket clientSocket=serverSocket.accept();
                new HandlerClientThread(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class HandlerClientThread implements Runnable{
        private Socket socket;
        public HandlerClientThread(Socket clientSocket) {
            socket=clientSocket;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try{
//                DataInputStream dis=new DataInputStream();
//                String clientMsg=dis.readUTF();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

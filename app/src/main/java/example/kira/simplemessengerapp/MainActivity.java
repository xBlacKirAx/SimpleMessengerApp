package example.kira.simplemessengerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText etInput;
    TextView tvMsgArea;
    Button btnSend;
    private final int port=5000;
    private ServerSocket serverSocket;
    Socket clientSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput=findViewById(R.id.etInput);
        tvMsgArea=findViewById(R.id.tvMsgArea);
        btnSend=findViewById(R.id.btnSend);


            //ServerThread
            Thread ServerThread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ServerSocket serverSocket=new ServerSocket(port);
//                        while(true){
                            System.out.println("Waiting for client");
                            clientSocket=serverSocket.accept();
                            System.out.println("Client accepted");
                            Thread.sleep(2000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_SHORT).show();
                                    try{
                                        InputStream inputStream = clientSocket.getInputStream();
                                        OutputStream outputStream=clientSocket.getOutputStream();
                                        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                                        String line;
                                        while((line=reader.readLine())!=null){
                                            if("quit".equalsIgnoreCase(line)){
                                                break;
                                            }
                                            String msg="You typed: "+line+"\n";
                                            outputStream.write(msg.getBytes());
                                        }
                                        clientSocket.close();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }

                                }
                            });
//                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            ServerThread.start();
//        Thread clientThread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Socket clientSocket=new Socket("10.0.2.2",8000);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        clientThread.start();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}

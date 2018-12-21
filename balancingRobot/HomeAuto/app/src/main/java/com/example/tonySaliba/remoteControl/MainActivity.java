package com.example.tonySaliba.remoteControl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;



public class MainActivity extends Activity {

    private Socket socket;
    PrintStream PS;
    String command;
    private static  int SERVERPORT = 80;
    private static  String SERVER_IP = "172.20.10.2";
    boolean start =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("12", "onCreate started*********************************");
        final Button connectBtn = (Button) findViewById(R.id.connect);
        connectBtn.setOnClickListener(new View.OnClickListener() {////////// connectBtn onClick() start
            public void onClick(View v) {
                try {
                    connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });////////// connectBtn onClick() end

        final Button forwardBtn = (Button) findViewById(R.id.forward);
        forwardBtn.setOnClickListener(new View.OnClickListener() {////////// forwardBtn onClick() start
            public void onClick(View v) {
                try {
                    forward();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });////////// forwardBtn onClick() end

        final Button backwardBtn = (Button) findViewById(R.id.backward);
        backwardBtn.setOnClickListener(new View.OnClickListener() {////////// backwardBtn onClick() start
            public void onClick(View v) {
                try {
                    backwards();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });////////// backwardBtn onClick() end

        final Button rightBtn = (Button) findViewById(R.id.right);
        rightBtn.setOnClickListener(new View.OnClickListener() {////////// rightBtn onClick() start
            public void onClick(View v) {
                try {
                    turnRight();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });////////// rightBtn onClick() end

        final Button leftBtn = (Button) findViewById(R.id.left);
        leftBtn.setOnClickListener(new View.OnClickListener() {////////// leftBtn onClick() start
            public void onClick(View v) {
                try {
                    turnLeft();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });////////// leftBtn onClick() end

        final Button stopBtn = (Button) findViewById(R.id.STOP);
        stopBtn.setOnClickListener(new View.OnClickListener() {////////// stopBtn onClick() start
            public void onClick(View v) {
                try {
                    stop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });////////// stopBtn onClick() end

    }
    public  void connect() throws IOException {
        Log.i("12", "connect started*********************************");
        TextView ip = (TextView) findViewById(R.id.editText);
        SERVER_IP =  ip.getText().toString();
        TextView port = (TextView) findViewById(R.id.editText2);
        SERVERPORT = Integer.parseInt( port.getText().toString());
        start =true;
        new Thread(new ClientThread("0")).start();
    }

    public  void forward( ) throws IOException {
        Log.i("12", "forward started*********************************");
        new Thread(new ClientThread("2")).start();
    }

    public void backwards( ) throws IOException{
        Log.i("12", "backwards started*********************************");
        new Thread(new ClientThread("1")).start();
    }
    public  void turnRight( ) throws IOException {
        Log.i("12", "turnRight started*********************************");
        new Thread(new ClientThread("4")).start();
    }

    public void turnLeft( ) throws IOException{
        Log.i("12", "turnLeft started*********************************");
        new Thread(new ClientThread("3")).start();
    }

    public void stop( ) throws IOException{
        Log.i("12", "stop started*********************************");
        new Thread(new ClientThread("0")).start();
    }




///////////////////////////////////////////////////////////////////////
    class ClientThread implements Runnable {
        public ClientThread(String x){
            command = x;
        }
        @Override
        public void run() {
            try {if(start == true){
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(SERVER_IP, SERVERPORT);
                 PS = new PrintStream(socket.getOutputStream());
                start = false;
            }

                else if(start == false){
                PS.print(command);}
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}




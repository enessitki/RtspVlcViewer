package com.example.EsetronEyeApp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class UdpClient {
    public void sendMessage(final String message) {
        Thread thread = new Thread(new Runnable() {

            String stringData;

            @Override
            public void run() {

                DatagramSocket ds = null;
                try {
                    ds = new DatagramSocket();
                    // IP Address below is the IP address of that Device where server socket is opened.
                    InetAddress serverAddr = InetAddress.getByName("192.168.2.55");
                    DatagramPacket dp;
                    dp = new DatagramPacket(message.getBytes(), message.length(), serverAddr, 6000);
//                    while(true){
                    ds.send(dp);
                    TimeUnit.SECONDS.sleep(1);
//                    }


//                    byte[] lMsg = new byte[22];
//                    dp = new DatagramPacket(lMsg, lMsg.length);
//                    ds.receive(dp);
//                    stringData = new String(lMsg, 0, dp.getLength());
//                    Log.i("", "");

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (ds != null) {
                        ds.close();
                    }
                }

//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        String s = mTextViewReplyFromServer.getText().toString();
//                        if (stringData.trim().length() != 0)
//                            mTextViewReplyFromServer.setText(s + "\nFrom Server : " + stringData);
//
//                    }
//                });
            }
        });

        thread.start();
    }



}

/*
 * pemrograman-berorientasi-objek-lanjut

 * Copyright (c) 2018
 * All rights reserved.
 * Written by od3ng created on Dec 10, 2018 2:13:27 AM
 * Email   : lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */
package pbo.lanjut.pertemuan7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author od3ng
 */
public class GreetClient {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(GreetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String sendMessage(String msg) {
        String resp = null;
        try {
            out.println(msg);
            resp = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(GreetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(GreetClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

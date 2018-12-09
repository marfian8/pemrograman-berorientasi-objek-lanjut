/*
 * pemrograman-berorientasi-objek-lanjut

 * Copyright (c) 2018
 * All rights reserved.
 * Written by od3ng created on Dec 10, 2018 4:44:41 AM
 * Email   : lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */
package pbo.lanjut.pertemuan7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author od3ng
 */
public class EchoMultiServer {

    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            System.out.println("Starting server on port "+port);
            serverSocket = new ServerSocket(port);
            while (true) {
                new EchoClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class EchoClientHandler extends Thread {

        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
        EchoMultiServer server = new EchoMultiServer();
        server.start(6666);
    }
}

package dev.teamtesseract.euclidean;

import dev.teamtesseract.euclidean.networking.ServerBinding;

import java.io.IOException;
import java.net.InetAddress;

public class EuclideanServer {

    public static final String GAME_VERSION = "1.17.1";
    public static final int PROTOCOL_VERSION = 756;

    public ServerBinding networkBinding;

    public EuclideanServer() {
        this.networkBinding = new ServerBinding();
        try {
            this.networkBinding.bind(InetAddress.getByName("127.0.0.1"), 25565, this); //TODO Obviously, actual dynamic ip and port
        } catch(IOException e) {
            System.out.println("Failed to bind server!");
            e.printStackTrace();
        }
    }

    public void startServer() {
        System.out.println("Starting server...");
        while(true) {

        }
    }
}

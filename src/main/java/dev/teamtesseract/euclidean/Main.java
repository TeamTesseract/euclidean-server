package dev.teamtesseract.euclidean;

public class Main {

    public final EuclideanServer server;

    public static void main(String[] args) {
        new Main(25565).run();
    }

    public Main(int port) {
        this.server = new EuclideanServer();
    }

    public void run() {
        server.startServer();
    }
}

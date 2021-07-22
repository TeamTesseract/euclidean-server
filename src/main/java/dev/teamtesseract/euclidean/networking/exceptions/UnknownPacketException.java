package dev.teamtesseract.euclidean.networking.exceptions;

public class UnknownPacketException extends Exception {

    public UnknownPacketException(String state, int packetId) {
        super(String.format("Unknown Packet with ID 0x%02x within the Protocol \"%s\"", packetId, state));
    }
}

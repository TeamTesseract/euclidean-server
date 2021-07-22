package dev.teamtesseract.euclidean.networking.packethandlers;

import dev.teamtesseract.euclidean.networking.exceptions.UnknownPacketException;
import dev.teamtesseract.euclidean.networking.packets.Packet;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class PacketHandler {

    private final Map<Integer, Supplier<? extends Packet.Incoming>> packetFactories = new HashMap<>();
    private final String protocolName;

    public PacketHandler(String protocolName) {
        this.protocolName = protocolName;
    }

    public Packet.Incoming getPacketFromId(int id) throws UnknownPacketException {
        Supplier<? extends Packet.Incoming> factory = packetFactories.get(id);
        if(factory == null)
            throw new UnknownPacketException(protocolName, id);
        return factory.get();
    }

    protected <P extends Packet.Incoming> void registerPacket(Supplier<P> factory) {
        packetFactories.put(factory.get().getPacketId(), factory);
    }
}

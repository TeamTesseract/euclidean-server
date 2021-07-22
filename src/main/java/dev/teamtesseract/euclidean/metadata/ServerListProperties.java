package dev.teamtesseract.euclidean.metadata;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.teamtesseract.euclidean.EuclideanServer;

import java.util.UUID;

public class ServerListProperties {

    private static final String FAVICON_PREFIX = "data:image/png;base64,";

    private final PlayerInfo playerInfo;
    private final String serverDescription;
    //TODO Favicon shit

    public ServerListProperties(PlayerInfo players, String serverDescription) {
        this.playerInfo = players;
        this.serverDescription = serverDescription;
    }

    public JsonObject getPingResponse() {
        JsonObject root = new JsonObject();

        JsonObject version = new JsonObject();
        version.addProperty("name", EuclideanServer.GAME_VERSION);
        version.addProperty("protocol", EuclideanServer.PROTOCOL_VERSION);
        root.add("version", version);

        JsonObject players = new JsonObject();
        players.addProperty("max", playerInfo.maxPlayers());
        players.addProperty("online", playerInfo.onlinePlayers());
        players.add("sample", playerInfo.getPlayerSample());
        version.add("players", players);

        JsonObject description = new JsonObject();
        description.addProperty("text", serverDescription);
        root.add("description", description);

        return root;
    }


    //TODO Actually do this properly, player objects and shit
    public static record PlayerInfo(int maxPlayers, int onlinePlayers, String... playerSamples) {
        public JsonArray getPlayerSample() {
            JsonArray sample = new JsonArray();
            for(String s : playerSamples) {
                JsonObject entry = new JsonObject();
                entry.addProperty("name", s);
                entry.addProperty("id", UUID.randomUUID().toString());
                sample.add(entry);
            }
            return sample;
        }
    }
}

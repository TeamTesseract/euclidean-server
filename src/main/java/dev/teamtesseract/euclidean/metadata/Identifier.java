package dev.teamtesseract.euclidean.metadata;

public class Identifier {

    public static final String DEFAULT_NAMESPACE = "minecraft";

    private final String namespace, value;

    public static Identifier of(String value) {
        if(value.contains(":")) {
            String[] values = value.split(":", 2);
            return of(values[0], values[1]);
        } else {
            return of(DEFAULT_NAMESPACE, value);
        }
    }

    public static Identifier of(String namespace, String value) {
        return new Identifier(namespace, value);
    }

    private Identifier(String namespace, String value) {
        this.namespace = namespace;
        this.value = value;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return namespace + ":" + value;
    }
}

package az.azerconnect.sales.msbalanceoperations.constants;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum SubscriberType {
    UNDEFINED("UNDEFINED", ""),
    PREPAID("Prepaid", "0"),
    POSTPAID("Postpaid", "1"),
    HYBRID("Hybrid", "2");

    private final String name;
    private final String id;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public static SubscriberType getSubscriberType(String id) {
        return Arrays.stream(SubscriberType.values())
                .filter(item -> item.id.equals(id))
                .findFirst()
                .orElse(SubscriberType.UNDEFINED);
    }
}

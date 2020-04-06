package net.kemitix.properties.typed;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TypedProperties {

    private static final TypedProperties EMPTY_PROPERTIES =
            new TypedProperties(Collections.emptyMap());

    private final Map<Class<? extends TypedProperty<?>>, Object> valueMap;

    private TypedProperties(
            final Map<Class<? extends TypedProperty<?>>, Object> valueMap
    ) {
        this.valueMap = valueMap;
    }

    public static TypedProperties create() {
        return EMPTY_PROPERTIES;
    }

    public <T> TypedProperties with(
            final Class<? extends TypedProperty<T>> key,
            final T value
    ) {
        final Map<Class<? extends TypedProperty<?>>, Object> updated =
                new HashMap<>(valueMap);
        updated.put(key, value);
        return new TypedProperties(updated);
    }

    public <T> Optional<T> find(
            final Class<? extends TypedProperty<T>> key,
            final Class<T> tClass
    ) {
        return Optional.ofNullable(valueMap.get(key))
                .map(tClass::cast);
    }
}

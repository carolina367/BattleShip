package helpers;

import java.util.HashMap;
import java.util.Objects;

public class MyHashMap<K, V extends Number> extends HashMap<K, V> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyHashMap<?, ?> that = (MyHashMap<?, ?>) o;
        if (size() != that.size()) return false;
        for (Entry<?, ?> entry : entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (!that.containsKey(key)) return false;
            if (!Objects.equals(value, that.get(key))) return false;
        }
        return true;
    }

    public int sumAll() {
        return values().stream().mapToInt(Number::intValue).sum();
    }
}

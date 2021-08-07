package project.sys.internal;

public class Set<K, V> {
    private K key;
    private V value;

    public Set() {}
    public Set(K key) {
        this.key = key;
    }
    public Set(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Set<K, V> setKey(K key) {
        this.key = key;
        return this;
    }

    public Set<K, V> setValue(V value) {
        this.value = value;
        return this;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

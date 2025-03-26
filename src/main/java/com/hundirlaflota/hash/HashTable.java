package com.hundirlaflota.hash;

import java.util.Objects;

/**
 * Implementación sencilla de una tabla hash con sondeo lineal.
 */
public class HashTable<K, V> {
    private static final int DEFAULT_SIZE = 16;

    private Entry<K, V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.table = new Entry[capacity];
        this.size = 0;
    }

    public boolean insert(K key, V value) {
        int index = hashFunction(key);
        int startIndex = index;

        do {
            if (table[index] == null || table[index].isDeleted) {
                table[index] = new Entry<>(key, value);
                size++;
                return true;
            } else if (Objects.equals(table[index].key, key)) {
                // Si la clave ya existe, actualizamos el valor
                table[index].value = value;
                return true;
            }
            index = (index + 1) % table.length;
        } while (index != startIndex);

        // La tabla está llena
        return false;
    }

    public V search(K key) {
        int index = hashFunction(key);
        int startIndex = index;

        do {
            if (table[index] == null) {
                return null; // No se encontró
            } else if (!table[index].isDeleted && Objects.equals(table[index].key, key)) {
                return table[index].value;
            }
            index = (index + 1) % table.length;
        } while (index != startIndex);

        return null;
    }

    public boolean remove(K key) {
        int index = hashFunction(key);
        int startIndex = index;

        do {
            if (table[index] == null) {
                return false;
            } else if (!table[index].isDeleted && Objects.equals(table[index].key, key)) {
                table[index].isDeleted = true;
                size--;
                return true;
            }
            index = (index + 1) % table.length;
        } while (index != startIndex);

        return false;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return table.length;
    }

    private int hashFunction(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    /**
     * Clase interna para almacenar la entrada (key, value)
     */
    private static class Entry<K, V> {
        K key;
        V value;
        boolean isDeleted;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }
    }
}

package com.company;

//функция работы со строковыми ключами- хэш
// СНИЛС+++ - ключ
// опробование
public class MyHashTable<K, V> {

    private static class Entry<K, V> { // Bucket Реализация
        K key;
        V value;
        boolean deleted;

        Entry(K key, V value) {  // КОНСТРУКТОР
            this.key = key;
            this.value = value;
            deleted = false;
        }
    }

    // вместимость таблицы по умолчанию
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    // Если таблица заполнена более чем на 70, то она расширяется
    private static final double DEFAULT_LOAD_FACTOR = 0.7;
    // arr buckets
    private Entry<K, V>[] table;
    private int size;

    public MyHashTable() { // Конструктор- дефолт емкость
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public MyHashTable(int initialCapacity) { // Конструктор- наша собственная емкость
        table = new Entry[initialCapacity];
        size = 0;
    }

    // функция работы со строковыми ключами- хэш
    public static int myHash(String input) {
        int hash = 0;
        for (int i = 0; i < input.length(); i++) {
            char charAtI = input.charAt(i);
            hash += charAtI; // Просто суммируем коды символов строки
        }
        return hash;
    }

    public void put(K key, V value) { // Положить в таблицу
        if (key == null) {
            throw new IllegalArgumentException("Ключ не может быть пустым (null)");
        }

        // Проверка вместимости таблицы
        if (size >= table.length * DEFAULT_LOAD_FACTOR) {
            resize(); // Увеличение размера таблицы, если необходимо
        }

        int hash = myHash(key.toString()); // Вычисление хеш-кода ключа
        int index = hash % table.length; // Вычисление начального индекса
        int originalIndex = index;
        int i = 1;

        // Поиск пустой ячейки (bucket) методом открытой адресации (опробование)
        while (table[index] != null && !table[index].deleted) {
            // Open addressing: пересчитываем индекс
            index = (originalIndex + i * i) % table.length;
            i++;
        }
        // Вставляем пару ключ-значение в ячейку
        table[index] = new Entry<>(key, value);
        size++;
    }

    public V get(K key) {
        int hash = myHash(key.toString()); // Вычисление хеш-кода ключа
        int index = hash % table.length; // Вычисление начального индекса
        int originalIndex = index;
        int i = 1;

        // Поиск значения по ключу методом открытой адресации
        while (table[index] != null) {
            if (!table[index].deleted && table[index].key.equals(key)) {
                return table[index].value; // Возвращаем значение, если ключ найден
            }
            // Open addressing: пересчитываем индекс
            index = (originalIndex + i * i) % table.length;
            i++;
        }
        // Если ключ не найден, возвращаем null
        return null;
    }

    public void remove(K key) { // удаление
        int hash = myHash(key.toString()); // Вычисление хеш-кода ключа
        int index = hash % table.length; // Вычисление начального индекса
        int originalIndex = index;
        int i = 1;

        // Удаление элемента из таблицы методом открытой адресации
        while (table[index] != null) {
            if (!table[index].deleted && table[index].key.equals(key)) {
                table[index].deleted = true; // Помечаем элемент как удаленный
                size--;
                return;
            }
            // Open addressing: пересчитываем индекс
            index = (originalIndex + i * i) % table.length;
            i++;
        }
    }

    private void resize() { // Увеличиваем размер
        Entry<K, V>[] newTable = new Entry[table.length * 2];

        // Перехеширование элементов в новую таблицу
        for (Entry<K, V> entry : table) {
            if (entry != null && !entry.deleted) {
                int hash = myHash(entry.key.toString()); // Вычисление хеш-кода ключа
                int index = hash % newTable.length; // Вычисление начального индекса
                int originalIndex = index;
                int i = 1;

                // Поиск новой позиции методом открытой адресации
                while (newTable[index] != null) {
                    // Open addressing: пересчитываем индекс
                    index = (originalIndex + i * i) % newTable.length;
                    i++;
                }

                newTable[index] = entry; // Помещаем элемент в новую таблицу
            }
        }
        table = newTable; // Заменяем старую таблицу на новую
    }

    public void printTable() { // Распечатка таблицы
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> entry = table[i];
            if (entry != null && !entry.deleted) {
                System.out.println("Index " + i + ": Key = " + entry.key + ", Value = " + entry.value);
            }
        }
    }
}

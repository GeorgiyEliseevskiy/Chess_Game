package com.company;

import java.util.Arrays;

public class ArrayListCustom<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;

    public ArrayListCustom() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(E element) {
        if (size == elements.length) {
            ensureCapacity();
        }
        elements[size++] = element;
    }
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер " + index);
        }
        return (E) elements[index];
    }

    public int size() {
        return size;
    }

    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    public void remove(E element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Сдвигаем элементы после удаляемого на одну позицию влево
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            // Уменьшаем размер списка
            size--;
        }
    }

    private void ensureCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

}
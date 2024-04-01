package com.white.homework;

import java.util.Arrays;


public class MyStringList implements StringList<String> {

    private int size = 0;
    private int capacity;
    private String[] arr;

    public MyStringList(int capacity) {
        this.capacity = capacity;
        arr = new String[capacity];
    }

    @Override
    public String add(String item) {
        validation(item);
        if (size >= capacity) increaseCapacity();
        arr[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validation(index, item);
        if (size + 1 > capacity) increaseCapacity();
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        size++;
        arr[index] = item;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validation(index, item);
        arr[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validation(item);
        if (!contains(item)) throw new IllegalArgumentException("Item is absent");
        int indexSwap = 0;
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                indexSwap = i;
                break;
            }
        }
        for (int i = indexSwap; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        validation(index);
        String removeItem = arr[index];
        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
            if (i == size - 1) arr[i] = null;
        }
        size--;
        return removeItem;
    }

    @Override
    public boolean contains(String item) {
        boolean isContains = false;
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) return true;
        }
        return isContains;
    }

    @Override
    public int indexOf(String item) {
        validation(item);
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        validation(item);
        for (int i = size - 1; i > 0; i--) {
            if (arr[i].equals(item)) return i;
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validation(index);
        return arr[index];
    }

    @Override
    public boolean equals(StringList<String> otherList) {
        if (otherList == null) throw new IllegalArgumentException();
        boolean isEquals = true;
        if (otherList == this) {
            return true;
        }
        if (this.size != otherList.size()) return false;
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(otherList.get(i))) {
                isEquals = false;
                break;
            }
        }
        return isEquals;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return arr[0] == null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public String[] toArray() {
        String[] stringArray = new String[size];
        System.arraycopy(arr, 0, stringArray, 0, size);
        return stringArray;
    }

    @Override
    public String toString() {
        return "MyStringList{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }

    private void increaseCapacity() {
        capacity *= 2;
        String[] tempArr = new String[capacity];
        System.arraycopy(arr, 0, tempArr, 0, arr.length);
        arr = tempArr;
    }

    private void validation(String item) {
        if (item == null) throw new IllegalArgumentException("Null can't be added");
    }

    private void validation(int index) {
        if (index >= size && index != 0) throw new IndexOutOfBoundsException();
    }

    private void validation(int index, String item) {
        if (item == null) throw new IllegalArgumentException("Null can't be added");
        if (index >= size) throw new IndexOutOfBoundsException();
    }
}

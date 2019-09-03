package io.github.paulszefer;

public class List {

    public static final int DEFAULT_STARTING_SIZE = 32;

    public List() {};
    public List(int size) {};
    public List(int size, value) {};
    public List(List list) {};
    public List(int[] array) {};

    private int[] data;
    private int count;

    public int size() { return 0; };

    public int first() { return 0; };
    public int last() { return 0; };
    public int get(int index) { return 0; };

    public void add(int value) {};
    public void insert(int value, int index) {};

    public int remove(int index) { return 0; };
    public List remove(int start, int end) { return new List(); };
    public int removeFirst() { return 0; };
    public int removeLast() { return 0; };

    public void fill(int value) {};
    public void fill(int value, int start) {};
    public void fill(int value, int start, int end) {};
    public void empty() {};

    private void increaseArraySize() {};
    private void increaseArraySize(double factor) {};
}
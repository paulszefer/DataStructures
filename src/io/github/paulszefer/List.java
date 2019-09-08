package io.github.paulszefer;

import java.util.Arrays;

/**
 * A list to hold elements.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class List {

    /**
     * The default starting size of the list.
     */
    public static final int DEFAULT_STARTING_SIZE = 32;

    // Stores the elements in the list
    private int[] data;

    // The number of elements in the list
    private int count;

    /**
     * Creates an empty list.
     */
    public List() {
        data = new int[DEFAULT_STARTING_SIZE];
        count = 0;
    };

    /**
     * Creates a list to hold the elements of the given array.
     *
     * @param array the array of elements to hold
     */
    public List(int[] array) {
        data = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            data[i] = array[i];
        }
        count = array.length;
    };

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return count;
    };

    /**
     * Returns a copy of the given list.
     *
     * @return a copy of the given list
     */
    public List copy() {
        return new List(Arrays.copyOfRange(data, 0, count));
    }

    /**
     * @return the first element in the list
     */
    public int first() {
        checkListEmpty();
        return data[0];
    };

    /**
     * @return the last element in the list
     */
    public int last() {
        checkListEmpty();
        return data[count - 1];
    };

    /**
     * @return the nth element in the list
     *
     * @param index the index of the element to return
     */
    public int get(int index) {
        checkListEmpty();
        checkIndexValid(index);

        return data[index];
    };

    /**
     * Adds the element to the end of the list.
     *
     * @param value the value of the element to add
     */
    public void add(int value) {
        if (count >= data.length) {
            increaseArraySize();
        }
        data[count] = value;
        count++;
    };

    /**
     * Adds the elements in {@code list} to the end of the list.
     *
     * @param list a list of elements to add to this list
     */
    public void add(List list) {
        if (count + list.size() > data.length) {
            increaseArraySize();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
            count++;
        }
    };

    /**
     * Inserts the element into the list at the specified index.
     *
     * All subsequent elements will be shifted forward by one.
     *
     * @param value the value of the element to insert
     * @param index the index of the newly inserted element
     */
    public void insert(int value, int index) {
        checkIndexValid(index);

        if (count == data.length) {
            increaseArraySize();
        }

        for (int i = count - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = value;
        count++;
    };

    /**
     * Removes the element from the list at the specified index.
     *
     * @param index the index of the element to remove
     * @return the removed element
     */
    public int remove(int index) {
        checkListEmpty();
        checkIndexValid(index);

        int value = data[index];
        for (int i = index; i < count - 1; i++) {
            data[i] = data[i + 1];
        }
        count--;
        return value;
    };

    /**
     * Removes the elements from the list between the given indices.
     *
     * The range of elements removed is from {@code start} (inclusive) to {@code end} (exclusive).
     *
     * <pre>{@code Eg. [0, 1, 2, 3, 4]
     *
     *   remove(0, 1) -> [0]
     *   remove(1, 4) -> [1, 2, 3]
     *   remove(0, 4) -> [0, 1, 2, 3]}</pre>
     *
     * The size of the returned list will be equal to ({@code end} - {@code start}).
     *
     * @param start the index to start removing at (inclusive)
     * @param end the index to stop removing at (exclusive)
     * @return the removed element
     */
    public List remove(int start, int end) {
        checkListEmpty();

        checkIndexValid(start);

        if (start > end) {
            throw new IllegalArgumentException();
        }

        if (start == end) {
            return new List();
        }

        checkIndexValid(end - 1);

        int[] removed = new int[end - start];
        for (int i = 0; i < count; i++) {
            if (i >= start && i < end) {
                removed[i - start] = data[i];
            } else if (i >= end) {
                data[i - end + start] = data[i];
            }
        }
        count -= end - start;

        return new List(removed);
    };

    /**
     * Removes the first element from the list.
     *
     * @return the removed element
     */
    public int removeFirst() {
        checkListEmpty();

        int removed = first();
        data = Arrays.copyOfRange(data, 1, count);
        count--;

        return removed;
    };

    /**
     * Removes the last element from the list.
     *
     * @return the removed element
     */
    public int removeLast() {
        checkListEmpty();
        count--;
        return data[count];
    };

    /**
     * Empties the list.
     */
    public void empty() {
        count = 0;
    };

    // Increases the size of the internal storage array by a factor of two
    private void increaseArraySize() {
        data = Arrays.copyOf(data, data.length * 2);
    };

    /**
     * @throws a {@code ListEmptyException} if the list is empty.
     */
    private void checkListEmpty() {
        if (count == 0) {
            throw new ListEmptyException();
        }
    }

    /**
     * @throws a {@code IndexOutOfBoundsException} if the given index is invalid
     */
    private void checkIndexValid(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
    }
}
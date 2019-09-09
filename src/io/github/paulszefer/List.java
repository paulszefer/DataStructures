package io.github.paulszefer;

import java.util.Arrays;

/**
 * A list to hold elements.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class List<T> {

    /**
     * The default starting size of the list.
     */
    public static final int DEFAULT_STARTING_SIZE = 32;

    // Stores the elements in the list
    private Object[] data;

    // The number of elements in the list
    private int count;

    /**
     * Creates an empty list.
     */
    public List() {
        data = new Object[DEFAULT_STARTING_SIZE];
        count = 0;
    };

    /**
     * Creates a list to hold the elements of the given array.
     *
     * @param array the array of elements to hold
     */
    public List(T[] array) {
        data = new Object[array.length];
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
    public List<T> copy() {
        List<T> copy = new List<>();
        for (int i = 0; i < count; i++) {
            @SuppressWarnings("unchecked")
            T value = (T)data[i];
            copy.add(value);
        }
        return copy;
    }

    /**
     * @return the first element in the list
     */
    public T first() {
        checkListEmpty();

        @SuppressWarnings("unchecked")
        T value = (T)data[0];
        return value;
    };

    /**
     * @return the last element in the list
     */
    public T last() {
        checkListEmpty();

        @SuppressWarnings("unchecked")
        T value = (T)data[count - 1];
        return value;
    };

    /**
     * @return the nth element in the list
     *
     * @param index the index of the element to return
     */
    public T get(int index) {
        checkListEmpty();
        checkIndexValid(index);

        @SuppressWarnings("unchecked")
        T value = (T)data[index];
        return value;
    };

    /**
     * Adds the element to the end of the list.
     *
     * @param value the value of the element to add
     */
    public void add(T value) {
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
    public void add(List<T> list) {
        if (count + list.size() > data.length) {
            increaseArraySize();
        }
        for (int i = 0; i < list.size(); i++) {
            @SuppressWarnings("unchecked")
            T value = list.get(i);
            add(value);
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
    public void insert(T value, int index) {
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
    public T remove(int index) {
        checkListEmpty();
        checkIndexValid(index);

        @SuppressWarnings("unchecked")
        T value = (T)data[index];
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
    public List<T> remove(int start, int end) {
        checkListEmpty();

        checkIndexValid(start);

        if (start > end) {
            throw new IllegalArgumentException();
        }

        if (start == end) {
            return new List<>();
        }

        checkIndexValid(end - 1);

        List<T> removed = new List<>();
        for (int i = 0; i < count; i++) {
            if (i >= start && i < end) {
                @SuppressWarnings("unchecked")
                T value = (T)data[i];
                removed.add(value);
            } else if (i >= end) {
                data[i - end + start] = data[i];
            }
        }
        count -= end - start;

        return removed;
    };

    /**
     * Removes the first element from the list.
     *
     * @return the removed element
     */
    public T removeFirst() {
        checkListEmpty();

        T removed = first();
        data = Arrays.copyOfRange(data, 1, count);
        count--;

        return removed;
    };

    /**
     * Removes the last element from the list.
     *
     * @return the removed element
     */
    public T removeLast() {
        checkListEmpty();
        count--;

        @SuppressWarnings("unchecked")
        T value = (T)data[count];
        return value;
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
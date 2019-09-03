package io.github.paulszefer;

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

    /**
     * Creates an empty list with size {@value #DEFAULT_STARTING_SIZE}.
     */
    public List() {};

    /**
     * Creates an empty list with the given size.
     *
     * @param size the initial size of the list
     */
    public List(int size) {};

    /**
     * Creates an list with the given size, filled with the given element.
     *
     * @param size the initial size of the list
     * @param value the value to fill each element with
     */
    public List(int size, int value) {};

    /**
     * Creates a list to hold the elements of the given list.
     *
     * @param list the list of elements to hold
     */
    public List(List list) {};

    /**
     * Creates a list to hold the elements of the given array.
     *
     * @param array the array of elements to hold
     */
    public List(int[] array) {};

    // Stores the elements in the list
    private int[] data;

    // The number of elements in the list
    private int count;

    /**
     * @return the number of elements in the list
     */
    public int size() { return 0; };

    /**
     * @return the first element in the list
     */
    public int first() { return 0; };

    /**
     * @return the last element in the list
     */
    public int last() { return 0; };

    /**
     * @return the nth element in the list
     *
     * @param index the index of the element to return
     */
    public int get(int index) { return 0; };

    /**
     * Adds the element to the end of the list.
     *
     * @param value the value of the element to add
     */
    public void add(int value) {};

    /**
     * Inserts the element into the list at the specified index.
     *
     * All subsequent elements will be shifted forward by one.
     *
     * @param value the value of the element to insert
     * @param index the index of the newly inserted element
     */
    public void insert(int value, int index) {};

    /**
     * Removes the element from the list at the specified index.
     *
     * @param index the index of the element to remove
     * @return the removed element
     */
    public int remove(int index) { return 0; };

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
    public List remove(int start, int end) { return new List(); };

    /**
     * Removes the first element from the list.
     *
     * @return the removed element
     */
    public int removeFirst() { return 0; };

    /**
     * Removes the last element from the list.
     *
     * @return the removed element
     */
    public int removeLast() { return 0; };

    /**
     * Fills the list with the given value.
     *
     * @param value the value to fill each element with
     */
    public void fill(int value) {};

    /**
     * Fills the list with the given value from {@code start} to the end of the list.
     *
     * @param value the value to fill each element with
     * @param start the index to start filling at (inclusive)
     */
    public void fill(int value, int start) {};

    /**
     * Fills the list with the given value from {@code start} to {@code end}.
     *
     * @param value the value to fill each element with
     * @param start the index to start filling at (inclusive)
     * @param end the index to stop filling at (exclusive)
     */
    public void fill(int value, int start, int end) {};

    /**
     * Fills the list based on the given parameters.
     *
     * @param startValue the value to start at
     * @param change the value to add to each previous value to get the next
     * @param factor the value to multiply each previous value by to get the next
     */
    public void fillCustom(int startValue, int change, int factor) {};

    /**
     * Empties the list.
     */
    public void empty() {};

    // Increases the size of the internal storage array by a factor of two
    private void increaseArraySize() {};

    // Increases the size of the internal storage array by the given factor
    private void increaseArraySize(double factor) {};
}
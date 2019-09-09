package io.github.paulszefer;

import static io.github.paulszefer.TestAsserts.assertEqual;
import static io.github.paulszefer.TestAsserts.assertThrows;

public class ListTest_Integer extends Test {

    private static final int LIST1_SIZE = 0;
    private static final int LIST2_SIZE = 6;
    private static final int LIST3_SIZE = 5;
    private static final int LIST4_SIZE = 31;

    private List<Integer> list1;
    private List<Integer> list2;
    private List<Integer> list3;
    private List<Integer> list4;

    public void setUp() {
        list1 = new List<>();
        list2 = new List<>(new Integer[] {
            0, 1, 2, 3, 4, 5
        });
        list3 = new List<>(new Integer[] {
            3, 7, -1, -2, -10
        });
        list4 = new List<>(new Integer[] {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
            30
        });
    }

    public void test_List_IsEmpty() throws Exception {
        list1 = new List<>();
        assertEqual(0, list1.size());
    }

    public void test_List_EmptyArray_ListIsEmpty() throws Exception {
        Integer[] arr = new Integer[] { };
        list1 = new List<>(arr);
        assertEqual(0, list1.size());
    }

    public void test_List_Array_HasCorrectSize() throws Exception {
        Integer[] arr = new Integer[] { 0, 1 };
        list1 = new List<>(arr);
        assertEqual(2, list1.size());
    }

    public void test_List_Array_HasCorrectSize2() throws Exception {
        Integer[] arr = new Integer[] { -2, -3, -4 };
        list1 = new List<>(arr);
        assertEqual(3, list1.size());
    }

    public void test_copy_EmptyList_CreatesEmptyList() throws Exception {
        List<Integer> list = list1.copy();
        assertEqual(0, list.size());
    }

    public void test_copy_List_CreatesCorrectList() throws Exception {
        List<Integer> list = list2.copy();
        assertEqual(6, list.size());
        assertEqual(0, list.get(0));
        assertEqual(1, list.get(1));
        assertEqual(2, list.get(2));
        assertEqual(3, list.get(3));
        assertEqual(4, list.get(4));
        assertEqual(5, list.get(5));
    }

    public void test_copy_List_CreatesCorrectList2() throws Exception {
        List<Integer> list = list3.copy();
        assertEqual(  5, list.size());
        assertEqual(  3, list.get(0));
        assertEqual(  7, list.get(1));
        assertEqual( -1, list.get(2));
        assertEqual( -2, list.get(3));
        assertEqual(-10, list.get(4));
    }

    public void test_first_EmptyList_ThrowsException() throws Exception {
        assertThrows(ListEmptyException.class, list1::first);
    }

    public void test_first_List_GetsCorrectValue() throws Exception {
        assertEqual(0, list2.first());
    }

    public void test_first_List_GetsCorrectValue2() throws Exception {
        assertEqual(3, list3.first());
    }

    public void test_last_EmptyList_ThrowsException() throws Exception {
        assertThrows(ListEmptyException.class, list1::last);
    }

    public void test_last_List_GetsCorrectValue() throws Exception {
        assertEqual(5, list2.last());
    }

    public void test_last_List_GetsCorrectValue2() throws Exception {
        assertEqual(-10, list3.last());
    }

    public void test_get_EmptyList_ThrowsException() throws Exception {
        assertThrows(ListEmptyException.class, list1::last);
    }

    public void test_get_List_NegativeIndex_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list3.get(-1));
    }

    public void test_get_List_IndexTooHigh_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list3.get(LIST3_SIZE));
    }

    public void test_get_List_FirstIndex_GetsCorrectValue() throws Exception {
        assertEqual(3, list3.get(0));
    }

    public void test_get_List_MiddleIndex_GetsCorrectValue() throws Exception {
        assertEqual(7, list3.get(1));
    }

    public void test_get_List_LastIndex_GetsCorrectValue() throws Exception {
        assertEqual(-10, list3.get(LIST3_SIZE - 1));
    }

    public void test_add_EmptyList() throws Exception {
        int value = 5;
        list1.add(value);
        assertEqual(LIST1_SIZE + 1, list1.size());
        assertEqual(value, list1.get(0));
    }

    public void test_add_List() throws Exception {
        int value = 10;
        list2.add(value);
        assertEqual(LIST2_SIZE + 1, list2.size());
        assertEqual(value, list2.get(6));
    }

    public void test_add_List_ExtendsListSize() throws Exception {
        int value = 15;
        list4.add(value);
        assertEqual(LIST4_SIZE + 1, list4.size());
        assertEqual(value, list4.get(LIST4_SIZE));
        list4.add(value + 1);
        assertEqual(LIST4_SIZE + 2, list4.size());
        assertEqual(value + 1, list4.get(LIST4_SIZE + 1));
        list4.add(value + 2);
        assertEqual(LIST4_SIZE + 3, list4.size());
        assertEqual(value + 2, list4.get(LIST4_SIZE + 2));
    }

    public void test_insert_List_NegativeIndex_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list2.insert(10, -1));
    }

    public void test_insert_List_IndexTooHigh_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list2.insert(10, LIST2_SIZE));
    }

    public void test_insert_List_FirstIndex() throws Exception {
        int value = 10;
        list3.insert(value, 0);
        assertEqual(LIST3_SIZE + 1, list3.size());
        assertEqual(value, list3.get(0));
        assertEqual(3, list3.get(1));
        assertEqual(-10, list3.get(LIST3_SIZE));
    }

    public void test_insert_List_MiddleIndex() throws Exception {
        int value = 10;
        list3.insert(value, 1);
        assertEqual(LIST3_SIZE + 1, list3.size());
        assertEqual(3, list3.get(0));
        assertEqual(value, list3.get(1));
        assertEqual(-10, list3.get(LIST3_SIZE));
    }

    public void test_insert_List_LastIndex() throws Exception {
        int value = 10;
        list3.insert(value, LIST3_SIZE - 1);
        assertEqual(LIST3_SIZE + 1, list3.size());
        assertEqual(3, list3.get(0));
        assertEqual(value, list3.get(LIST3_SIZE - 1));
        assertEqual(-10, list3.get(LIST3_SIZE));
    }

    public void test_remove_List_NegativeIndex_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list2.remove(-1));
    }

    public void test_remove_List_IndexTooHigh_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list2.remove(LIST2_SIZE));
    }

    public void test_remove_List_FirstIndex() throws Exception {
        int value = list3.remove(0);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual(3, value);
        assertEqual(7, list3.get(0));
        assertEqual(-1, list3.get(1));
        assertEqual(-10, list3.get(LIST3_SIZE - 2));
    }

    public void test_remove_List_MiddleIndex() throws Exception {
        int value = list3.remove(1);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual(7, value);
        assertEqual(3, list3.get(0));
        assertEqual(-1, list3.get(1));
        assertEqual(-10, list3.get(LIST3_SIZE - 2));
    }

    public void test_remove_List_LastIndex() throws Exception {
        int value = list3.remove(LIST3_SIZE - 1);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual(-10, value);
        assertEqual(3, list3.get(0));
        assertEqual(-1, list3.get(LIST3_SIZE - 3));
        assertEqual(-2, list3.get(LIST3_SIZE - 2));
    }

    public void test_remove_start_end_EmptyList_ThrowsException() throws Exception {
        assertThrows(ListEmptyException.class, () -> list1.remove(0, 1));
    }

    public void test_remove_start_end_List_StartIndexNegative_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list2.remove(-1, 1));
    }

    public void test_remove_start_end_List_EndIndexNegative_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list2.remove(-1, -1));
        // list2.remove(0, -1) caught by test_remove_start_end_List_StartIndexLargerThanEndIndex_ThrowsException
        // list2.remove(-2, -1) caught by test_remove_start_end_List_StartIndexNegative_ThrowsException
    }

    public void test_remove_start_end_List_StartIndexEqualToEndIndex_ReturnsEmptyList() throws Exception {
        int start = 0;
        int end = 0;
        List<Integer> list = list3.remove(start, end);
        assertEqual(0, list.size());
    }

    public void test_remove_start_end_List_StartIndexLargerThanEndIndex_ThrowsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> list2.remove(1, 0));
    }

    public void test_remove_start_end_List_RemoveOneFromStart() throws Exception {
        int start = 0;
        int end = 1;
        List<Integer> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual(3, list.get(0));
        assertEqual(7, list3.get(0));
        assertEqual(-1, list3.get(1));
        assertEqual(-2, list3.get(2));
        assertEqual(-10, list3.get(3));
    }

    public void test_remove_start_end_List_RemoveOneFromMiddle() throws Exception {
        int start = 1;
        int end = 2;
        List<Integer> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual(7, list.get(0));
        assertEqual(3, list3.get(0));
        assertEqual(-1, list3.get(1));
        assertEqual(-2, list3.get(2));
        assertEqual(-10, list3.get(3));
    }

    public void test_remove_start_end_List_RemoveOneFromEnd() throws Exception {
        int start = LIST3_SIZE - 1;
        int end = LIST3_SIZE;
        List<Integer> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual(-10, list.get(0));
        assertEqual(3, list3.get(0));
        assertEqual(7, list3.get(1));
        assertEqual(-1, list3.get(2));
        assertEqual(-2, list3.get(3));
    }

    public void test_remove_start_end_List_RemoveMultipleFromStart() throws Exception {
        int start = 0;
        int end = 3;
        List<Integer> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - end + start, list3.size());
        assertEqual(3, list.get(0));
        assertEqual(7, list.get(1));
        assertEqual(-1, list.get(2));
        assertEqual(-2, list3.get(0));
        assertEqual(-10, list3.get(1));
    }

    public void test_remove_start_end_List_RemoveMultipleFromMiddle() throws Exception {
        int start = 1;
        int end = 3;
        List<Integer> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - end + start, list3.size());
        assertEqual(7, list.get(0));
        assertEqual(-1, list.get(1));
        assertEqual(3, list3.get(0));
        assertEqual(-2, list3.get(1));
        assertEqual(-10, list3.get(2));
    }

    public void test_remove_start_end_List_RemoveMultipleFromEnd() throws Exception {
        int start = 2;
        int end = LIST3_SIZE;
        List<Integer> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - end + start, list3.size());
        assertEqual(-1, list.get(0));
        assertEqual(-2, list.get(1));
        assertEqual(-10, list.get(2));
        assertEqual(3, list3.get(0));
        assertEqual(7, list3.get(1));
    }

    public void test_remove_start_end_List_RemoveAll() throws Exception {
        int start = 0;
        int end = LIST3_SIZE;
        List<Integer> list = list3.remove(start, end);
        assertEqual(0, list3.size());
        assertEqual(3, list.get(0));
        assertEqual(7, list.get(1));
        assertEqual(-1, list.get(2));
        assertEqual(-2, list.get(3));
        assertEqual(-10, list.get(4));
    }

    public void test_removeFirst_EmptyList_ThrowsException() throws Exception {
        assertThrows(ListEmptyException.class, () -> list1.removeFirst());
    }

    public void test_removeFirst_List() throws Exception {
        int value = list3.removeFirst();
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual(3, value);
        assertEqual(7, list3.get(0));
        assertEqual(-1, list3.get(1));
        assertEqual(-2, list3.get(2));
        assertEqual(-10, list3.get(3));
    }

    public void test_removeLast_EmptyList_ThrowsException() throws Exception {
        assertThrows(ListEmptyException.class, () -> list1.removeLast());
    }

    public void test_removeLast_List() throws Exception {
        int value = list3.removeLast();
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual(-10, value);
        assertEqual(3, list3.get(0));
        assertEqual(7, list3.get(1));
        assertEqual(-1, list3.get(2));
        assertEqual(-2, list3.get(3));
    }

    public void test_empty_EmptyList() throws Exception {
        list1.empty();
        assertEqual(0, list1.size());
    }

    public void test_empty_List() throws Exception {
        list2.empty();
        assertEqual(0, list2.size());
    }
}
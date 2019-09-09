package io.github.paulszefer;

import static io.github.paulszefer.TestAsserts.assertEqual;
import static io.github.paulszefer.TestAsserts.assertThrows;

public class ListTest_String extends Test {

    private static final int LIST1_SIZE = 0;
    private static final int LIST2_SIZE = 6;
    private static final int LIST3_SIZE = 5;
    private static final int LIST4_SIZE = 31;

    private List<String> list1;
    private List<String> list2;
    private List<String> list3;
    private List<String> list4;

    public void setUp() {
        list1 = new List<>();
        list2 = new List<>(new String[] {
            "Hello", "World", "I", "am", "a", "list"
        });
        list3 = new List<>(new String[] {
            "I", "am", "not", "an", "array"
        });
        list4 = new List<>(new String[] {
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
            "twenty", "twenty-one", "twenty-two", "twenty-three", "twenty-four",
            "twenty-five", "twenty-six", "twenty-seven", "twenty-eight", "twenty-nine",
            "thirty"
        });
    }

    public void test_List_IsEmpty() throws Exception {
        List<String> list = new List<>();
        assertEqual(0, list.size());
    }

    public void test_List_EmptyArray_ListIsEmpty() throws Exception {
        String[] arr = new String[] { };
        List<String> list = new List<>(arr);
        assertEqual(0, list.size());
    }

    public void test_List_Array_HasCorrectValues() throws Exception {
        String[] arr = new String[] { "Hello", "World" };
        List<String> list = new List<>(arr);
        assertEqual("Hello", list.get(0));
        assertEqual("World", list.get(1));
    }

    public void test_List_Array_HasCorrectSize() throws Exception {
        String[] arr = new String[] { "Hello", "World" };
        List<String> list = new List<>(arr);
        assertEqual(2, list.size());
    }

    public void test_List_Array_HasCorrectSize2() throws Exception {
        String[] arr = new String[] { "Hello", "World", "I", };
        List<String> list = new List<>(arr);
        assertEqual(3, list.size());
    }

    public void test_copy_EmptyList_CreatesEmptyList() throws Exception {
        List<String> list = list1.copy();
        assertEqual(0, list.size());
    }

    public void test_copy_List_CreatesCorrectList() throws Exception {
        List<String> list = list2.copy();
        assertEqual(LIST2_SIZE, list.size());
        assertEqual("Hello", list.get(0));
        assertEqual("World", list.get(1));
        assertEqual("I", list.get(2));
        assertEqual("am", list.get(3));
        assertEqual("a", list.get(4));
        assertEqual("list", list.get(5));
    }

    public void test_copy_List_CreatesCorrectList2() throws Exception {
        List<String> list = list3.copy();
        assertEqual(LIST3_SIZE, list.size());
        assertEqual("I", list.get(0));
        assertEqual("am", list.get(1));
        assertEqual("not", list.get(2));
        assertEqual("an", list.get(3));
        assertEqual("array", list.get(4));
    }

    public void test_first_EmptyList_ThrowsException() throws Exception {
        assertThrows(ListEmptyException.class, list1::first);
    }

    public void test_first_List_GetsCorrectValue() throws Exception {
        assertEqual("Hello", list2.first());
    }

    public void test_first_List_GetsCorrectValue2() throws Exception {
        assertEqual("I", list3.first());
    }

    public void test_last_EmptyList_ThrowsException() throws Exception {
        assertThrows(ListEmptyException.class, list1::last);
    }

    public void test_last_List_GetsCorrectValue() throws Exception {
        assertEqual("list", list2.last());
    }

    public void test_last_List_GetsCorrectValue2() throws Exception {
        assertEqual("array", list3.last());
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
        assertEqual("I", list3.get(0));
    }

    public void test_get_List_MiddleIndex_GetsCorrectValue() throws Exception {
        assertEqual("am", list3.get(1));
    }

    public void test_get_List_LastIndex_GetsCorrectValue() throws Exception {
        assertEqual("array", list3.get(LIST3_SIZE - 1));
    }

    public void test_add_EmptyList() throws Exception {
        String value = "test";
        list1.add(value);
        assertEqual(LIST1_SIZE + 1, list1.size());
        assertEqual(value, list1.get(0));
    }

    public void test_add_List() throws Exception {
        String value = "another test";
        list2.add(value);
        assertEqual(LIST2_SIZE + 1, list2.size());
        assertEqual(value, list2.get(6));
    }

    public void test_add_List_ExtendsListSize() throws Exception {
        String value1 = "yet";
        String value2 = "another";
        String value3 = "test";
        list4.add(value1);
        assertEqual(LIST4_SIZE + 1, list4.size());
        assertEqual(value1, list4.get(LIST4_SIZE));
        list4.add(value2);
        assertEqual(LIST4_SIZE + 2, list4.size());
        assertEqual(value2, list4.get(LIST4_SIZE + 1));
        list4.add(value3);
        assertEqual(LIST4_SIZE + 3, list4.size());
        assertEqual(value3, list4.get(LIST4_SIZE + 2));
    }

    public void test_insert_List_NegativeIndex_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list2.insert("test", -1));
    }

    public void test_insert_List_IndexTooHigh_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list2.insert("test", LIST2_SIZE));
    }

    public void test_insert_List_FirstIndex() throws Exception {
        String value = "test";
        list3.insert(value, 0);
        assertEqual(LIST3_SIZE + 1, list3.size());
        assertEqual(value, list3.get(0));
        assertEqual("I", list3.get(1));
        assertEqual("array", list3.get(LIST3_SIZE));
    }

    public void test_insert_List_MiddleIndex() throws Exception {
        String value = "test";
        list3.insert(value, 1);
        assertEqual(LIST3_SIZE + 1, list3.size());
        assertEqual("I", list3.get(0));
        assertEqual(value, list3.get(1));
        assertEqual("array", list3.get(LIST3_SIZE));
    }

    public void test_insert_List_LastIndex() throws Exception {
        String value = "test";
        list3.insert(value, LIST3_SIZE - 1);
        assertEqual(LIST3_SIZE + 1, list3.size());
        assertEqual("I", list3.get(0));
        assertEqual(value, list3.get(LIST3_SIZE - 1));
        assertEqual("array", list3.get(LIST3_SIZE));
    }

    public void test_remove_List_NegativeIndex_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list2.remove(-1));
    }

    public void test_remove_List_IndexTooHigh_ThrowsException() throws Exception {
        assertThrows(IndexOutOfBoundsException.class, () -> list2.remove(LIST2_SIZE));
    }

    public void test_remove_List_FirstIndex() throws Exception {
        String value = list3.remove(0);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual("I", value);
        assertEqual("am", list3.get(0));
        assertEqual("not", list3.get(1));
        assertEqual("array", list3.get(LIST3_SIZE - 2));
    }

    public void test_remove_List_MiddleIndex() throws Exception {
        String value = list3.remove(1);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual("am", value);
        assertEqual("I", list3.get(0));
        assertEqual("not", list3.get(1));
        assertEqual("array", list3.get(LIST3_SIZE - 2));
    }

    public void test_remove_List_LastIndex() throws Exception {
        String value = list3.remove(LIST3_SIZE - 1);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual("array", value);
        assertEqual("I", list3.get(0));
        assertEqual("not", list3.get(LIST3_SIZE - 3));
        assertEqual("an", list3.get(LIST3_SIZE - 2));
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
        List<String> list = list3.remove(start, end);
        assertEqual(0, list.size());
    }

    public void test_remove_start_end_List_StartIndexLargerThanEndIndex_ThrowsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> list2.remove(1, 0));
    }

    public void test_remove_start_end_List_RemoveOneFromStart() throws Exception {
        int start = 0;
        int end = 1;
        List<String> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual("I", list.get(0));
        assertEqual("am", list3.get(0));
        assertEqual("not", list3.get(1));
        assertEqual("an", list3.get(2));
        assertEqual("array", list3.get(3));
    }

    public void test_remove_start_end_List_RemoveOneFromMiddle() throws Exception {
        int start = 1;
        int end = 2;
        List<String> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual("am", list.get(0));
        assertEqual("I", list3.get(0));
        assertEqual("not", list3.get(1));
        assertEqual("an", list3.get(2));
        assertEqual("array", list3.get(3));
    }

    public void test_remove_start_end_List_RemoveOneFromEnd() throws Exception {
        int start = LIST3_SIZE - 1;
        int end = LIST3_SIZE;
        List<String> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual("array", list.get(0));
        assertEqual("I", list3.get(0));
        assertEqual("am", list3.get(1));
        assertEqual("not", list3.get(2));
        assertEqual("an", list3.get(3));
    }

    public void test_remove_start_end_List_RemoveMultipleFromStart() throws Exception {
        int start = 0;
        int end = 3;
        List<String> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - end + start, list3.size());
        assertEqual("I", list.get(0));
        assertEqual("am", list.get(1));
        assertEqual("not", list.get(2));
        assertEqual("an", list3.get(0));
        assertEqual("array", list3.get(1));
    }

    public void test_remove_start_end_List_RemoveMultipleFromMiddle() throws Exception {
        int start = 1;
        int end = 3;
        List<String> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - end + start, list3.size());
        assertEqual("am", list.get(0));
        assertEqual("not", list.get(1));
        assertEqual("I", list3.get(0));
        assertEqual("an", list3.get(1));
        assertEqual("array", list3.get(2));
    }

    public void test_remove_start_end_List_RemoveMultipleFromEnd() throws Exception {
        int start = 2;
        int end = LIST3_SIZE;
        List<String> list = list3.remove(start, end);
        assertEqual(LIST3_SIZE - end + start, list3.size());
        assertEqual("not", list.get(0));
        assertEqual("an", list.get(1));
        assertEqual("array", list.get(2));
        assertEqual("I", list3.get(0));
        assertEqual("am", list3.get(1));
    }

    public void test_remove_start_end_List_RemoveAll() throws Exception {
        int start = 0;
        int end = LIST3_SIZE;
        List<String> list = list3.remove(start, end);
        assertEqual(0, list3.size());
        assertEqual("I", list.get(0));
        assertEqual("am", list.get(1));
        assertEqual("not", list.get(2));
        assertEqual("an", list.get(3));
        assertEqual("array", list.get(4));
    }

    public void test_removeFirst_EmptyList_ThrowsException() throws Exception {
        assertThrows(ListEmptyException.class, () -> list1.removeFirst());
    }

    public void test_removeFirst_List() throws Exception {
        String value = list3.removeFirst();
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual("I", value);
        assertEqual("am", list3.get(0));
        assertEqual("not", list3.get(1));
        assertEqual("an", list3.get(2));
        assertEqual("array", list3.get(3));
    }

    public void test_removeLast_EmptyList_ThrowsException() throws Exception {
        assertThrows(ListEmptyException.class, () -> list1.removeLast());
    }

    public void test_removeLast_List() throws Exception {
        String value = list3.removeLast();
        assertEqual(LIST3_SIZE - 1, list3.size());
        assertEqual("array", value);
        assertEqual("I", list3.get(0));
        assertEqual("am", list3.get(1));
        assertEqual("not", list3.get(2));
        assertEqual("an", list3.get(3));
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
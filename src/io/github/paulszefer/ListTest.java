package io.github.paulszefer;

import static io.github.paulszefer.TestAsserts.assertEqual;
import static io.github.paulszefer.TestAsserts.assertThrows;

public class ListTest extends Test {

    private List list1;
    private List list2;
    private List list3;

    public void setUp() {
        list1 = new List();
        list2 = new List(new int[] {
           0, 1, 2, 3, 4, 5
        });
        list3 = new List(new int[] {
           3, 7, -1, -2, -10
        });
    }

    public void test_List_IsEmpty() throws Exception {
        list1 = new List();
        assertEqual(0, list1.size());
    }

    public void test_List_EmptyArray_ListIsEmpty() throws Exception {
        int[] arr = new int[] { };
        list1 = new List(arr);
        assertEqual(0, list1.size());
    }

    public void test_List_Array_HasCorrectSize() throws Exception {
        int[] arr = new int[] { 0, 1 };
        list1 = new List(arr);
        assertEqual(2, list1.size());
    }

    public void test_List_Array_HasCorrectSize2() throws Exception {
        int[] arr = new int[] { -2, -3, -4 };
        list1 = new List(arr);
        assertEqual(3, list1.size());
    }

    public void test_copy_EmptyList_CreatesEmptyList() throws Exception {
        List list = list1.copy();
        assertEqual(0, list.size());
    }

    public void test_copy_List_CreatesCorrectList() throws Exception {
        List list = list2.copy();
        assertEqual(6, list.size());
        assertEqual(0, list.get(0));
        assertEqual(1, list.get(1));
        assertEqual(2, list.get(2));
        assertEqual(3, list.get(3));
        assertEqual(4, list.get(4));
        assertEqual(5, list.get(5));
    }

    public void test_copy_List_CreatesCorrectList2() throws Exception {
        List list = list3.copy();
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
}
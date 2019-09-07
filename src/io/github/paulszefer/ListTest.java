package io.github.paulszefer;

public class ListTest extends Test {

    private List list1;
    private List list2;

    public void setUp() {
        list1 = new List();
        list2 = new List(new int[] {
           0, 1, 2, 3, 4, 5
        });
    }

    public void test_List_IsEmpty() throws Exception {
        list1 = new List();
        assertEqual(0, list1.size());
    }

    public void test_List_Array_GivenEmptyArray_ListIsEmpty() throws Exception {
        int[] arr = new int[] { };
        list1 = new List(arr);
        assertEqual(0, list1.size());
    }

    public void test_List_Array_GivenArray_HasCorrectSize() throws Exception {
        int[] arr = new int[] { 0, 1 };
        list1 = new List(arr);
        assertEqual(2, list1.size());
    }

    public void test_List_Array_GivenArray_HasCorrectSize2() throws Exception {
        int[] arr = new int[] { -2, -3, -4 };
        list1 = new List(arr);
        assertEqual(3, list1.size());
    }
}
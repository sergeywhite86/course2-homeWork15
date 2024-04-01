package com.white.homework;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class MyStringListTest {
    private static StringList<String> out;

    @BeforeEach
    public void init() {
        out = new MyStringList(3);
    }

    @Test
    void add_Then_All_Ok() {
        String actual = out.add("Hello");
        String expected = "Hello";
        assertEquals(1, out.size());
        assertEquals(expected, actual);
    }

    @Test
    void testAdd_Throw_Then_Param_Is_Null() {
        assertThrows(IllegalArgumentException.class, () -> out.add(null));
    }

    @ParameterizedTest
    @MethodSource("provideParams")
    void add_To_Index_Is_Ok(int index, String value, String expected) {
        out.add("1");
        out.add("2");
        out.add("3");
        int actualSize = 3;
        String actual = out.add(index, value);
        assertEquals(expected, actual);
        assertEquals(++actualSize, out.size());
    }

    @Test
    void add_By_Index_Throw_Then_Param_Is_More_Size() {
        out.add("1");
        assertThrows(IndexOutOfBoundsException.class, () -> out.add(1, "value"));
    }

    @ParameterizedTest
    @MethodSource("provideParams")
    void set_Is_Ok(int index, String value, String expected) {
        out.add("1");
        out.add("2");
        out.add("3");
        int actualSize = 3;
        String actual = out.set(index, value);
        assertEquals(expected, actual);
        assertEquals(actualSize, out.size());

    }

    public static Stream<Arguments> provideParams() {
        return Stream.of(
                Arguments.of(0, "Hello", "Hello"),
                Arguments.of(1, "world", "world"),
                Arguments.of(2, "!", "!"));
    }

    @Test
    void set_By_Index_Throw_Then_Param_Is_More_Size() {
        assertThrows(IndexOutOfBoundsException.class, () -> out.set(0, "value"));
    }

    @Test
    void remove_Is_Ok() {
        out.add("Hi");
        String expected = "Hi";
        String actual = out.remove("Hi");
        assertEquals(expected, actual);
        assertEquals(0, out.size());
    }

    @Test
    void remove_Throw_Then_Item_Is_Absent() {
        out.add("1");
        out.add("2");
        assertThrows(IllegalArgumentException.class, () -> out.remove("3"));
    }

    @Test
    void remove_By_Index_Is_Ok() {
        out.add("Hi");
        String expected = "Hi";
        String actual = out.remove(0);
        assertEquals(expected, actual);
        assertEquals(0, out.size());
    }

    @Test
    void remove_By_Index_Throw_Then_Item_Is_Absent() {
        out.add("1");
        out.add("2");
        assertThrows(IndexOutOfBoundsException.class, () -> out.remove(2));
    }

    @Test
    void contains_Then_Item_Is_Exist() {
        out.add("1");
        out.add("2");
        assertTrue(out.contains("1"));
    }

    @Test
    void contains_Then_Item_Is_Not_Exist() {
        out.add("1");
        out.add("2");
        assertFalse(out.contains("3"));
    }

    @Test
    void indexOf_Then_Item_Is_Exist() {
        out.add("1");
        out.add("2");
        int actual = out.indexOf("1");
        int expected = 0;
        assertEquals(expected, actual);

    }

    @Test
    void indexOf_Then_Item_Is_Not_Exist() {
        out.add("1");
        out.add("2");
        int actual = out.indexOf("3");
        int expected = -1;
        assertEquals(expected, actual);

    }

    @Test
    void lastIndexOf_Then_Item_Is_exist() {
        out.add("1");
        out.add("2");
        out.add("3");
        out.add("2");
        int actual = 3;
        int expected = out.lastIndexOf("2");
        assertEquals(expected, actual);
    }

    @Test
    void lastIndexOf_Then_Item_Not_exist() {
        out.add("1");
        out.add("2");
        out.add("3");
        out.add("2");
        int actual = -1;
        int expected = out.lastIndexOf("0");
        assertEquals(expected, actual);
    }

    @Test
    void get_Is_Ok() {
        out.add("1");
        out.add("2");
        String actual = out.get(0);
        String expected = "1";
        assertEquals(expected, actual);
    }

    @Test
    void get_Throw_Then_Index_More_Size() {
        out.add("1");
        out.add("2");
        assertThrows(IndexOutOfBoundsException.class, () -> out.get(2));
    }

    @Test
    void Equals_Then_Data_Equal() {
        StringList<String> list = new MyStringList(10);
        out.add("1");
        out.add("2");
        list.add("1");
        list.add("2");
        assertTrue(out.equals(list));
    }

    @Test
    void Equals_Then_Data_Not_Equal() {
        StringList<String> list = new MyStringList(10);
        out.add("1");
        out.add("2");
        list.add("1");
        list.add("3");
        assertFalse(out.equals(list));
    }

    @Test
    void size() {
        out.add("1");
        out.add("2");
        int expected = 2;
        int actual = out.size();
        assertEquals(expected, actual);
    }

    @Test
    void isEmpty_Then_Size_Zero() {
        assertTrue(out.isEmpty());
    }

    @Test
    void isEmpty_Then_Size_Not_Zero() {
        out.add("1");
        assertFalse(out.isEmpty());
    }

    @Test
    void clear_Is_Ok() {
        out.add("1");
        out.add("2");
        out.add("3");
        out.clear();
        int expected = 0;
        int actual = out.size();
        assertEquals(expected, actual);
    }

    @Test
    void toArray() {
        String[] arr = {"1", "2", "3"};
        out.add("1");
        out.add("2");
        out.add("3");
        boolean expected = Arrays.equals(arr, out.toArray());
        assertTrue(expected);
    }
}
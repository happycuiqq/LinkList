package com.cui;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

/**
 * java类简单作用描述
 *
 * @Package: com.cui
 * @Author: cui
 * @CreateDate: 2018/4/12 22:03
 * @UpdateUser: cui
 * @Version: 1.0
 */
public class DoubleLinkListTest {
    DoubleLinkList<String> list = new DoubleLinkList<String>();

    /**
     * 初始化长度
     */
    private final int INIT_LENGTH = 10;

    private final String HEADER = "header";
    private final String TAIL = "tail";
    private final String ELEMENT = "element";

    /**
     * 初始化头
     */
    private void initHeader() {
        list.clear();
        list.addHeader(HEADER);
    }

    /**
     * 初始化尾
     */
    private void initTail() {
        list.clear();
        list.addTail(TAIL);
    }

    /**
     * 初始化一个元素
     */
    private void initOne() {
        list.clear();
        list.add(ELEMENT);
    }

    /**
     * 初始化两个元素
     */
    private void initTwo() {
        // 加头加尾
        list.clear();
        list.addHeader(HEADER);
        list.addTail(TAIL);
    }

    /**
     * 初始化多个元素
     */
    private void initList() {
        list.clear();
        for (int i = 0; i < INIT_LENGTH; i++) {
            list.add(ELEMENT + i);
        }
    }

    /**
     * 初始化多个元素
     */
    private void initReserveList() {
        list.clear();
        for (int i = INIT_LENGTH - 1; i >= 0; i--) {
            list.add(ELEMENT + i);
        }
    }


    @Before
    public void setUp() throws Exception {
        list.clear();
    }

    @Test
    public void testAddHeader() throws Exception {
        initHeader();
        assertEquals(HEADER, list.get(0));
        assertEquals(1, list.length());

        initTwo();
        assertEquals(HEADER, list.get(0));
        assertEquals(2, list.length());
    }

    @Test
    public void testAddTail() throws Exception {
        // 只加头
        initTail();
        assertEquals(TAIL, list.get(list.length() - 1));
        assertEquals(1, list.length());
        // 加头加尾
        initTwo();
        assertEquals(TAIL, list.get(list.length() - 1));
        assertEquals(2, list.length());
    }

    @Test
    public void testAdd() throws Exception {
        // 加在第一个位置
        initOne();
        assertEquals(ELEMENT, list.get(0));
        assertEquals(1, list.length());

        initList();
        assertEquals(10, list.length());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsert() throws Exception {
        // 无元素,加在第一个位置
        initOne();
        assertEquals(ELEMENT, list.get(0));

        // 有元素,插入第一个位置
        initOne();
        list.insert("element1", 0);
        assertEquals("element1", list.get(0));
        assertEquals(ELEMENT, list.get(1));
        assertEquals(2, list.length());


        // 有元素,插入中间位置
        initList();
        list.insert("element-5", 5);
        assertEquals("element-5", list.get(5));


        // 测试异常

        // 无元素,加在第一个位置
        list.clear();
        list.insert("element-1", 1);

        // 有元素,插入第一个位置
        initList();
        list.insert("element-11", 11);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveHeader() throws Exception {
        // 上来就删除,会引发异常
        list.removeHeader();

        // 先添加后删除,链表依旧为空
        initOne();
        list.removeHeader();
        assertEquals(0, list.length());

        // 添加两个,删除头,还剩下一个
        initTwo();
        list.removeHeader();
        assertEquals(1, list.length());

        // 添加多个
        initList();
        list.removeHeader();
        assertEquals(9, list.length());
        assertEquals("element1", list.get(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveTail() throws Exception {
        // 上来就删除,会引发异常
        list.removeTail();

        // 先添加后删除,链表依旧为空
        initOne();
        list.removeTail();
        assertEquals(true, list.empty());

        // 添加两个,删除头,还剩下一个
        initTwo();
        list.removeTail();
        assertEquals(1, list.length());


        // 添加多个
        initList();
        list.removeTail();
        assertEquals(9, list.length());
        assertEquals("element8", list.get(list.length() - 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove() throws Exception {
        list.remove(0);
        list.remove(1);

        initOne();
        list.remove(0);
        assertEquals(0, list.length());
        list.remove(0);

        initTwo();
        list.remove(1);
        assertEquals(0, list.length());
        assertEquals(HEADER, list.get(0));

        // 从中间删除,个数减一,第五个变成第六个
        initList();
        list.remove(5);
        assertEquals(9, list.length());
        assertEquals(ELEMENT + "6", list.get(5));
        list.remove(10);
    }

    @Test
    public void testLength() throws Exception {
        assertEquals(0, list.length());

        initOne();
        assertEquals(1, list.length());

        initTwo();
        assertEquals(2, list.length());

        initList();
        assertEquals(INIT_LENGTH, list.length());
    }

    @Test
    public void testEmpty() throws Exception {

        assertEquals(true, list.empty());

        initOne();
        assertEquals(false, list.empty());

        initTwo();
        assertEquals(false, list.empty());

        initList();
        assertEquals(false, list.empty());

    }

    @Test
    public void testClear() throws Exception {
        list.clear();
        assertEquals(true, list.empty());

        initOne();
        assertEquals(false, list.empty());
        list.clear();
        assertEquals(true, list.empty());

        initTwo();
        assertEquals(false, list.empty());
        list.clear();
        assertEquals(true, list.empty());


        initList();
        assertEquals(false, list.empty());
        list.clear();
        assertEquals(true, list.empty());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get() throws Exception {
        list.get(0);

        initOne();
        assertEquals(ELEMENT, list.get(0));

        initTwo();
        assertEquals(HEADER, list.get(0));
        assertEquals(TAIL, list.get(1));

        initList();
        for (int i = 0; i < INIT_LENGTH; i++) {
            assertEquals(ELEMENT + i, list.get(i));
        }
        list.get(INIT_LENGTH + 1);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("", list.toString());

        StringBuilder stringBuilder = new StringBuilder();

        initOne();
        stringBuilder.append("[");
        stringBuilder.append(ELEMENT);
        stringBuilder.append(">");
        stringBuilder.append("]");
        assertEquals(stringBuilder.toString(), list.toString());

        initTwo();
        stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(HEADER);
        stringBuilder.append(">");
        stringBuilder.append(TAIL);
        stringBuilder.append(">");
        stringBuilder.append("]");
        assertEquals(stringBuilder.toString(), list.toString());

        initList();
        stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < INIT_LENGTH; i++) {
            stringBuilder.append(ELEMENT + i);
            stringBuilder.append(">");
        }
        stringBuilder.append("]");
        assertEquals(stringBuilder.toString(), list.toString());

    }

    @Test
    public void testReverse() throws Exception {
        list.reverse();

        // 生成一个正序的列表
        initList();
        // 倒置
        list.reverse();
        String listStr = list.toString();
        // 重新生成一个倒置的链表
        initReserveList();
        assertEquals(listStr, list.toString());
    }

}
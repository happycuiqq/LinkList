package com.cui;

import java.util.NoSuchElementException;

/**
 * 双向链表:实现元素添加、删除、插入、反转、提取
 *
 * @Package: com.cui
 * @ClassName: DoubleLinkList
 * @Author: cui
 * @CreateDate: 2018/4/11 21:55
 * @Version: 1.0
 */
public class DoubleLinkList<E> {

    /**
     * @Description: 定义一个内部类Node，Node实例代表链表的节点
     */
    private class Node {
        /**
         * 节点数据
         */
        private E item;
        /**
         * 指向上一个节点的引用
         */
        private Node prev;
        /**
         * 指向下一个节点的引用
         */
        private Node next;


        public Node() {

        }

        public Node(Node prev, E item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    /**
     * 保存该链表的头结点
     */
    private Node header;
    /**
     * 保存该链表的尾节点
     */
    private Node tail;
    /**
     * 保存该链表中已包含的节点数
     */
    private int size;


    public DoubleLinkList() {
        header = null;
        tail = null;
    }

    public DoubleLinkList(E element) {
        header = new Node(null, element, null);
        tail = header;
        size++;
    }


    /**
     * 向线性链表的表头插入一个元素
     *
     * @param element
     */
    public void addHeader(E element) {
        Node temp_header = header;
        Node newNode = new Node(null, element, temp_header);
        header = newNode;
        // 链表为空,尾部指向该元素
        if (temp_header == null) {
            tail = newNode;
        } else {
            temp_header.prev = newNode;
        }
        size++;
    }


    /**
     * 向线性链表的表尾插入一个元素
     *
     * @param element
     */
    public void addTail(E element) {
        Node temp_tail = tail;
        Node newNode = new Node(temp_tail, element, null);
        tail = newNode;
        // 链表为空,头部指向该元素
        if (temp_tail == null) {
            header = newNode;
        } else {
            temp_tail.next = newNode;
        }
        size++;
    }

    /**
     * 为方便使用增加add方法,默认往后边添加元素
     *
     * @param element
     */
    public void add(E element) {
        addTail(element);
    }

    /**
     * 在指定位置插入元素
     *
     * @param element
     * @param index
     */
    public void insert(E element, int index) {
        // 如果插入位置大于长度,则抛出异常
        if (index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        if (index == size) {
            addTail(element);
        } else {
            insertNode(element, getNodeByIndex(index));
        }
    }

    /**
     * 在线性表中某个元素位置插入一个节点
     *
     * @param element
     * @param node
     */
    private void insertNode(E element, Node node) {
        Node pre = node.prev;
        Node newNode = new Node(pre, element, node);
        node.prev = newNode;
        // 如果前面节点为空,则代表为头结点
        if (pre == null) {
            header = newNode;
        } else {
            pre.next = newNode;
        }
        size++;
    }


    /**
     * 移走线性链表的头结点
     */
    public void removeHeader() {
        if (header == null) {
            throw new NoSuchElementException("此节点不存在");
        }
        Node next = header.next;
        header.item = null;
        header.next = null;
        header = next;
        if (next == null) {
            tail = null;
        } else {
            header.prev = null;
        }
        size--;
    }

    /**
     * 移走线性链表的尾结点
     */
    public void removeTail() {
        if (tail == null) {
            throw new NoSuchElementException("此节点不存在");
        }

        Node prev = tail.prev;
        tail.item = null;
        tail.prev = null;
        tail = prev;
        if (prev == null) {
            header = null;
        } else {
            tail.next = null;
        }
        size--;
    }

    /**
     * 移走线性表中的任意一个节点
     *
     * @param index
     */
    public void remove(int index) {
        checkElementIndex(index);
        removeNode(getNodeByIndex(index));
    }

    /**
     * 删除元素
     *
     * @param node
     */
    private void removeNode(Node node) {
        Node pre = node.prev;
        Node next = node.next;
        node.item = null;
        if (pre == null) {
            header = next;
        } else {
            pre.next = next;
            node.prev = null;
        }
        if (next == null) {
            tail = pre;
        } else {
            next.prev = pre;
            node.next = null;
        }
        size--;
    }


    /**
     * @return 返回链表的长度
     */
    public int length() {
        return size;
    }

    /**
     * @return 链表是否为空
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * 清空线性表
     */
    public void clear() {
        for (Node current = header; current != null; ) {
            Node next = current.next;
            current.item = null;
            current.prev = null;
            current.next = null;
            current = next;
        }
        header = null;
        tail = null;
        size = 0;
    }

    /**
     * 获取链式线性表中索引为index处的元素
     *
     * @param index
     * @return 元素
     */
    public E get(int index) {
        checkElementIndex(index);
        return getNodeByIndex(index).item;
    }

    /**
     * 根据索引index获取指定位置的节点
     *
     * @param index
     * @return 元素
     */
    private Node getNodeByIndex(int index) {
        checkElementIndex(index);
        // 如果小于size/2,则从开始查找
        if (index < (size >> 1)) {
            // 从header节点开始遍历
            Node current = header;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
            // 如果小于size/2,则从结尾查找
        } else {
            Node current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("索引越界");
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public String toString() {
        if (header == null) {
            return "";
        }
        StringBuilder str = new StringBuilder("[");
        Node node = header;
        for (int i = 0; i < size; i++) {
            str.append(node.item);
            str.append(">");
            node = node.next;
        }
        str.append("]");
        return str.toString();
    }

    /**
     * 反转链表
     */
    public void reverse() {
        for (Node current = header; current != null; ) {
            Node prev = current.prev;
            Node next = current.next;
            current.prev = next;
            current.next = prev;
            current = next;
        }
        Node temp_header = header;
        header = tail;
        tail = temp_header;
    }

    public static void main(String[] args) {
        DoubleLinkList<String> list = new DoubleLinkList<String>();
        list.clear();
        System.out.println("从零开始:" + list.toString());

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.addHeader("7");
        list.insert("8", 4);
        System.out.println(list.length());
        System.out.println("子孙满堂:" + list.toString());

        list.removeHeader();
        System.out.println("去头:" + list.toString());
        list.removeTail();
        System.out.println("去尾巴:" + list.toString());
        list.remove(3);
        System.out.println("去中间:" + list.toString());

        list.reverse();
        System.out.println("颠三倒四:" + list.toString());

        list.clear();
        System.out.println("重归于零:" + list.toString());
    }
}

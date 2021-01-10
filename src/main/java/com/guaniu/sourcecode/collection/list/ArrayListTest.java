package com.guaniu.sourcecode.collection.list;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

/**
 * @Author: guaniu
 * @Description:
 *
 * @Date: Create in 15:19 2020/12/20
 * @Modified
 */
public class ArrayListTest {
    public static void main(String[] args) {
        // ArrayList 在 JDK1.7中默认容量10，JDK1.8中默认容量时0
        // ArrayList 是1.5倍扩容， 使用的是System.arraycopy(0,oldsrc,0,newsrc,length)

        // 如果同一个节点的链表数据节点个数 > TREEIFY_THRESHOLD=8且数组长度 >= MIN_TREEIFY_CAPACITY=64，
        // 则会将该链表进化位RedBlackTree,如果RedBlackTree中节点个数小于UNTREEIFY_THRESHOLD=6会退化为链表

        // HashMap是懒汉式创建的，只有在你put数据时候才会build
        // 单向链表转换为红黑树的时候会先变化为双向链表最终转换为红黑树，双向链表跟红黑树是共存的

        // JDK1.8链表尾插法，JDK1.7是头插法，原因是后插入的节点被访问概率更大
        ArrayList list = new ArrayList();

        // 初始容量10, 2倍扩容
        Vector vector = new Vector();
    }
}

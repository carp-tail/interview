package com.guaniu.sourcecode.string;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;

/**
 * @Author: guaniu
 * @Description: String 源码解读
 *
 * @summary
 * 1、String 是不可变字符串，因为其内部 char[] value,使用的是 final 修饰，在构造函数中已经初始化便不可以修改
 * String 的 "=" 操作是将String引用指向了一个新的String对象
 * a. “=” 右边是一个 String引用的话，没有创建新的对象，左边的引用指向右边引用的地址
 * b. "=" 右边是一个字符串常量的话，先检查字符串常量中是否存在该常量，存在的话即没有创建新的对象，将本引用指向该字符串常量的地址；
 *  不存在的话，新创建一个字符串常量对象，当前String引用指向它
 * 2、字符串常量池
 * 字符串常量池在java 1.6 中的实现实在方法区当中，java 1.7 开始字符串常量值直接在 堆中实现
 * 方法区知识一个概念，java 1.7 之前方法区的实现是 堆中的永久代（Permanent Generation）,java 8 之后已元数据空间实现
 * @Date: Create in 10:48 2020/12/19
 * @Modified
 */
class MyString{

    private final char[] value;
    private int hash;

    MyString(){
        this.value = new char[0];
    }

    void change(){
        // @Attention 本行代码会提示编译错误，表示value不能再指向新的引用
//        this.value = new char[1];
    }

    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) { // 只有在 hash 值为0且value长度大于0才会重新计算
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) { // 1、先判断是否指向同一地址
            return true;
        }
        if (anObject instanceof MyString) { // 2、运行时判断 是否是String类型
            MyString anotherString = (MyString)anObject;
            int n = value.length;
            if (n == anotherString.value.length) { // 3、判断字符串长度是否一致
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {              // 4、判断每个字符是否相同
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
}

public class StringTest {

    public static void main(String[] args) {
        String str1 = new String("abc"); // 堆中对象
        String str2 = "abc"; // 字符串常量池对象
        System.out.println("str1 == str2：" + (str1 == str2)); // “=字符串常量”的形式 和 "new" 的形式创建的字符串对象不是一个
        System.out.println("str1.equals(str2)：" + str1.equals(str2));

        String str3 = "abc"; // 字符串常量池已存在
        System.out.println("str2 == str3：" + (str2 == str3));
        System.out.println("str2.equals(str3)：" + str2.equals(str3));
    }
}

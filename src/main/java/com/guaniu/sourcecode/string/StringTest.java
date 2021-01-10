package com.guaniu.sourcecode.string;

/**
 * @Author: guaniu
 * @Description: String 源码解读
 *
 * @Summary
 * 1、String 是不可变字符串，因为其内部 char[] value,使用的是 final 修饰，在构造函数中已经初始化便不可以修改
 * String 的 "=" 操作是将String引用指向了一个新的String对象
 * a. “=” 右边是一个 String引用的话，没有创建新的对象，左边的引用指向右边引用的地址
 * b. "=" 右边是一个字符串常量的话，先检查字符串常量中是否存在该常量，存在的话即没有创建新的对象，将本引用指向该字符串常量的地址；
 *  不存在的话，新创建一个字符串常量对象，当前String引用指向它
 *
 * 2、字符串常量池
 * 字符串常量池在java 1.6 中的实现实在方法区当中，java 1.7 开始字符串常量值直接在 堆中实现
 * 方法区知识一个概念，java 1.7 之前方法区的实现是 堆中的永久代（Permanent Generation）,java 8 之后已元数据空间实现
 *
 * 3、String 的"+"操作使用了StringBuilder做优化
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
//        String str1 = new String("abc"); // 堆中对象复制了字符串常量池的对象
//        String str2 = "abc"; // 字符串常量池对象
//        // 此时str1创建了2个对象(从常量池中创建了“abc”并复制到堆中)，st2没有创建，反过来的话str2创建1个(常量池中创建"abc")，str1创建1个（常量池"abc"的复制）
//
//        System.out.println("str1 == str2：" + (str1 == str2)); // “=字符串常量”的形式 和 "new" 的形式创建的字符串对象不是一个
//        System.out.println("str1.equals(str2)：" + str1.equals(str2));
//
//        String str3 = "abc"; // 字符串常量池已存在
//        System.out.println("str2 == str3：" + (str2 == str3));
//        System.out.println("str2.equals(str3)：" + str2.equals(str3));


        String str4 = "赵钱";
        String str5 = "孙李";
        String str6 = "赵钱孙李";
        System.out.println("str6.equals(str4 + str5):" + (str6.equals(str4 + str5)));
        System.out.println("str6 = str4 + str5:" + (str6 == str4 + str5));              // false 通过StringBuilder.append(char c)进行优化,StringBuilder的toString方法返回的是 new 的String对象
        System.out.println("str6 = \"赵钱\" + \"孙李\":" + (str6 == "赵钱" + "孙李"));     // true 通过字符串常量池进行优化

        String str7 = "abc" + "d";
        String str8 = "ab" + "cd";
        System.out.println("str7 == str8:" + (str7 == str8));
    }
}

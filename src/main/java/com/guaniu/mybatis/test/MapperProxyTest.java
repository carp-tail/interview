package com.guaniu.mybatis.test;

import com.google.common.collect.Maps;
import org.apache.ibatis.annotations.Select;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: guaniu
 * @Description: 测试 通过jdk的反射机制完成 Mapper 接口的代理调用
 * @Date: Create in 11:40 2020/12/16
 * @Modified
 */

interface UserMapper{
    @Select("select * from user")
    List<User> selectAllUser();

    @Select("select * from user where id = #{id}")
    User selectUserById(Integer id);

    @Select("select * from user where name = #{name} and age = #{age}")
    List<User> selectUserByNameAndAge(String name, Integer age);
}
public class MapperProxyTest {
    public static void main(String[] args) {
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(UserMapper.class.getClassLoader(), new Class[]{UserMapper.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Annotation annotation = method.getAnnotation(Select.class);
                Annotation[] annotations = method.getDeclaredAnnotations();
                if (annotations != null && annotations.length > 0){
                    for (Annotation annotation : annotations){
                        String type = annotation.annotationType().getTypeName();
                        if (annotation instanceof Select){
                            String[] values = ((Select) annotation).value();
                            System.out.println(type + ": " + Arrays.toString(values));
                            if (args != null){
                                System.out.print("parameters:");
                                for (int i = 0; i < args.length; i++){
                                    Object o = args[i];
                                    System.out.print(o.toString() + " (" + o.getClass().getName() + ")" + (i==args.length-1 ? "\n" : ", "));
                                }
                            }
                        }
                    }
                    Map<String, Object> nameMap = buildMethodParamNameMap(method, args);
                }
                return null;
            }
        });

        userMapper.selectAllUser();
        userMapper.selectUserById(1);
        userMapper.selectUserByNameAndAge("李四",1);
    }

    static Map<String,Object> buildMethodParamNameMap(Method method, Object[] args){
        Map<String,Object> nameMap = Maps.newHashMap();
        Parameter[] parameters = method.getParameters();
        int[] index = {0};
        if (parameters != null){
            Arrays.asList(parameters).forEach(parameter -> {
                nameMap.put(parameter.getName(), args[index[0]++]);
            });
        }
        return nameMap;
    }
}

package com.landmine.code;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import lombok.ToString;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author: lanrish
 * date  : 2019-07-26
 * desc  :
 * <pre>
 *     创建一个新的类
 * </pre>
 */
public class CreateNewClassDemo {
    public static void main(String[] args) throws CannotCompileException, NotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("com.landmine.code.Student");
        ClassFile ccFile = ctClass.getClassFile();
        ConstPool constPool = ccFile.getConstPool();

        AnnotationsAttribute classAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation("lombok.ToString", constPool);
        classAttr.addAnnotation(annotation);
        ccFile.addAttribute(classAttr);

        //创建属性
        CtField field1 = CtField.make("private String name;", ctClass);
        CtField field2 = CtField.make("private int age;", ctClass);
        ctClass.addField(field1);
        ctClass.addField(field2);

        //创建方法
        CtMethod setName = CtNewMethod.make("public void setName(String name){this.name = name;}", ctClass);
        CtMethod getName = CtNewMethod.make("public String getName(){return this.name;}", ctClass);
        ctClass.addMethod(setName);
        ctClass.addMethod(getName);


        //添加构造器
        CtConstructor  constructor =  new CtNewConstructor().make("" +
                "public Student(int age, String name){" +
                "   this.age = age;" +
                "   this.name = name;" +
                "}",ctClass);

        ctClass.addConstructor(constructor);
        ctClass.writeFile("E:\\IdeaProjects\\StudyDemo\\JavassitDemo\\target\\classes\\");
        Class<?> clazz = ctClass.toClass();
        Constructor cons = clazz.getDeclaredConstructor(int.class,String.class);
        Object o = cons.newInstance(24, "lanrish");
        System.out.println(clazz.getCanonicalName());
        Method getMethod = clazz.getMethod("getName");
        System.out.println(getMethod.invoke(o));
        Method toStringMethod = clazz.getMethod("toString");
        System.out.println(toStringMethod.invoke(o));


    }
}

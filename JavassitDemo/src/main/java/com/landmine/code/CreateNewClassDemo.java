package com.landmine.code;

import javassist.*;

/**
 * author: lanrish
 * date  : 2019-07-26
 * desc  :
 * <pre>
 *     创建一个新的类
 * </pre>
 */
public class CreateNewClassDemo {
    public static void main(String[] args) throws CannotCompileException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("com.landmine.code.Student");
        //创建属性
        CtField field1 = CtField.make("private String name", ctClass);
        CtField field2 = CtField.make("private int age", ctClass);
        ctClass.addField(field1);
        ctClass.addField(field2);

        //创建方法
        String src;
        CtMethod setName = CtNewMethod.make("public void setName(String name){this.name = name;}", ctClass);
        CtMethod getName = CtNewMethod.make("public String getName(){return name;}", ctClass);
        ctClass.addMethod(setName);
        ctClass.addMethod(getName);

        //添加构造器
        CtConstructor constructor = new CtConstructor(new CtClass[]{CtClass.intType, pool.get("java.lang.String")}, ctClass);
        constructor.setBody("this.num= num; this.name");
    }
}

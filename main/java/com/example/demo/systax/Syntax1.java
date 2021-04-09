package com.example.demo.systax;


import com.example.demo.*;

public class Syntax1 {
    public static  void main(String[] args) {
        /**
         * 1.Lamdba 表达式的基础语法
         * 2. lamdba是一个匿名函数
         * 3.函数包括 返回值类型 方法名称  参数列表 方法体
         *   (因为是一个匿名函数 所有没有方法名称  返回值类型)
         *   但是lamdba只需要 参数列表 方法体
         *   ()用来描述参数列表
         *   {}用来描述方法体
         *   ->Lamdba运算符 读作goes to
         *   *
         *
         */
        //无参无返回
        LamdbaNoneReturnNoneParameter lamdba=()->{
            System.out.println("无参无返回");
        };
        lamdba.test();
        //无参数有返回值
        LamdbaStringReturnNoneParamter lamdba2=()->{
            System.out.println("无参数有返回值");
            return 100;
        };
        lamdba2.test();
        //单个参数的无返回
        LamdbaNoneReturnSingParamter lamdba3=(int b)->{
            System.out.println("单个参数的无返回"+b);
        };
        lamdba3.test(10);
        //单个参数有返回值
        LamdbaStringReturnSingParamter lamdba4=(int b)->{
            System.out.println("单个参数的无返回"+b);
            return b;
        };
        lamdba4.test(10);
        //多个参数的无返回
        LamdbaNoneReturnMutipleParamter lamdba5=(int b,int a)->{
            System.out.println("多个参数的无返回"+b);
        };
        lamdba5.test(10,100);

        //多个参数有返回值
        LamdbaStringReturnMutipleParamter lamdba6=(int b,int a)->{
            System.out.println("多个参数有返回值"+b);
            return b+a;
        };
        int a=lamdba6.test(10,100);
    }

    @FunctionalInterface
    public interface LamdbaNoneReturnMutipleParamter {
        void test(int a,int b);
    }

    @FunctionalInterface
    public interface LamdbaNoneReturnNoneParameter {
        void test();
    }

    @FunctionalInterface
    public interface LamdbaNoneReturnSingParamter {
        void test(int a);
    }

    @FunctionalInterface
    public interface LamdbaStringReturnMutipleParamter {
        int test(int a,int b);
    }

    @FunctionalInterface
    public interface LamdbaStringReturnNoneParamter {
        int test();
    }

    @FunctionalInterface
    public interface LamdbaStringReturnSingParamter {
        int test(int a);
    }


}


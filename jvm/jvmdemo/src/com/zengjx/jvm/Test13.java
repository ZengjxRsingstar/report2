package com.zengjx.jvm;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author zengjx
 * @Company zengjx
 * @Date 2019/10/24  15:51
 * @Version V1.0
 */
//Atomic  变量自增运算测试
//
public class Test13 {
private   static   volatile    int   count =0;//volatile 不具有原子性
private  static AtomicInteger  race =new AtomicInteger(0);
   public   static  void   increase(){
       race.incrementAndGet();
       System.out.println("---"+count++);
   }
   private   static   final   int THREADS_COUNT=20;
    public static void main(String[] args) {

      Thread[]  threads =new Thread[THREADS_COUNT];

        for (int i = 0; i <THREADS_COUNT ; i++) {
            threads[i]= new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <10000 ; j++) {
                        increase();
                    }

                }
            });
            threads[i].start();
        }
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println("race ="+race);//race =200000
        //---199994
        //---199995
        //---199996
        //---199997   int  的输出错误
        //race =200000 AtomicInteger 输出正确
    }
}

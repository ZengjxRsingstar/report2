package com.zengjx.jvm;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author zengjx
 * @Company zengjx
 * @Date 2019/10/24  12:33
 * @Version V1.0
 */
public class FinalizeEsccapeGC {

   public   static   FinalizeEsccapeGC   SAVE_HOKE=null;
    public static void main(String[] args) throws InterruptedException {
     SAVE_HOKE =new FinalizeEsccapeGC();
     SAVE_HOKE=null;
     System.gc();
     Thread.sleep(500);

     if(SAVE_HOKE!=null){
         SAVE_HOKE.isAlive();
     }else{
         System.out.println("  i  no   im  gone" );
     }


        SAVE_HOKE=null;
        System.gc();
        Thread.sleep(500);

        if(SAVE_HOKE!=null){
            SAVE_HOKE.isAlive();
        }else{
            System.out.println("  no   iam  gone ???");
        }
    }
    public    void   isAlive(){
        System.out.println("i  am  isALive");

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize  method  run ");
        FinalizeEsccapeGC.SAVE_HOKE=this;
    }
}

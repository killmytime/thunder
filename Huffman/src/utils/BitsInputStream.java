package utils;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class BitsInputStream {
    private InputStream inputStream;//输入流
    private int currentBytes;//当前位数
    private int number;//当前位总数
    private boolean ended;//是否结束
    //初始化
    public BitsInputStream(InputStream inputStream){
        this.inputStream=inputStream;
        this.number=0;
        this.ended=false;
    }
    //Byte读入流
    private int read() throws IOException{
         if (ended){
             return -1;
         }
         if (number==0){
             currentBytes=inputStream.read();
             if (currentBytes==-1){
                 ended=true;
                 return -1;
             }
             number=8;
         }
         number--;
         //高位补0
         return currentBytes>>>number&1;
    }
    //读完
    public int ended()throws IOException{
        int result=read();//尝试读入
        if (result!=-1){
            return result;
        }else {
           // System.out.println("读完了");
           throw new EOFException("End");
        }
    }
    //关闭输入流
    public void close() {
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new IllegalStateException("Could not close BinaryRead", e);
        }
    }
}

package utils;

import java.io.IOException;
import java.io.OutputStream;

public class BitsOutputStream {
    private OutputStream outputStream;//输出流
    private int currentType;//当前位
    private int currentBits;//当前位数
    //初始化
    public BitsOutputStream(OutputStream out){
        if (out==null){
            throw new NullPointerException("output is null");
        }
        this.outputStream=out;
        this.currentBits=0;
        this.currentType=0;
    }
    //Bits输出流
    public void write(int bits) throws IOException{
        currentType=currentType<<1|bits;
        currentBits++;
        if (currentBits==8){
            outputStream.write(currentType);
            currentBits=0;
        }
    }
    //关闭输出流
    public void close()  {
        try {
            //尝试输出
            while (currentBits != 0) {
                write(1);
            }
            outputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

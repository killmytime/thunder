package control;

import Data.FrequencyList;
import utils.BitsOutputStream;

import java.io.*;

import static java.util.Arrays.fill;

class FileCompress {
    //重新定义更为安全的缓冲流
    FileCompress(InputStream in,OutputStream out) throws IOException{
        InputStream inputStream = new BufferedInputStream(in);
        BitsOutputStream outputStream = new BitsOutputStream(new BufferedOutputStream(out));
        compress(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
    }
    private void compress(InputStream inputStream, BitsOutputStream outputStream)throws IOException{
        int[] frequencyTable=new int[257];
        fill(frequencyTable,1);
        FrequencyList frequencyList=new FrequencyList(frequencyTable);
        Encoder encoder=new Encoder(outputStream);
       encoder.setCodeTree(frequencyList.buildCodeTree());
       int i=0;
       while (true){
            int block=inputStream.read();
            if (block==-1){
                break;
            }
            encoder.write(block);
            frequencyList.increment(block);
            i++;
            if (i<262144&&isPowerOf2(i)||i%262144==0){

            encoder.setCodeTree( frequencyList.buildCodeTree());
            }
            if (i%262144==0) {
                frequencyList = new FrequencyList(frequencyTable);
            }
       }
       encoder.write(256);

    }
    //是不是2的多少次方
    private static boolean isPowerOf2(int x) {
        return x > 0 && (x & -x) == x;
    }
}

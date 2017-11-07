package control;

import Data.FrequencyList;
import utils.BitsInputStream;

import java.io.*;
import java.util.Arrays;

class FileDecompress {
    FileDecompress(InputStream in,OutputStream out) throws IOException{
        BitsInputStream inputStream = new BitsInputStream(new BufferedInputStream(in));
        OutputStream outputStream = new BufferedOutputStream(out);
        decompress(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
    }
    //逆向
    private void decompress(BitsInputStream bitsInputstream, OutputStream outputStream) throws IOException{
         int[] frequencyTable=new int[257];
        Arrays.fill(frequencyTable,1);
         FrequencyList frequencyList=new FrequencyList(frequencyTable);
        Decoder decompressDecoder=new Decoder(bitsInputstream);
        decompressDecoder.setCodeTree(frequencyList.buildCodeTree());
        int i=0;
        while (true){
            int block=decompressDecoder.read();
            if (block==256){
                break;
            }
            outputStream.write(block);
            frequencyList.increment(block);
            i++;
            if (i<262144&&isPowerOf2(i)||i%262144==0){
                decompressDecoder.setCodeTree(frequencyList.buildCodeTree());
            }
            if (i%262144==0){
                frequencyList=new FrequencyList(frequencyTable);
            }
        }
    }
    //判断是不是正数，2的多少次方
    private boolean isPowerOf2(int x){
        return x>0&&(x&-x)==x;
    }
}

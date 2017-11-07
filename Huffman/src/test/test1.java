package test;

import control.CompressTrim;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;


public class test1 {
    public static void main(String[] args) throws IOException {
        File file = new File("C:/Users/leiwe/Desktop/Test Cases");
        if (file.isDirectory()) {
            //随便写的
            //CompressTrim compressTrim=new CompressTrim("1","2");
            File[] files = file.listFiles();
            assert files != null;
            for (File file1 : files) {
//                String string = files[i].getName() + 1;
//                long begin=System.nanoTime();
                //File file1 = new File("C:/Users/leiwe/Desktop/Test Cases/test1exam/"+string);
                //compressTrim.Trim(files[i], file1);
               // new CompressTrim("C:/Users/leiwe/Desktop/Test Cases/test1 - single file/" + file1.getName(), "C:/Users/leiwe/Desktop/Test Cases/test1exam");
                //new CompressTrim("C:/Users/leiwe/Desktop/Test Cases/test2 - folder/1/" + file1.getName(), "C:/Users/leiwe/Desktop/Test Cases/test2exam");
                //new CompressTrim("C:/Users/leiwe/Desktop/Test Cases/test4 - large file/" + file1.getName(), "C:/Users/leiwe/Desktop/Test Cases/test4exam");
                //new CompressTrim("C:\\Users\\leiwe\\Desktop\\Test Cases\\test3 - empty file and empty folder/" + file1.getName(), "C:/Users/leiwe/Desktop/Test Cases/test3exam");
                new CompressTrim("C:/Users/leiwe/Desktop/Test Cases/"+file1.getName(),"C:/Users/leiwe/DeskTop/test");
//                long end=System.nanoTime();
//                long runtime=end-begin;
//                String str;
//                double rate=file1.length()/files[i].length();
//                NumberFormat nf  =  NumberFormat.getPercentInstance();
//                nf.setMinimumFractionDigits( 1 );
//                str  =  nf.format(rate);
//                System.out.print(files[i].getName()+"||"+files[i].length()+"||"+file1.length());
//                System.out.println();

            }
        }
    }
}

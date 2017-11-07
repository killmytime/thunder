package test;

import control.DeCompressTrim;

import java.io.File;
import java.io.IOException;

public class test2 {
    public static void main(String[] args) throws IOException {
        File file=new File("C:/Users/leiwe/Desktop/Test Cases/test4exam");
        if (file.isDirectory()){
            File[] files=file.listFiles();
            assert files != null;
            for (File file1:files){
//                System.out.println(file1.getName());
//                if (file1.getName().lastIndexOf(".lwj")!=-1) System.out.println("lwj格式");
//                else System.out.println("666");

                //new DeCompressTrim("C:/Users/leiwe/Desktop/Test Cases/test1exam/"+file1.getName(),"C:/Users/leiwe/Desktop/Test Cases/test1exam1");
               // new DeCompressTrim("C:/Users/leiwe/Desktop/Test Cases/test3exam/"+file1.getName(),"C:/Users/leiwe/Desktop/Test Cases/test3exam3");
                new DeCompressTrim("C:/Users/leiwe/Desktop/Test Cases/test4exam/"+file1.getName(),"C:/Users/leiwe/Desktop/Test Cases/test4exam4");
            }
        }
    }
}

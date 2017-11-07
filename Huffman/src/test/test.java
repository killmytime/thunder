package test;

import java.io.File;

public class test {
    public static void main(String[] args) {
        File file=new File("F:\\learning\\DS\\PJ\\Project 1\\Test Cases\\test2 - folder");
        File[] files=file.listFiles();
        for (File afile:files){
            System.out.println(afile.getName());
        }
    }
}

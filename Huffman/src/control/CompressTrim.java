package control;

import GUI.Message;

import java.io.*;
import java.util.Arrays;

public class CompressTrim {
    private File input;
    private File output;
    private OutputStream outputStream;
    private InputStream inputStream;
    private String inputName;
    private File outFile;
    private int state = 0;

    public CompressTrim(String in, String out) throws IOException {
        input = new File(in);
        output = new File(out);
        if (!input.exists()) {
            new Message("请选择一个您要操作的项");
        }
//        else if (!input.isDirectory()&&input.length()==0){
//            new Message("你选择的文件无有效内容");
//         }
        else if (!output.isDirectory() && !output.exists()) {
            new Message("请选择正确的输出路径");
        } else {
            inputName = input.getName();
            String outfile;
            //创建文件
            if (!input.isDirectory()) {
                outfile = CreateFile(out, getName(inputName));
            } else {
                outfile = CreateFile(out, inputName);

            }
            outFile = new File(outfile);
            outputStream = new FileOutputStream(outFile);
            long begin = System.currentTimeMillis();
            Trim(input, output);
            long end = System.currentTimeMillis();
            long runtime = end - begin;
            System.out.println("文件夹名"+input.getName() + "   " + runtime+"毫秒");
            outputStream.close();
            state = 1;
        }
    }

    private void Trim(File input, File output) throws IOException {
        boolean valid = input != null && input.exists() && output != null && output.exists() && output.isDirectory();
        if (valid && input.isDirectory()) {
            File[] files = input.listFiles();
            //能走到这一步，必定file不为null,不妨去声明一下
            assert files != null;
            long[] filesNumber = new long[files.length];
            //System.out.println("文件数量");
            byte[] folderName = inputName.getBytes();
            /* 这里+100是为了不让指针越界，后面相关位置对应+100，-100,同时也区分了文件夹的解压和文件的解压
            因为文件名有的蛮长的，不过100应该够了
            虽然具体为什么会越界不是很清楚，不过这样一来就没事了 */
            //文件夹名长度
            int nameLength = folderName.length + 100;
            //System.out.println(nameLength);
            outputStream.write(intToByte4(nameLength));
            //文件数量
            int fileNumber = files.length;
            outputStream.write(intToByte4(fileNumber));
            //文件名
            outputStream.write(folderName);
            if (files.length != 0) {
                String notEmptyDirectory="111";
                byte[] notEmpty=notEmptyDirectory.getBytes();
                System.out.println(Arrays.toString(notEmpty));
                outputStream.write(notEmpty);
                //把文件夹里的每个遍历一遍，如果又遇到文件夹就进去遍历
                for (int i = 0; i < files.length; i++) {
                    //System.out.println(i);
                    Trim(files[i], this.output);
                    filesNumber[i] = this.outFile.length();
                }
                outputStream = new FileOutputStream(outFile, true);
                for (long aFilesNumber : filesNumber) {
                    byte[] fileBegin = LongToBytes(aFilesNumber);
                    System.out.println(aFilesNumber);
                    outputStream.write(fileBegin);
                }
                //后面对应除以8
                int filesNumberLength = filesNumber.length * 8;
                byte[] fileEnd = intToByte4(filesNumberLength);
                outputStream.write(fileEnd);
            } else {
                //LwjEmptyDirectory
                String emptyDirectory="LED";
                byte[] empty=emptyDirectory.getBytes();
                outputStream.write(empty);
            }
        } else if (valid && !input.isDirectory()) {
            inputStream = new FileInputStream(input);
            String inputName = input.getName();
            byte[] fileName = inputName.getBytes();
            int nameLength = fileName.length;
            outputStream = new FileOutputStream(outFile, true);
            outputStream.write(intToByte4(nameLength));
            outputStream.write(fileName);
            if (input.length() != 0) {
                String notEmptyFile="111";
                byte[] notEmpty=notEmptyFile.getBytes();
                outputStream.write(notEmpty);
//            long begin=System.currentTimeMillis();
                new FileCompress(inputStream, outputStream);
//            long end=System.currentTimeMillis();
//            long runtime=end-begin;
//            String str;
//            double rate=1.0*outFile.length()/input.length();
//            NumberFormat nf  =  NumberFormat.getPercentInstance();
//            nf.setMinimumFractionDigits( 1 );
//            str  =  nf.format(rate);
//            System.out.print("文件名为"+inputName+"原文件大小"+input.length()+"压缩文件大小"+outFile.length()+"   运行时间为"+runtime+"毫秒  ");
//            System.out.println("压缩率为"+str+"  ");
                System.out.println("文件名为" + inputName + "压缩完成");
            }else {
                //LwjEmptyFile
                String emptyFile="LEF";
                byte[] empty=emptyFile.getBytes();
                outputStream.write(empty);
            }
        }
    }

    //创建文件的判断（文件夹/文件的不同情况）
    private static String CreateFile(String filePath, String fileName) {
        String temp = filePath + "/" + fileName + ".lwj";
        File file = new File(temp);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("创建失败");
            e.printStackTrace();
        }
        return temp;
    }

    ////转化出成字节数组便于操作，写入（对齐读取，放入）
    private static byte[] intToByte4(int i) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (i & 0xFF);
        targets[2] = (byte) (i >> 8 & 0xFF);
        targets[1] = (byte) (i >> 16 & 0xFF);
        targets[0] = (byte) (i >> 24 & 0xFF);
        return targets;
    }
    //获得没有后缀的文件名(美观起见)
    private String getName(String in) {
        //对于tar.gz不准，不过将就一下吧
        if (in.lastIndexOf(".") != -1) {
            int dotLastIndex = in.lastIndexOf(".");
            return in.substring(0, dotLastIndex);
        } else return in;
    }

    //长整型转化为字节数组，便于操作，写入 8*8=64
    private byte[] LongToBytes(long values) {
        byte[] buffer = new byte[8];
        for (int i = 0; i < 8; i++) {
            int offset = 64 - (i + 1) * 8;
            buffer[i] = (byte) ((values >> offset) & 0xff);
        }
        return buffer;
    }

    public int getState() {
        return state;
    }
}

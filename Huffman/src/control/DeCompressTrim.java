package control;

import GUI.Message;

import java.io.*;
import java.util.Arrays;

public class DeCompressTrim {
    private File input;
    private File output;
    private InputStream inputStream;
    private int state;

    public DeCompressTrim(String in, String out) throws IOException {
        if (in.lastIndexOf(".lwj") == -1) {
            new Message("您选择的文件不是lwj类型");
            // System.out.println("您选择的文件不是lwj类型");
        } else {
            this.input = new File(in);
            this.output = new File(out);
            if (!input.exists()) {
                throw new IllegalArgumentException("Input file is not exist");
            } else {
                this.inputStream = new FileInputStream(input);
                //System.out.println(input.getName());
                long begin = System.currentTimeMillis();
                Trim(input, output, 0, input.length());
                long end = System.currentTimeMillis();
                long runtime = end - begin;
                System.out.println("文件名" + input.getName() + "  " + "解压时间" + runtime + "毫秒  ");
                state = 1;
            }
        }
    }

    private void Trim(File input, File output, long CurrentLocation, long NextLocation) throws IOException {
        byte[] judge = new byte[3];
        if (this.output.exists()) {
            if (this.output.isDirectory()) {
                //文件名长度
                this.inputStream = new FileInputStream(input);
                inputStream.skip(CurrentLocation);
                byte[] filenameLength = new byte[4];
                inputStream.read(filenameLength);
                int length = byte4ToInt(filenameLength);

                //System.out.println(length);
                //是目录
                if (length > 100) {
                    //当前目录文件数目
                    this.inputStream = new FileInputStream(input);
                    inputStream.skip(4 + CurrentLocation);
                    byte[] fileNumberByte = new byte[4];
                    inputStream.read(fileNumberByte);
                    int fileNumber = byte4ToInt(fileNumberByte);
                    //文件夹名字
                    byte[] filename = new byte[length - 100];
                    inputStream.read(filename);
                    String fileName = new String(filename);
                    File create = new File(output.getAbsolutePath() + "/" + fileName);
                    if (!create.exists()) {
                        create.mkdirs();
                    }
                    this.inputStream = new FileInputStream(input);
                    inputStream.skip(CurrentLocation+8+fileName.length());
                    inputStream.read(judge);
                    System.out.println(Arrays.toString(judge));
                    if (Arrays.equals(judge,"111".getBytes())) {
                        System.out.println(1);
                        byte[] filesNumber = new byte[4];
                        this.inputStream = new FileInputStream(input);
                        inputStream.skip(NextLocation - 4);
                        inputStream.read(filesNumber);
                        int filesLocation = byte4ToInt(filesNumber);
                        this.inputStream = new FileInputStream(input);
                        inputStream.skip(NextLocation - 4 - filesLocation);
                        long[] Locations = new long[filesLocation / 8];
                        for (int i = 0; i < Locations.length; i++) {
                            byte[] Location = new byte[8];
                            inputStream.read(Location);
                            Locations[i] = BytesToLong(Location);
                        }

                        inputStream.close();
                        //循环递归
                        for (int i = 0; i < fileNumber; i++) {
                            if (i == 0) {
                                Trim(this.input, create, CurrentLocation + 11 + filename.length, Locations[0]);
                                continue;
                            }
                            if (i < fileNumber - 1) {
                                Trim(this.input, create, Locations[i - 1], Locations[i + 1]);
                            } else {
                                Trim(this.input, create, Locations[i - 1], 0);
                            }
                        }
                    }
                } else {
                    byte[] filename = new byte[length];
                    inputStream.read(filename);
                    System.out.println(length);
                    Arrays.toString(filename);
                    String addString = new String(filename);
                    String createPath = output.getAbsolutePath() + "/" + addString;
                    File create = new File(createPath);
                    System.out.println(createPath);
                    //path错误
                    if (!create.exists()) {
                        create.createNewFile();
                    }
                    inputStream.read(judge);
                    if (Arrays.equals(judge, "111".getBytes())) {
                        OutputStream outputStream = new FileOutputStream(create);
                        new FileDecompress(this.inputStream, outputStream);
                    }
                }
            }
        }

    }

    private static int byte4ToInt(byte[] bytes) {
        int b0 = bytes[0] & 0xFF;
        int b1 = bytes[1] & 0xFF;
        int b2 = bytes[2] & 0xFF;
        int b3 = bytes[3] & 0xFF;
        return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
    }


    private long BytesToLong(byte[] buffer) {
        long values = 0;
        for (int i = 0; i < 8; i++) {
            values <<= 8;
            values |= (buffer[i] & 0xff);
        }
        return values;
    }

    public int getState() {
        return state;
    }
}

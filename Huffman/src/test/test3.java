package test;

public class test3 {
    public static void main(String[] args) {
        String emptyDirectory="LED";
        byte[] empty=emptyDirectory.getBytes();
        for (int i=0;i<empty.length;i++){
            System.out.println(empty[i]+"  "+i);
        }
    }
//    private static byte[] stringToByte4(String string){
//        byte[] targets=new byte[4];
//        targets=string.getBytes();
//        return targets;
//    }
}

package util;

import java.io.UnsupportedEncodingException;

public class Transfer {
    public String transfer(String str) throws UnsupportedEncodingException {
        byte[] strBetween=str.getBytes("8859_1");
        String string=new String(strBetween,"GB2312");
        return string;
    }
}

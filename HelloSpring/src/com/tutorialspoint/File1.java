package com.tutorialspoint;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;


public class File1 {
	public static void main(String[] args) {
		try {
			byte[] b = File1.encode1();
			System.out.println(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static byte[] encode1() throws Exception{
		String filePath = "G:/二维码/qrCode.png";
        File file = new File(filePath);
        //获得指定文件的byte[]
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1000];
        //public void write(byte []b, int of, int len) 将指定字节数组中从偏移量 off 开始的 len 个字节写入此字节数组输出流。
        int n;
        while((n=in.read(b))!=-1){
        	int len = in.read(b);
        	System.out.println(len);
        	out.write(b, 0, n);
        }
        System.out.println(out);
        in.close();
        out.close();
        
        return out.toByteArray();
    }
}

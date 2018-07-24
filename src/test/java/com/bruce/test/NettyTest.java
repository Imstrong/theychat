package com.bruce.test;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NettyTest {
    @Test
    public void testReadAndWrite(){
        String str="abcde";
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());

        buffer.flip();
        byte[] buf=new byte[buffer.limit()];
        buffer.get(buf,2,2);
            System.out.println(new String(buf));

        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
    }
    @Test
    public void testDirectBuffer(){
        long start=System.currentTimeMillis();
        FileChannel cin=null;
        FileChannel cout=null;
        try{
            //使用直接缓冲区（内存映射文件）方式
            cin=FileChannel.open(Paths.get("E:\\迅雷下载","cn_visio_2010_x86_x64_dvd_516301.iso"),StandardOpenOption.READ);
            cout=FileChannel.open(Paths.get("E:\\迅雷下载","cn_visio_2010_x86_x64_dvd_516301_bak.iso"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
            MappedByteBuffer inbuffer=cin.map(FileChannel.MapMode.READ_ONLY,0,cin.size());
            MappedByteBuffer outbuffer=cout.map(FileChannel.MapMode.READ_WRITE,0,cin.size());
            byte[] buf=new byte[inbuffer.limit()];
            inbuffer.get(buf);
            outbuffer.put(buf);
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(cin!=null){
                try{
                    cin.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(cout!=null){
                try{
                    cout.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("map方式耗时 "+(end-start)+"毫秒");
    }
    @Test
    public void testDirectTransfer(){
        long start=System.currentTimeMillis();
        FileChannel cin=null;
        FileChannel cout=null;
        try{
            //使用直接缓冲区（内存映射文件）方式
            cin=FileChannel.open(Paths.get("E:\\迅雷下载","cn_visio_2010_x86_x64_dvd_516301.iso"),StandardOpenOption.READ);
            cout=FileChannel.open(Paths.get("E:\\迅雷下载","cn_visio_2010_x86_x64_dvd_516301_bak.iso"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
//            cout.transferFrom(cin,0,cin.size());
            cin.transferTo(0,cin.size(),cout);
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(cin!=null){
                try{
                    cin.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(cout!=null){
                try{
                    cout.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("transferFrom方式耗时 "+(end-start)+"毫秒");
    }
    @Test
    public void testInDirect(){
        long start=System.currentTimeMillis();
        FileInputStream fis=null;
        FileOutputStream fos=null;
        FileChannel cin=null;
        FileChannel cout=null;
        try{
            fis=new FileInputStream(new File("E:\\迅雷下载","cn_visio_2010_x86_x64_dvd_516301.iso"));
            fos=new FileOutputStream(new File("E:\\迅雷下载","cn_visio_2010_x86_x64_dvd_516301_bak.iso"));
            cin=fis.getChannel();
            cout=fos.getChannel();

            ByteBuffer bin=ByteBuffer.allocate(1024);
            while(cin.read(bin)!=-1){
                bin.flip();
                cout.write(bin);
                bin.clear();
            }
        }catch(Exception e){
            if(fis!=null){
                try{
                    fis.close();
                }catch(IOException e1){
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try{
                    fos.close();
                }catch(IOException e1){
                    e.printStackTrace();
                }
            }
            if(cin!=null){
                try{
                    cin.close();
                }catch(IOException e1){
                    e.printStackTrace();
                }
            }
            if(cout!=null){
                try{
                    cout.close();
                }catch(IOException e1){
                    e.printStackTrace();
                }
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("stream方式耗时 "+(end-start)+"毫秒");
    }
}

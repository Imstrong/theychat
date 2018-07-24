package com.bruce.theychat.hotswap;

public class ClassModifier {
    /**
     * class文件中常量池的起始偏移（4字节魔数，4字节版本号）
     */
    private static final int CONSTANT_POOL_COUNT_INDEX=8;
    /**
     * Constant_utf8_info常量池中此类常量的tag标识位的值
     */
    private static final int CONSTANT_Utf8_info=1;
    /**
     * 定义各类型常量所占常量池长度
     */
    private static final int[] CONSTANT_ITEM_LENGTH={-1,-1,-1,5,5,9,9,3,3,5,5,5,5};
    /**
     * 定义一个u1的长度（字节数）
     */
    private static final int u1=1;
    /**
     * 定义一个u2的长度（字节数）
     */
    private static final int u2=2;
    private byte[] classByte;
    public ClassModifier(byte[] classByte){
        this.classByte=classByte;
    }
    /**
     * 修改常量池中Constant_Utf8_info的值
     * @param oldStr 修改前的值
     * @param newStr 修改后的值
     */
    public byte[] modifyUTF8Constant(String oldStr,String newStr){
        int cpc=getConstantPoolCount();
        int offset=CONSTANT_POOL_COUNT_INDEX+u2;
        for(int i=0;i<cpc;i++){
            int tag=ByteUtils.bytes2Int(classByte,offset,u1);
            if(tag==CONSTANT_Utf8_info){
                int len=ByteUtils.bytes2Int(classByte,offset+u1,u2);
                offset+=(u1+u2);
                String str=ByteUtils.bytes2String(classByte,offset,len);
                if(str.equalsIgnoreCase(oldStr)){
                    byte[] strBytes=ByteUtils.string2Bytes(newStr);
                    byte[] strLen=ByteUtils.int2Bytes(newStr.length(),u2);
                    classByte=ByteUtils.bytesReplace(classByte,offset-u2,u2,strLen);
                    classByte=ByteUtils.bytesReplace(classByte,offset,len,strBytes);
                    return classByte;
                }else{
                    offset+=len;
                }
            }else{
                offset+=CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }
    public int getConstantPoolCount(){
        return ByteUtils.bytes2Int(classByte,CONSTANT_POOL_COUNT_INDEX,u2);
    }
}

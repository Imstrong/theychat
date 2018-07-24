package com.bruce.theychat.hotswap;

public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader(){
        super(HotSwapClassLoader.class.getClassLoader());
    }

    /**
     * 公开父类的defineClass，相当于覆写
     * @param classByte
     * @return
     */
    public Class loadByte(byte[] classByte){
        return defineClass(null,classByte,0,classByte.length);
    }
}

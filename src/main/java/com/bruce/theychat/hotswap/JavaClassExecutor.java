package com.bruce.theychat.hotswap;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class JavaClassExecutor {
    public static String execute(byte[] classByte){
        HackSystem.clearBuffer();
        ClassModifier cm=new ClassModifier(classByte);
        byte[] modiBytes=cm.modifyUTF8Constant("java/lang/System","com/bruce/theychat/hotswap/HackSystem");
        HotSwapClassLoader loader=new HotSwapClassLoader();
        Class clazz=loader.loadByte(modiBytes);
        try{
            Method method=clazz.getMethod("main",new Class[]{String[].class});
            method.invoke(null,new String[]{null});
        }catch(Throwable e){
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}

package com.wang.provider.jvm;

import com.sun.imageio.plugins.common.InputStreamAdapter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyByteClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(name));
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(null/*"com.wang.provider.jvm.BusServiceImpl"*/, b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

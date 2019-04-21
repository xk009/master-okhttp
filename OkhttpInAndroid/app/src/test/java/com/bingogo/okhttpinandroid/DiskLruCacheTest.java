package com.bingogo.okhttpinandroid;

import okhttp3.internal.Util;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.io.FileSystem;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class DiskLruCacheTest {

    private static final String KEY_URL = "url";

    public static void main(String[] args) {

        File cacheDir = new File("CacheDisk.tmp");
        DiskLruCache diskLruCache = DiskLruCache.create(FileSystem.SYSTEM, cacheDir, 1, 1, 50 * 50 * 1024);

//        putCache(diskLruCache);

        getCache(diskLruCache);
    }

    private static void getCache(DiskLruCache diskLruCache) {
        //获取缓存的内容
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(KEY_URL);
            if (snapshot != null) {
                //获取到输入流
                Source in = snapshot.getSource(0);
                //包装流
                BufferedSource source = Okio.buffer(in);
                System.out.println("read disk cache result:" + source.readString(Charset.forName("utf-8")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void putCache(DiskLruCache diskLruCache) {
        //添加一个缓存
        String KEY = "url";
        BufferedSink sink = null;
        DiskLruCache.Editor editor = null;
        try {
            editor = diskLruCache.edit(KEY_URL);
            if (editor != null) {
                //获取到输出流
                sink = Okio.buffer(editor.newSink(0));
                //然后就可以写入内容
                sink.writeString("我是写入到缓存的内容", Charset.forName("UTF-8"));
                editor.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                editor.abort();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            //最后关闭输出流
            Util.closeQuietly(sink);
        }
    }
}

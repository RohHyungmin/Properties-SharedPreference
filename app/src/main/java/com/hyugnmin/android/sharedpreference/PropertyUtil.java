package com.hyugnmin.android.sharedpreference;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by besto on 2017-03-21.
 */

public class PropertyUtil {
    private String PROP_FILE = "sp.properties";
    private String internalStorage;
    private static PropertyUtil instance = null;
    private static Context context;

    // 내부저장소 절대경로 가져오기 ( /data/data/패키지명/files )
    String internalStoragePath;
    final String propertyFile = "test.properties";

    //생성자가 호출될 때 internalStorage를 세팅해준다
    private PropertyUtil() {
        internalStorage = context.getFilesDir().getAbsolutePath();
    }

    public static PropertyUtil getInstance (Context ctx) {
        context = ctx;
        if(instance == null) {
            instance = new PropertyUtil();
        }

        return instance;
    }

    public void saveProperty(String key, String value){
        Properties prop = new Properties();
        prop.put(key, value);

        try {
            // 앱의 내부저장소/files/test.properties 파일을 저장
            FileOutputStream fos = new FileOutputStream(internalStoragePath + "/" + propertyFile);
            prop.store(fos, "comment"); // key=value
            // 저장 후 파일을 닫아준다
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        String value = "";

        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(internalStoragePath + "/" + propertyFile);
            prop.load(fis);
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        //prop.list(System.out); // 프로퍼티 목록 전체 나열하기

        value = prop.getProperty(key);

        return value;
    }
}

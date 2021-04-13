package com.muse.xiangta.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 保存读取SharedPreferences的内容
 */
public class SPHelper {

    // 加密数据的key
    private static final String KEY = "BEN";
    // 默认xml文件名
    private static final String PREFS_NAME = "prefs_name";

    /**
     * 加密
     *
     * @param value
     * @return
     */
    private static String encoderStr(String value) {
        String md5Key = EncryptUtil.md5Digest(KEY);
        return EncryptUtil.desEncoder(value, md5Key);
    }

    /**
     * 解密
     *
     * @param value
     * @return
     */
    private static String decoderStr(String value) {
        String md5Key = EncryptUtil.md5Digest(KEY);
        return EncryptUtil.desDecoder(value, md5Key);
    }

    /**
     * 保存数据
     *
     * @param c
     * @param prefsName
     * @param key
     * @param value
     */
    public static void setString(Context c, String prefsName, String key, String value) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, encoderStr(value));
        editor.commit();
    }

    /**
     * 保存数据
     *
     * @param c
     * @param key
     * @param value
     */
    public static void setString(Context c, String key, String value) {
        setString(c, PREFS_NAME, key, value);
    }

    /**
     * 读取数据
     *
     * @param c
     * @param prefsName
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context c, String prefsName, String key, String defValue) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return defValue.equals(value) ? value : decoderStr(value);
    }

    /**
     * 读取数据
     *
     * @param c
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context c, String key, String defValue) {
        return getString(c, PREFS_NAME, key, defValue);
    }

    /**
     * 读取数据(默认值是"")
     *
     * @param c
     * @param key 读取的名称
     * @return 读取的值
     */
    public static String getString(Context c, String key) {
        return getString(c, PREFS_NAME, key, "");
    }

    /**
     * 保存int类型数据
     *
     * @param c
     * @param prefsName
     * @param key
     * @param value
     */
    public static void setInt(Context c, String prefsName, String key, int value) {
        setString(c, prefsName, key, String.valueOf(value));
    }

    /**
     * 保存int类型数据
     *
     * @param c
     * @param key
     * @param value
     */
    public static void setInt(Context c, String key, int value) {
        setInt(c, PREFS_NAME, key, value);
    }

    /**
     * 读取int类型数据
     *
     * @param c
     * @param prefsName
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context c, String prefsName, String key, int defValue) {
        return Integer.valueOf(getString(c, prefsName, key, String.valueOf(defValue)));
    }

    /**
     * 读取int类型数据
     *
     * @param c
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context c, String key, int defValue) {
        return getInt(c, PREFS_NAME, key, defValue);
    }

    /**
     * 保存boolean值
     *
     * @param c
     * @param prefsName
     * @param key
     * @param value
     */
    public static void setBoolean(Context c, String prefsName, String key, boolean value) {
        setString(c, prefsName, key, String.valueOf(value));
    }

    /**
     * 保存boolean值
     *
     * @param c
     * @param key
     * @param value
     */
    public static void setBoolean(Context c, String key, boolean value) {
        setBoolean(c, PREFS_NAME, key, value);
    }

    /**
     * 读取boolean值
     *
     * @param c
     * @param prefsName
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context c, String prefsName, String key, boolean defValue) {
        return Boolean.valueOf(getString(c, prefsName, key, String.valueOf(defValue)));
    }

    /**
     * 读取boolean值
     *
     * @param c
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context c, String key, boolean defValue) {
        return getBoolean(c, PREFS_NAME, key, defValue);
    }

    /**
     * 保存double类型数据
     *
     * @param c
     * @param prefsName
     * @param key
     * @param value
     */
    public static void setDouble(Context c, String prefsName, String key, double value) {
        setString(c, prefsName, key, String.valueOf(value));
    }

    /**
     * 保存double类型数据
     *
     * @param c
     * @param key
     * @param value
     */
    public static void setDouble(Context c, String key, double value) {
        setDouble(c, PREFS_NAME, key, value);
    }

    /**
     * 读取double类型数据
     *
     * @param c
     * @param prefsName
     * @param key
     * @param defValue
     * @return
     */
    public static double getDouble(Context c, String prefsName, String key, double defValue) {
        return Double.valueOf(getString(c, prefsName, key, String.valueOf(defValue)));
    }

    /**
     * 读取double类型数据
     *
     * @param c
     * @param key
     * @param defValue
     * @return
     */
    public static double getDouble(Context c, String key, double defValue) {
        return Double.valueOf(getString(c, PREFS_NAME, key, String.valueOf(defValue)));
    }

    public static boolean setObjectToShare(Context context, Object object, String key) {
        SharedPreferences share = PreferenceManager.getDefaultSharedPreferences(context);
        if (object == null) {
            SharedPreferences.Editor editor = share.edit().remove(key);
            return editor.commit();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        String objectStr = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        try {
            baos.close();
            oos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = share.edit();
        editor.putString(key, objectStr);
        return editor.commit();
    }


    public static Object getObjectFromShare(Context context, String key) {
        SharedPreferences sharePre = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            String wordBase64 = sharePre.getString(key, "");
            // 将base64格式字符串还原成byte数组
            if (wordBase64 == null || wordBase64.equals("")) {
                // 不可少，否则在下面会报java.io.StreamCorruptedException
                return null;
            }
            byte[] objBytes = Base64.decode(wordBase64.getBytes(), Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            // 将byte数组转换成product对象
            Object obj = ois.readObject();
            bais.close();
            ois.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
package org.jing1578.basicapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import org.jing1578.basicapplication.utils.LogUtil;

/**
 * Created by jing1578 on 2016/12/9.
 *内容uri包括权限和路径,权限一般为包名.provider,路径为表所在路径
 * 内容uri例子:content://com.example.app.provider/table1("content://"+包名.provider+"/"+path)
 * Uri uri = Uri.parse("content://com.example.app.provider/table1")
 *内容 URI 的格式主要就只有以上两种，以路径结尾就表示期望访问该表中所有的数据，
 *以 id 结尾就表示期望访问该表中拥有相应 id 的数据。
 *
 * 通配符
 * 一个能够匹配任意表的内容 URI 格式就可以写成：content://com.example.app.provider/*
 * 一个能够匹配 table1 表中任意一行数据的内容 URI 格式就可以写成：content://com.example.app.provider/table1/#
 */



public class ContentResolverUtil {
    private static final String TAG=ContentResolverUtil.class.getSimpleName();

    public static void readContacts(Context context) {
        //添加权限<uses-permission android:name="android.permission.READ_CONTACTS" />
        Cursor cursor = null;
        try {
            // 查询联系人数据
            cursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            //query第一个参数为Uri uri
            //query第二个参数为查询的列表数组new  String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER}
            //query第三个参数为查询占位符  ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME= ?  and  ContactsContract.CommonDataKinds.Phone.NUMBER=?
            //query第四个参数为占位符值 new String[]{"张三","13177817586"};
            //query第五个参数查询结果排序方式

            StringBuffer stringBuffer=new StringBuffer();
            while (cursor.moveToNext()) {
            // 获取联系人姓名
                String displayName = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            // 获取联系人手机号
                String number = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                stringBuffer.append("联系人:"+displayName + ",号码:" + number).append("\n");
            }
            LogUtil.e(TAG,"联系人详情:"+stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * 插入一条数据例子
     * @param context
     */
    private void insert(Context context){
        Uri uri = Uri.parse("content://com.example.app.provider/table1");
        ContentValues values = new ContentValues();
        values.put("column1", "text");
        values.put("column2", 1);
        context.getContentResolver().insert(uri, values);
    }

    /**
     * 更新一条数据例子
     * @param context
     */
    private void  update(Context context){
        Uri uri = Uri.parse("content://com.example.app.provider/table1");
        ContentValues values = new ContentValues();
        values.put("column1", "");
        context.getContentResolver().update(uri, values, "column1 = ? and column2 = ?", new
                String[] {"text", "1"});
    }

    /**
     * 删除一条数据例子
     * @param context
     */
    private void delete(Context context) {
        Uri uri = Uri.parse("content://com.example.app.provider/table1");
        context.getContentResolver().delete(uri, "column2 = ?", new String[]{"1"});
    }

}

package org.jing1578.basicapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jing1578.baselibrary.utils.LogUtil;


/**
 * Created by jing1578 on 2016/12/15.
 * 构 建 出SQLiteOpenHelper 的实例之后，再调用它的 getReadableDatabase()或 getWritableDatabase()方法就能够创建数据库了，
 * 数据库文件会存放在/data/data/<package name>/databases/目录下
 */
//存储大量复杂的关系型数据
//    我们手机的短信程序中可能会有很多个会话，每个会话中又包含了很多条信息
//    内容，并且大部分会话还可能各自对应了电话簿中的某个联系人

//  //SQLite语句 它的数据类型很简单， integer 表示整型,real 表示浮点型，text 表示文本类型，blob 表示二进制类型。另外，建表语句中我们还
// //使用了 primary key 将 id 列设为主键，并用 autoincrement 关键字表示 id 列是自增长的
public class BookCategoryDB extends SQLiteOpenHelper {


    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";
    private Context mContext;

    public BookCategoryDB(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
//        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//创建表时,不写
//        //开发时这样做,先将已经存在的表删除掉，是因为如果在创建表时发现这张表已经存在了，就会直接报错
//        db.execSQL("drop table if exists Book");
//        db.execSQL("drop table if exists Category");
//        onCreate(db);
////        //正式时,保证数据不丢失
////        switch (oldVersion) {
////            case 1:
////                db.execSQL(CREATE_CATEGORY);
////            case 2:
////                db.execSQL("alter table Book add column category_id integer");
////            default:
////        }
    }


    public void deleteAndAddValue(BookCategoryDB dbHelper) {
        //   //数据库复杂操作使用事务
        // db.beginTransaction();// 开启事务
        // db.setTransactionSuccessful(); // 事务已经执行成功
        // db.endTransaction(); // 结束事务
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction(); // 开启事务
        try {
            db.delete("Book", null, null);
//        if (true) {
//// 在这里手动抛出一个异常，让事务失败
//            throw new NullPointerException();
//        }
            ContentValues values = new ContentValues();
            values.put("name", "Game of Thrones");
            values.put("author", "George Martin");
            values.put("pages", 720);
            values.put("price", 20.85);
            db.insert("Book", null, values);
            db.setTransactionSuccessful(); // 事务已经执行成功
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction(); // 结束事务
        }
        db.close();
    }

    public void addValue(BookCategoryDB dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        // 开始组装第一条数据
        values.put("name", "The Da Vinci Code");
        values.put("author", "Dan Brown");
        values.put("pages", 454);
        values.put("price", 16.96);
        db.insert("Book", null, values); // 插入第一条数据
        values.clear();
        // 开始组装第二条数据
        values.put("name", "The Lost Symbol");
        values.put("author", "Dan Brown");
        values.put("pages", 510);
        values.put("price", 19.95);
        db.insert("Book", null, values); // 插入第二条数据

//        db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
//                new String[] { "The Da Vinci Code", "Dan Brown", "454", "16.96" });
//        db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
//                new String[] { "The Lost Symbol", "Dan Brown", "510", "19.95" });
        db.close();
    }

    public void  updateValue(BookCategoryDB dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", 10.99);
        db.update("Book", values, "name = ?", new String[]{"The DaVinci Code" });
//        db.execSQL("update Book set price = ? where name = ?", new String[] { "10.99",
//                "The Da Vinci Code" });
        db.close();
    }

    public void deleteValue(BookCategoryDB dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Book", "pages > ?", new String[] { "500" });
//        db.execSQL("delete from Book where pages > ?", new String[] { "500" });
        db.close();
    }

    public void queryAll(BookCategoryDB dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // 查询Book表中所有的数据
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
//        Cursor cursor= db.rawQuery("select * from Book", null);
        if (cursor.moveToFirst()) {
            do {
        // 遍历Cursor对象，取出数据并打印
                String name = cursor.getString(cursor.
                        getColumnIndex("name"));
                String author = cursor.getString(cursor.
                        getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex
                        ("pages"));
                double price = cursor.getDouble(cursor.
                        getColumnIndex("price"));
                LogUtil.d("MainActivity", "book name is " + name);
                LogUtil.d("MainActivity", "book author is " + author);
                LogUtil.d("MainActivity", "book pages is " + pages);
                LogUtil.d("MainActivity", "book price is " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }


}

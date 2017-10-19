package org.jing1578.basicapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.jing1578.basicapplication.model.Book;
import org.jing1578.basicapplication.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jing1578 on 2016/12/22.
 */

public class DBAction {

    private static final String DB_NAME="BookStore.db";

    private static final int VERSION=2;

    private Context context;

    private BookCategoryDB dbHelper;
    private SQLiteDatabase db;

    private static DBAction basicApplicationDB;

    public DBAction(Context context) {
        this.context=context;
         dbHelper = new BookCategoryDB(context, DB_NAME, null,VERSION);
    }

    public synchronized static DBAction getInstance(Context context){
        if (basicApplicationDB==null){
            basicApplicationDB=new DBAction(context);
        }
        return basicApplicationDB;
    }

    public void saveBook(Book book){
        db=dbHelper.getWritableDatabase();
        if (book==null){
            ContentValues values=new ContentValues();
            values.put("author",book.getAuthor());
            values.put("price",book.getPrice());
            values.put("pages",book.getPages());
            values.put("name",book.getName());
            db.insert("Book",null,values);
        }
    }

    public List<Book> loadBooks(){
        db = dbHelper.getReadableDatabase();
        List<Book> bookList=new ArrayList<>();
        Cursor cursor=db.query("Book",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Book book=new Book();
                book.setId(cursor.getInt(cursor.getColumnIndex("id")));
                book.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
                book.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
                book.setPages(cursor.getInt(cursor.getColumnIndex("pages")));
                book.setName(cursor.getString(cursor.getColumnIndex("name")));
                bookList.add(book);
            }while (cursor.moveToNext());
        }
        return bookList;
    }

    public void saveCategory(Category category){
        db=dbHelper.getWritableDatabase();
        if (category==null){
            ContentValues values=new ContentValues();
            values.put("category_name",category.getCategory_name());
            values.put("category_code",category.getCategory_code());
            db.insert("Category",null,values);
        }
    }

    public List<Category> loadCategories(){
        db = dbHelper.getReadableDatabase();
        List<Category> categoryList=new ArrayList<>();
        Cursor cursor=db.query("Category",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Category category=new Category();
                category.setCategory_code(cursor.getString(cursor.getColumnIndex("category_code")));
                category.setCategory_name(cursor.getString(cursor.getColumnIndex("category_name")));
                category.setId(cursor.getInt(cursor.getColumnIndex("id")));
                categoryList.add(category);
            }while (cursor.moveToNext());
        }
        return categoryList;
    }

    // 关闭数据库
    public void closeDB() {
        if (db != null) {
            db.close();
        }
    }

}

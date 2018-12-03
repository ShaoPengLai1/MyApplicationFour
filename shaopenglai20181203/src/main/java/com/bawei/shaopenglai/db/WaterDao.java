package com.bawei.shaopenglai.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bawei.shaopenglai.bean.WaterBean;

import java.util.ArrayList;
import java.util.List;

public class WaterDao {
    private static WaterDao instance;
    private Context context;
    private final SqlitHelper sqlitHelper;
    private final SQLiteDatabase sb;

    public WaterDao(Context context) {
        this.context = context;
        sqlitHelper=new SqlitHelper(context);
        sb=sqlitHelper.getReadableDatabase();
    }
    public static WaterDao getInstance(Context context){

        if (instance==null){
            instance=new WaterDao(context);
        }
        return instance;
    }

    public void add(String name,String uuid){
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("uuid",uuid);
        sb.insert("waters",null,values);
    }
    //删除
    public void delete(String uuid){
        sb.delete("waters","uuid=?",new String[]{uuid});
    }
    //删除所有
    public void delAll(){
        sb.delete("waters",null,null);
    }
    //查询所有
    public List<WaterBean> select(){
        List<WaterBean> list=new ArrayList<>();
        Cursor cursor = sb.query("waters", null, null, null, null, null, null, null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String uuid = cursor.getString(cursor.getColumnIndex("uuid"));
                WaterBean bean=new WaterBean(name,uuid);
                list.add(bean);

            }

        }
        return list;
    }
    //判断是否重复
    public boolean hasData(String name){
        Cursor cursor = sb.rawQuery("select id as _id,name from waters where name=?", new String[]{name});
        return cursor.moveToNext();
    }

}

package com.bawei.shaopenglai;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.shaopenglai.bean.WaterBean;
import com.bawei.shaopenglai.db.WaterDao;
import com.bawei.shaopenglai.view.TitleBarView;
import com.bawei.shaopenglai.view.WaterFall;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private WaterFall waterFall;
    private EditText editText;
    private Button button;
    private List<String> list=new ArrayList<>();
    private WaterFall waterFall_serach;
    private WaterFall waterFall_hot;
    private TitleBarView title;
    private Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        waterFall_serach=findViewById(R.id.water_fall_serach);
        waterFall_hot=findViewById(R.id.water_fall_hot);
        title=findViewById(R.id.title);
        button=findViewById(R.id.clear);

        title.setOnButtonClientListener(new TitleBarView.OnButtonClientListener() {
            private String uuid1;
            @Override
            public void onBUttonClick(final String str) {

                if (str.equals("")){
                    return;
                }else {
                    final TextView textView=new TextView(MainActivity.this);
                    UUID uuid=UUID.randomUUID();
                    textView.setTag(uuid);
                    uuid1=String.valueOf(textView.getTag());
                    WaterDao.getInstance(MainActivity.this).add(str,uuid1);
                    textView.setText(str);
                    textView.setTextSize(20);
                    textView.setBackgroundResource(R.drawable.edit_bg);
                    waterFall_serach.addView(textView);
                    textView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            WaterDao.getInstance(MainActivity.this).delete(uuid1);
                            waterFall_serach.removeView(v);
                            return true;
                        }
                    });

                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            textView.setTextSize(30);
                            textView.setTextColor(Color.YELLOW);
                            textView.setBackgroundResource(R.drawable.edit_bg);
                            waterFall_serach.addView(textView);
                            Toast.makeText(MainActivity.this,"点击了:"+str,Toast.LENGTH_LONG).show();

                        }

                    });
                }

            }
        });

        List<WaterBean> select=WaterDao.getInstance(MainActivity.this).select();
        if (select.size()!=0){
            for (final WaterBean s:select){
                final TextView textView=new TextView(MainActivity.this);
                textView.setText(s.getName());
                textView.setTextSize(20);
                textView.setBackgroundResource(R.drawable.edit_bg);
                waterFall_serach.addView(textView);
                textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        WaterDao.getInstance(MainActivity.this).delete(s.getUuid());
                        waterFall_serach.removeView(v);
                        return true;
                    }
                });
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"点击了:"+s.getName(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        //热门搜索
        String[] str=new String[]{
                "考拉三周年人气热销榜","电动牙刷","尤妮佳",
                "豆豆鞋","沐浴露","日东奶茶","榴莲","地三鲜","水煮肉片","可乐","汉堡","炸鸡","烤串","啤酒"
        };
        for (int i=0;i<str.length;i++){
            TextView text=new TextView(MainActivity.this);
            text.setText(str[i]);
            text.setTextSize(20);
            text.setBackgroundResource(R.drawable.edit_bg);
            waterFall_hot.addView(text);
        }
        //清空历史
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaterDao.getInstance(MainActivity.this).delAll();
                waterFall_serach.removeAllViews();
            }
        });
    }


}

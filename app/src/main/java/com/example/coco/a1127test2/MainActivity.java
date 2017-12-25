package com.example.coco.a1127test2;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String HOST = "https://api.tianapi.com/"; //上传数据的网址
    String KEY = "d73d3c76a06d24d93645d3fd735bf449";//上传数据的key值
    String imgUrl = "http://p3.so.qhmsg.com/sdr/534_768_/t012223d2b2eed285b8.jpg";//上传数据图片的网址
    HashMap<String, String> map = new HashMap<>();//上传数据的map集合
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.mTv);

        //将网址拼接的参数存入map集合
        map.put("key", KEY);
        map.put("page", 1 + "");
        map.put("num", 10 + "");
        map.put("img", imgUrl);

    }

    //点击按钮上传数据
    public void upDate(View view) {
        OkUtils.upDate(HOST, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {//失败
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "上传数据失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {//成功
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mTv.setText(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, "上传数据成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //点击按钮上传图片
    public void upImg(View view) {
        mTv.setText("");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        OkUtils.upImg(HOST, imgUrl, path, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {//失败
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "上传图片失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {//成功
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "上传图片成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

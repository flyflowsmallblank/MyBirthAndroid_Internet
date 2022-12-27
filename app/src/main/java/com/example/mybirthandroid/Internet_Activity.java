package com.example.mybirthandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Internet_Activity extends AppCompatActivity {
    private Button btInternet;
    private TextView tvInetName;
    private TextView tvInetStatus;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        mHandler = new myHandler();
        initView();
        initClick();
    }

    /**
     * 初始化操作
     */

    private void initView(){
        btInternet = findViewById(R.id.bt_connection_center);
        tvInetName =findViewById(R.id.tv_connect_name);
        tvInetStatus = findViewById(R.id.tv_connect_status);
    }

    private void initClick(){
        btInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //点击开启网络请求
                HashMap<String,String> map = new HashMap<>();
                map.put("name","陈思言");
                map.put("status","敲代码");
                startConnection("http://10.17.96.48:3030/",map);
            }
        });
    }

    private void startConnection(String mUrl,HashMap<String,String> map){
        new Thread(()->{
            try{
                URL url = new URL(mUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");  //设置网络申请方式为get
                connection.setConnectTimeout(8000);  //设置网络连接不能超过的最大时间
                connection.setReadTimeout(8000);     //读取时间范围
                connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                connection.setRequestProperty("Accept-Encoding", "gzip,deflate");
                connection.setDoInput(true);//补充的，许可输入输出的
                connection.setDoOutput(true);
                StringBuilder dataToWrite = new StringBuilder();
                for(String key : map.keySet()){
                    dataToWrite.append(key).append("=").append(map.get(key)).append("&");
                }
                connection.connect();          //开始连接
                OutputStream out = connection.getOutputStream();
                out.write(dataToWrite.substring(0,dataToWrite.length()-1).getBytes());
                InputStream in = connection.getInputStream();   //从接口处获得输入流，然后对输入流进行处理
                String responseData = streamToString(in);        //将输入流拼接成字符串,调用方法
                Message message = new Message();
                message.obj = responseData;
                mHandler.sendMessage(message);
                Log.d("LX", "startConnection: "+responseData);
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    private String streamToString(InputStream in){
        StringBuilder sb = new StringBuilder();
        String oneLine;
        BufferedReader reader= new BufferedReader(new InputStreamReader(in));
        try{
            while((oneLine = reader.readLine()) != null){        //readline一读读一行
                sb.append(oneLine).append('\n');           //换行增加可读性
            }
        }catch (IOException e){  //捕获输入输出异常
            e.printStackTrace();
        }finally {
            try{
                in.close();               //关闭输入流
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private void jsonDecodeTest(String jsonData){
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject redRock = jsonObject.getJSONObject("redrock");
            int woman_num = redRock.getInt("woman_num");
            int man_num = redRock.getInt("man_num");
            JSONArray jsonArray = jsonObject.getJSONArray("man");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject man = jsonArray.getJSONObject(i);
                String name = man.getString("name");
                String status = man.getString("status");
                if(name.equals("李戬")){
                    tvInetName.setText("姓名："+name);
                    tvInetStatus.setText("干啥："+status);
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    private class myHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String responseData = msg.obj.toString();
            jsonDecodeTest(responseData);
        }
    }
}
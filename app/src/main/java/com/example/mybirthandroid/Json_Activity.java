package com.example.mybirthandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json_Activity extends AppCompatActivity {
    private Button btJson;
    private TextView tvName;
    private TextView tvStatue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        initView();
        initClick();
    }

    /**
     * 初始化开始
     */

    private void initView() {
        btJson = findViewById(R.id.bt_json_text);
        tvName = findViewById(R.id.tv_json_name);
        tvStatue = findViewById(R.id.tv_json_statue);
    }

    private void initClick(){  //点击就解析json
       btJson.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Json_Answer();
           }
       });
    }

    /**
     * 初始化结束
     */

    private void Json_Answer(){
        String jsonData = "{\"redrock\":{\"woman_num\": 5,\"man_num\": 0" +
                "}," + "\"man\":[{\"name\":\"王鸿杨\",\"status\":\"摸鱼\"}," +
                "{\"name\":\"艾卫熹\",\"status\":\"刷leetcode\"}," +
                "{\"name\":\"郭晓强\",\"status\":\"手撸compose\"}, " +
                "{\"name\":\"李戬" + "\",\"status\":\"写自定义view\"}," +
                "{\"name\":\"郭涵宇\",\"status\":\"写博客\"},]}";

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject redRockObject = jsonObject.getJSONObject("redrock");
            int jsonWomanNum = redRockObject.getInt("woman_num");
            int jsonManNum = redRockObject.getInt("man_num");              //剥洋葱结构，注意了，注意等号后第一个是什么
            Toast.makeText(Json_Activity.this,"学姐数量"+jsonWomanNum+"学长数量"+jsonManNum,Toast.LENGTH_SHORT).show();
            JSONArray jsonArray = jsonObject.getJSONArray("man");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject man = jsonArray.getJSONObject(i);    //注意这里将后面数组分开来，一个数组里面有两个值
                String name = man.getString("name");      //让name得到man中的name值
                String status = man.getString("status");   //让status得到man中的status值
                if(name.equals("郭晓强")){
                    tvName.setText(name);
                    tvStatue.setText(status);
                }
            }
        }catch (JSONException jsonException){
            jsonException.printStackTrace();
        }

    }
}
package com.example.mybirthandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * 多线程测试
 */

public class MuchTread_Activity extends AppCompatActivity{
    private Button btClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_much_tread);
        btClick = findViewById(R.id.tread_bt_center);
        click();
    }



    private void click(){
        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try{
                            suspend();
                        }catch (InterruptedException e){
                            e.getStackTrace();
                        }
                    }
                };
                Thread thread1 = new Thread(runnable);
                thread1.start();
                Toast.makeText(view.getContext(),"我爱你",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void suspend() throws InterruptedException {
        Thread.sleep(6000);
    }
}
package com.example.tzadmin.tzsk_windows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tzadmin.tzsk_windows.HttpModule.Http;
import com.example.tzadmin.tzsk_windows.HttpModule.HttpResp;
import com.example.tzadmin.tzsk_windows.SaveAuthModule.SaveAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SaveAuth.SetUp(this);

        if(checkAuth()) {
            helper.message(this,"Auth OK");
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private boolean checkAuth () {
        Http http = new Http();
        if(SaveAuth.get()) {
            HttpResp resp = http.GET("auth");
            if(resp.Code == 200) return true;
        }
        return false;
    }
}

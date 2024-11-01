package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PackageManager packageManager = this.getPackageManager();
        //InstallSourceInfo installSourceInfo = packageManager.getInstallSourceInfo(DeviceReporter);

        //跳转到无障碍服务设置页
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        //在服务被销毁时，关闭前台服务
        super.onDestroy();
    }
}
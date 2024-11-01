package com.example.testapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.InstallSourceInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

public class MyInstalledReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("sdk版本：", String.valueOf(Build.VERSION.SDK_INT));
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:" + packageName.replace("package:", "")));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //Log.i("sdk版本：", String.valueOf(Build.VERSION.SDK_INT));

            try {
                InstallSourceInfo info = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    info = context.getPackageManager().getInstallSourceInfo(packageName.replace("package:", ""));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }


            context.startActivity(intent);
        }
    }
}

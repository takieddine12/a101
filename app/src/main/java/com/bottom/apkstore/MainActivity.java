package com.bottom.apkstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button storeAppBtn = findViewById(R.id.storeBtn);
        Button notStoreAppBtn = findViewById(R.id.notStoreBtn);

        storeAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAppFromPlayStore("com.facebook.katana")){
                    Toast.makeText(MainActivity.this,"App Installed From Google Play",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,"App Not Installed From Google Play",Toast.LENGTH_LONG).show();
                }
            }
        });
        notStoreAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAppFromPlayStore("com.bottom.pyjava")){
                    Toast.makeText(MainActivity.this,"App Installed From Google Play",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,"App Not Installed From Google Play",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public boolean isAppFromPlayStore(String  packageName) {
        PackageManager packageManager =getPackageManager();
        try {
            ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, 0);
            String installerPackageName = packageManager.getInstallerPackageName(appInfo.packageName);
            return installerPackageName != null && installerPackageName.equals("com.android.vending");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

}
package com.sdr.hkvideo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sdr.hklibrary.SDR_HIKVISION_9800_HTTP;

public class MainActivity extends BaseActivity {

    private EditText edtUrl;
    private EditText edtUserName;
    private EditText edtPassWord;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUrl = findViewById(R.id.main_edt_url);
        edtUserName = findViewById(R.id.main_edt_username);
        edtPassWord = findViewById(R.id.main_edt_password);
        login = findViewById(R.id.main_btn_login);
        edtUrl.setText(getPlaceHolder("ip") + getPlaceHolder("port"));
        edtUserName.setText(getPlaceHolder("name"));
        edtPassWord.setText(getPlaceHolder("password"));


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtUrl.getText().toString().trim();
                String userName = edtUserName.getText().toString().trim();
                String passWord = edtPassWord.getText().toString().trim();
                SDR_HIKVISION_9800_HTTP.getInstance().start(getContext(), url, userName, passWord);
            }
        });
    }


    public String getPlaceHolder(String key) {
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString(key);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    protected boolean onActivityAnimate() {
        return false;
    }
}

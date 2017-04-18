package com.shlg.chuang.littledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shlg.chuang.littledemo.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText mobileEditText = (EditText) findViewById(R.id.mobile_edit_text);
        findViewById(R.id.commit_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                requestRegister(mobileEditText.getText().toString());
            }
        });
    }

    private void requestRegister(String mobileString) {
        String postBody = "{"
                + "\"mobile\""
                + ":"
                + "\"" + mobileString + "\""
                + "}";
        String requestUrl = getString(R.string.app_server) + "/register";
        HttpUtil.sendOkHttpPostRequest(requestUrl, postBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "register Failure", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() >= 200 && response.code()< 300) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "return code error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}

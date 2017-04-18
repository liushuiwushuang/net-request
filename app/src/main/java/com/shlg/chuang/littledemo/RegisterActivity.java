package com.shlg.chuang.littledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shlg.chuang.littledemo.entity.MobileNumber;
import com.shlg.chuang.littledemo.remote.api.MobileNumberApi;
import com.shlg.chuang.littledemo.util.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText mobileEditText = (EditText) findViewById(R.id.mobile_edit_text);
        findViewById(R.id.commit_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                requestRegister2(mobileEditText.getText().toString());
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

    private void requestRegister2(String mobileString) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobileString);
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(getResources().getString(R.string.app_server))
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        MobileNumberApi mobileNumberApi = retrofit.create(MobileNumberApi.class);
        retrofit2.Call<MobileNumber> call = mobileNumberApi.userRegister(params);
        call.enqueue(new retrofit2.Callback<MobileNumber>() {
            @Override
            public void onResponse(retrofit2.Call<MobileNumber> call, retrofit2.Response<MobileNumber> response) {
                if (response.isSuccessful()) {

                } else {

                }
            }

            @Override
            public void onFailure(retrofit2.Call<MobileNumber> call, Throwable t) {

            }
        });
    }
}

package vn.icar.taxicontacts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.icar.taxicontacts.api.ApiClient;
import vn.icar.taxicontacts.api.UserService;

public class RegisterActivity extends AppCompatActivity {
    EditText _id,name,password;
    Button btnregister;
    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        _id= findViewById(R.id.edt_reg_username);
        name= findViewById(R.id.edt_name);
        password= findViewById(R.id.edt_reg_password);
        btnregister= findViewById(R.id.btn_register);
        btnback= findViewById(R.id.btn_back);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(_id.getText().toString()) || TextUtils.isEmpty(name.getText().toString()) ||
                        TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(vn.icar.taxicontacts.RegisterActivity.this, "Username/password required", Toast.LENGTH_SHORT).show();
                } else {
                    register();
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(vn.icar.taxicontacts.RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void register() {

        UserService client = ApiClient.getUserService();
        Call<LoginResponse> loginReponseCall= client.register(_id.getText().toString(), name.getText().toString(), password.getText().toString());
        loginReponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Toast.makeText(vn.icar.taxicontacts.RegisterActivity
                        .this, "Register Successful: " , Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity( new Intent(vn.icar.taxicontacts.RegisterActivity.this,MainActivity.class));

                    }
                }, 50);
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(vn.icar.taxicontacts.RegisterActivity.this, "Register Fail", Toast.LENGTH_SHORT).show();

            }
        });


    }
    }

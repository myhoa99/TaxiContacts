package vn.icar.taxicontacts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.icar.taxicontacts.api.ApiClient;
import vn.icar.taxicontacts.api.UserService;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btnlogin;
    TextView tvforgetpass, tvsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // ánh xạ
        username= findViewById(R.id.edt_username);
        password= findViewById(R.id.edt_password);
        btnlogin= findViewById(R.id.btn_login);
        tvforgetpass= findViewById(R.id.tv_forgetpass);
        tvsignin= findViewById(R.id.tv_signin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText().toString())||TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(vn.icar.taxicontacts.LoginActivity.this, "Username/password required", Toast.LENGTH_SHORT).show();
                }
                else {
                   login();
                }
            }
        });
        tvforgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent= new Intent(vn.icar.taxicontacts.LoginActivity.this, RegisterActivity.class);
               startActivity(intent);

            }
        });


    }
    public void  login(){
        UserService client = ApiClient.getUserService();
       Call<LoginResponse> loginReponseCall= client.userLogin(username.getText().toString(), password.getText().toString());
       loginReponseCall.enqueue(new Callback<LoginResponse>() {
           @Override
           public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
               Toast.makeText(vn.icar.taxicontacts.LoginActivity.this, "Login Successful: " , Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity( new Intent(vn.icar.taxicontacts.LoginActivity.this,MainActivity.class));

                    }
                }, 50);
           }
           @Override
           public void onFailure(Call<LoginResponse> call, Throwable t) {
               Toast.makeText(vn.icar.taxicontacts.LoginActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();

           }
       });


    }

}
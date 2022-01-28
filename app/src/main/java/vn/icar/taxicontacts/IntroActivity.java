package vn.icar.taxicontacts;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        checkPermission();
    }
    //Hàm event khi người dùng đang lựa chọn cho phép or từ chối ở trên dialog xin quyền
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[3] == PackageManager.PERMISSION_GRANTED
                    && grantResults[4] == PackageManager.PERMISSION_GRANTED
                    && grantResults[5] == PackageManager.PERMISSION_GRANTED
                    && grantResults[6] == PackageManager.PERMISSION_GRANTED
                    && grantResults[7] == PackageManager.PERMISSION_GRANTED
            ) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(vn.icar.taxicontacts.IntroActivity.this, LoginActivity.class));
                        finish();
                    }
                }, 0);
            } else {
                Toast.makeText(this, "Bạn đã từ chối quyền", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void checkPermission() {
        //Đòi quyền khi mà quyền đó chưa được cho phép
        ActivityCompat.requestPermissions(vn.icar.taxicontacts.IntroActivity.this,
                new String[]{Manifest.permission.READ_CONTACTS
                        , Manifest.permission.READ_CALL_LOG
                        , Manifest.permission.CALL_PHONE
                        , Manifest.permission.SEND_SMS
                        , Manifest.permission.WRITE_CONTACTS
                        , Manifest.permission.CAMERA
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.READ_PHONE_STATE}, 1);
        //Check xem nếu các quyền đều được cho phép rồi hay chưa
        if(ContextCompat.checkSelfPermission(vn.icar.taxicontacts.IntroActivity.this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(vn.icar.taxicontacts.IntroActivity.this,
                Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(vn.icar.taxicontacts.IntroActivity.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(vn.icar.taxicontacts.IntroActivity.this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(vn.icar.taxicontacts.IntroActivity.this,
                Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(vn.icar.taxicontacts.IntroActivity.this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(vn.icar.taxicontacts.IntroActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(vn.icar.taxicontacts.IntroActivity.this,
                Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(vn.icar.taxicontacts.IntroActivity.this, LoginActivity.class));
                    finish();
                }
            }, 0);
        }
    }
}

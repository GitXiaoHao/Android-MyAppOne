package top.yh.my_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ActionUriActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_uri);
        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_my).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        String phoneNo = "12345";
        Intent intent = new Intent();
        Uri uri = null;
        switch (view.getId()){
            //打电话
            case R.id.btn_dial:
                //设置意图准备拨号
                intent.setAction(Intent.ACTION_DIAL);
                //声明uri
                uri = Uri.parse("tel:" + phoneNo);
                intent.setData(uri);
                startActivity(intent);
                break;
            //发短信
            case R.id.btn_sms:
                //设置意图准备短信
                intent.setAction(Intent.ACTION_SENDTO);
                //声明uri
                uri = Uri.parse("smsto:" + phoneNo);
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.btn_my:
                //设置意图准备短信
                intent.setAction("android.intent.action.MYACTION");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);
            default:
                break;

        }
    }
}
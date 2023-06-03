package top.yh.my_activity.back_data;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDateTime;

import top.yh.my_activity.R;

public class ActResponseActivity extends AppCompatActivity {
    private String mResponse = "睡了";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_response);
        TextView tvRequest = findViewById(R.id.tv_request);
        //从上个页面获取数据
        Bundle extras = getIntent().getExtras();
        String time = extras.getString("time");
        String text = extras.getString("text");
        String format = String.format("收到请求时间：%s,收到请求内容：%s", time, text);
        tvRequest.setText(format);
        ((TextView) findViewById(R.id.tv_response)).setText("待返回的消息是：" + mResponse);
        findViewById(R.id.btn_response).setOnClickListener(view -> {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("time", LocalDateTime.now().toString());
            bundle.putString("text", mResponse);
            intent.putExtras(bundle);
            //携带意图返回上一个页面
            setResult(Activity.RESULT_OK, intent);
            //结束当前页面
            finish();
        });
    }
}
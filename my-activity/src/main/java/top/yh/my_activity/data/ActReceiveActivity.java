package top.yh.my_activity.data;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import top.yh.my_activity.R;

public class ActReceiveActivity extends AppCompatActivity {
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_receive);
        TextView view = findViewById(R.id.tv_receive);
        //从上一个页面传来的意图获取包裹
        Bundle extras = getIntent().getExtras();
        String time = extras.getString("time");
        String text = extras.getString("text");
        String format = String.format("收到请求时间：%s,收到请求内容：%s", time, text);
        view.setText(format);
    }
}
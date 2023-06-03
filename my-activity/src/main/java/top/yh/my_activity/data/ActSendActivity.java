package top.yh.my_activity.data;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDateTime;

import top.yh.my_activity.R;

public class ActSendActivity extends AppCompatActivity {
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_send);
         TextView textView = findViewById(R.id.tv_send);
        findViewById(R.id.btn_send).setOnClickListener(view -> {
            Intent intent = new Intent(ActSendActivity.this, ActReceiveActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("time", LocalDateTime.now().toString());
            bundle.putString("text", textView.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}
package top.yh.my_activity.back_data;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDateTime;

import top.yh.my_activity.R;

public class ActRequestActivity extends AppCompatActivity {
    private final String mRequest = "睡啦吗";
    private TextView tvResponse;

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_request);
        TextView tvRequest = findViewById(R.id.tv_request);
        tvRequest.setText("待发送的消息：" + mRequest);
        tvResponse = findViewById(R.id.tv_response);
        ActivityResultLauncher<Intent> register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result != null) {
                Intent data = result.getData();
                if (data != null && result.getResultCode() == Activity.RESULT_OK) {
                    Bundle extras = data.getExtras();
                    String time = extras.getString("time");
                    String text = extras.getString("text");
                    String format = String.format("收到返回时间：%s,收到返回内容：%s", time, text);
                    tvResponse.setText(format);
                }
            }
        });
        findViewById(R.id.btn_request).setOnClickListener(view -> {
            Intent intent = new Intent(ActRequestActivity.this, ActResponseActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("time", LocalDateTime.now().toString());
            bundle.putString("text", mRequest);
            intent.putExtras(bundle);
            //带返回结果跳转
            register.launch(intent);
        });
    }
}
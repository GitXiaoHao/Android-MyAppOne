package top.yh.mybutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import top.yh.mybutton.util.DateUtil;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
    }

    public void onClick(View view) {
        String desc = String.format("%s 您点击了按钮:%s",DateUtil.getNowTime(),((Button)view).getText());
        result.setText(desc);
    }
}
package top.yh.mybutton;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import top.yh.mybutton.util.DateUtil;

public class ButtonEnableActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView result;
    private Button test;
    private Button disable;
    private Button enable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_enable);
        enable = findViewById(R.id.btn_enable);
        disable = findViewById(R.id.btn_disable);
        test = findViewById(R.id.btn_test);
        result = findViewById(R.id.result);

        enable.setOnClickListener(this);
        disable.setOnClickListener(this);
        test.setOnClickListener(this);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_enable:
                //启用当前控件
                test.setEnabled(true);
                //设置按钮的文字的颜色
                test.setTextColor(Color.BLACK);
                break;
            case R.id.btn_disable:
                //禁用当前控件
                test.setEnabled(false);
                test.setTextColor(Color.GRAY);
                break;
            case R.id.btn_test:
                String desc = String.format("%s 您点击了按钮:%s", DateUtil.getNowTime(),((Button)view).getText());
                result.setText(desc);
                break;
            default:
                break;
        }
    }
}
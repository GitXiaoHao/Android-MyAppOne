package top.yh.simplecontrol;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import top.yh.simplecontrol.utils.Utils;

/**
 * @author yuhao
 * @user yuhao
 * @date
 */
public class TextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        TextView textView = findViewById(R.id.text1);
        textView.setText(R.string.text1);
        textView.setBackgroundColor(Color.RED);
        textView.setBackgroundResource(R.color.purple_200);
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = Utils.dip2px(this,300);
        textView.setLayoutParams(layoutParams);
    }
}

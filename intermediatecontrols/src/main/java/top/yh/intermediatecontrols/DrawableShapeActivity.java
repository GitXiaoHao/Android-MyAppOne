package top.yh.intermediatecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

public class DrawableShapeActivity extends AppCompatActivity implements View.OnClickListener {
    View view;
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_shape);
        view = findViewById(R.id.v_content);
        findViewById(R.id.btn_oval).setOnClickListener(this);
        findViewById(R.id.btn_rect).setOnClickListener(this);
        //背景设置为圆角矩形
        view.setBackgroundResource(R.drawable.shape_rect_gold);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_oval:
                view.setBackgroundResource(R.drawable.shape_rect_gold);
                break;
            case R.id.btn_rect:
                view.setBackgroundResource(R.drawable.shape_oval_rose);
                break;
            default:
        }
    }
}
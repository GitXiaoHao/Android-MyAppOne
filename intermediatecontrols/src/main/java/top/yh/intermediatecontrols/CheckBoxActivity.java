package top.yh.intermediatecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class CheckBoxActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        CheckBox ckSystem = findViewById(R.id.ck_system);
        CheckBox ckCustom = findViewById(R.id.ck_custom);
        ckSystem.setOnCheckedChangeListener(this);
        ckCustom.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String desc = String.format("您%s了这个CheckBox",b ? "勾选" : "取消勾选");
        compoundButton.setText(desc);
    }
}
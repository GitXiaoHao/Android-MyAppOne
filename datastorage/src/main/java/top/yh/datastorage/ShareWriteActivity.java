package top.yh.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class ShareWriteActivity extends AppCompatActivity {
    private EditText etn;
    private EditText eta;
    private EditText ett;
    private EditText etw;
    private CheckBox cbw;
    private SharedPreferences sp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_write);
        etn = findViewById(R.id.et_name);
        eta = findViewById(R.id.et_age);
        ett = findViewById(R.id.et_tall);
        etw = findViewById(R.id.et_weight);
        cbw = findViewById(R.id.cb_wed);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        findViewById(R.id.btn_save).setOnClickListener(view -> {
            String name = etn.getText().toString();
            String age = eta.getText().toString();
            String tall = ett.getText().toString();
            String weight = etw.getText().toString();
            //获得编辑器
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("name",name);
            editor.putInt("age", Integer.parseInt(age));
            editor.putInt("tall", Integer.parseInt(tall));
            editor.putFloat("weight", Float.parseFloat(weight));
            editor.putBoolean("wed",cbw.isChecked());
            //提交
            editor.apply();
        });
        reload();
    }

    private void reload() {
        String name = sp.getString("name", null);
        if (name != null) {
            etn.setText(name);
        }
        int age = sp.getInt("age",0);
        if (age != 0) {
            eta.setText(String.valueOf(age));
        }
        //..
    }
}
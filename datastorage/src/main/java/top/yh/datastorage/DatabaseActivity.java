package top.yh.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tvd = findViewById(R.id.tv_database);
        //生成一个测试数据库的完整路径
        String databaseName = getFilesDir() + "/test.db";
        findViewById(R.id.btn_create).setOnClickListener(view -> {
            //创建或打开数据库
            SQLiteDatabase sd = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
            String desc = String.format("数据库%s创建%s", sd.getPath(), "成功");
            tvd.setText(desc);
        });
        findViewById(R.id.btn_delete).setOnClickListener(view -> {
            boolean result = deleteDatabase(databaseName);
            String desc = String.format("数据库%s删除%s", databaseName, result ? "成功" : "失败");
            tvd.setText(desc);
        });
    }
}
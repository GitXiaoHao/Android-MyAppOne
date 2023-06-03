package top.yh.intermediatecontrols;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
@SuppressLint({"MissingInflatedId", "LocalSuppress"})
public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        findViewById(R.id.btn_alert).setOnClickListener(this);
         tvAlert = findViewById(R.id.tv_alert);
    }

    @Override
    public void onClick(View view) {
        //创建提醒对话框的构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("尊敬的用户");
        builder.setMessage("你真的要卸载我嘛");
        builder.setPositiveButton("残忍卸载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvAlert.setText("离开");
            }
        });
        builder.setNegativeButton("我再想想", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvAlert.setText("不卸载");
            }
        });
        //创建对话框
        AlertDialog alertDialog = builder.create();
        //显示
        alertDialog.show();
    }
}
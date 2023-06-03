package top.yh.intermediatecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

@SuppressLint({"MissingInflatedId", "LocalSuppress"})
public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    TimePicker dpTime;
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_time).setOnClickListener(this);
        dpTime = findViewById(R.id.dp_time);
        //改成24小时
        dpTime.setIs24HourView(true);
        tvTime = findViewById(R.id.tv_time);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                @SuppressLint("DefaultLocale") String desc = String.format("您选择的时间是%d时%d分",
                        dpTime.getHour(), dpTime.getMinute());
                tvTime.setText(desc);
                break;
            case R.id.btn_time:
                Calendar c = Calendar.getInstance();

                //构建时间对话框
                TimePickerDialog dialog = new TimePickerDialog(this,this,c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        true);
                dialog.show();
                break;
            default:
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        @SuppressLint("DefaultLocale") String desc = String.format("您选择的时间是%d时%d分", i, i1);
        tvTime.setText(desc);
    }
}
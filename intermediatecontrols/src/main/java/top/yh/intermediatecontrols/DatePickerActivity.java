package top.yh.intermediatecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

@SuppressLint({"MissingInflatedId", "LocalSuppress"})
public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    DatePicker dpDate;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_date).setOnClickListener(this);
        dpDate = findViewById(R.id.dp_date);
        tvDate = findViewById(R.id.tv_date);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                @SuppressLint("DefaultLocale") String desc = String.format("您选择的日期是%d年%d月%d日", dpDate.getYear(), dpDate.getMonth() + 1, dpDate.getDayOfMonth());
                tvDate.setText(desc);
                break;
            case R.id.btn_date:
                //获取当前时间
                Calendar c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(this,this,c.get(Calendar.YEAR),c.get(Calendar.MONTH) + 1,c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            default:
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        @SuppressLint("DefaultLocale") String desc = String.format("您选择的日期是%d年%d月%d日", i, i1 + 1, i2);
        tvDate.setText(desc);
    }
}
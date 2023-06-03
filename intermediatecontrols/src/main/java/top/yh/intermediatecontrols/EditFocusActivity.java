package top.yh.intermediatecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint({"MissingInflatedId", "LocalSuppress"})
public class EditFocusActivity extends AppCompatActivity implements View.OnFocusChangeListener {
    EditText etPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_focus);
        etPhone = findViewById(R.id.et_phone);
        EditText etPassword = findViewById(R.id.et_password);
        etPhone.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            String phone = etPhone.getText().toString();
            //手机号不足11
            if (TextUtils.isEmpty(phone) || phone.length() < 11) {
                //手机号码编辑框请求焦点,把光标移回手机号编辑框
                etPhone.requestFocus();
                Toast.makeText(this,"请输入11位的手机号码",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
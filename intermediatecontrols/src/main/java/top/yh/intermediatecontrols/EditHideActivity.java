package top.yh.intermediatecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import top.yh.intermediatecontrols.util.ViewUtil;

@SuppressLint({"MissingInflatedId", "LocalSuppress"})
public class EditHideActivity extends AppCompatActivity {
    private EditText etPhone;
    private EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hide);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        etPhone.addTextChangedListener(new HideTextWatcher(etPhone,11));
        etPassword.addTextChangedListener(new HideTextWatcher(etPassword,6));
    }

    private class HideTextWatcher implements TextWatcher {
        private EditText mView;
        private int maxLength;
        public HideTextWatcher(EditText et, int maxLength) {
            this.mView = et;
            this.maxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            //获得已输入的字符串
            String str = editable.toString();
            if(str.length() == maxLength){
                //隐藏输入法
                ViewUtil.hideOneInputMethod(EditHideActivity.this,mView);
            }
        }
    }
}
package top.yh.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

import top.yh.datastorage.database.UserDBHelper;
import top.yh.datastorage.entity.User;
import top.yh.datastorage.util.ViewUtil;

public class SQLiteHelperActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etn;
    private EditText eta;
    private EditText ett;
    private EditText etw;
    private CheckBox cbw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_helper);
        etn = findViewById(R.id.et_name);
        eta = findViewById(R.id.et_age);
        ett = findViewById(R.id.et_tall);
        etw = findViewById(R.id.et_weight);
        cbw = findViewById(R.id.cb_wed);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
    }

    private UserDBHelper ud;

    @Override
    protected void onStart() {
        super.onStart();
        ud = UserDBHelper.getInstance(this);
        ud.openReadLink();
        ud.openWriteLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ud.closeLink();
    }

    @Override
    public void onClick(View view) {
        String name = etn.getText().toString();
        String age = eta.getText().toString();
        String tall = ett.getText().toString();
        String weight = etw.getText().toString();
        boolean checked = cbw.isChecked();
        User user = new User(name,
                Integer.parseInt(age),
                Integer.parseInt(tall),
                Float.parseFloat(weight),
                checked);
        switch (view.getId()) {
            case R.id.btn_add:
                if (ud.insert(user) > 0) {
                    ViewUtil.showToast(this, "添加成功");
                }
                break;
            case R.id.btn_del:
                if (ud.deleteByName(name) > 0) {
                    ViewUtil.showToast(this, "删除成功");
                }
                break;
            case R.id.btn_update:
                if (ud.update(user) > 0) {
                    ViewUtil.showToast(this, "修改成功");
                }
                break;
            case R.id.btn_query:
                List<User> list = ud.queryAll();
                list.forEach(u -> Log.d("ning", u.toString()));
                break;
            default:
        }
    }
}
package top.yh.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import top.yh.datastorage.dao.BookDao;
import top.yh.datastorage.entity.BookInfo;
import top.yh.datastorage.entity.User;
import top.yh.datastorage.util.ViewUtil;

public class RoomWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etAuthor;
    private EditText etPress;
    private EditText etPrice;
    private BookDao bookDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_write);
        etName = findViewById(R.id.et_name);
        etAuthor = findViewById(R.id.et_author);
        etPress = findViewById(R.id.et_press);
        etPrice = findViewById(R.id.et_price);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        bookDao = MyApplication.getInstance().getBookDataBase().bookDao();
    }
    @Override
    public void onClick(View view) {
        String name = etName.getText().toString();
        String author = etAuthor.getText().toString();
        String press = etPress.getText().toString();
        String price = etPrice.getText().toString();
        BookInfo bookInfo = new BookInfo(name, author, press, Double.parseDouble(price));
        switch (view.getId()) {
            case R.id.btn_add:
                bookDao.insert(bookInfo);
                break;
            case R.id.btn_del:
                bookDao.delete(bookInfo);
                break;
            case R.id.btn_update:
               bookDao.update(bookInfo);
                break;
            case R.id.btn_query:
                List<BookInfo> list = bookDao.queryAll();
                list.forEach(u -> Log.d("ning", u.toString()));
                break;
            default:
        }
    }
}
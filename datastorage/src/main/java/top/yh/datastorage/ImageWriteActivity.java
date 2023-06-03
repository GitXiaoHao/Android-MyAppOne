package top.yh.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;

import top.yh.datastorage.util.FileUtil;
import top.yh.datastorage.util.ViewUtil;

public class ImageWriteActivity extends AppCompatActivity {
    private ImageView ivContent;
    private  String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_write);
        findViewById(R.id.btn_save).setOnClickListener(view -> {
            String fileName = System.currentTimeMillis() + ".jpeg";
            //获取当前APP的私有下载目录
            path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()
                    + File.separatorChar + fileName;
            //从指定资源文件中获取位图对象
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.github);
            //把位图对象保存为图片文件
            FileUtil.saveImage(path,bitmap);
            ViewUtil.showToast(ImageWriteActivity.this,"保存成功");

        });
        findViewById(R.id.btn_read).setOnClickListener(view -> {
            Bitmap bitmap = FileUtil.openImage(path);
            //第二种方法
            //Bitmap bitmap = BitmapFactory.decodeFile(path);
            ivContent.setImageBitmap(bitmap);
            //第三种方式
            ivContent.setImageURI(Uri.parse(path));
        });
    }
}
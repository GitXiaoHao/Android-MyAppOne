package top.yh.dsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import top.yh.dsp.database.ShoppingDBHelper;
import top.yh.dsp.entity.GoodsInfo;
import top.yh.dsp.util.ViewUtil;

public class ShoppingDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ShoppingDBHelper dbHelper;
    private TextView tvTitle;
    private TextView tvDesc;
    private TextView tvPrice;
    private TextView tvCount;
    private ImageView ivThumb;
    private int goodsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_detail);
        tvTitle = findViewById(R.id.tv_title);
        tvCount = findViewById(R.id.tv_count);
        tvPrice = findViewById(R.id.tv_price);
        tvDesc = findViewById(R.id.tv_desc);
        ivThumb = findViewById(R.id.iv_thumb);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cart).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
        tvCount.setText(MyApplication.getInstance().goodsCount);
        dbHelper = ShoppingDBHelper.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showDetail();
    }

    private void showDetail() {

        goodsId = getIntent().getIntExtra("goods_id", 0);
        if (goodsId > 0) {
            GoodsInfo goodsInfo = dbHelper.queryGoodsInfoById(goodsId);
            tvTitle.setText(goodsInfo.getName());
            tvDesc.setText(goodsInfo.getDescription());
            tvPrice.setText(String.valueOf(goodsInfo.getPrice()));
            ivThumb.setImageURI(Uri.parse(goodsInfo.getPicPath()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_cart:
                startActivity(new Intent(this,ShoppingCartActivity.class));
                break;
            case R.id.btn_add:
                addToCart(goodsId);
                break;
            default:
        }
    }

    private void addToCart(int goodsId) {
        dbHelper.insertCartInfo(goodsId);
        tvCount.setText(++MyApplication.getInstance().goodsCount);
        ViewUtil.showToast(this,"已添加到购物车");

    }
}
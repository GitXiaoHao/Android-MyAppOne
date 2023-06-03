package top.yh.dsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URI;
import java.util.List;

import top.yh.dsp.database.ShoppingDBHelper;
import top.yh.dsp.entity.GoodsInfo;
import top.yh.dsp.util.ViewUtil;

public class ShoppingChannelActivity extends AppCompatActivity implements View.OnClickListener {
    private ShoppingDBHelper dbHelper;
    private TextView tvCount;
    private GridLayout gridLayoutChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_channerl);
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("手机商城");
        tvCount = findViewById(R.id.tv_count);
        gridLayoutChannel = findViewById(R.id.gl_channel);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cart).setOnClickListener(this);
        dbHelper = ShoppingDBHelper.getInstance(this);
        dbHelper.openReadLink();
        dbHelper.openWriteLink();
        //从数据库中查询出商品信息，并展示
        showGoods();
    }

    private void showGoods() {
        //商品条目是一个线性布局，设置布局的宽度位屏幕的一半
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth / 2, LinearLayout.LayoutParams.WRAP_CONTENT);
        //移除下面的所有子视图
        gridLayoutChannel.removeAllViews();
        List<GoodsInfo> goodsInfoList = dbHelper.queryAllGoodsInfo();
        for (GoodsInfo goodsInfo : goodsInfoList) {
            //获取布局文件item_goods.xml的根视图
            View v = LayoutInflater.from(this).inflate(R.layout.item_goods, null);
            ImageView ivThumb = v.findViewById(R.id.iv_thumb);
            TextView tvName = v.findViewById(R.id.tv_name);
            TextView tvPrice = v.findViewById(R.id.tv_price);
            Button btnAdd = v.findViewById(R.id.btn_add);
            //给控件设置值
            ivThumb.setImageURI(Uri.parse(goodsInfo.getPicPath()));
            tvName.setText(goodsInfo.getName());
            tvPrice.setText(String.valueOf(goodsInfo.getPrice()));
            //添加到购物车
            btnAdd.setOnClickListener(view -> {
                addToCart(goodsInfo);
            });
            ivThumb.setOnClickListener(view -> {
                startActivity(new Intent(ShoppingChannelActivity.this,ShoppingDetailActivity.class)
                        .putExtra("goods_id",goodsInfo.getId()));
            });
            //把商品视图添加到网格布局
            gridLayoutChannel.addView(v,layoutParams);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //查询购物车商品总数并展示
        showCartInfoTotal();
    }

    private void showCartInfoTotal() {
        int count = dbHelper.countCartInfo();
        MyApplication.getInstance().goodsCount = count;
        tvCount.setText(count);
    }

    /**
     * 把指定商品添加到购物车
     * @param goodsInfo
     */
    private void addToCart(GoodsInfo goodsInfo) {
        dbHelper.insertCartInfo(goodsInfo.getId());
        tvCount.setText(++MyApplication.getInstance().goodsCount);
        ViewUtil.showToast(this,"已添加一部" + goodsInfo.getName() + "到购物车");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.closeLink();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                //点击返回 关闭当前页面
                finish();
                break;
            case R.id.iv_cart:
                Intent intent = new Intent(this, ShoppingCartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
        }
    }
}
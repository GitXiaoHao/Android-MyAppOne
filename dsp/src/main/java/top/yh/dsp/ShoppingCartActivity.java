package top.yh.dsp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.yh.dsp.database.ShoppingDBHelper;
import top.yh.dsp.entity.CartInfo;
import top.yh.dsp.entity.GoodsInfo;
import top.yh.dsp.util.ViewUtil;
@SuppressLint("InflateParams")
public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvCount;
    private LinearLayout llCart;
    private ShoppingDBHelper dbHelper;
    private TextView tvTotalPrice;
    private LinearLayout llEmpty;
    private LinearLayout llContent;
    /**
     * 购物车商品信息列表
     */
    private List<CartInfo> cartInfoList;
    /**
     * 声明一个根据商品编号查找商品信息的映射，把商品信息缓存起来
     */
    private Map<Integer, GoodsInfo> goodsInfoMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        TextView tvTitle = findViewById(R.id.tv_title);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cart).setOnClickListener(this);
        findViewById(R.id.btn_shopping_channel).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_settle).setOnClickListener(this);
        llEmpty = findViewById(R.id.ll_empty);
        llContent = findViewById(R.id.ll_content);
        tvTitle.setText("购物车");
        tvCount = findViewById(R.id.tv_count);
        tvCount.setText(MyApplication.getInstance().goodsCount);
        llCart = findViewById(R.id.ll_cart);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        dbHelper = ShoppingDBHelper.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showCart();
    }

    /**
     * 展示购物车商品列表
     */
    private void showCart() {
        //移除下面的所有子视图
        llCart.removeAllViews();
        //查询购物车数据库所有的商品记录
        cartInfoList = dbHelper.queryAllCartInfo();
        if (cartInfoList.size() == 0) {
            return;
        }
        for (CartInfo cartInfo : cartInfoList) {
            //根据商品编号查询商品数据库中的商品记录
            GoodsInfo goodsInfo = dbHelper.queryGoodsInfoById(cartInfo.getGoodsId());
            goodsInfoMap.put(goodsInfo.getId(), goodsInfo);
            //获取布局文件item_cart.xml的根视图
            View view = LayoutInflater.from(this).inflate(R.layout.item_cart, null);
            ImageView ivThumb = view.findViewById(R.id.iv_thumb);
            TextView tvName = view.findViewById(R.id.tv_name);
            TextView tvDesc = view.findViewById(R.id.tv_desc);
            TextView tvCount = view.findViewById(R.id.tv_count);
            TextView tvPrice = view.findViewById(R.id.tv_price);
            TextView tvSum = view.findViewById(R.id.tv_sum);
            ivThumb.setImageURI(Uri.parse(goodsInfo.getPicPath()));
            tvName.setText(goodsInfo.getName());
            tvDesc.setText(goodsInfo.getDescription());
            tvCount.setText(cartInfo.getCount());
            tvPrice.setText(String.valueOf((int) goodsInfo.getPrice()));
            int price = (int) (cartInfo.getCount() * goodsInfo.getPrice());
            tvSum.setText(String.valueOf(price));
            //给商品行添加长按时间，删除该商品信息
            view.setOnLongClickListener(v -> {
                new AlertDialog.Builder(ShoppingCartActivity.this)
                        .setMessage("是否从购物车删除" + goodsInfo.getName() + "?")
                        .setPositiveButton("是",((dialogInterface, i) -> {
                            //移除
                            llCart.removeView(v);
                            //删除该商品
                            deleteGoods(cartInfo);
                        }))
                        .setNegativeButton("否",null)
                        .create().show();
                return true;
            });
            //点击跳转详情
            view.setOnClickListener(v -> {
                startActivity(new Intent(ShoppingCartActivity.this,ShoppingDetailActivity.class)
                        .putExtra("goods_id",goodsInfo.getId()));
            });
            //往购物车列表添加该商品行
            llCart.addView(view);
        }
        //重新计算购物车中的商品总金额
        refreshTotalPrice();

    }

    private void deleteGoods(CartInfo cartInfo) {
        MyApplication.getInstance().goodsCount -= cartInfo.getCount();
        //从购物车的数据中删除商品
        dbHelper.deleteCartInfoByGoodsId(cartInfo);
        //从列表中删除商品
        cartInfoList.remove(cartInfo);
        //显示最新的商品数量
        showCount();
        ViewUtil.showToast(this,"已从购物车删除" + goodsInfoMap.remove(cartInfo.getGoodsId()).getName());
        refreshTotalPrice();
    }

    /**
     * 显示购物车图标中的商品数量
     */
    private void showCount() {
        tvCount.setText(MyApplication.getInstance().goodsCount);
        if (MyApplication.getInstance().goodsCount == 0) {
            //显示
            llEmpty.setVisibility(View.VISIBLE);
            //隐藏
            llContent.setVisibility(View.GONE);
            llCart.removeAllViews();
        }else{
            llContent.setVisibility(View.VISIBLE);
            llEmpty.setVisibility(View.GONE);
        }
    }

    private void refreshTotalPrice() {
        int totalPrice = 0;
        for (CartInfo cartInfo : cartInfoList) {
            GoodsInfo goodsInfo = goodsInfoMap.get(cartInfo.getGoodsId());
            assert goodsInfo != null;
            totalPrice += (int) (cartInfo.getCount() * goodsInfo.getPrice());
        }
        tvTotalPrice.setText(totalPrice);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                //点击返回 关闭当前页面
                finish();
                break;
            case R.id.iv_cart:

                break;
            case R.id.btn_shopping_channel:
                //从购物车页面跳到商城页面
                startActivity(new Intent(this,ShoppingChannelActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.btn_clear:
                dbHelper.deleteAllCartInfo();
                MyApplication.getInstance().goodsCount = 0;
                showCount();
                ViewUtil.showToast(this,"购物车已清空");
                refreshTotalPrice();
                break;
            case R.id.btn_settle:
                //结算
                new AlertDialog.Builder(this)
                        .setTitle("结算商品")
                        .setMessage("抱歉，支付系统尚未开通")
                        .setPositiveButton("我知道了",null)
                        .create().show();
                break;
            default:
        }
    }
}

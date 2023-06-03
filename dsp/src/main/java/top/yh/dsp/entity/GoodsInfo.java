package top.yh.dsp.entity;

import java.util.ArrayList;
import java.util.List;

import top.yh.dsp.R;

/**
 * @user
 * @date
 */
public class GoodsInfo {
    private int id;
    private String name;
    private String description;
    private float price;
    private String picPath;
    private int pic;
    private static String[] nameArray = {
            "iPhone11", "Mate30", "小米10", "OPPO Reno3", "vivo X30", "荣耀30s"
    };
    private static String[] descArray = {
            "Apple Iphone11 256G 绿色 4g全网通手机",
            "华为 HUAWEI Mate30 8GB+256G 丹霞橙 5G全网通 全面屏手机",
            "小米 MI10 8GB+128GB 钛银黑 5G手机 游戏拍照手机",
            "OPPO Reno3 8Gb+128GB 蓝色星夜 双模5G 拍照游戏智能手机",
            "vivo X30 8GB+128GB 绯云 5G全网通 美艳拍照手机",
            "荣耀30s 8GB+128GB 蝶羽红 5G芯片 自拍全面屏手机"
    };
    private static float[] priceArray = {
            6299, 4999, 3999, 2999, 2998, 2399
    };
    private static int[] pirArray = {
            R.mipmap.github, R.mipmap.github, R.mipmap.github,
            R.mipmap.github, R.mipmap.github, R.mipmap.github
    };

    public GoodsInfo(int id, String name, String description, float price, String picPath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.picPath = picPath;
    }

    public GoodsInfo(int id, String name, String description, float price, int pic) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pic = pic;
    }

    public GoodsInfo(String name, String description, float price, int pic) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pic = pic;
    }

    /**
     * 获取默认的手机信息列表
     * @return
     */
    public static List<GoodsInfo> getDefaultList(){
        List<GoodsInfo> goodsInfoList = new ArrayList<>();
        for (int i = 0; i < nameArray.length; i++) {
            GoodsInfo goodsInfo = new GoodsInfo(
                    i,
                    nameArray[i],
                    descArray[i],
                    priceArray[i],
                    pirArray[i]);
            goodsInfoList.add(goodsInfo);
        }
        return goodsInfoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}

package top.yh.mybutton.cal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.CancellationException;

import top.yh.mybutton.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView input;
    /**
     * 第一个操作数
     */
    private String firstNum = "";
    /**
     * 运算符
     */
    private String operator = "";
    /**
     * 第二个操作数
     */
    private String secondNum = "";
    /**
     * 当前的计算结果
     */
    private String result = "";
    /**
     * 显示的文本内容
     */
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //获取input输入框
        input = findViewById(R.id.input);
        //返回的
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        String inputText;
        if (view.getId() == R.id.image_btn_sqrt) {
            inputText = "√";
        } else {
            //除了根号外其他的按钮
            inputText = ((TextView) view).getText().toString();
        }
        switch (view.getId()) {
            //取消按钮
            case R.id.btn_cancel:
                break;
            //清除按钮
            case R.id.btn_clear:
                clear();
                break;
            //加减乘除
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                operator = inputText;
                refreshText(showText + operator);
                break;
            //等号
            case R.id.btn_equal:
                double calResult = calFour();
                refreshOperate(String.valueOf(calResult));
                refreshText(showText + "=" + result);
                break;
            //求导数按钮
            case R.id.btn_reciprocal:
                double reciprocalResult = 1.0 / Double.parseDouble(firstNum);
                refreshOperate(String.valueOf(reciprocalResult));
                refreshText(showText + inputText + "/=" + result);
                break;
                //开平方
            case R.id.image_btn_sqrt:
                double sqrtResult = Math.sqrt(Double.parseDouble(firstNum));
                refreshOperate(String.valueOf(sqrtResult));
                refreshText(showText + inputText + "=" + result);
                break;
                //数字和小数点
            default:
                //上次结果已经出来了
                if (result.length() > 0 && "".equals(operator)) {
                    clear();
                }
                //无运算符，继续拼接第一个
                if ("".equals(operator)) {
                    firstNum += inputText;
                }else{
                    //有运算符
                    secondNum += inputText;
                }
                //判断是否为0
                if ("0".equals(showText) && !".".equals(inputText)) {
                    refreshText(inputText);
                    break;
                }
                refreshText(showText + inputText);
                break;
        }

    }

    /**
     * 四则运算
     * @return
     */
    private double calFour() {
        switch (operator){
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "x":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            case "÷":
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
            default:
                break;
        }
        return 0;
    }

    private void clear() {
        refreshText("");
        refreshOperate("");
    }
    /**
     * 刷新运算结果
     */
    private void refreshOperate(String newResult){
        //计算结果
        result = newResult;
        //第一个数当成了计算结果
        firstNum = result;
        //第二个数和运算符清空
        secondNum = operator = "";
    }
    /**
     * 刷新文本显示
     */
    private void refreshText(String text){
        showText = text;
        input.setText(showText);
    }
}
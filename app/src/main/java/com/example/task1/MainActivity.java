package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Stack<Character> stack = new Stack<>();
    TextView textView, textMath;
    Button digit0, digit1, digit2, digit3, digit4, digit5, digit6, digit7, digit8, digit9, add, div, mul, sub, CE, C, BS, equal;
    String result = "";
    String input = "";
    char toantu = '+';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        init();

    }

    private void init() {
        textView = findViewById(R.id.text_view);
        textMath = findViewById(R.id.text_math);
        digit1 = findViewById(R.id.button_1);
        digit0 = findViewById(R.id.button_0);
        digit2 = findViewById(R.id.button_2);
        digit3 = findViewById(R.id.button_3);
        digit4 = findViewById(R.id.button_4);
        digit5 = findViewById(R.id.button_5);
        digit6 = findViewById(R.id.button_6);
        digit7 = findViewById(R.id.button_7);
        digit8 = findViewById(R.id.button_8);
        digit9 = findViewById(R.id.button_9);
        add = findViewById(R.id.button_add);
        div = findViewById(R.id.button_div);
        mul = findViewById(R.id.button_mul);
        sub = findViewById(R.id.button_sub);
        CE = findViewById(R.id.button_CE);
        BS = findViewById(R.id.button_BS);
        C = findViewById(R.id.button_C);
        equal = findViewById(R.id.button_equal);
        try {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "font_digital/DS-DIGI.TTF");
            textView.setTypeface(typeface);
            textMath.setTypeface(typeface);
        } catch (Exception ex) {
            Log.e("Loi", ex.toString());
        }
        digit9.setOnClickListener(this);
        digit8.setOnClickListener(this);
        digit7.setOnClickListener(this);
        digit6.setOnClickListener(this);
        digit5.setOnClickListener(this);
        digit4.setOnClickListener(this);
        digit3.setOnClickListener(this);
        digit2.setOnClickListener(this);
        digit1.setOnClickListener(this);
        digit0.setOnClickListener(this);
        BS.setOnClickListener(this);
        CE.setOnClickListener(this);
        C.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        div.setOnClickListener(this);
        mul.setOnClickListener(this);
        equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_BS:
                eventBS();
                break;
            case R.id.button_C:
                result = "0";
                input = "";
                textView.setText(result);
                textMath.setText(input);
                break;
            case R.id.button_CE:
                eventCE();
                break;
            case R.id.button_add:
                toantu = '+';
                if (result.equals("")) {
                    input = input.substring(0, input.length() - 1) + toantu;
                } else
                    input += result + toantu;
                result = "";
                textMath.setText(input);
                break;
            case R.id.button_sub:
                toantu = '-';
                if (result.equals("")) {
                    input = input.substring(0, input.length() - 1) + toantu;
                } else
                    input += result + toantu;
                result = "";
                textMath.setText(input);
                break;
            case R.id.button_mul:
                toantu = 'x';
                if (result.equals("")) {
                    input = input.substring(0, input.length() - 1) + toantu;
                } else
                    input += result + toantu;
                result = "";
                textMath.setText(input);
                break;
            case R.id.button_div:
                toantu = '/';
                if (result.equals("")) {
                    input = input.substring(0, input.length() - 1) + toantu;
                } else
                    input += result + toantu;
                result = "";
                textMath.setText(input);
                break;
            case R.id.button_0:
                result += "0";
                textView.setText(result);
                break;
            case R.id.button_1:
                if (check0())
                    result = "";
                result += "1";
                textView.setText(result);
                break;
            case R.id.button_2:
                if (check0())
                    result = "";
                result += "2";
                textView.setText(result);
                break;
            case R.id.button_3:
                if (check0())
                    result = "";
                result += "3";
                textView.setText(result);
                break;
            case R.id.button_4:
                if (check0())
                    result = "";
                result += "4";
                textView.setText(result);
                break;
            case R.id.button_5:
                if (check0())
                    result = "";
                result += "5";
                textView.setText(result);
                break;
            case R.id.button_6:
                if (check0())
                    result = "";
                result += "6";
                textView.setText(result);
                break;
            case R.id.button_7:
                if (check0())
                    result = "";
                result += "7";
                textView.setText(result);
                break;
            case R.id.button_8:
                if (check0())
                    result = "";
                result += "8";
                textView.setText(result);
                break;
            case R.id.button_9:
                if (check0())
                    result = "";
                result += "9";
                textView.setText(result);
                break;
            case R.id.button_equal:
                input += result;
                result =""+ math(input);
                textMath.setText(input);
                textView.setText(result);
                break;
        }
    }

    private boolean check0() {
        if (result.equals("0"))
            return true;
        return false;
    }

    private void eventCE() {
        result = "";
        textView.setText(result);
    }

    private void eventBS() {
        result = result.substring(0, result.length() - 1);
        textView.setText(result);
    }

    private int math(String s) {
        int result = 0;
        ArrayList<String> arrayList = change(createArr(s));
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arrayList.size(); i++) {

            String c = arrayList.get(i);
            if (!(c.equals("+") || c.equals("-") || c.equals("/") || c.equals("x"))) {
                stack.push(Integer.parseInt(c));
            } else {

                    int a = stack.pop();
                    int b = stack.pop();
                    if (c.equals("+")) {
                        result = a + b;
                        stack.push(result);
                    }
                    if (c.equals("-")) {
                        result = b-a;
                        stack.push(result);
                    }
                    if (c.equals("x")) {
                        result = a * b;
                        stack.push(result);
                    }
                    if (c.equals("/")) {
                        result = b/a;
                        stack.push(result);
                    }
                }
            }

        return result;
    }

    private ArrayList<String> createArr(String s) {
        ArrayList<String> arr = new ArrayList<>();
        String a = "";
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                if (!a.equals("")) {
                    arr.add(a);
                    a = "";
                }
                arr.add(s.charAt(i) + "");
            } else {
                a += s.charAt(i);
            }
            if (i == s.length() - 1) {
                arr.add(a);
            }
        }
        return arr;
    }

    private ArrayList<String> change(ArrayList<String> arr) {
        ArrayList<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < arr.size(); i++) {
            String c = arr.get(i);
            if (!(c.equals("+") || c.equals("-") || c.equals("/") || c.equals("x")))
                result.add(c);
            else {
                while (!stack.empty()) {
                    if (c.equals("+") || c.equals("-")) {
                        result.add(stack.pop());
                    } else {
                        String b = stack.peek();
                        if (b.equals("+") || b.equals("-")) {
                            break;
                        }
                        result.add(stack.pop());
                    }
                }
                stack.push(c);
            }
        }
        while (!stack.empty()) {
            result.add(stack.pop());
        }
        return result;
    }
}

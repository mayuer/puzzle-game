package com.example.day03_zgq_pintu;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    // 第一行的图片
    private ImageButton mImageButton_00x00;
    private ImageButton mImageButton_00x01;
    private ImageButton mImageButton_00x02;
    // 第二行的图片
    private ImageButton mImageButton_01x00;
    private ImageButton mImageButton_01x01;
    private ImageButton mImageButton_01x02;
    // 第三行的图片
    private ImageButton mImageButton_02x00;
    private ImageButton mImageButton_02x01;
    private ImageButton mImageButton_02x02;

    private TextView mTextViewTimer;
    private Button mButtonStart;
    // 定义图片的集合
    private int[] mImage = new int[]{R.drawable.img_house_00x00,
            R.drawable.img_house_00x01, R.drawable.img_house_00x02,
            R.drawable.img_house_01x00, R.drawable.img_house_01x01,
            R.drawable.img_house_01x02, R.drawable.img_house_02x00,
            R.drawable.img_house_02x01, R.drawable.img_house_02x02};
    // 定义一个图片下标的集合
    private int[] mImageIdex = new int[9];
    //定义白色按钮
    private int mWbuttonId = R.id.image_02x02;
    //白色按钮的下标
    private int mWbuttonIndex = 8;
    //定義一個幾時變量
    private int mTimer;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                mTextViewTimer.setText("時間:" + mTimer);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化控件
        initView();
        // 初始化数据
        initData();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mTimer++;
                    Message msg = new Message();
                    msg.what = 1;
                    mHandler.sendMessage(msg);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        thread.start();
    }

    private void initView() {
        mImageButton_00x00 = (ImageButton) findViewById(R.id.image_00x00);
        mImageButton_00x01 = (ImageButton) findViewById(R.id.image_00x01);
        mImageButton_00x02 = (ImageButton) findViewById(R.id.image_00x02);

        mImageButton_01x00 = (ImageButton) findViewById(R.id.image_01x00);
        mImageButton_01x01 = (ImageButton) findViewById(R.id.image_01x01);
        mImageButton_01x02 = (ImageButton) findViewById(R.id.image_01x02);

        mImageButton_02x00 = (ImageButton) findViewById(R.id.image_02x00);
        mImageButton_02x01 = (ImageButton) findViewById(R.id.image_02x01);
        mImageButton_02x02 = (ImageButton) findViewById(R.id.image_02x02);

        mTextViewTimer = (TextView) findViewById(R.id.main_textview_timer);

        mButtonStart = (Button) findViewById(R.id.main_button_start);
        mButtonStart.setOnClickListener(this);
        mImageButton_00x00.setOnClickListener(this);
        mImageButton_00x02.setOnClickListener(this);
        mImageButton_00x01.setOnClickListener(this);

        mImageButton_01x00.setOnClickListener(this);
        mImageButton_01x02.setOnClickListener(this);
        mImageButton_01x01.setOnClickListener(this);

        mImageButton_02x00.setOnClickListener(this);
        mImageButton_02x02.setOnClickListener(this);
        mImageButton_02x01.setOnClickListener(this);
    }

    private void initData() {
        //初始化下標
        for (int i = 0; i < mImageIdex.length; i++) {
            mImageIdex[i] = i;
        }
        ImageButton im = (ImageButton) findViewById(mWbuttonId);
        im.setVisibility(View.VISIBLE);

        mWbuttonId = R.id.image_02x02;
        //白色按钮的下标
        mWbuttonIndex = 8;
        //mImageButton_02x02.setVisibility(View.INVISIBLE);
        ImageButton imageW = (ImageButton) findViewById(mWbuttonId);
        imageW.setVisibility(View.INVISIBLE);
        mTimer = 0;
        // 打乱顺序
        int mA, mB;

        for (int i = 0; i < 10; i++) {
            // [0,1)
            mA = (int) (Math.random() * 8);
            do {
                mB = (int) (Math.random() * 8);
            } while (mB == mA);
            exIndex(mA, mB);
        }
        mImageButton_00x00.setBackgroundResource(mImage[mImageIdex[0]]);
        mImageButton_00x01.setBackgroundResource(mImage[mImageIdex[1]]);
        mImageButton_00x02.setBackgroundResource(mImage[mImageIdex[2]]);
        mImageButton_01x00.setBackgroundResource(mImage[mImageIdex[3]]);
        mImageButton_01x01.setBackgroundResource(mImage[mImageIdex[4]]);
        mImageButton_01x02.setBackgroundResource(mImage[mImageIdex[5]]);
        mImageButton_02x00.setBackgroundResource(mImage[mImageIdex[6]]);
        mImageButton_02x01.setBackgroundResource(mImage[mImageIdex[7]]);
        mImageButton_02x02.setBackgroundResource(mImage[mImageIdex[8]]);

    }

    private void exIndex(int mA, int mB) {
        int mC = mImageIdex[mA];
        mImageIdex[mA] = mImageIdex[mB];
        mImageIdex[mB] = mC;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_button_start:
                initData();
                break;
            case R.id.image_00x00:
                exButton(R.id.image_00x00, 0);
                break;
            case R.id.image_00x01:
                exButton(R.id.image_00x01, 1);
                break;
            case R.id.image_00x02:
                exButton(R.id.image_00x02, 2);
                break;
            case R.id.image_01x00:
                exButton(R.id.image_01x00, 3);
                break;
            case R.id.image_01x01:
                exButton(R.id.image_01x01, 4);
                break;
            case R.id.image_01x02:
                exButton(R.id.image_01x02, 5);
                break;
            case R.id.image_02x00:
                exButton(R.id.image_02x00, 6);
                break;
            case R.id.image_02x01:
                exButton(R.id.image_02x01, 7);
                break;
            case R.id.image_02x02:
                exButton(R.id.image_02x02, 8);
                break;
            default:
                break;
        }
    }

    //用来交换按钮的方法
    private void exButton(int mButtonId, int mButtonIndex) {
        //计算白色按钮坐标
        int mWx = mWbuttonIndex % 3;
        int mWy = mWbuttonIndex / 3;
        //所点击按钮的ID
        int mBx = mButtonIndex % 3;
        int mBy = mButtonIndex / 3;

        int x = Math.abs(mWx - mBx);
        int y = Math.abs(mBy - mWy);

        //判断是否在同一行或同一列
        if (x == 0 && y == 1 || x == 1 && y == 0) {
//            Toast.makeText(MainActivity.this, "can exchange", Toast.LENGTH_LONG).show();
            //把白色区域显示图片
            ImageButton imageW = (ImageButton) findViewById(mWbuttonId);
            imageW.setVisibility(View.VISIBLE);
            imageW.setBackgroundResource(mImage[mImageIdex[mButtonIndex]]);

            //把所点击的图片变为白色
            ImageButton imageButtonC = (ImageButton) findViewById(mButtonId);
            imageButtonC.setVisibility(View.INVISIBLE);
            //交換下標數組
            exIndex(mWbuttonIndex, mButtonIndex);
            //重新賦值白色按鈕
            mWbuttonId = mButtonId;
            mWbuttonIndex = mButtonIndex;
            if (gameOver() == true) {
                Toast.makeText(MainActivity.this, "successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean gameOver() {
        for (int i = 0; i < mImageIdex.length; i++) {
            if (mImageIdex[i] != i) {
                return false;
            }
        }
        return true;
    }
}

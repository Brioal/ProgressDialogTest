package com.brioal.progressdialogtest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (progressDialog != null) {
                progressDialog.setProgress(msg.what % 100);
                progressDialog.setSecondaryProgress(msg.what % 100 < 100 ? msg.what % 100 + 20 : 100);

            }
        }
    };
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    Thread.sleep(200);
                    i++;
                    handler.sendEmptyMessage(i);
                } catch (Exception e) {
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new Thread(runnable).start();
    }

    public void showHorizontal(View view) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true); // 能够返回
        progressDialog.setTitle("标题"); // 不设置标题的话图标不会显示
        progressDialog.setMessage("提示信息");
        progressDialog.setCanceledOnTouchOutside(true); // 点击外部返回
        progressDialog.setIndeterminate(true);//设置为不明确,则会再最大最小值之间滚动
        progressDialog.show();
    }


    public void showHorizontal_Icon(View view) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true); // 能够返回
        progressDialog.setCanceledOnTouchOutside(true); // 点击外部返回
        progressDialog.setIcon(R.mipmap.ic_launcher); // 设置图标
        progressDialog.setTitle("标题"); // 不设置标题的话图标不会显示
        progressDialog.setMessage("提示信息");
        progressDialog.show();

    }

    public void Horizontal_Icon_CancleButton(View view) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true); // 能够返回
        progressDialog.setCanceledOnTouchOutside(true); // 点击外部返回
        progressDialog.setIcon(R.mipmap.ic_launcher); // 设置图标
        progressDialog.setTitle("标题"); // 不设置标题的话图标不会显示
        progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });
        progressDialog.setMessage("提示信息");
        progressDialog.show();
    }

    public void Horizontal_Icon_ThreeButton(View view) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true); // 能够返回
        progressDialog.setCanceledOnTouchOutside(true); // 点击外部返回
        progressDialog.setIcon(R.mipmap.ic_launcher); // 设置图标
        progressDialog.setTitle("标题"); // 不设置标题的话图标不会显示
        progressDialog.setButton(ProgressDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });
        progressDialog.setButton(ProgressDialog.BUTTON_NEUTRAL, "中立", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });
        progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });
        progressDialog.setMessage("提示信息");
        progressDialog.show();
    }

    //此种方式创建的只能是圆形的
    public void showSpinner(View view) { //后两个属性设置为是否不可明确 ,是否支持返回小时
        progressDialog = ProgressDialog.show(MainActivity.this, "标题", " 提示信息", false, true);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //使用返回按钮时候的事件
            }
        });
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //Dialog消失时候的事件
            }
        });
    }

    public void showSpinnerIcon(View view) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("标题");
        progressDialog.setMessage("提示信息");
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.show();
    }
}



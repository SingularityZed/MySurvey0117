package com.survey.hzyanglili1.mysurvey.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobeta.android.dslv.DragSortListView;
import com.survey.hzyanglili1.mysurvey.Application.Constants;
import com.survey.hzyanglili1.mysurvey.NetStateChangeReceiver;
import com.survey.hzyanglili1.mysurvey.R;
import com.survey.hzyanglili1.mysurvey.activity.edit.MySurveiesActivity;
import com.survey.hzyanglili1.mysurvey.activity.edit.SurveyPrelookActivity;
import com.survey.hzyanglili1.mysurvey.activity.use.SurveyListActivity;
import com.survey.hzyanglili1.mysurvey.service.NetworkStateService;
import com.survey.hzyanglili1.mysurvey.utils.CountHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends BaseActivity {

    private TextView showBt = null;
    private TextView editBt = null;

    private NetStateChangeReceiver netStateChangeReceiver = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showBt = (TextView)findViewById(R.id.activity_main_show) ;
        editBt = (TextView)findViewById(R.id.activity_main_edit);

        //开启网络状态监控   service方式内存不足时容易被kill
        //Intent intent = new Intent(this, NetworkStateService.class);
        //startService(intent);

        if (netStateChangeReceiver == null){
            netStateChangeReceiver = new NetStateChangeReceiver();

            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(netStateChangeReceiver, filter);
        }


        showBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, SurveyListActivity.class));

            }
        });

        editBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, MySurveiesActivity.class));
            }
        });

    }

    @Override
    protected void onDestroy() {

        if (netStateChangeReceiver != null) {
            unregisterReceiver(netStateChangeReceiver);
        }

        super.onDestroy();
    }
}

package com.trimble.notepadapp.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.trimble.notepadapp.adapter.FileAdapter;
import com.trimble.notepadapp.R;
import com.trimble.notepadapp.util.Billing;
import com.trimble.notepadapp.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, FileAdapter.IFileAdapterCallBack {

    private static final String TAG = "MainActivity";

    private static final String SDCARD_PATH = "sdcard";
    private static final int STORAGE_PERMISSION_REQUEST = 1;
    private static final int SETTINGS_RESULT = 1;
    public static final String FILE_PATH = "File Path";
    private long startTime;
    private RecyclerView mFileListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Billing.getInstance().startCalculate( System.currentTimeMillis());
        mFileListView = findViewById(R.id.rvlistFile);


        requestStoragePermission();

        mFileListView.setLayoutManager(new LinearLayoutManager(this));
        List<File> fileList = new ArrayList<>();
        FileUtil.getListOfFiles(new File(SDCARD_PATH), fileList);
        FileAdapter fileAdapter = new FileAdapter(fileList, this);
        mFileListView.setAdapter(fileAdapter);

        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        FloatingActionButton mFABAddFile = findViewById(R.id.fabAddFile);
        mFABAddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(null);
            }
        });
    }

    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case STORAGE_PERMISSION_REQUEST:
                if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    openSettings();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SETTINGS_RESULT:
                if (checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    break;
                }
                break;
            default:
                break;
        }
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivityForResult(intent, SETTINGS_RESULT);
    }

    @Override
    public void onRefresh() {
        loadRecyclerViewData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecyclerViewData();

    }

    private void loadRecyclerViewData() {
        mSwipeRefreshLayout.setRefreshing(true);
        List<File> fileList = new ArrayList<>();
        FileUtil.getListOfFiles(new File(SDCARD_PATH), fileList);
        FileAdapter fileAdapter = new FileAdapter(fileList, this);
        mFileListView.setAdapter(fileAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void startNewActivity(String filepath) {
        Intent intent = new Intent(this, NewActivity.class);
        if (filepath != null)
            intent.putExtra(FILE_PATH, filepath);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Billing.getInstance().endCalculate( System.currentTimeMillis());

        Toast.makeText(this, "Total cost $" + Billing.getInstance().getAmmount(), Toast.LENGTH_LONG).show();
    }
}

package com.biits.biits_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.biits.biits_test.model.SampleData;
import com.biits.biits_test.support.JSonReader;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public Button mShowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeButton();
        initializeRecyclerView();
    }

    private void initializeButton() {
        mShowButton = findViewById(R.id.show_button);
    }

    private void initializeRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(
                new GridLayoutManager(this, getResources().getInteger(R.integer.res_column_count_1)));
        MainAdapter adapter = new MainAdapter(this, readSampleData());
        mRecyclerView.setAdapter(adapter);
    }

    private ArrayList<SampleData> readSampleData() {
        InputStream inputStream = getResources().openRawResource(R.raw.sample_data);
        Type sampleList = new TypeToken<ArrayList<SampleData>>() {
        }.getType();
        try {
            return JSonReader.readJsonStream(inputStream, sampleList);
        } catch (IOException e) {
            Log.e(TAG, "Error reading JSON product list", e);
            return new ArrayList<>();
        }
    }

}

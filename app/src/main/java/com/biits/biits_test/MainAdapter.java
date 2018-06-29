package com.biits.biits_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.biits.biits_test.model.SampleData;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>  {

    private static final String TAG = "MainAdapter";
    private ArrayList<SampleData> mSampleData;
    private Context mContext;

    MainAdapter(Context context, ArrayList<SampleData> sampleData) {
        mSampleData = sampleData;
        mContext = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new MainViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, final int position) {
        holder.bind(mSampleData.get(position));
        holder.mDesignationET.setTag(R.id.sample_entry);
        ((MainActivity)mContext).mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Sample Data" + mSampleData, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSampleData != null ? mSampleData.size() : 0;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTV;
        private EditText mDesignationET;

        MainViewHolder(View itemView) {
            super(itemView);
            mNameTV = itemView.findViewById(R.id.name_tv);
            mDesignationET = itemView.findViewById(R.id.designation_et);

            mDesignationET.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mSampleData.get(getAdapterPosition()).setDesignation(mDesignationET.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        void bind(SampleData sampleData) {
            mDesignationET.setTag(R.id.sample_entry, sampleData);
            mNameTV.setText(sampleData.getName());
            mDesignationET.setText(sampleData.getDesignation());
        }
    }
}

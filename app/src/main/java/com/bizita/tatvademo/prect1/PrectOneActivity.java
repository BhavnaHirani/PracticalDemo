package com.bizita.tatvademo.prect1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bizita.tatvademo.R;
import com.bizita.tatvademo.databinding.ActivityPrectOneBinding;
import com.bizita.tatvademo.prect2.PrectTwoActivity;

public class PrectOneActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityPrectOneBinding binding;
    LinearLayoutManager layoutManager;
    GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_prect_one);

        layoutManager = new GridLayoutManager(this, 4);
        binding.rvComponent.setLayoutManager(layoutManager);
        binding.rvComponent.setAdapter(gridAdapter);
        binding.btnSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnSearch:

                break;
        }
    }
}
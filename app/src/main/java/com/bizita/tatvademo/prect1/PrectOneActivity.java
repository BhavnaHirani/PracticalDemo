package com.bizita.tatvademo.prect1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.bizita.tatvademo.R;
import com.bizita.tatvademo.databinding.ActivityPrectOneBinding;
import com.bizita.tatvademo.databinding.ItemRawBinding;
import java.util.ArrayList;

public class PrectOneActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityPrectOneBinding binding;
    RecyclerView rv;
    CommonAdapter commonAdapter;
    ArrayList<ModelItem> modelItemArrayList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        binding = DataBindingUtil.setContentView(this,R.layout.activity_prect_one);

        rv = binding.rvComponent;


        binding.btnSearch.setOnClickListener(this);

    }

    private void setDataInAdapter() {

        if (!TextUtils.isEmpty(binding.btnSearch.getText().toString())) {

            int enterNumber = Integer.parseInt(binding.edtSearch.getText().toString());
            linearLayoutManager = new GridLayoutManager(context, enterNumber/2);
            rv.setLayoutManager(linearLayoutManager);
            rv.setItemAnimator(new DefaultItemAnimator());

            modelItemArrayList.clear();

            for (int i = 0; i < enterNumber; i++) {
                Log.e("@Item", "== " + i);
                modelItemArrayList.add(new ModelItem(i, R.drawable.ic_baseline_account_circle_24));
            }

            commonAdapter = new CommonAdapter(context, R.layout.item_raw, modelItemArrayList) {
                @Override
                public void onUpdateView(CommonHolder holder, Object object, int pos) {
                    ModelItem modelItem = modelItemArrayList.get(pos);
                    ItemRawBinding rawBinding = (ItemRawBinding) holder.binding;

                    rawBinding.image.setImageResource(modelItem.getItemIMage());
                }
            };

            rv.setAdapter(commonAdapter);
            commonAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnSearch:
                setDataInAdapter();
                break;
        }
    }
}
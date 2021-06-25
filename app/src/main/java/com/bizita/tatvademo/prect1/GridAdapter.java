package com.bizita.tatvademo.prect1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bizita.tatvademo.R;
import com.bizita.tatvademo.databinding.ItemRawBinding;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRawBinding itemRawBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_raw, parent, false);
        return new GridViewHolder(itemRawBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class GridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ItemRawBinding itemRawBinding;
        public GridViewHolder(@NonNull ItemRawBinding itemRawBinding) {
            super(itemRawBinding.getRoot());
            this.itemRawBinding = itemRawBinding;
        }

        @Override
        public void onClick(View v) {

        }
    }
}

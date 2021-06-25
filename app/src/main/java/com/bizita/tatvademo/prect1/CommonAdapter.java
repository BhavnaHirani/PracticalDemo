package com.bizita.tatvademo.prect1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.CommonHolder>{

    public Context context;
    public int layoutId;
    public ArrayList<? super Object> data;

    public CommonAdapter(Context context, int layoutId, List<?> arrayList){
        this.context = context;
        this.layoutId = layoutId;
        this.data = (ArrayList<? super Object>) arrayList;
    }

    public void setData(ArrayList<?> data){
        this.data = (ArrayList<? super Object>) data;
        notifyDataSetChanged();
    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),viewType, parent, false);
        return new CommonHolder(binding);
    }

    @Override
    public void onBindViewHolder(CommonHolder holder, int position) {
        onUpdateView(holder,this.data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutId;
    }

    public class CommonHolder extends RecyclerView.ViewHolder{

        public ViewDataBinding binding;

        public CommonHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public abstract void onUpdateView(CommonHolder holder, Object object, int pos);

}

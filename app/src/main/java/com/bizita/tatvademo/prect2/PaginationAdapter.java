package com.bizita.tatvademo.prect2;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bizita.tatvademo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.LinkedList;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<UserModel.User> userList;
    private static final int LOADING = 0;
    private static final int ITEM = 1;
    private boolean isLoadingAdded = false;

    public PaginationAdapter(Context context){
        this.context = context;
        userList = new LinkedList<>();
    }

    public void setUserList(List<UserModel.User> userModelList){
        this.userList = userModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){

            case ITEM:
                View viewItem = inflater.inflate(R.layout.item_user,parent,false);
                viewHolder = new UserViewHolder(viewItem);
                break;

            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress,parent,false);
                viewHolder = new LoadingViewHolder(viewLoading);
                break;

        }
        return viewHolder;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView email;
        private TextView fname;
        private ImageView avatar;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.tvEmail);
            fname = (TextView) itemView.findViewById(R.id.tvName);
            avatar = (ImageView) itemView.findViewById(R.id.imgavatar);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder{

        private ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserModel.User userModel = userList.get(position);
        switch (getItemViewType(position)){
            case ITEM:
                UserViewHolder userViewHolder = (UserViewHolder) holder;
                userViewHolder.fname.setText(userModel.getFirstName() + "  " +userModel.getLastName());
                userViewHolder.email.setText(userModel.getEmail());
                Glide.with(context).load(userModel.getAvatar()).apply(RequestOptions.centerCropTransform()).into(userViewHolder.avatar);
                break;
            case LOADING:
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == userList.size() -1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void addLoadingFooter(){
        isLoadingAdded = true;
        add(new UserModel.User());
    }

    public void add(UserModel.User user){
        userList.add(user);
        notifyItemInserted(userList.size()-1);
    }

    public void removeLoadingFooter(){
        isLoadingAdded = false;
        int position = userList.size()-1;
        UserModel.User userModel = getItem(position);
        if (userModel != null){
            userList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public UserModel.User getItem(int position){
        return userList.get(position);
    }

   public void addAll(List<UserModel.User> userModels){
        for(UserModel.User model: userModels){
            add(model);
        }
    }
}

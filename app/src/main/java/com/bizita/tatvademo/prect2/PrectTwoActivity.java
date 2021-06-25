package com.bizita.tatvademo.prect2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bizita.tatvademo.R;
import com.bizita.tatvademo.databinding.ActivityPrectTwoBinding;
import com.bizita.tatvademo.prect1.CommonAdapter;
import com.bizita.tatvademo.prect1.ModelItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrectTwoActivity extends AppCompatActivity {

    ActivityPrectTwoBinding binding;

    RecyclerView rv;
    PaginationAdapter paginationAdapter;
    private boolean isLoading = false;
    private boolean isLastpage = false;
    private int currentPage = 1;
    MyApi myApi;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_prect_two);

        myApi = RetroAPIClient.getClient().create(MyApi.class);
        rv = binding.rvPaging;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        paginationAdapter = new PaginationAdapter(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(paginationAdapter);

        rv.addOnScrollListener(new PaginationScrollListner(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading= true;
                currentPage +=1;
                loadNextPage();

            }

            @Override
            public boolean isLastPage() {
                return isLastpage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();
    }

    private void loadNextPage() {
        myApi.getUserData().enqueue(new Callback<List<UserModel.User>>() {
            @Override
            public void onResponse(Call<List<UserModel.User>> call, Response<List<UserModel.User>> response) {
                paginationAdapter.removeLoadingFooter();
                isLoading = false;

                List<UserModel.User> userModels = response.body();
                paginationAdapter.addAll(userModels);

                if (currentPage != 100) paginationAdapter.addLoadingFooter();
                else isLastpage = true;
            }

            @Override
            public void onFailure(Call<List<UserModel.User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadFirstPage() {
        myApi.getUserData().enqueue(new Callback<List<UserModel.User>>() {
            @Override
            public void onResponse(Call<List<UserModel.User>> call, Response<List<UserModel.User>> response) {
                Log.e("@Response","=="+response.body().toString());
                List<UserModel.User> userModels = response.body();
                binding.progress.setVisibility(View.GONE);
                paginationAdapter.addAll(userModels);

                if (currentPage <= 100) paginationAdapter.addLoadingFooter();
                else isLastpage = true;
            }

            @Override
            public void onFailure(Call<List<UserModel.User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
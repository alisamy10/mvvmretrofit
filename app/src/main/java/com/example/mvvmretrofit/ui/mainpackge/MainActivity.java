package com.example.mvvmretrofit.ui.mainpackge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvmretrofit.R;
import com.example.mvvmretrofit.model.PostsModel;


import java.util.List;


public class MainActivity extends AppCompatActivity {

    PostAdapter postAdapter;
    RecyclerView recyclerView;
    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRec();

         mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getData();

        observeToLiveData();




    }

    private void observeToLiveData() {
        mainViewModel.data.observe(this, new Observer<List<PostsModel>>() {
            @Override
            public void onChanged(List<PostsModel> postsModels) {
                postAdapter.setList(postsModels);
            }
        });

        mainViewModel.msg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRec() {
        postAdapter =new PostAdapter();

        recyclerView=findViewById(R.id.rec);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }
}

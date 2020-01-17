package com.example.mvvmretrofit.ui.mainpackge;

import androidx.lifecycle.MutableLiveData;
import com.example.mvvmretrofit.apimanger.ApiManger;
import com.example.mvvmretrofit.model.PostsModel;
import com.example.mvvmretrofit.roomdatabase.MyDataBase;
import java.util.List;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RepositryPost {
    public MutableLiveData<List<PostsModel>> data = new MutableLiveData<>();
   // public MutableLiveData <String> msg=new MutableLiveData<>();

    public void getData(){

        ApiManger.getApis().getPostModel().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<PostsModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onSuccess(final List<PostsModel> postsModels) {
                data.setValue(postsModels);
                cahcePostToRoom(postsModels);
            }
            @Override
            public void onError(Throwable e) {
                getAllPostFromRoom();
            }
        });
    }
    private void getAllPostFromRoom() {
        Thread thread =new Thread(){
            @Override
            public void run() {
                super.run();
                data.postValue(MyDataBase.getInstance().postDao().getAllPosts());
            }
        };
        thread.start();
    }
    private void cahcePostToRoom(final List<PostsModel>postsModels) {
        Thread thread =new Thread(){
            public void run(){
                super.run();
                MyDataBase.getInstance().postDao().cahePosts(postsModels);
            }
        };
        thread.start();
    }
}

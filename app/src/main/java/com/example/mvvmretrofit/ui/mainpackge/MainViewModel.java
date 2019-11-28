package com.example.mvvmretrofit.ui.mainpackge;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmretrofit.apimanger.ApiManger;
import com.example.mvvmretrofit.model.PostsModel;
import com.example.mvvmretrofit.roomdatabase.MyDataBase;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

   public MutableLiveData <List<PostsModel>>data = new MutableLiveData<>();
   public MutableLiveData <String> msg=new MutableLiveData<>();

    public void getData(){

        ApiManger.getApis().getPostModel().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<PostsModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(final List<PostsModel> postsModels) {
                //save Data to room

                data.setValue(postsModels);
                Thread thread =new Thread(){
                    @Override
                    public void run(){
                        super.run();
                        MyDataBase.getInstance().postDao().cahePosts(postsModels);
                    }
                };
                thread.start();
            }

            @Override
            public void onError(Throwable e) {
                msg.setValue("jhjh"+e.getLocalizedMessage());
                //getDatafromroom
                Thread thread =new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        MyDataBase.getInstance().postDao().getAllPosts();
                    }
                };
                thread.start();

            }
        });

    }
}

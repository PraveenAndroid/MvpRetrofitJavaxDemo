package com.example.pc.mvpretrofitjavaxdemo.mvp;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.pc.mvpretrofitjavaxdemo.model.MovieResponse;
import com.example.pc.mvpretrofitjavaxdemo.network.NetworkClient;
import com.example.pc.mvpretrofitjavaxdemo.network.NetworkInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 8/29/2018.
 */

public class MainPresenterImpl implements MainPresenterInterface {

    MainViewInterface mvi;
    private String TAG = "MainPresenter";

    public MainPresenterImpl(MainViewInterface mvi) {
        this.mvi = mvi;
    }


    public Observable<MovieResponse> getObservable(){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getMovies("004cbaf19212094e32aa9ef6f6577f22")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    @Override
    public void getMovies() {

        getObservable().subscribeWith(getObserver());
    }

    public DisposableObserver<MovieResponse> getObserver(){
        return new DisposableObserver<MovieResponse>() {

            @Override
            public void onNext(@NonNull MovieResponse movieResponse) {
                Log.d(TAG,"OnNext"+movieResponse.getTotalResults());
                mvi.displayMovies(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {

                Log.d(TAG,"Completed");
            }
        };
    }
}

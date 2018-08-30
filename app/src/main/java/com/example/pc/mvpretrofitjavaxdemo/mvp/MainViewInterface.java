package com.example.pc.mvpretrofitjavaxdemo.mvp;
import com.example.pc.mvpretrofitjavaxdemo.model.MovieResponse;
/**
 * Created by pc on 8/29/2018.
 */

public interface MainViewInterface {

    void showToast(String s);
    void displayMovies(MovieResponse movieResponse);
    void displayError(String s);
}

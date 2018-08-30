package com.example.pc.mvpretrofitjavaxdemo.network;
import com.example.pc.mvpretrofitjavaxdemo.model.MovieResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pc on 8/29/2018.
 */

public interface NetworkInterface {

    @GET("discover/movie")
    Observable<MovieResponse> getMovies(@Query("api_key") String api_key);
}

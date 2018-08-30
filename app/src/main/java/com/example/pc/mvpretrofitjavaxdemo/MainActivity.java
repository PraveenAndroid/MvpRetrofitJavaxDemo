package com.example.pc.mvpretrofitjavaxdemo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;
import com.example.pc.mvpretrofitjavaxdemo.adapter.MoviesAadapter;
import com.example.pc.mvpretrofitjavaxdemo.model.MovieResponse;
import com.example.pc.mvpretrofitjavaxdemo.mvp.MainPresenterImpl;
import com.example.pc.mvpretrofitjavaxdemo.mvp.MainViewInterface;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;

    //Added in Part 2 of the series
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String TAG = "MainActivity";
    RecyclerView.Adapter adapter;
    MainPresenterImpl mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setupMVP();
        setupViews();
        getMovieList();

    }

    private void getMovieList() {

        mainPresenter.getMovies();
    }

    private void setupViews() {

        setSupportActionBar(toolbar);

        rvMovies.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupMVP() {

        mainPresenter = new MainPresenterImpl(this);
    }

    @Override
    public void showToast(String s) {

        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        if(movieResponse!=null) {

            Log.d(TAG,movieResponse.getResults().get(1).getTitle());
            Log.d(TAG,movieResponse.getTotalResults()+"");
            adapter = new MoviesAadapter(movieResponse.getResults(), MainActivity.this);
            rvMovies.setAdapter(adapter);

        }else{
            Log.d(TAG,"Movies response null");
        }
    }

    @Override
    public void displayError(String s) {

        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
    }
}

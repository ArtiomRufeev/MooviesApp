package com.itrexgroup.test.moovies.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.itrexgroup.test.moovies.R;
import com.itrexgroup.test.moovies.db.DBHelper;
import com.itrexgroup.test.moovies.db.MoovieMapper;
import com.itrexgroup.test.moovies.db.MoovieRealm;
import com.itrexgroup.test.moovies.model.Moovie;
import com.itrexgroup.test.moovies.model.MooviesListResponse;
import com.itrexgroup.test.moovies.network.MooviesApiService;
import com.itrexgroup.test.moovies.network.MooviesServiceHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MooviesAdapter.OnMoovieClickListener {

    public static final String FILENAME = "moovies.realm";
    @BindView(R.id.rv_moovies)
    RecyclerView rvMoovies;

    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.layout_error)
    LinearLayout layoutError;

    private MooviesAdapter mooviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name(FILENAME).build();
        Realm.setDefaultConfiguration(config);

        mooviesAdapter = new MooviesAdapter(this);

        mooviesAdapter.setItems(DBHelper.retrieveMooviesFromRealm());
        rvMoovies.setAdapter(mooviesAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(rvMoovies.getContext(), LinearLayoutManager.VERTICAL, false);
        rvMoovies.setLayoutManager(layoutManager);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMoovies();
            }
        });
        getMoovies();
    }

    private void getMoovies() {
        hideError();
        swipeRefresh.setRefreshing(true);
        MooviesApiService api = MooviesServiceHelper.getApiService();

        Call<MooviesListResponse> call = api.getMooviesList();
        call.enqueue(new Callback<MooviesListResponse>() {
            @Override
            public void onResponse(Call<MooviesListResponse> call, Response<MooviesListResponse> response) {
                swipeRefresh.setRefreshing(false);
                if (response.isSuccessful()) {
                    ArrayList<Moovie> moovies = response.body().getMoovies();
                    DBHelper.saveMooviesToRealm(moovies);
                    mooviesAdapter.setItems(moovies);
                } else {
                    showDefaultError(mooviesAdapter.getItemCount() == 0);
                }
            }

            @Override
            public void onFailure(Call<MooviesListResponse> call, Throwable t) {
                showDefaultError(mooviesAdapter.getItemCount() == 0);
                swipeRefresh.setRefreshing(false);
            }
        });
    }


    private void showDefaultError(boolean emptyCache) {
        layoutError.setVisibility(emptyCache ? View.VISIBLE : View.GONE);
        if (!emptyCache) {
            Toast.makeText(this, R.string.loading_error, Toast.LENGTH_SHORT).show();
        }
    }


    private void hideError() {
        layoutError.setVisibility(View.GONE);
    }

    @Override
    public void onMoovieClick(Moovie moovie) {
        MoovieDetailActivity.start(this, moovie);
    }

    @OnClick(R.id.btn_retry)
    void onRetryClicked() {
        getMoovies();
    }
}

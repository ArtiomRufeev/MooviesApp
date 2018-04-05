package com.itrexgroup.test.moovies.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.itrexgroup.test.moovies.R;
import com.itrexgroup.test.moovies.model.Moovie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_MOOVIE = "key_moovie";

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_name_eng)
    TextView tvNameEng;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    @BindView(R.id.tv_premiere)
    TextView tvPremiere;

    @BindView(R.id.iv_poster)
    ImageView ivPoster;

    public static void start(Context context, Moovie moovie) {
        Intent intent = new Intent(context, MoovieDetailActivity.class);
        intent.putExtra(EXTRA_KEY_MOOVIE, moovie);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Moovie moovie = ((Moovie) getIntent().getExtras().getSerializable(EXTRA_KEY_MOOVIE));
        fillInData(moovie);
        setTitle(moovie.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void fillInData(Moovie moovie) {
        RequestOptions options = new RequestOptions();
        options.fitCenter();

        Glide.with(this).load(moovie.getImage()).apply(options).into(ivPoster);
        tvDescription.setText(moovie.getDescription());
        tvPremiere.setText(moovie.getPremiere());
        tvName.setText(moovie.getName());
        tvNameEng.setText(moovie.getNameEng());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}


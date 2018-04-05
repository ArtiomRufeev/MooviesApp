package com.itrexgroup.test.moovies.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itrexgroup.test.moovies.R;
import com.itrexgroup.test.moovies.model.Moovie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MooviesAdapter extends RecyclerView.Adapter<MooviesAdapter.MoovieVH> {

    private List<Moovie> items = new ArrayList<>();

    private OnMoovieClickListener onMoovieClickListener;

    interface OnMoovieClickListener {

        void onMoovieClick(Moovie moovie);
    }

    MooviesAdapter(OnMoovieClickListener onMoovieClickListener) {
        this.onMoovieClickListener = onMoovieClickListener;
    }

    void setItems(List<Moovie> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MoovieVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_moovie, parent, false);

        return new MoovieVH(view);
    }

    @Override
    public void onBindViewHolder(final MoovieVH holder, final int position) {
        final Moovie item = getItem(position);
        Glide.with(holder.ivThumbnail).load(item.getImage()).into(holder.ivThumbnail);
        holder.tvMoovieName.setText(holder.tvMoovieName.getResources().getString(R.string.name_placeholder, item.getName(), item.getNameEng()));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMoovieClickListener.onMoovieClick(items.get(position));
            }
        });
    }

    private Moovie getItem(int position) {
        return items.get(position);
    }

    static class MoovieVH extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_thumbnail)
        ImageView ivThumbnail;

        @BindView(R.id.tv_name)
        TextView tvMoovieName;

        View view;

        MoovieVH(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }
}

package com.androidnights.animerealmexample.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnights.animerealmexample.R;
import com.androidnights.animerealmexample.realm.models.Anime;
import com.androidnights.animerealmexample.utils.Utility;
import com.androidnights.animerealmexample.widgets.SquareImageView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Condesa on 03/03/16.
 */
public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder>
    implements RealmChangeListener{

    private final RealmResults<Anime> animes;
    private AppCompatActivity appCompatActivity;
    private AnimeListener animeListener;

    public AnimeAdapter(RealmResults<Anime> animes, AppCompatActivity appCompatActivity) {
        this.animes = animes;
        this.appCompatActivity = appCompatActivity;
        animes.addChangeListener(this);
        try{
            animeListener = (AnimeListener) appCompatActivity;
        }catch (ClassCastException e){
            throw new ClassCastException(appCompatActivity.toString()
                    + " must implement AnimeListener");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_item_anime, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Anime anime = animes.get(position);
        Picasso.with(appCompatActivity)
                .load(anime.getImage())
                .into(holder.image);
        holder.labelTitle.setText(anime.getTitle());
        holder.labelGenders.setText(Utility.getGenresInfo(anime));
        holder.containerHolder.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                animeListener.onLongClick(anime.getId());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    @Override
    public void onChange() {
        notifyDataSetChanged();
    }

    public interface AnimeListener {
        void onLongClick(int animeId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.container_holder)
        ViewGroup containerHolder;
        @Bind(R.id.label_title)
        TextView labelTitle;
        @Bind(R.id.label_genders)
        TextView labelGenders;
        @Bind(R.id.image)
        SquareImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.karan.musicwiki.activities;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.karan.musicwiki.R;
import com.karan.musicwiki.adapter.GenreAdapter;
import com.karan.musicwiki.api.GenreApiInterface;
import com.karan.musicwiki.apidispatchers.GenreApiDispatcher;
import com.karan.musicwiki.database.entity.Genre;
import com.karan.musicwiki.database.entity.Tag;
import com.karan.musicwiki.utils.ApiCompletionHandlerInterface;
import com.karan.musicwiki.utils.Constant;
import com.karan.musicwiki.utils.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements RecyclerViewClickListener {


    @BindView(R.id.gene_rv)
    RecyclerView recyclerView;
    @BindView(R.id.expand_btn)
    Button expand;

    @Inject
    GenreApiInterface genreApiInterface;
    private GenreAdapter genreAdapter;
    private List<Tag> tagsList;
    private List<Tag> tagsList1;
    private List<Tag> tagList2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getGenre(Constant.SECRET_KEY);
    }

    public void getGenre(String key) {
        GenreApiDispatcher
                .getGenre(genreApiInterface, key, "chart.gettoptags", "json", new ApiCompletionHandlerInterface<Genre>() {
                    @Override
                    public void completed(Genre resource) {
                        if (resource.getTags().getTag().size() != 0) {
                            tagsList = resource.getTags().getTag();
                            initView();
                        }

                    }

                    @Override
                    public void failed(int code) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_message), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(Throwable t) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_message), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
    }

    public void initView() {
        recyclerView.setHasFixedSize(true);
        tagsList1 = new ArrayList<>();
        tagList2 = new ArrayList<>();
        for (int i = 0; i < tagsList.size(); i++) {
            if (i < 10) {
                tagsList1.add(tagsList.get(i));
            } else {
                tagList2.add(tagsList.get(i));
            }
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        genreAdapter = new GenreAdapter(getApplicationContext(), tagsList1, this::recyclerViewListClicked);
        recyclerView.setAdapter(genreAdapter);
    }

    @OnClick(R.id.expand_btn)
    public void changeButton() {
        if (expand.getBackground().getConstantState() == getResources().getDrawable(R.drawable.ic_down_arrow).getConstantState()) {
            expand.setBackground(getResources().getDrawable(R.drawable.ic_up_arrow));
            genreAdapter.setAddtionalData(tagList2);
        } else {
            expand.setBackground(getResources().getDrawable(R.drawable.ic_down_arrow));
            genreAdapter.removeAddtionalData(tagList2);
        }


    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Intent intent = new Intent(getApplicationContext(), GenreDetailsActivity.class);
        String genre = tagsList.get(position).getName();
        intent.putExtra("genre", genre);
        startActivity(intent);
    }
}
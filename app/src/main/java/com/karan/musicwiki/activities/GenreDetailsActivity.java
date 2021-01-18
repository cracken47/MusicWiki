package com.karan.musicwiki.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.karan.musicwiki.R;
import com.karan.musicwiki.adapter.GenreAdapter;
import com.karan.musicwiki.api.GenreApiInterface;
import com.karan.musicwiki.apidispatchers.GenreApiDispatcher;
import com.karan.musicwiki.database.entity.GenreDetails;
import com.karan.musicwiki.database.entity.Tag;
import com.karan.musicwiki.fragments.FragmentAlbum;
import com.karan.musicwiki.fragments.FragmentArtists;
import com.karan.musicwiki.fragments.FragmentTracks;
import com.karan.musicwiki.utils.ApiCompletionHandlerInterface;
import com.karan.musicwiki.utils.Constant;
import com.karan.musicwiki.utils.ViewPagerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenreDetailsActivity extends BaseActivity {


    public static String selectedTab = "";
    @BindView(R.id.name_tv)
    TextView genreName;
    @BindView(R.id.genre_details_tv)
    TextView genreDescription;
    @BindView(R.id.viewpager_orderhome)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @Inject
    GenreApiInterface genreApiInterface;
    FragmentAlbum fragmentAlbum;
    FragmentArtists fragmentArtists;
    FragmentTracks fragmentTracks;
    private GenreAdapter genreAdapter;
    private List<Tag> tagsList;
    private String genreNameString;
    private GenreDetails genreDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        ButterKnife.bind(this);
        genreNameString = getIntent().getExtras().getString("genre");
        getGenreDetails(genreNameString, Constant.SECRET_KEY);
        fragmentAlbum = new FragmentAlbum();
        fragmentArtists = new FragmentArtists();
        fragmentTracks = new FragmentTracks();
        Bundle bundle = new Bundle();
        bundle.putString("genre", genreNameString);
        fragmentAlbum.setArguments(bundle);
        fragmentArtists.setArguments(bundle);
        fragmentTracks.setArguments(bundle);
    }

    public void getGenreDetails(String tagName, String key) {
        GenreApiDispatcher
                .getGenreDetails(genreApiInterface, key, tagName, new ApiCompletionHandlerInterface<GenreDetails>() {
                    @Override
                    public void completed(GenreDetails resource) {
                        genreDetails = resource;
                        initView();
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
        genreName.setText(genreNameString);
        genreDescription.setText(genreDetails.getTag().getWiki().getSummary());
        setUpViewPagerLayout();
    }

    public void setUpViewPagerLayout() {

        setupViewPager(viewPager);
        setCurrentTab();
    }

    private void setCurrentTab() {
        if (selectedTab.equals("ALBUM")) {
            viewPager.setCurrentItem(1);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fragmentAlbum, "ALBUM");
        adapter.addFragment(fragmentArtists, "ARTIST");
        adapter.addFragment(fragmentTracks, "TRACKS");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }

}
package com.example.alquran.p.listsurah;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquran.R;
import com.example.alquran.dataalquran.BaseActi;
import com.example.alquran.model.Surah;
import com.example.alquran.p.listayat.ListAyatActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class ListSurahActivity extends BaseActi<ListSurahPresenter> implements ListViewSurah, ListSurahAdapter.OnSurahItemClick {

    @BindView(R.id.listSurah)
    RecyclerView recyclerView;

    @BindView(R.id.mainDrawer)
    DrawerLayout mainDrawer;


    private ListSurahAdapter listSurahAdapter;
    private String loadTerjemahan = LOAD_INDONESIA;

    @Override
    public ListSurahPresenter initPresenter() {
        return new ListSurahPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_surah);



        listSurahAdapter = new ListSurahAdapter(new Surahcallback(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listSurahAdapter);

        mPresenter.loadSurah(loadTerjemahan);



        }


    @Override
    public void onLoad(ArrayList<Surah> data) {
        listSurahAdapter.submitList(data);
    }

    @Override
    public void onCLick(Surah surah) {
        Intent ayat = new Intent(ListSurahActivity.this, ListAyatActivity.class);
        ayat.putExtra(ListAyatActivity.KEY_AYAT, surah.getAyat());
        ayat.putExtra(ListAyatActivity.KEY_SURAH, surah.getSurah());
        ayat.putExtra(ListAyatActivity.KEY_TERJEMAHAN, surah.getTerjemahan());
        ayat.putExtra(ListAyatActivity.KEY_LOAD_TERJEMAHAN, loadTerjemahan);
        startActivity(ayat);
    }
}


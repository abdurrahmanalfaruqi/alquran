package com.example.alquran.p.listayat;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquran.R;
import com.example.alquran.dataalquran.BaseActi;
import com.example.alquran.model.Ayat;

import java.util.ArrayList;

import butterknife.BindView;

public class ListAyatActivity extends BaseActi<ListAyatPresenter> implements ListAyatview {


    @BindView(R.id.listayat)
    RecyclerView listAyat;

    private ListAyatAdapter listAyatAdapter;

    public static final String KEY_SURAH = "surah";
    public static final String KEY_AYAT = "ayat";
    public static final String KEY_TERJEMAHAN = "terjemahan";
    public static final String KEY_LOAD_TERJEMAHAN = "load_terjemahan";

    @Override
    public ListAyatPresenter initPresenter() {
        return new ListAyatPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_ayat);

        String surah = getIntent().getStringExtra(KEY_SURAH);
        String ayat = getIntent().getStringExtra(KEY_AYAT);
        String terjemahan = getIntent().getStringExtra(KEY_TERJEMAHAN);
        String loadTerjemahan = getIntent().getStringExtra(KEY_LOAD_TERJEMAHAN);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(ayat);
            getSupportActionBar().setSubtitle(terjemahan);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        listAyatAdapter = new ListAyatAdapter(new AyatDifcallback());

        listAyat.setHasFixedSize(true);
        listAyat.setLayoutManager(new LinearLayoutManager(this));
        listAyat.setAdapter(listAyatAdapter);

        mPresenter.loadAyat(surah, loadTerjemahan);
    }

    @Override
    public void onLoad(ArrayList<Ayat> data) {
        listAyatAdapter.submitList(data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


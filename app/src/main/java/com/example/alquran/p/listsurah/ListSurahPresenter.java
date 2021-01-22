package com.example.alquran.p.listsurah;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.alquran.dataalquran.BasePre;
import com.example.alquran.database.DataHelper;
import com.example.alquran.database.DatabaseContract;
import com.example.alquran.model.Surah;

import java.util.ArrayList;

public class ListSurahPresenter extends BasePre<ListViewSurah> {

    ListSurahPresenter(ListViewSurah view) {
        super.attach(view);
    }

    void loadSurah(String loadTerjemahan) {
        SQLiteDatabase database = DataHelper.getDatabase();
        Cursor cursor = database.query(DatabaseContract.TableSurah.TABLE_SURAH, null, null, null, null, null, null);

        ArrayList<Surah> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String surah = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableSurah.SURAH));
                String ayat = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableSurah.AYAT));
                String terjemahan = cursor.getString(cursor.getColumnIndexOrThrow(loadTerjemahan));
                String jumlahAyat = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableSurah.JUMLAH_AYAT));

                data.add(new Surah(surah , ayat , terjemahan , jumlahAyat));
            } while (cursor.moveToNext());
        }
        View.onLoad(data);

        cursor.close();
        database.close();
    }
}



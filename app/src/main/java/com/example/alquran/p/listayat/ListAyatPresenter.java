package com.example.alquran.p.listayat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alquran.dataalquran.BasePre;
import com.example.alquran.database.DataHelper;
import com.example.alquran.database.DatabaseContract;
import com.example.alquran.model.Ayat;

import java.util.ArrayList;

public class ListAyatPresenter  extends BasePre<ListAyatview> {

    ListAyatPresenter(ListAyatview view) {
        super.attach(view);
    }

    void loadAyat(String loadSurah, String loadTerjemahan) {
        SQLiteDatabase database = DataHelper.getDatabase();
        Cursor cursor = database.query(DatabaseContract.TableAyat.TABLE_AYAT, null, DatabaseContract.TableAyat.SURAH + " LIKE '" + loadSurah + "'", null, null, null, null);

        ArrayList<Ayat> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String surah = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableAyat.SURAH));
                String ayat = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableAyat.AYAT));
                String arab = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableAyat.TOTALAYAT));
                String terjemahan = cursor.getString(cursor.getColumnIndexOrThrow(loadTerjemahan));

                data.add(new Ayat(surah, ayat, arab, terjemahan));
            } while (cursor.moveToNext());
        }
        View.onLoad(data);

        cursor.close();
        database.close();
    }
}


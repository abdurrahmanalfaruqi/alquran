package com.example.alquran.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

    private static DataHelper helper;

    public static void initDatabase(Context context) {
        if (helper == null) {
            helper = new DataHelper(context);
        }
    }

    public static SQLiteDatabase getDatabase() {
        return helper.getWritableDatabase();
    }

    private DataHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.TableSurah.CREATE_TABLE);
        db.execSQL(DatabaseContract.TableAyat.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

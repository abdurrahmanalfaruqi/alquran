package com.example.alquran.p.splashscreen;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;

import com.example.alquran.dataalquran.BasePre;
import com.example.alquran.database.DataHelper;
import com.example.alquran.database.DatabaseContract;
import com.example.alquran.model.ModelAyat;
import com.example.alquran.model.ModelSurah;
import com.example.alquran.t.RawParser;

import java.io.IOException;
import java.util.List;

public class SplashscreenPresenter extends BasePre<SplashscreenView> {

    SplashscreenPresenter(SplashscreenView mView) {
        super.attach(mView);
    }

    void startPrepareData() {
        new PrepareData(View).execute();
    }

    private static class PrepareData extends AsyncTask<Void, Integer, Void> {

        private SplashscreenView view;
        private SQLiteDatabase database;

        PrepareData(SplashscreenView view) {
            this.view = view;
            database = DataHelper.getDatabase();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.onPrepare();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            int progress = 0;
            publishProgress(progress);

            try {
                database.beginTransaction();

                SQLiteStatement statement = database.compileStatement(DatabaseContract.TableSurah.QUERY_STATEMENT);
                List<ModelSurah> surahList = RawParser.getRawSurah();
                for (ModelSurah surah : surahList) {
                    statement.bindString(1, surah.getSurah());
                    statement.bindString(2, surah.getAyat());
                    statement.bindString(3, surah.getTerjemahanIndonesia());
                    statement.bindString(4, surah.getTerjemahanEnglish());
                    statement.bindString(5, surah.getJumlahAyat());
                    statement.execute();
                    statement.clearBindings();
                    progress++;
                    publishProgress(progress);
                }

                Thread.sleep(2000);

                statement = database.compileStatement(DatabaseContract.TableAyat.QUERY_STATEMENT);
                List<ModelAyat> ayatList = RawParser.getRawAyat();
                for (ModelAyat ayat : ayatList) {
                    statement.bindString(1, ayat.getSurah());
                    statement.bindString(2, ayat.getAyat());
                    statement.bindString(3, ayat.getArab());
                    statement.bindString(4, ayat.getTerjemahanIndonesia());
                    statement.bindString(5, ayat.getTerjemahanEnglish());
                    statement.execute();
                    statement.clearBindings();
                    progress++;
                    publishProgress(progress);
                }

                Thread.sleep(2000);
                database.setTransactionSuccessful();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                database.endTransaction();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            view.onProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            view.onSuccess();
        }
    }
}



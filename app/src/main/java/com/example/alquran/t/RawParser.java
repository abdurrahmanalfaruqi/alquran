package com.example.alquran.t;

import com.example.alquran.App;
import com.example.alquran.R;
import com.example.alquran.model.ModelAyat;
import com.example.alquran.model.ModelSurah;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RawParser {
    public static List<ModelAyat> getRawAyat() throws IOException {
        BufferedReader reader = App.getRawResources(R.raw.ayat);
        List<ModelAyat> ayatList = new ArrayList<>();

        String rawAyat;
        while ((rawAyat = reader.readLine()) != null) {
            String[] rawAyats = rawAyat.split("//");
            ayatList.add(new ModelAyat(rawAyats[0], rawAyats[1], rawAyats[2], rawAyats[3], rawAyats[4]));
        }

        return ayatList;
    }

    public static List<ModelSurah> getRawSurah() throws IOException {
        BufferedReader reader = App.getRawResources(R.raw.surah);
        List<ModelSurah> surahList = new ArrayList<>();

        String rawSurah;
        while ((rawSurah = reader.readLine()) != null) {
            String[] rawSurahs = rawSurah.split("//");

            if (rawSurahs.length < 5){
                continue;
            }
            surahList.add(new ModelSurah(rawSurahs[0], rawSurahs[1], rawSurahs[2], rawSurahs[3], rawSurahs[4]));
        }

        return surahList;
    }
}

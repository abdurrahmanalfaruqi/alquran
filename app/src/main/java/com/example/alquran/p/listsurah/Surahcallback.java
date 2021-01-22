package com.example.alquran.p.listsurah;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.alquran.model.Surah;

public class Surahcallback extends DiffUtil.ItemCallback<Surah> {

    @Override
    public boolean areItemsTheSame(@NonNull Surah oldItem, @NonNull Surah newItem) {
        return oldItem.getSurah().equals(newItem.getSurah());
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Surah oldItem, @NonNull Surah newItem) {
        return oldItem == newItem;
    }
}


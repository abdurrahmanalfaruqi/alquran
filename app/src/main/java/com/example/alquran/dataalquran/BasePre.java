package com.example.alquran.dataalquran;

public class BasePre <V> {

    public V View;
     public void attach(V view)
    {
        View = view;
    }

    public void detach() {
        View = null;
    }
}


package com.adoptame.andrearodriguez.adoptame.utils;

import android.content.Context;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by andrearodriguez on 12/27/16.
 */

public class SharedPreference {

    public static final String PREFS_NAME = "BEBES_APP";
    public static final String FAVORITES = "BEBE_Favoritos";

    public SharedPreference() {
    }

    public void saveFavorites(Context context, List<Bebe> favorites) {
        android.content.SharedPreferences settings;
        android.content.SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, Bebe bebe) {
        List<Bebe> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Bebe>();
        favorites.add(bebe);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Bebe bebe) {
        ArrayList<Bebe> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(bebe);
            saveFavorites(context, favorites);
        }
    }
    public ArrayList<Bebe> getFavorites(Context context) {
        android.content.SharedPreferences settings;
        List<Bebe> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Bebe[] favoriteItems = gson.fromJson(jsonFavorites,
                    Bebe[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Bebe>(favorites);
        } else
            return null;

        return (ArrayList<Bebe>) favorites;
    }
}

package com.example.uxlab_animedxd.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class Anime implements Parcelable {
    private final int cover;
    private final String title;
    private final String genre;
    private final String synopsis;

    public Anime(int cover, String title, String genre, String synopsis) {
        this.cover = cover;
        this.title = title;
        this.genre = genre;
        this.synopsis = synopsis;
    }

    protected Anime(Parcel in) {
        cover = in.readInt();
        title = in.readString();
        genre = in.readString();
        synopsis = in.readString();
    }

    @DrawableRes
    public int getCover() {
        return cover;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getGenre() {
        return genre;
    }

    @NonNull
    public String getSynopsis() {
        return synopsis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(cover);
        dest.writeString(title);
        dest.writeString(genre);
        dest.writeString(synopsis);
    }

    public static final Creator<Anime> CREATOR = new Creator<>() {
        @Override
        public Anime createFromParcel(Parcel in) {
            return new Anime(in);
        }

        @Override
        public Anime[] newArray(int size) {
            return new Anime[size];
        }
//       {{-- test --}
    };
}
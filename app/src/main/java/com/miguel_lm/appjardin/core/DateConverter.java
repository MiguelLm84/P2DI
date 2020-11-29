package com.miguel_lm.appjardin.core;

import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.media.Image;

import androidx.room.TypeConverter;

import java.sql.Blob;
import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Picture toImage(Blob imagestam){
        return imagestam == null ? null : new Picture((Picture) imagestam);
    }

    @TypeConverter
    public static Blob toImagestam(Picture image){
        return image == null ? null : (Blob)image;
    }
}

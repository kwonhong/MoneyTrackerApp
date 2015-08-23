package ysdesigns.moneytrackerapp.NewTransactionPackage;

import android.graphics.drawable.Drawable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by james on 15-08-20.
 */
@Data @AllArgsConstructor
public class Category {
    private String name;
    private Drawable imageDrawable;
}

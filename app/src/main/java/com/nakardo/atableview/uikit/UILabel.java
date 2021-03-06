package com.nakardo.atableview.uikit;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import java.io.InputStream;

public class UILabel extends TextView {
    private static final String BOLD_FONT_PATH = "fonts/Roboto-Bold.ttf";
    private static final String REGULAR_FONT_PATH = "fonts/Roboto-Regular.ttf";
    private boolean dontConsumeNonUrlClicks = true;
    public boolean linkHit;

    public boolean isMoved = false;

    private static boolean assetExists(Context context, String path) {
        boolean exists = false;
        try {
            InputStream stream = context.getAssets().open(path);
            stream.close();
            exists = true;
        } catch (Exception e) {
        }

        return exists;
    }

    public UILabel(Context context) {
        super(context);
    }

    public UILabel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        //super.setTypeface(tf, style);
        if (!isInEditMode()) {
            String customTypefacePath = REGULAR_FONT_PATH;
            if (style == Typeface.BOLD) {
                customTypefacePath = BOLD_FONT_PATH;
            }

            // load custom font if available on app bundle.
            if (assetExists(getContext(), customTypefacePath)) {
                AssetManager assets = getContext().getAssets();
                tf = Typeface.createFromAsset(assets, customTypefacePath);
            }

            super.setTypeface(tf, style);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        linkHit = false;
        boolean res = super.onTouchEvent(event);

        if (dontConsumeNonUrlClicks)
            return linkHit;
        return res;
    }

    public boolean hasFocusable() {
        return false;
    }
}

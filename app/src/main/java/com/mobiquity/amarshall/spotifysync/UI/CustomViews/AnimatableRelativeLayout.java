package com.mobiquity.amarshall.spotifysync.UI.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * an fragment base that allows for fractional changes
 * see http://stackoverflow.com/a/11015834
 */
public class AnimatableRelativeLayout extends RelativeLayout {
    public static final String TAG = AnimatableRelativeLayout.class.getCanonicalName();

    public AnimatableRelativeLayout(Context context) {
        super(context);
    }

    public AnimatableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float getXFraction() {
        return getWidth() != 0 ? getX() / getWidth() : 0;
    }

    public void setXFraction(float xFraction) {
        final int width = getWidth();
        setX((width > 0) ? (xFraction * width) : -9999);
    }

}

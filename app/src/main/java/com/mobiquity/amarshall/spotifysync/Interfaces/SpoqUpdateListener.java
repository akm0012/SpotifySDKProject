package com.mobiquity.amarshall.spotifysync.Interfaces;

import com.mobiquity.amarshall.spotifysync.Models.SpoqModel;

/**
 * Created by jfowler on 4/11/16.
 */
public interface SpoqUpdateListener {
    void onUpdate(SpoqModel model);

    void onError(String message);
}

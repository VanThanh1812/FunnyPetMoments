package com.funnypet.funnypet.ui;

import com.funnypet.funnypet.model.Video;

/**
 * Created by thanhpv on 8/17/17.
 */

public interface MainViewInterface {
    void showPreview();
    void showVideo(Video video);
    void showVideoFail();
}

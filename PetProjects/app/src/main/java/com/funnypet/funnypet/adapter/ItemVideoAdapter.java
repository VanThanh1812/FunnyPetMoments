package com.funnypet.funnypet.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.funnypet.funnypet.R;
import com.funnypet.funnypet.model.Video;
import com.github.rtoshiro.view.video.FullscreenVideoLayout;
import com.github.rtoshiro.view.video.FullscreenVideoView;
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.SimpleMainThreadMediaPlayerListener;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by thanhpv on 8/17/17.
 */

public class ItemVideoAdapter extends RecyclerView.Adapter<ItemVideoAdapter.VideoHolder> {
    private Activity context;
    private ArrayList<Video> arrayList;
    private OnItemClick listenClick;

    public ItemVideoAdapter(Activity activity, ArrayList<Video> arrayList) {
        this.context = activity;
        this.arrayList = arrayList;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View videoHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_video, parent ,false);
        return new VideoHolder(videoHolder);
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        VideoHolder videoHolder = holder;
        videoHolder.setup(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        if (arrayList == null){
            return 0;
        }else {
            return arrayList.size();
        }
    }

    public void updateVideo(Video video) {
        Log.d("track", video.getTitle());
        arrayList.add(video);
        notifyDataSetChanged();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {

        Video video;
        TextView tvTitle;
        VideoPlayerView videoPlay;
        ImageView ivCover;
        private VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
            @Override
            public void onPlayerItemChanged(MetaData metaData) {

            }
        });

        public VideoHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            videoPlay = itemView.findViewById(R.id.videoView);
            ivCover = itemView.findViewById(R.id.ivCover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }


        public void setup(final Video video) {
            this.video = video;
            tvTitle.setText(video.getTitle());

            videoPlay.addMediaPlayerListener(new SimpleMainThreadMediaPlayerListener(){
                @Override
                public void onVideoPreparedMainThread() {
                    super.onVideoPreparedMainThread();
                    ivCover.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onVideoStoppedMainThread() {
                    super.onVideoStoppedMainThread();
                    ivCover.setVisibility(View.VISIBLE);
                }

                @Override
                public void onVideoCompletionMainThread() {
                    super.onVideoCompletionMainThread();
                    ivCover.setVisibility(View.VISIBLE);
                }
            });

            ivCover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mVideoPlayerManager.playNewVideo(null, videoPlay, video.getUrl());
                }
            });
        }
    }

    public interface OnItemClick {
        void onItemClick(FullscreenVideoLayout videoPlay);
    }
}

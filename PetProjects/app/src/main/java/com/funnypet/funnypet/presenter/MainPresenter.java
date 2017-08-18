package com.funnypet.funnypet.presenter;

import android.content.Context;
import android.util.Log;

import com.funnypet.funnypet.firebase.Constant;
import com.funnypet.funnypet.model.Video;
import com.funnypet.funnypet.ui.MainViewInterface;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by thanhpv on 8/17/17.
 */

public class MainPresenter implements ChildEventListener{
    private Context context;
    private MainViewInterface mainViewInterface;

    public MainPresenter(Context context, MainViewInterface mainViewInterface) {
        this.context = context;
        this.mainViewInterface = mainViewInterface;
    }

    public void loadData(){

        mainViewInterface.showPreview();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Constant.VIDEO);
        myRef.addChildEventListener(this);

    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Video video = dataSnapshot.getValue(Video.class);
        mainViewInterface.showVideo(video);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}

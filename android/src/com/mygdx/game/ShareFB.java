package com.mygdx.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;


public class ShareFB extends AppCompatActivity {

    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        shareDialog = new ShareDialog(this);

        share();


    }
    private int fetchScorefromLibGDX() {

        String score = " ";
        try {
            Preferences prefs = Gdx.app.getPreferences("prefs");
            score = prefs.getString("score", "No name stored");
            if(!score.equals("No name stored")) {
                return Integer.parseInt(score);
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    public void share(){

        String score = String.valueOf(fetchScorefromLibGDX());
        ShareOpenGraphObject object = new ShareOpenGraphObject.Builder()
                .putString("og:type", "game.achievement")
                .putString("og:title", "MONSTER\nMy highest score is "+  " " + score)
                .build();


        ShareOpenGraphAction action = new ShareOpenGraphAction.Builder()
                .setActionType("games.achieves")
                .putObject("game", object)
                .build();




        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareOpenGraphContent content = new ShareOpenGraphContent.Builder()
                    .setPreviewPropertyName("game")
                    .setAction(action)
                    .build();

            ShareDialog.show(ShareFB.this, content);
        }

    }

}

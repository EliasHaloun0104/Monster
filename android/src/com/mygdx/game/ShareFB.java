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

    public void share(){
        /*Bitmap image = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .setRef("scawca"+1231)
                    .build();

            ShareLinkContent content2 = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://firebasestorage.googleapis.com/v0/b/monsterapp-6ba0b.appspot.com" +
                            "/o/ic_launcher.png?alt=media&token=f64cb433-80f4-4340-88c3-190b4879a5fd"))
                    .setQuote("Hello Guys"+15)
                    .build();
            // for multimedia
            *//*ShareContent shareContent = new ShareMediaContent.Builder()
                    .addMedium(photo)
                    .addMedium(content2)
                    .build();*//*

        }*/
        ShareOpenGraphObject object = new ShareOpenGraphObject.Builder()
                .putString("og:type", "game.achievement")
                .putString("og:title", "MONSTER\nMy highest score is "+ " HERE ")
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

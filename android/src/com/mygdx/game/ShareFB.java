package com.mygdx.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareStoryContent;
import com.facebook.share.widget.ShareDialog;

public class ShareFB extends AppCompatActivity {

    private ShareDialog shareDialog;
    private Button sha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_share);


        shareDialog = new ShareDialog(this);
        share();
        /*sha = findViewById(R.id.shee);
        sha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });*/

    }

    public void share(){
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            ShareLinkContent content2 = new ShareLinkContent.Builder()
                    .setQuote("Hello Guys")
                    .build();

            // for multimedia
            /*ShareContent shareContent = new ShareMediaContent.Builder()
                    .addMedium(photo)
                    .addMedium(content2)
                    .build();*/
            shareDialog.show(content);
        }
    }
}

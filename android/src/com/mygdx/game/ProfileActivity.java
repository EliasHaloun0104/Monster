package com.mygdx.game;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private TextView name, status;
    private ImageView profileImageView;
    private Button sendFriendReqBtn, declineFriendReqBtn;

    private DatabaseReference usersDatabase;
    private DatabaseReference friendReqDatabase;
    private DatabaseReference friendDatabase;
    private DatabaseReference notificationDatabase;
    private DatabaseReference rootRef;

    private FirebaseUser currentUser;
    private ProgressDialog progressDialog;

    private String current_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final String userId = getIntent().getStringExtra("user_id");

        rootRef = FirebaseDatabase.getInstance().getReference();

        profileImageView = findViewById(R.id.prof_image);
        name = findViewById(R.id.prof_userName);
        status = findViewById(R.id.prof_status);
        sendFriendReqBtn = findViewById(R.id.prof_sendReq);
        declineFriendReqBtn = findViewById(R.id.prof_declineReq);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading user data");
        progressDialog.setMessage("Please wait while loading user data");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        declineFriendReqBtn.setVisibility(View.INVISIBLE);
        declineFriendReqBtn.setEnabled(false);


        usersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        friendReqDatabase = FirebaseDatabase.getInstance().getReference().child("Friend_req");
        friendDatabase = FirebaseDatabase.getInstance().getReference().child("Friends");
        notificationDatabase = FirebaseDatabase.getInstance().getReference().child("Notifications");

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        current_state = "not_friends";


        usersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String display_name = dataSnapshot.child("name").getValue().toString();
                String display_status = dataSnapshot.child("status").getValue().toString();
                name.setText(display_name);
                status.setText(display_status);

                if (currentUser.getUid().equals(userId)) {
                    declineFriendReqBtn.setEnabled(false);
                    declineFriendReqBtn.setVisibility(View.INVISIBLE);

                    sendFriendReqBtn.setEnabled(false);
                    sendFriendReqBtn.setVisibility(View.INVISIBLE);
                }

                // Friend request sent/received
                friendReqDatabase.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild(userId)) {

                            String req_type = dataSnapshot.child(userId).child("request_type").getValue().toString();

                            if (req_type.equals("received")) {

                                current_state = "req_received";
                                String acceptFriendRequest = "ACCEPT REQUEST";
                                sendFriendReqBtn.setText(acceptFriendRequest);

                                declineFriendReqBtn.setVisibility(View.VISIBLE);
                                declineFriendReqBtn.setEnabled(true);

                            } else if (req_type.equals("sent")) {
                                current_state = "req_sent";
                                String cancelFriendRequest = "CANCEL REQUEST";
                                sendFriendReqBtn.setText(cancelFriendRequest);

                                declineFriendReqBtn.setVisibility(View.INVISIBLE);
                                declineFriendReqBtn.setEnabled(false);
                            }
                            progressDialog.dismiss();

                            // User is a friend
                        } else {
                            friendDatabase.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    declineFriendReqBtn.setVisibility(View.INVISIBLE);
                                    declineFriendReqBtn.setEnabled(false);

                                    if (dataSnapshot.hasChild(userId)) {

                                        current_state = "friends";
                                        String unfriend = "REMOVE";
                                        sendFriendReqBtn.setText(unfriend);

                                    }
                                    progressDialog.dismiss();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        sendFriendReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //NOT FRIENDS STATE
                if (current_state.equals("not_friends")) {
                    DatabaseReference notificationRef = rootRef.child("Notifications").child(userId).push();
                    String newNotificationId = notificationRef.getKey();

                    HashMap<String, String> notificationData = new HashMap<>();
                    notificationData.put("from", currentUser.getUid());
                    notificationData.put("type", "request");

                    Map requestMap = new HashMap();
                    requestMap.put("Friend_req/" + currentUser.getUid() + "/" + userId + "/request_type", "sent");
                    requestMap.put("Friend_req/" + userId + "/" + currentUser.getUid() + "/request_type", "received");
                    requestMap.put("Notifications/" + userId + "/" + newNotificationId, notificationData);

                    rootRef.updateChildren(requestMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                sendFriendReqBtn.setEnabled(true);
                                current_state = "req_sent";
                                String cancel = "Cancel Request";
                                sendFriendReqBtn.setText(cancel);
                            } else {
                                Toast.makeText(ProfileActivity.this, "There was some error in sending request", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }

                // CANCEL REQUEST STATE
                if (current_state.equals("req_sent")) {
                    Map cancelMap = new HashMap();
                    cancelMap.put("Friend_req/" + currentUser.getUid() + "/" + userId, null);
                    cancelMap.put("Friend_req/" + userId + "/" + currentUser.getUid(), null);

                    rootRef.updateChildren(cancelMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                sendFriendReqBtn.setEnabled(true);
                                current_state = "not_friends";
                                String sendFriendReq = "ADD FRIEND";
                                sendFriendReqBtn.setText(sendFriendReq);

                            }
                        }
                    });
                }

                //REQUEST RECEIVED STATE
                if (current_state.equals("req_received")) {
                    final String currentDate = DateFormat.getDateInstance().format(new Date());

                    Map friendsMap = new HashMap();
                    friendsMap.put("Friends/" + currentUser.getUid() + "/" + userId + "/date", currentDate);
                    friendsMap.put("Friends/" + userId + "/" + currentUser.getUid() + "/date", currentDate);

                    friendsMap.put("Friend_req/" + currentUser.getUid() + "/" + userId, null);
                    friendsMap.put("Friend_req/" + userId + "/" + currentUser.getUid(), null);

                    rootRef.updateChildren(friendsMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                            if (databaseError == null) {
                                sendFriendReqBtn.setEnabled(true);
                                current_state = "friends";
                                String unfriend = "REMOVE";
                                sendFriendReqBtn.setText(unfriend);

                                declineFriendReqBtn.setVisibility(View.INVISIBLE);
                                declineFriendReqBtn.setEnabled(false);
                            } else {
                                String error = databaseError.getMessage();
                                Toast.makeText(ProfileActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                //UNFRIEND
                if (current_state.equals("friends")) {
                    Map unfriendMap = new HashMap();
                    unfriendMap.put("Friends/" + currentUser.getUid() + "/" + userId, null);
                    unfriendMap.put("Friends/" + userId + "/" + currentUser.getUid(), null);

                    rootRef.updateChildren(unfriendMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                current_state = "not_friends";
                                String send = "ADD FRIEND";
                                sendFriendReqBtn.setText(send);

                            } else {
                                sendFriendReqBtn.setEnabled(true);
                                String error = databaseError.getMessage();
                                Toast.makeText(ProfileActivity.this, error, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
        declineFriendReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map declineMap = new HashMap();
                declineMap.put("Friend_req/"+currentUser.getUid()+"/"+userId,null);
                declineMap.put("Friend_req/"+userId+"/"+currentUser.getUid(),null);

                rootRef.updateChildren(declineMap, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if (databaseError==null) {
                            current_state = "not_friends";
                            String addFriend = "ADD FRIEND";
                            sendFriendReqBtn.setText(addFriend);
                            sendFriendReqBtn.setEnabled(true);

                            declineFriendReqBtn.setEnabled(false);
                            declineFriendReqBtn.setVisibility(View.INVISIBLE);
                        }
                    }
                });


            }
        });
    }
}

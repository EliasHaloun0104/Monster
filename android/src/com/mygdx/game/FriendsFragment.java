package com.mygdx.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FriendsFragment extends Fragment {

    private RecyclerView friendsList;
    private DatabaseReference friendsDatabase;
    private DatabaseReference usersDatabase;

    private FirebaseAuth mAuth;


    private String currentUserId;

    private View view;

    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_friends, container, false);

        friendsList = view.findViewById(R.id.friendlist);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getUid();

        friendsDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(currentUserId);
        usersDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        friendsDatabase.keepSynced(true);
        usersDatabase.keepSynced(true);

        friendsList.setHasFixedSize(true);
        friendsList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Friends, FriendsViewHolder> friendsRecyclerViewAdapter = new FirebaseRecyclerAdapter<Friends, FriendsViewHolder>(
                Friends.class,
                R.layout.users_single_layout,
                FriendsViewHolder.class,
                friendsDatabase
        ) {
            @Override
            protected void populateViewHolder(final FriendsViewHolder viewHolder, final Friends model, int position) {


                viewHolder.setDate(model.getDate());
                final String list_userId = getRef(position).getKey();

                usersDatabase.child(list_userId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {


                            String userName = dataSnapshot.child("name").getValue().toString();

                            if (dataSnapshot.hasChild("online")) {
                                String userOnline =  dataSnapshot.child("online").getValue().toString();
                                viewHolder.setUserOnline(userOnline);
                            }
                            viewHolder.setName(userName);

                            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CharSequence[] options = new CharSequence[]{"Open Profile", "Send Message"};
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("Select an option");
                                    builder.setItems(options, new AlertDialog.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (which == 0) {
                                                Intent intent = new Intent(getContext(), ProfileActivity.class);
                                                intent.putExtra("user_id", list_userId);
                                                startActivity(intent);
                                            }
                                            if (which == 1) {

                                                // for the chat
                                            }
                                        }
                                    });
                                    builder.show();

                                }
                            });

                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        };
        friendsList.setAdapter(friendsRecyclerViewAdapter);
    }

    public static class FriendsViewHolder extends RecyclerView.ViewHolder {
        View view;

        public FriendsViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setDate(String date) {
            TextView dateView = view.findViewById(R.id.user_single_status);
            dateView.setText(date);
        }

        public void setName(String name) {
            TextView userNameView = view.findViewById(R.id.user_single_name);
            userNameView.setText(name);
        }

        public void setUserOnline(String onlineStatus) {
            ImageView onlineImage = view.findViewById(R.id.user_single_onoff);

            if (onlineStatus.equals("true")) {
                onlineImage.setVisibility(View.VISIBLE);
            } else {
                onlineImage.setVisibility(View.INVISIBLE);

            }

        }

    }
}

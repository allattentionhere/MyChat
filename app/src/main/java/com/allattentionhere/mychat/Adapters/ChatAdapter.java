package com.allattentionhere.mychat.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allattentionhere.mychat.Activities.MainActivity;
import com.allattentionhere.mychat.Helper.MyApplication;
import com.allattentionhere.mychat.Model.Message;
import com.allattentionhere.mychat.R;
import com.google.gson.Gson;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private Message[] messages;
    private Activity _activity;

    public ChatAdapter(Message[] messages, Activity _activity) {
        this.messages = messages;
        this._activity = _activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_chat_message, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Message r = messages[position];
        holder.txt_username.setText(r.getUsername());
        holder.txt_body.setText(r.getBody());

        if (r.getImage_url() != null && r.getImage_url().trim().length() > 0) {
            MyApplication.imageLoader.displayImage(r.getImage_url(), holder.civ_profile);
        } else {
            holder.civ_profile.setImageResource(R.drawable.person);
        }

        String[] parts = r.getMessage_time().split("T");
        holder.txt_time.setText(r.getName() + " " + parts[1] + " " + parts[0]);

        if (r.isFavorite()) {
            holder.iv_fav.setImageDrawable(_activity.getResources().getDrawable(R.drawable.star_filled));
        } else {
            holder.iv_fav.setImageDrawable(_activity.getResources().getDrawable(R.drawable.star_empty));
        }

        holder.iv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r.isFavorite()) {
                    r.setFavorite(false);
                    holder.iv_fav.setImageDrawable(_activity.getResources().getDrawable(R.drawable.star_empty));
                } else {
                    r.setFavorite(true);
                    holder.iv_fav.setImageDrawable(_activity.getResources().getDrawable(R.drawable.star_filled));
                }
                ((MainActivity) _activity).refreshUsers();
            }
        });

    }

    @Override
    public int getItemCount() {
        return messages.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_username, txt_body, txt_time;
        CircleImageView civ_profile;
        ImageView iv_fav;

        public MyViewHolder(View view) {
            super(view);
            txt_username = (TextView) view.findViewById(R.id.txt_username);
            txt_body = (TextView) view.findViewById(R.id.txt_body);
            txt_time = (TextView) view.findViewById(R.id.txt_time);
            civ_profile = (CircleImageView) view.findViewById(R.id.civ_profile);
            iv_fav = (ImageView) view.findViewById(R.id.iv_fav);
        }
    }
}
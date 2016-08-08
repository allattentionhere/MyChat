package com.allattentionhere.mychat.Adapters;

import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allattentionhere.mychat.Helper.MyApplication;
import com.allattentionhere.mychat.Model.ChatCount;
import com.allattentionhere.mychat.Model.Message;
import com.allattentionhere.mychat.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private ChatCount[] users;
    public UsersAdapter(ChatCount[] users) {
        this.users = users;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_user, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.txt_username.setText(" "+users[position].getUsername());
        holder.txt_fav.setText(" "+users[position].getFavourite()+"");
        holder.txt_total.setText(" "+users[position].getTotal()+"");
    }

    @Override
    public int getItemCount() {
        return users.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_username, txt_fav, txt_total;
        public MyViewHolder(View view) {
            super(view);
            txt_username = (TextView) view.findViewById(R.id.txt_username);
            txt_fav = (TextView) view.findViewById(R.id.txt_fav);
            txt_total = (TextView) view.findViewById(R.id.txt_total);
        }
    }
}
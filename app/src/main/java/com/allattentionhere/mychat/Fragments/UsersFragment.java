package com.allattentionhere.mychat.Fragments;

import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.allattentionhere.mychat.Activities.MainActivity;
import com.allattentionhere.mychat.Adapters.ChatAdapter;
import com.allattentionhere.mychat.Adapters.UsersAdapter;
import com.allattentionhere.mychat.Model.ChatCount;
import com.allattentionhere.mychat.R;
import com.google.gson.Gson;


public class UsersFragment extends Fragment {

    RecyclerView rv_users;
    private UsersAdapter mUsersAdapter;
    ChatCount[] cc;

    public UsersFragment() {
    }

    public static UsersFragment newInstance() {
        UsersFragment fragment = new UsersFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_users, container, false);
        rv_users = (RecyclerView) rootView.findViewById(R.id.rv_users);

        cc = ((MainActivity) getActivity()).getmChat().getEachUserMessageCount();
        mUsersAdapter = new UsersAdapter(cc);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_users.setLayoutManager(mLayoutManager);
        rv_users.setItemAnimator(new DefaultItemAnimator());
        rv_users.setAdapter(mUsersAdapter);

        return rootView;
    }


}

package com.allattentionhere.mychat.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allattentionhere.mychat.Activities.MainActivity;
import com.allattentionhere.mychat.Adapters.ChatAdapter;
import com.allattentionhere.mychat.R;


public class ChatFragment extends Fragment  {

    RecyclerView rv_chat;
    private ChatAdapter mChatAdapter;

    public ChatFragment() {
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        rv_chat = (RecyclerView) rootView.findViewById(R.id.rv_chat);

        mChatAdapter = new ChatAdapter(((MainActivity)getActivity()).getmChat().getMessages(),getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_chat.setLayoutManager(mLayoutManager);
        rv_chat.setItemAnimator(new DefaultItemAnimator());
        rv_chat.setAdapter(mChatAdapter);

        return rootView;
    }



}

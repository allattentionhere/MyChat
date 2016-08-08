package com.allattentionhere.mychat.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.allattentionhere.mychat.Fragments.ChatFragment;
import com.allattentionhere.mychat.Fragments.UsersFragment;
import com.allattentionhere.mychat.Helper.Datacallback;
import com.allattentionhere.mychat.Helper.HttpRequestHelper;
import com.allattentionhere.mychat.Helper.SectionsPagerAdapter;
import com.allattentionhere.mychat.Model.Chat;
import com.allattentionhere.mychat.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Datacallback {

    private Snackbar sb;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Chat mChat;
    TabLayout tabLayout;
    ArrayList<Fragment> frag_list = new ArrayList<>();
    ArrayList<String> title_list = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListener();
        makeNetworkCall();

    }

    private void setListener() {
        findViewById(R.id.fab_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNetworkCall();
            }
        });
    }

    private void init() {
        mViewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        ((Toolbar) findViewById(R.id.toolbar)).setTitle("MyChat");
        frag_list.add(ChatFragment.newInstance());
        frag_list.add(UsersFragment.newInstance());
        title_list.add("CHAT");
        title_list.add("USERS");
    }

    private void makeNetworkCall() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //make network call
            showDialog("Loading...");
            new HttpRequestHelper().MakeJsonGetRequest("/test_data", null, this, this);
        } else {
            showSnackbar("Not connected to Internet", "RETRY");
        }
    }

    @Override
    public void onSuccess(JSONObject success, String uri) {
        if (uri.equalsIgnoreCase("/test_data")) {
            hideDialog();
            String str_success = success.toString().replace("\"image-url\":", "\"image_url\":").replace("\"message-time\":", "\"message_time\":");
            Log.d("resp", "resp=" + str_success);
            mChat = new Gson().fromJson(str_success, Chat.class);
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), frag_list, title_list);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            tabLayout.setupWithViewPager(mViewPager);

        }
    }

    @Override
    public void onFailure(JSONObject failure, String uri) {
        hideDialog();
        showSnackbar("Network call failed.", "RETRY");
    }

    public Chat getmChat() {
        return mChat;
    }

    public void refreshUsers() {
        mViewPager.getAdapter().notifyDataSetChanged();
    }

    public void showDialog(String message) {
        if (pd == null) pd = new ProgressDialog(this);
        pd.setMessage(message);
        pd.setCancelable(false);
        pd.show();
    }

    public void hideDialog() {
        if (pd != null && pd.isShowing()) pd.dismiss();
    }

    private void showSnackbar(String s, String action) {
        if (sb != null && sb.isShownOrQueued()) {
            sb.dismiss();
        }
        sb = Snackbar.make(findViewById(R.id.main_content), s, Snackbar.LENGTH_LONG).setDuration(Snackbar.LENGTH_INDEFINITE);
        if (action != null) {
            sb.setAction(action, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    makeNetworkCall();
                }
            });
        }
        sb.show();
    }

}

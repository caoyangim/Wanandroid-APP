package com.cy.recgallery;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cy.recgallery.fragment.MainFragment;
import com.cy.recgallery.fragment.ViewPageFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        //setListener();
    }

    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        ViewPageFragment mainFragment = new ViewPageFragment();
        transaction.replace(R.id.id_content,mainFragment);
        transaction.commit();
    }
}

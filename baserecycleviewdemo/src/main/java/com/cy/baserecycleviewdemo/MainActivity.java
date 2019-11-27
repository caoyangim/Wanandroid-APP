package com.cy.baserecycleviewdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cy.baserecycleviewdemo.adapter.HomeAdapter;
import com.cy.baserecycleviewdemo.adapter.MultipleItemQuickAdapter;
import com.cy.baserecycleviewdemo.adapter.SectionAdapter;
import com.cy.baserecycleviewdemo.bean.HomeItem;
import com.cy.baserecycleviewdemo.bean.MySection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private BaseQuickAdapter mAdapter;
    private int mCurrentCounter = 0;
    private int TOTAL_COUNTER = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mRecyclerView.smoothScrollToPosition(0);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = findViewById(R.id.recycle_View);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,6));
        initAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initAdapter() {
        //mAdapter = new SectionAdapter(R.layout.item_rc,R.layout.item_section_head,initMySection());
        mAdapter = new MultipleItemQuickAdapter(initData(10));
        mAdapter.openLoadAnimation();
        mAdapter.addHeaderView(getView());
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("--->","mCurrentCounter:"+mCurrentCounter);
                        if (mCurrentCounter >= TOTAL_COUNTER) {
                            //数据全部加载完毕
                            mAdapter.loadMoreEnd();
                        } else {
                            //成功获取更多数据
                            mAdapter.addData(initData(10));
                            mCurrentCounter = mAdapter.getData().size();
                            mAdapter.loadMoreComplete();
                        }
                    }
                },1000);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, "click:"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private View getView() {
        View view = View.inflate(this,R.layout.rc_head,null);
        return view;
    }

    private List<HomeItem> initData(int pageNum) {
        final List<HomeItem> itemList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < pageNum; i++) {
            switch (random.nextInt(3)+1){
                case 1:
                    //一行添加1个
                    HomeItem item = new HomeItem("","title"+i,1);
                    itemList.add(item);
                    break;
                case 2:
                    //一行添加2个
                    HomeItem item2 = new HomeItem("","title"+i,2);
                    itemList.add(item2);
                    itemList.add(item2);
                    break;
                case 3:
                    //一行添加3个
                    HomeItem item3 = new HomeItem("","title"+i,3);
                    itemList.add(item3);
                    itemList.add(item3);
                    itemList.add(item3);
                    break;
            }

        }
        return itemList;
    }

    public List<MySection> initMySection(){
        List<MySection> list = new ArrayList<>();
        list.add(new MySection(true,"mCurrentCounter:"+mCurrentCounter));
        for (int i = 0; i < 10; i++) {
            HomeItem item = new HomeItem("","title"+i,1);
            MySection mySection = new MySection(item);
            list.add(mySection);
        }
        return list;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

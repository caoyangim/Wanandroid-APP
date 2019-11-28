package com.cy.mvpframetest.main;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseActivity;
import com.cy.mvpframetest.home.HomeFragment;
import com.cy.mvpframetest.knowledge.knowledge.KnowledgeFragement;
import com.cy.mvpframetest.navigation.NavigationFragment;
import com.cy.mvpframetest.project.ProjectFragment;
import com.cy.mvpframetest.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by lenovo on 2019/6/17
 * 功能描述：
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView BNView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.FABtn)
    FloatingActionButton FABtn;

    private List<Fragment> fragments;
    private int lastShowFragment;
    private String TAG = "MainActivity>>>>";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initSubViews(View view) {
        initFragment();
        mToolbar.setTitle(R.string.fragment_home);
        BNView.setItemTextAppearanceActive(R.style.bottom_selected_text);
        BNView.setItemTextAppearanceInactive(R.style.bottom_normal_text);
        BNView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_home:
                        if(lastShowFragment!=0){
                            replaceFragment(lastShowFragment,0);
                            lastShowFragment=0;
                            initFABtn();
                            mToolbar.setTitle(R.string.fragment_home);
                        }
                        break;
                    case R.id.item_system:
                        if(lastShowFragment!=1){
                            replaceFragment(lastShowFragment,1);
                            lastShowFragment=1;
                            mToolbar.setTitle(R.string.fragment_system);
                            FABtn.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.item_navigation:
                        if(lastShowFragment!=2){
                            replaceFragment(lastShowFragment,2);
                            lastShowFragment=2;
                            mToolbar.setTitle(R.string.fragment_navigation);
                            FABtn.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.item_project:
                        if(lastShowFragment!=3){
                            replaceFragment(lastShowFragment,3);
                            lastShowFragment=3;
                            mToolbar.setTitle(R.string.fragment_project);
                            FABtn.setVisibility(View.GONE);
                        }
                        break;
                }
                // 默认 false
                // false 的话 下面颜色不会变化
                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        //设置左上角显示三道横线
        toggle.syncState();

        //图标颜色
        //mNavigationView.setItemIconTintList(null);

        //获取xml头布局view
        View headerView = mNavigationView.getHeaderView(0);
        //添加头布局的另外一种方式
        //View headview=navigationview.inflateHeaderView(R.layout.navigationview_header);

        //寻找头部里面的控件
        ImageView imageView = headerView.findViewById(R.id.bg_nav);
        ImageView img_head = headerView.findViewById(R.id.img);
        Glide.with(this).load(R.drawable.avatar)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3)))
                .into(imageView);
        Glide.with(this).load(R.drawable.avatar).into(img_head);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFABtn();
    }

    private void initFragment() {
        fragments=new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(KnowledgeFragement.newInstance());
        fragments.add(NavigationFragment.newInstance());
        fragments.add(ProjectFragment.newInstance());
        lastShowFragment=0;
        /**
         * 在 Activity 中加载第一个 Fragment
         */
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_main,fragments.get(0))
                .show(fragments.get(0))
                .commit();

        Log.d(TAG,"initFragment over");
    }

    @SuppressLint("RestrictedApi")
    private void initFABtn(){
        Log.e(">>>",fragments.get(lastShowFragment).getClass().getName());
        if (!fragments.get(lastShowFragment).getClass().getName().equals("com.cy.mvpframetest.home.HomeFragment")) return;
        HomeFragment homeFragment = (HomeFragment) fragments.get(lastShowFragment);
        final RecyclerView mRcView = homeFragment.getRecyclerView();
        final NestedScrollView mNsView = homeFragment.getNestedScrollView();
        FABtn.setVisibility(View.VISIBLE);
        FABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNsView.smoothScrollTo(0,0);
            }
        });
    }

    /**
     * 替换 Activity 中的 Fragment
     * @param lastShowFragment
     * @param index
     */
    private void replaceFragment(int lastShowFragment,int index){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments.get(lastShowFragment));

        // 确认需要的 Fragment 是否已添加
        if(!fragments.get(index).isAdded()){
            transaction.add(R.id.fragment_main, fragments.get(index));
        }
        transaction.show(fragments.get(index)).commitAllowingStateLoss();

        Log.d(TAG,"replaceFragment  lastShowFragment : "+lastShowFragment+" index : "+index);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return checkBackAction() || super.onKeyDown(keyCode, event);
    }
    //双击退出相关
    private boolean mFlag = false;
    private long mTimeout = -1;
    private boolean checkBackAction() {
        long time = 3000L;//判定时间设为3秒
        boolean flag = mFlag;
        mFlag = true;
        boolean timeout = (mTimeout == -1 || (System.currentTimeMillis() - mTimeout) > time);
        if (mFlag && (mFlag != flag || timeout)) {
            mTimeout = System.currentTimeMillis();
            ToastUtil.showToast("再点击一次回到桌面");
            return true;
        }
        return !mFlag;
    }
}

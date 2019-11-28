package com.cy.mvpframetest.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseFragment;
import com.cy.mvpframetest.bean.ArticleList;
import com.cy.mvpframetest.bean.BannerDataItem;
import com.cy.mvpframetest.content.ContentActivity;
import com.cy.mvpframetest.home.adapter.HomeRcAdapter;
import com.cy.mvpframetest.home.adapter.LoadingFooter;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

import static com.cy.mvpframetest.content.ContentActivity.toContentActivity;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public class HomeFragment extends BaseFragment<HomePresenterImpl, HomeModelImpl> implements HomeContract.HomeView {
    @BindView(R.id.home_rc)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.nested_view)
    NestedScrollView NSView;

    private HomeRcAdapter mAdapter;
    protected LoadingFooter.FooterState mState = LoadingFooter.FooterState.Normal;
    private int page_count = 0;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void initData() {
        mPresenter.loadData(page_count);
        mPresenter.loadHomeBannel();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initSubViews(View view) {
        mAdapter = new HomeRcAdapter(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //mRecyclerView.addOnScrollListener(mOnScrollListener);
        NSView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())){
                    //滑动到底部
                    if (mState == LoadingFooter.FooterState.Loading) {
                        Log.d("@TAG", "the state is Loading, just wait..");
                        return;
                    }

                    page_count++;
                    setState(LoadingFooter.FooterState.Loading);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mPresenter.loadData(page_count);
                        }
                    },1000);

                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setProgressViewEndTarget(true, 180);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page_count = 0;
                mPresenter.loadData(0);
            }
        });
    }

    @Override
    public void setData(ArticleList beanList) {
        //articleDetailData.addAll(beanList.getDatas());
        if (beanList.getCurPage() == 1){
            mAdapter.setDatas(beanList.getDatas());
            mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }else {
            mAdapter.addAll(beanList.getDatas());
        }
        mAdapter.setOnItemClickListener(new HomeRcAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                toContentActivity(getActivity(),mAdapter.getDatas().get(position).getId(), mAdapter.getDatas().get(position).getLink(), mAdapter.getDatas().get(position).getTitle());
            }
        });
        setState(LoadingFooter.FooterState.Normal);
    }

    @Override
    public void setHomeBannel(final List<BannerDataItem> bannerDataItems) {
        List<String> images = new ArrayList<>();
        for (BannerDataItem dataItem:bannerDataItems){
            images.add(dataItem.getImagePath());
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                toContentActivity(getActivity(),bannerDataItems.get(position).getId(), bannerDataItems.get(position).getUrl(), bannerDataItems.get(position).getTitle());
            }
        });
        banner.setImageLoader(new MyLoader());
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    protected void setState(LoadingFooter.FooterState mState) {
        this.mState = mState;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                changeAdaperState();
            }
        });
    }

    //改变底部bottom的样式,加载中
    protected void changeAdaperState() {
        if (mAdapter != null && mAdapter.mFooterHolder != null) {
            mAdapter.mFooterHolder.setData(mState);
        }
    }


    @Override
    public void showLoading() {
        super.showLoading();
        setState(LoadingFooter.FooterState.Loading);
    }

    @Override
    public void showError(String msg) {
        setState(LoadingFooter.FooterState.NetWorkError);
    }

    public RecyclerView getRecyclerView(){
        return mRecyclerView;
    }

    public NestedScrollView getNestedScrollView(){
        return NSView;
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }


    //自定义的图片加载器 banner使用
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}

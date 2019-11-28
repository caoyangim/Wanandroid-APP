package com.cy.mvpframetest.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseFragment;
import com.cy.mvpframetest.bean.ArticleDetailData;
import com.cy.mvpframetest.bean.NavCategoryBean;
import com.cy.mvpframetest.content.ContentActivity;
import com.cy.mvpframetest.navigation.adapter.NavListAdapter;
import com.cy.mvpframetest.utils.ToastUtil;
import com.google.android.flexbox.FlexboxLayout;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import butterknife.BindView;
import butterknife.OnItemClick;

import static com.cy.mvpframetest.content.ContentActivity.toContentActivity;

public class NavigationFragment extends BaseFragment<NavigationPresenterImpl,NavigationModelImpl> implements NavigationContract.NavigationView {
    @BindView(R.id.nav_list)
    RecyclerView categoryList;
    @BindView(R.id.cat_title)
    TextView catTitle;
    @BindView(R.id.nav_flex)
    FlexboxLayout mFlexbox;


    private Queue<TextView> mFlexItemTextViewCaches = new LinkedList<>();

    private NavListAdapter mAdapter;
    private View lastFocus;
    private LayoutInflater mInflater;

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initSubViews(View view) {

    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(final List<NavCategoryBean> beanList) {
        mAdapter = new NavListAdapter(R.layout.item_navlist,beanList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                catTitle.setText(beanList.get(position).getName());
                view.setBackground(ResourcesCompat.getDrawable(getResources(), R.color.white, null));
                if (lastFocus != null)
                    lastFocus.setBackground(ResourcesCompat.getDrawable(getResources(), R.color.bg_nav_list, null));
                lastFocus = view;
                setFlexLayout(beanList.get(position).getArticles());
            }
        });
        categoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        categoryList.setAdapter(mAdapter);
        setFlexLayout(beanList.get(0).getArticles());
        catTitle.setText(beanList.get(0).getName());
    }

    private void setFlexLayout(final List<ArticleDetailData> data) {
        mFlexbox.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            TextView child = createOrGetCacheFlexItemTextView(mFlexbox);
            child.setText(data.get(i).getTitle());
            final int finalI = i;
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toContentActivity(getActivity(),data.get(finalI).getId(),data.get(finalI).getLink(),data.get(finalI).getTitle());
                }
            });
            mFlexbox.addView(child);
        }
    }

    private TextView createOrGetCacheFlexItemTextView(FlexboxLayout fbl) {
        TextView tv = mFlexItemTextViewCaches.poll();
        if (tv != null) {
            return tv;
        }
        return createFlexItemTextView(fbl);
    }

    private TextView createFlexItemTextView(FlexboxLayout fbl) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(fbl.getContext());
        }
        return (TextView) mInflater.inflate(R.layout.item_knowledge_rc_child, fbl, false);
    }

}

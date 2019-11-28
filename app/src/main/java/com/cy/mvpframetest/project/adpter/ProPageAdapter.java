package com.cy.mvpframetest.project.adpter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cy.mvpframetest.bean.ProCategoryBean;
import com.cy.mvpframetest.project.projectlist.ProjectListFragment;

import java.util.ArrayList;
import java.util.List;

public class ProPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;
    public ProPageAdapter(FragmentManager fm, List<ProCategoryBean> categories) {
        super(fm);
        if (fragments != null) {
            fragments.clear();
        }
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        for (ProCategoryBean category : categories) {
            titles.add(category.getName());
            Fragment fragment = ProjectListFragment.newInstance(category.getId());
            fragments.add(fragment);
        }
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return titles.size();
    }


    /**
     * 此方法用来显示tab上的名字
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}

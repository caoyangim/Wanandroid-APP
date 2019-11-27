package com.cy.recgallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.cy.recgallery.adapter.RcAdapter;
import com.cy.recgallery.adapter.ScalableCardItemDecoration;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

    @BindView(R.id.rec)
    RecyclerView mRecyclerView;
    @BindView(R.id.bac_img)
    ImageView mBacImag;

    private RcAdapter mAdapter;
    //CardScaleHelper mCardScaleHelper;
    int mLastPos = -1;
    List<String> datas = Arrays.asList(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559884528&di=4b78eb906804b4d7c4c75f780b8555e9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201609%2F15%2F20160915103550_FxWCE.jpeg"
            ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559884528&di=4b78eb906804b4d7c4c75f780b8555e9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201609%2F15%2F20160915103550_FxWCE.jpeg"
            ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559289723579&di=ce622578ba9bf431ee55c43c3b4b8c27&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fcedeae5c5f717e1acfe31473d54190998c5508771881d-TNHJ2b_fw658"
            ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559289766095&di=637e259bc304c4f575bf10ba4f9aee6b&imgtype=0&src=http%3A%2F%2Fimage.namedq.com%2Fuploads%2F20181219%2F23%2F1545233794-OpGuernCUI.jpg"
            ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559289876960&di=c95a33a8cb9cadab370dd3921a601b3a&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201704%2F21%2F20170421192631_ZXJiV.thumb.700_0.jpeg"
            ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559289810171&di=5001fedaf150ba7a09982747d70e834f&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201602%2F01%2F20160201013529_V8zT3.thumb.700_0.jpeg"
            ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559884528&di=4b78eb906804b4d7c4c75f780b8555e9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201609%2F15%2F20160915103550_FxWCE.jpeg");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,null);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void init() {
        mAdapter = new RcAdapter(datas,getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearSnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mRecyclerView);
        /*mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.attachToRecyclerView(mRecyclerView);*/
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new ScalableCardItemDecoration());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // dx>0则表示右滑, dx<0表示左滑, dy<0表示上滑, dy>0表示下滑
                int childCount = recyclerView.getChildCount();//总item的数量
                int width = recyclerView.getChildAt(0).getWidth();//第一个item的宽度
                int padding = (recyclerView.getWidth() - width) / 2;//这个padding是 recycler的宽度减去第一个item的宽度然后除以2，作为padding
                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);//获取每一个child
                    float rate = 0;//是一个缩放比例
                    if (v.getLeft() <= padding) {//如果view距离左边的宽度 小于等于 左侧剩余空间(padding) （意味着这个view开始往左边滑动了，并且有遮挡）
                        if (v.getLeft() >= padding - v.getWidth()) {//如果view距离左边的距离 小于等于滑进去的距离 （其实就是说滑动到一半的时候）
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();//（这个比例的计算结果一般都会大于1，这样一来，根据下面的 1- rate * 0.1 得出，这个比例最多不会到达1，也就是 1- 0.1， 也就是 0.9， 所以这个view的宽度最大不会小于他本身的90%）
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                    } else {
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {//这个过程大概是指这个view 从最后侧刚刚出现的时候开始滑动过padding的距离
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                    }
                }
            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                }
            }
        });
        //加载完成后的监听
        mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mRecyclerView.getChildCount() < 3) {
                    if (mRecyclerView.getChildAt(1) != null) {
                        View v1 = mRecyclerView.getChildAt(1);
                        v1.setScaleY(0.9f);
                    }
                } else {
                    if (mRecyclerView.getChildAt(0) != null) {
                        View v0 = mRecyclerView.getChildAt(0);
                        v0.setScaleY(0.9f);
                    }
                    if (mRecyclerView.getChildAt(2) != null) {
                        View v2 = mRecyclerView.getChildAt(2);
                        v2.setScaleY(0.9f);
                    }
                }
            }
        });
    }
}

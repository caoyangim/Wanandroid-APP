package com.cy.mvpframetest.knowledge.knowledgeList;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cy.mvpframetest.R;
import com.cy.mvpframetest.base.BaseActivity;
import com.cy.mvpframetest.bean.ArticleList;
import com.cy.mvpframetest.content.ContentActivity;
import com.cy.mvpframetest.http.api.KnowledgeLoader;
import com.cy.mvpframetest.knowledge.adapter.KnowledgeListRcAdapter;
import com.r0adkll.slidr.Slidr;

import java.util.Objects;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class KnowledgeListActivity extends BaseActivity {
    public final static String ID_INTENT = "idIntent";
    public final static String TIT_INTENT = "titIntent";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rc_knowledge)
    RecyclerView mrc_view;
    KnowledgeListRcAdapter mAdapter;

    ArticleList data;
    private int cid;
    private int TOTAL_COUNTER , mCurrentPage = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_list;
    }

    @Override
    protected void initSubViews(View view) {
        int primary = getResources().getColor(R.color.colorPrimary);
        int secondary = getResources().getColor(R.color.colorPrimary);
        Slidr.attach(this,primary,secondary);
        toolBarSet();
        initData();
    }

    private void toolBarSet() {
        toolbar.setTitle("知识体系 >>> "+getIntent().getStringExtra(TIT_INTENT));
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        //getSupportActionBar().setHomeButtonEnabled(true); 设置返回键可用,此处无效
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        //category ID
        cid = getIntent().getIntExtra(ID_INTENT,0);
        //请求数据
        KnowledgeLoader loader = new KnowledgeLoader();
        Observable<ArticleList> observable = loader.getKnowledgeListBean(0, cid);
        observable.subscribe(new Action1<ArticleList>() {
            @Override
            public void call(ArticleList articleList) {
                Log.e(">>T_T", String.valueOf(articleList.getDatas().size()));
                data = articleList;
                TOTAL_COUNTER = data.getPageCount();
                mCurrentPage = data.getCurPage();
                mAdapter = new KnowledgeListRcAdapter(R.layout.item_knoledge_list, data.getDatas());
                mAdapter.setOnItemClickListener(mListener);
                mAdapter.setOnLoadMoreListener(RequestLoadMoreListener);
                mrc_view.setLayoutManager(new LinearLayoutManager(KnowledgeListActivity.this));
                mrc_view.addItemDecoration(new DividerItemDecoration(KnowledgeListActivity.this, DividerItemDecoration.VERTICAL));
                mrc_view.setAdapter(mAdapter);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                //TODO 请求失败会闪退
            }
        });
    }

    private BaseQuickAdapter.OnItemClickListener mListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(KnowledgeListActivity.this, ContentActivity.class);
            intent.putExtra("id", data.getDatas().get(position).getId());
            intent.putExtra("url", data.getDatas().get(position).getLink());
            intent.putExtra("title", data.getDatas().get(position).getTitle());
            Objects.requireNonNull(KnowledgeListActivity.this).startActivity(intent);
        }
    };

    private BaseQuickAdapter.RequestLoadMoreListener RequestLoadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            Log.e(">>>","RequestLoadMoreListener");
            mrc_view.post(new Runnable() {
                @Override
                public void run() {
                    if (mCurrentPage >= TOTAL_COUNTER) {
                        //数据全部加载完毕
                        mAdapter.loadMoreEnd();
                    } else {
                        KnowledgeLoader loader = new KnowledgeLoader();
                        Observable<ArticleList> observable = loader.getKnowledgeListBean(mCurrentPage, cid);
                        observable.subscribe(new Action1<ArticleList>() {
                            @Override
                            public void call(ArticleList articleList) {
                                data = articleList;
                                mCurrentPage = data.getCurPage();
                                //加载更多
                                mAdapter.addData(data.getDatas());
                                mAdapter.loadMoreComplete();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                //TODO 请求失败会闪退
                            }
                        });
                    }
                }
            });
        }
    };

}

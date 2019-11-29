package com.cy.mvpframetest.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cy.mvpframetest.R;
import com.cy.mvpframetest.bean.ArticleDetailData;
import com.cy.mvpframetest.bean.ArticleList;
import com.cy.mvpframetest.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public class HomeRcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ArticleDetailData> datas = new ArrayList<>();
    public FooterHolder mFooterHolder;
    private OnItemClickListener mOnItemClickListener;

    public HomeRcAdapter(Context context) {
        this.mContext = context;
    }

    public void setDatas(List<ArticleDetailData> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public List<ArticleDetailData> getDatas(){
        return this.datas;
    }

    public void addAll(List<ArticleDetailData> list) {
        int lastIndex = this.datas.size();
          if (this.datas.addAll(list)) {
              notifyItemRangeInserted(lastIndex, list.size());
          }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if(viewType == 0){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home_rc,viewGroup,false);
            return new NormalHolder (view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.sample_common_list_footer, viewGroup, false);
            mFooterHolder = new FooterHolder(view);
            return mFooterHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NormalHolder){
            if (mOnItemClickListener == null) return;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });
            ((NormalHolder)holder).setData(position);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == datas.size())
            return 1;
        else
            return 0;
    }

    public class NormalHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.rv_tit)
        TextView rv_tit;
        /*@BindView(R.id.rv_author)
        TextView rv_author;*/
        @BindView(R.id.rv_assistant)
        TextView rv_assistant;
        @BindView(R.id.rv_date)
        TextView rv_date;
        public NormalHolder (@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(int position){
            ArticleDetailData data = datas.get(position);
            rv_tit.setText(data.getTitle());
            //rv_author.setText("作者："+data.getAuthor());
            rv_assistant.setText(data.getChapterName());rv_date.setText(data.getNiceDate());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}

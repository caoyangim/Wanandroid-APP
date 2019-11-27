package com.cy.mvpframetest.knowledge.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cy.mvpframetest.R;
import com.cy.mvpframetest.bean.KnowledgeBean;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2019/8/6
 * 功能描述：知识体系（一级）界面 adapter
 */
public class KnowledgeRcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<KnowledgeBean> knowledgeBeanList = new ArrayList<>();
    private Queue<TextView> mFlexItemTextViewCaches = new LinkedList<>();
    private LayoutInflater mInflater = null;
    private OnItemClickListener mOnItemClickListener;

    public KnowledgeRcAdapter(Context context) {
        mContext = context;
    }

    public void setDatas(List<KnowledgeBean> knowledgeBeanList){
        this.knowledgeBeanList = knowledgeBeanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_knowledge_rc,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,final int i) {
        if (holder instanceof ViewHolder){

            ((ViewHolder)holder).setData(i);
        }
    }

    @Override
    public int getItemCount() {
        return knowledgeBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.fbl)
        FlexboxLayout fbl;
        @BindView(R.id.knowledge_rc_tit)
        TextView tit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(int position){
            final KnowledgeBean bean = knowledgeBeanList.get(position);
            tit.setText(bean.getName());
            for (int i = 0; i < bean.getChildren().size(); i++) {
                TextView child = createOrGetCacheFlexItemTextView(fbl);
                child.setText(bean.getChildren().get(i).getName());
                final int finalI = i;
                child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(bean,finalI);
                    }
                });
                fbl.addView(child);
            }
        }
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        FlexboxLayout fbl = ((ViewHolder)holder).fbl;
        for (int i = 0; i < fbl.getChildCount(); i++) {
            mFlexItemTextViewCaches.offer((TextView) fbl.getChildAt(i));
        }
        fbl.removeAllViews();
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

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(KnowledgeBean bean,int position);
    }
}

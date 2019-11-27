package com.cy.emotionrain.adapter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2019/5/31
 * 功能描述：
 */
public class ScalableCardItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.ViewHolder holder = parent.getChildViewHolder(view);
        int position = holder.getAdapterPosition() == RecyclerView.NO_POSITION ? holder.getOldPosition() : holder.getAdapterPosition();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int itemCount = layoutManager.getItemCount();

        if(position != 0 && position != itemCount - 1){
            return;
        }

        int peekWidth = getPeekWidth(parent, view);
        boolean isVertical = layoutManager.canScrollVertically();
        //移除item时adapter position为-1。

        if (isVertical) {
            if (position == 0) {
                outRect.set(0, peekWidth, 0, 0);
            } else if (position == itemCount - 1) {
                outRect.set(0, 0, 0, peekWidth);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        } else {
            if (position == 0) {
                outRect.set(peekWidth, 0, 0, 0);
            } else if (position == itemCount - 1) {
                outRect.set(0, 0, peekWidth, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }

    public static int getPeekWidth(RecyclerView recyclerView, View itemView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        boolean isVertical = layoutManager.canScrollVertically();
        int position = recyclerView.getChildAdapterPosition(itemView);
        //TODO RecyclerView使用wrap_content时，获取的宽度可能会是0。
        int parentWidth = recyclerView.getMeasuredWidth();
        int parentHeight = recyclerView.getMeasuredHeight(); //有时会拿到0
        parentWidth = parentWidth == 0 ? recyclerView.getWidth() : parentWidth;
        parentHeight = parentHeight == 0 ? recyclerView.getHeight() : parentHeight;
        int parentEnd = isVertical ? parentHeight : parentWidth;
        int parentCenter = parentEnd / 2;

        int itemSize = isVertical ? itemView.getMeasuredHeight() : itemView.getMeasuredWidth();

        if (itemSize == 0) {

            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            int widthMeasureSpec =
                    RecyclerView.LayoutManager.getChildMeasureSpec(parentWidth,
                            layoutManager.getWidthMode(),
                            recyclerView.getPaddingLeft() + recyclerView.getPaddingRight(),
                            layoutParams.width, layoutManager.canScrollHorizontally());

            int heightMeasureSpec =
                    RecyclerView.LayoutManager.getChildMeasureSpec(parentHeight,
                            layoutManager.getHeightMode(),
                            recyclerView.getPaddingTop() + recyclerView.getPaddingBottom(),
                            layoutParams.height, layoutManager.canScrollVertically());


            itemView.measure(widthMeasureSpec, heightMeasureSpec);
            itemSize = isVertical ? itemView.getMeasuredHeight() : itemView.getMeasuredWidth();
        }


        /*
            计算ItemDecoration的大小，确保插入的大小正好使view的start + itemSize / 2等于parentCenter。
         */
        int startOffset = parentCenter - itemSize / 2;
        int endOffset = parentEnd - (startOffset + itemSize);

        return position == 0 ? startOffset : endOffset;
    }
}

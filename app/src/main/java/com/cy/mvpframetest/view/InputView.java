package com.cy.mvpframetest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cy.mvpframetest.R;

/**
 * input_icon:输入框前的图标
 * input_hint:输入框的提示内容
 * is_password:是否以密文展示
 */
public class InputView extends FrameLayout {
    private int inputIcon;
    private String inputHint;
    private boolean isPassword;

    private EditText iv_edit;

    public InputView(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        if (attrs == null) return;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputView);
        inputIcon = typedArray.getResourceId(R.styleable.InputView_input_icon,R.mipmap.heart_hollow);
        inputHint = typedArray.getString(R.styleable.InputView_input_hint);
        isPassword = typedArray.getBoolean(R.styleable.InputView_is_password,false);
        typedArray.recycle();

        Drawable icon = ContextCompat.getDrawable(context, inputIcon);
        icon.setBounds(0, 0, icon.getMinimumWidth(),icon.getMinimumHeight());

        View view = LayoutInflater.from(context).inflate(R.layout.input_view, this, false);
        iv_edit = view.findViewById(R.id.iv_edit);

        iv_edit.setHint(inputHint);
        iv_edit.setInputType(isPassword ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT);
        iv_edit.setCompoundDrawables(icon,null,null,null);

        addView(view);
    }

    public String getInputStr(){
        return iv_edit.getText().toString().trim();
    }
}

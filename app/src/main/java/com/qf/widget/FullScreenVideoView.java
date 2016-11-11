package com.qf.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by lenovo on 2016/11/10.
 */
public class FullScreenVideoView extends VideoView {
//    private OnCompletionListener onCompletionListener;
//
//    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
//        this.onCompletionListener = onCompletionListener;
//    }

    public FullScreenVideoView(Context context) {
        super(context);
// TODO Auto-generated constructor stub
    }

    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

//    public interface OnCompletionListener {
//        void onCompletion(MediaPlayer mp);
//    }
}

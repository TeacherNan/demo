package com.sdk.ui;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wb-wangxiaolong on 13-12-11.
 */
public class DragController extends SimpleFloatViewManager implements View.OnTouchListener,GestureDetector.OnGestureListener{

    private static final String TAG = "sdk.ui";
    private GestureDetector mDetector;
    private DragGridView mDragGridView;
    private boolean mDragging = false;

    public DragController(DragGridView gridView) {
        super(gridView);
        mDragGridView = gridView;
        mDetector = new GestureDetector(gridView.getContext(),this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG, "DragController#onTouch()");
        mDetector.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "DragController#onDown()");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "DragController#onShowPress()");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "DragController#onSingleTapUp()");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "DragController#onScroll()");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "DragController#onLongPress()");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "DragController#onFling()");
        return false;
    }


    public boolean startDrag(int position, int deltaX, int deltaY) {

        mDragging = mDragGridView.startDrag(position ,deltaX,deltaY);
        return mDragging;
    }


}

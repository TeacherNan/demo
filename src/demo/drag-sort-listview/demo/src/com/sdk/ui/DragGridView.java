package com.sdk.ui;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

/**
 * Created by wb-wangxiaolong on 13-12-10.
 */
public class DragGridView extends GridView{
    private static final String TAG = "sdk.ui";
    /**
     * A listener that receives callbacks whenever the floating View
     * hovers over a new position.
     */
    private DragListener mDragListener;

    /**
     * A listener that receives a callback when the floating View
     * is dropped.
     */
    private DropListener mDropListener;

    /**
     * The View that floats above the GridView and represents
     * the dragged item.
     */
    private View mFloatView;

    /**
     * 让使用者自定义floatview
     */
    private FloatViewManager mFloatViewManager = null;

    /**
     * A touch event is in progress.
     */
    private boolean mInTouchEvent = false;

    private boolean mDragEnabled = true;

    /**
     * Offset (in x) within the dragged item at which the user
     * picked it up (or first touched down with the digitalis).
     */
    private int mDragDeltaX;

    /**
     * Offset (in y) within the dragged item at which the user
     * picked it up (or first touched down with the digitalis).
     */
    private int mDragDeltaY;

    /**
     * Drag state enum.
     */
    private final static int IDLE = 0;
    private final static int REMOVING = 1;
    private final static int DROPPING = 2;
    private final static int STOPPED = 3;
    private final static int DRAGGING = 4;

    private int mDragState = IDLE;


    public DragGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private  void init(){
        DragController dragController = new DragController(this);
        mFloatViewManager = dragController;
        setOnTouchListener(dragController);
    }



    public interface FloatViewManager {


        public View onCreateFloatView(int position);


        public void onDragFloatView(View floatView, Point location, Point touch);


        public void onDestroyFloatView(View floatView);
    }

    public interface DropListener {
        public void drop(int from, int to);
    }

    public interface DragListener {
        public void drag(int from, int to);
    }

    public void setDropListener(DropListener l) {
        mDropListener = l;
    }

    public void setDragListener(DragListener l) {
        mDragListener = l;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG, "DragGridView#onTouchEvent()");
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "DragGridView#onInterceptTouchEvent()");
        return super.onInterceptTouchEvent(ev);
    }

    public boolean startDrag(int position, int deltaX, int deltaY) {
        if ( mFloatViewManager == null) {
            return false;
        }

        View v = mFloatViewManager.onCreateFloatView(position);

        if (v == null) {
            return false;
        } else {
            return startDrag(position,v,deltaX, deltaY);
        }

    }

    public void setDragEnabled(boolean enabled) {
        mDragEnabled = enabled;
    }

    public boolean isDragEnabled() {
        return mDragEnabled;
    }

    public boolean startDrag(int position, View floatView,int deltaX, int deltaY) {
        if (mDragState != IDLE ||  mFloatView != null || floatView == null
                || !mDragEnabled) {
            return false;
        }

        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        // mDragState = dragType;
        mDragState = DRAGGING;

        mFloatView = floatView;
        measureFloatView(); // sets mFloatViewHeight

        mDragDeltaX = deltaX;
        mDragDeltaY = deltaY;
        mDragStartY = mY;

        // updateFloatView(mX - mDragDeltaX, mY - mDragDeltaY);
        mFloatLoc.x = mX - mDragDeltaX;
        mFloatLoc.y = mY - mDragDeltaY;

        // set src item invisible
        final View srcItem = getChildAt(mSrcPos - getFirstVisiblePosition());

        if (srcItem != null) {
            srcItem.setVisibility(View.INVISIBLE);
        }

        if (mTrackDragSort) {
            mDragSortTracker.startTracking();
        }

        // once float view is created, events are no longer passed
        // to ListView
        switch (mCancelMethod) {
            case ON_TOUCH_EVENT:
                super.onTouchEvent(mCancelEvent);
                break;
            case ON_INTERCEPT_TOUCH_EVENT:
                super.onInterceptTouchEvent(mCancelEvent);
                break;
        }

        requestLayout();

        if (mLiftAnimator != null) {
            mLiftAnimator.start();
        }

        return true;
    }


}

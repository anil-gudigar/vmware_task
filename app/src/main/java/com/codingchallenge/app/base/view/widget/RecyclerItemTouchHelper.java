package com.codingchallenge.app.base.view.widget;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.codingchallenge.R;

/**
 * Created by Anil Gudigar on 11/24/18.
 */
public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;
    private boolean swipeBack = false;
    private static int direction = ItemTouchHelper.LEFT;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = viewHolder.itemView.findViewById(R.id.view_foreground);
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = viewHolder.itemView.findViewById(R.id.view_foreground);
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = viewHolder.itemView.findViewById(R.id.view_foreground);
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = viewHolder.itemView.findViewById(R.id.view_foreground);
        final View backgroundView = viewHolder.itemView.findViewById(R.id.view_background);
        if(dX > 0){
            direction = ItemTouchHelper.RIGHT;
            backgroundView.findViewById(R.id.swipe_left_icon).setVisibility(View.VISIBLE);
            backgroundView.findViewById(R.id.swipe_right_icon).setVisibility(View.GONE);
        }else{
            direction = ItemTouchHelper.LEFT;
            backgroundView.findViewById(R.id.swipe_left_icon).setVisibility(View.GONE);
            backgroundView.findViewById(R.id.swipe_right_icon).setVisibility(View.VISIBLE);
        }
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
        final View backgroundView = viewHolder.itemView.findViewById(R.id.view_background);
        backgroundView.findViewById(R.id.swipe_left_icon).setVisibility(View.GONE);
        backgroundView.findViewById(R.id.swipe_right_icon).setVisibility(View.GONE);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}

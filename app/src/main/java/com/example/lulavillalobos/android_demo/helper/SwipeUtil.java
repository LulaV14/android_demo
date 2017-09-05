package com.example.lulavillalobos.android_demo.helper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.lulavillalobos.android_demo.R;

/**
 * Created by lulavillalobos on 9/4/17.
 */

public class SwipeUtil extends ItemTouchHelper.SimpleCallback {

    private Drawable background;
    private Drawable deleteIcon;

    private int margin;

    private boolean initiated;
    private Context context;

    private int leftColorCode;
    private String leftSwipeLabel;

    public SwipeUtil(Context context, int drag_dir, int swipe_dir) {
        super(drag_dir, swipe_dir);
        this.context = context;
    }

    private void init() {
        background = new ColorDrawable();
        margin = (int) context.getResources().getDimension(R.dimen.padding_swipe_delete); //TODO
        deleteIcon = ContextCompat.getDrawable(context, android.R.drawable.ic_menu_delete);
        deleteIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        initiated = true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    //@Override
    //public abstract void onSwiped(RecyclerView.ViewHolder, int direction);

    @Override
    public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return super.getSwipeDirs(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;
        if(!initiated) {
            init();
        }

        int itemHeight = itemView.getBottom() - itemView.getTop();

        //Setting Swipe Background
        ((ColorDrawable) background).setColor(leftColorCode);
        background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        background.draw(c);

        int intrinsicWidth = deleteIcon.getIntrinsicWidth();
        int intrinsicHeight = deleteIcon.getIntrinsicHeight();

        int markLeft = itemView.getRight() - margin - intrinsicWidth;
        int markRight = itemView.getRight() - margin;
        int markTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
        int markBottom = markTop + intrinsicHeight;

        //Setting Swipe Icon
        deleteIcon.setBounds(markLeft, markTop + 16, markRight, markBottom);
        deleteIcon.draw(c);

        //Setting Swipe Text
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(48);
        paint.setTextAlign(Paint.Align.CENTER);
        c.drawText(leftSwipeLabel, markLeft + 40, markTop + 10, paint);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

}

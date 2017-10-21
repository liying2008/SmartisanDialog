package cc.duduhuo.dialog.smartisan.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net<br>
 * Date: 2017/10/21 15:51 <br>
 * Version: 1.0 <br>
 * Description: recycler view line divider <br>
 * Remark: <br>
 * =======================================================
 */
public class DividerLineDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
        android.R.attr.listDivider
    };

    private Drawable mDivider;

    public DividerLineDecoration(Context context) {
        super();
        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDivider = typedArray.getDrawable(0);

        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int childCount = parent.getChildCount();
        // the last child has not bottom divider
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            mDivider.setBounds(0, child.getBottom() + params.bottomMargin,
                child.getWidth(), child.getBottom() + mDivider.getIntrinsicHeight());
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        if (parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1) {
            outRect.bottom = 0;
        } else {
            outRect.bottom = mDivider.getIntrinsicHeight();
        }
        outRect.top = 0;
        outRect.left = 0;
        outRect.right = 0;
    }
}

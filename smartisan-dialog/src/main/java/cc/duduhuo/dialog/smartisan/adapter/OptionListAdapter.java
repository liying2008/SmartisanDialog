package cc.duduhuo.dialog.smartisan.adapter;

import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cc.duduhuo.dialog.smartisan.R;
import cc.duduhuo.dialog.smartisan.listener.OnOptionItemSelectListener;

/**
 * =======================================================
 * Author: liying - liruoer2008@yeah.net <br>
 * Date: 2017/5/13 16:26 <br>
 * Version: 1.0 <br>
 * Description: Option list adapter <br>
 * Remark:  <br>
 * =======================================================
 */
public class OptionListAdapter extends RecyclerView.Adapter<OptionListAdapter.ViewHolder> {
    private List<? extends CharSequence> mOptionList;
    private CharSequence mLast = "";
    @ColorInt
    private int mLastColor;
    private OnOptionItemSelectListener mListener;
    private int mItemGravity;

    public OptionListAdapter(List<? extends CharSequence> optionList, CharSequence last, @ColorInt int lastColor) {
        this.mOptionList = optionList;
        this.mLastColor = lastColor;
        if (!TextUtils.isEmpty(last)) {
            this.mLast = last;
        }
    }

    public void setItemGravity(int gravity) {
        this.mItemGravity = gravity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ddh_sm_item_option_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CharSequence option = mOptionList.get(position);
        holder.mTvItemName.setText(option);
        holder.mTvItemName.setGravity(mItemGravity);
        if (option.toString().equals(mLast.toString())) {
            holder.mTvItemName.setTextColor(mLastColor);
        } else {
            holder.mTvItemName.setTextColor(0xff333333);
        }
        holder.mRlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onSelect(holder.getAdapterPosition(), option);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mOptionList == null) return 0;
        return mOptionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mRlItem;
        private TextView mTvItemName;

        public ViewHolder(View itemView) {
            super(itemView);
            mRlItem = (RelativeLayout) itemView.findViewById(R.id.rlItem);
            mTvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
        }
    }

    public void setOnOptionItemSelectListener(OnOptionItemSelectListener listener) {
        this.mListener = listener;
    }
}

package cc.duduhuo.dialog.smartisan.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cc.duduhuo.dialog.smartisan.R;
import cc.duduhuo.dialog.smartisan.bean.Choice;
import cc.duduhuo.dialog.smartisan.listener.OnSingleChoiceSelectListener;

/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net <br>
 * Date: 2017/10/18 21:27 <br>
 * Version: 1.0 <br>
 * Description: Single choice items adapter <br>
 * Remark: <br>
 * =======================================================
 */
public class SingleChoiceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    /** the checked item's position */
    private int mCheckedPosition;
    private boolean hasDescription;
    private Choice[] mChoices;
    private boolean isRadioIconHide;
    private OnSingleChoiceSelectListener mSelectListener;

    public SingleChoiceListAdapter(Context context, boolean hasDescription, Choice[] choices, boolean isRadioIconHide) {
        this.mContext = context;
        this.hasDescription = hasDescription;
        this.mChoices = choices;
        this.isRadioIconHide = isRadioIconHide;
    }

    public void setOnSingleChoiceSelectListener(OnSingleChoiceSelectListener listener) {
        this.mSelectListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (hasDescription) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.ddh_sm_item_choice_desc, parent, false);
            return new DescViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.ddh_sm_item_choice_nodesc, parent, false);
            return new NoDescViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (hasDescription) {
            final DescViewHolder descHolder = (DescViewHolder) holder;
            if (isRadioIconHide) {
                descHolder.mIvCheck.setVisibility(View.GONE);
            }

            descHolder.mTvItem.setText(mChoices[position].getItem());
            descHolder.mTvDescription.setText(mChoices[position].getDescription());
            boolean isChecked = mChoices[position].isChecked();
            if (isChecked) {
                mCheckedPosition = descHolder.getAdapterPosition();
                descHolder.mIvCheck.setImageResource(R.drawable.ddh_sm_shape_radio_checked);
            } else {
                descHolder.mIvCheck.setImageResource(R.drawable.ddh_sm_shape_radio_normal);
            }
            descHolder.mLlRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectListener != null) {
                        mSelectListener.onSelect(descHolder.getAdapterPosition());
                    }
                    refreshCheckState(descHolder.getAdapterPosition());
                }
            });
        } else {
            final NoDescViewHolder nodescHolder = (NoDescViewHolder) holder;
            if (isRadioIconHide) {
                nodescHolder.mIvCheck.setVisibility(View.GONE);
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, mContext.getResources().getDisplayMetrics());
                nodescHolder.mLlRoot.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, px));
                nodescHolder.mTvItem.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            }

            nodescHolder.mTvItem.setText(mChoices[position].getItem());
            boolean isChecked = mChoices[position].isChecked();
            if (isChecked) {
                mCheckedPosition = nodescHolder.getAdapterPosition();
                nodescHolder.mIvCheck.setImageResource(R.drawable.ddh_sm_shape_radio_checked);
            } else {
                nodescHolder.mIvCheck.setImageResource(R.drawable.ddh_sm_shape_radio_normal);
            }
            nodescHolder.mLlRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectListener != null) {
                        mSelectListener.onSelect(nodescHolder.getAdapterPosition());
                    }
                    refreshCheckState(nodescHolder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mChoices != null) {
            return mChoices.length;
        } else {
            return 0;
        }
    }

    private void refreshCheckState(int position) {
        if (mCheckedPosition != position) {
            mChoices[mCheckedPosition].setChecked(false);
            mChoices[position].setChecked(true);
            notifyItemChanged(mCheckedPosition);
            notifyItemChanged(position);
        }
    }

    private static class DescViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLlRoot;
        private TextView mTvItem;
        private TextView mTvDescription;
        private ImageView mIvCheck;

        DescViewHolder(View itemView) {
            super(itemView);
            mLlRoot = (LinearLayout) itemView.findViewById(R.id.llRoot);
            mTvItem = (TextView) itemView.findViewById(R.id.tvItem);
            mTvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            mIvCheck = (ImageView) itemView.findViewById(R.id.ivCheck);
        }
    }

    private static class NoDescViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLlRoot;
        private TextView mTvItem;
        private ImageView mIvCheck;

        NoDescViewHolder(View itemView) {
            super(itemView);
            mLlRoot = (LinearLayout) itemView.findViewById(R.id.llRoot);
            mTvItem = (TextView) itemView.findViewById(R.id.tvItem);
            mIvCheck = (ImageView) itemView.findViewById(R.id.ivCheck);
        }
    }
}

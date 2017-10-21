package cc.duduhuo.dialog.smartisan;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cc.duduhuo.dialog.smartisan.adapter.DividerLineDecoration;
import cc.duduhuo.dialog.smartisan.adapter.SingleChoiceListAdapter;
import cc.duduhuo.dialog.smartisan.bean.Choice;
import cc.duduhuo.dialog.smartisan.listener.OnSingleChoiceSelectListener;

/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net <br>
 * Date: 2017/10/18 20:49 <br>
 * Version: 1.0  <br>
 * Description: SingleChoiceDialog <br>
 * Remark:   <br>
 * =======================================================
 */
public class SingleChoiceDialog extends AlertDialog {
    private Context mContext;
    private TextView mTvTitle;
    private RecyclerView mRvChoices;
    private SingleChoiceListAdapter mAdapter;
    private boolean hasDescription = false;
    private TextView mTv1;
    private TextView mTv2;
    private View mDivider1;
    private View mDivider2;

    private String mTitle = "";
    private int mTitleTextSize = 18;
    private String mText1 = "";
    private String mText2 = "";
    private boolean isRadioIconHide = false;
    private Choice[] mChoices;
    @DrawableRes
    private int mLeftBgId = R.drawable.ddh_sm_selector_dialog_normal_btn_bg;
    @DrawableRes
    private int mRightBgId = R.drawable.ddh_sm_selector_dialog_normal_btn_bg;

    private OnBtnSelectListener mBtnListener;

    SingleChoiceDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddh_sm_dialog_single_choice);
        findViews();
    }

    private void findViews() {
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mRvChoices = (RecyclerView) findViewById(R.id.rvChoices);
        mTv1 = (TextView) findViewById(R.id.tv1);
        mTv2 = (TextView) findViewById(R.id.tv2);
        mDivider1 = findViewById(R.id.divider1);
        mDivider2 = findViewById(R.id.divider2);
        mRvChoices.setLayoutManager(new LinearLayoutManager(mContext));
        mRvChoices.addItemDecoration(new DividerLineDecoration(mContext));
        mRvChoices.getItemAnimator().setChangeDuration(0);
    }

    /**
     * Set the dialog title
     *
     * @param title dialog title
     * @return This dialog instance
     */
    public SingleChoiceDialog setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    /**
     * Set the dialog title
     *
     * @param sp dialog title text size in sp.
     * @return This dialog instance.
     */
    public SingleChoiceDialog setTitleTextSize(int sp) {
        this.mTitleTextSize = sp;
        return this;
    }

    /**
     * Set the text of the left button
     *
     * @param btn1Text The text of the left button
     * @return This dialog instance
     */
    public SingleChoiceDialog setLeftBtnText(String btn1Text) {
        this.mText1 = btn1Text;
        return this;
    }

    /**
     * Set the text of the right button
     *
     * @param btn2Text The text of the right button
     * @return This dialog instance
     */
    public SingleChoiceDialog setRightBtnText(String btn2Text) {
        this.mText2 = btn2Text;
        return this;
    }

    /**
     * Set the left button background
     *
     * @param resId Background resource id
     * @return This dialog instance
     */
    public SingleChoiceDialog setLeftBtnBackground(@DrawableRes int resId) {
        this.mLeftBgId = resId;
        return this;
    }

    /**
     * Set the right button background
     *
     * @param resId Background resource id
     * @return This dialog instance
     */
    public SingleChoiceDialog setRightBtnBackground(@DrawableRes int resId) {
        this.mRightBgId = resId;
        return this;
    }

    /**
     * Set a list of items to be displayed in the dialog as the content
     *
     * @param items       the items to be displayed.
     * @param checkedItem specifies which item is checked. If -1 no items are checked.
     */
    public SingleChoiceDialog setSingleChoiceItems(CharSequence[] items, int checkedItem) {
        hasDescription = false;
        setChoices(items, null, checkedItem);
        return this;
    }

    /**
     * Set a list of items to be displayed in the dialog as the content
     *
     * @param items       the items to be displayed.
     * @param checkedItem specifies which item is checked. If -1 no items are checked.
     */
    public SingleChoiceDialog setSingleChoiceItems(List<? extends CharSequence> items, int checkedItem) {
        hasDescription = false;
        int size = items.size();
        CharSequence[] itemArray = new CharSequence[size];
        for (int i = 0; i < size; i++) {
            itemArray[i] = items.get(i);
        }
        setChoices(itemArray, null, checkedItem);
        return this;
    }

    /**
     * Set a list of items to be displayed in the dialog as the content
     *
     * @param items        the items to be displayed.
     * @param descriptions the descriptions to be displayed.
     * @param checkedItem  specifies which item is checked. If -1 no items are checked.
     */
    public SingleChoiceDialog setSingleChoiceItems(CharSequence[] items, CharSequence[] descriptions, int checkedItem) {
        hasDescription = true;
        setChoices(items, descriptions, checkedItem);
        return this;
    }

    /**
     * Set a list of items to be displayed in the dialog as the content
     *
     * @param items        the items to be displayed.
     * @param descriptions the descriptions to be displayed.
     * @param checkedItem  specifies which item is checked. If -1 no items are checked.
     */
    public SingleChoiceDialog setSingleChoiceItems(List<? extends CharSequence> items, List<? extends CharSequence> descriptions, int checkedItem) {
        hasDescription = true;
        int itemSize = items.size();
        CharSequence[] itemArray = new CharSequence[itemSize];
        for (int i = 0; i < itemSize; i++) {
            itemArray[i] = items.get(i);
        }

        int descSize = descriptions.size();
        CharSequence[] descSizeArray = new CharSequence[descSize];
        for (int i = 0; i < descSize; i++) {
            descSizeArray[i] = descriptions.get(i);
        }

        setChoices(itemArray, descSizeArray, checkedItem);
        return this;
    }

    private void setChoices(CharSequence[] items, CharSequence[] descriptions, int checkedItem) {
        int size;
        if (descriptions == null) {
            size = items.length;
        } else {
            size = items.length >= descriptions.length ? descriptions.length : items.length;
        }
        mChoices = new Choice[size];
        for (int i = 0; i < size; i++) {
            mChoices[i] = new Choice();
            mChoices[i].setItem(items[i]);
            if (descriptions != null) {
                mChoices[i].setDescription(descriptions[i]);
            }
            mChoices[i].setChecked(false);
        }
        if (checkedItem >= 0 && checkedItem <= items.length) {
            mChoices[checkedItem].setChecked(true);
        }
    }

    /**
     * hide radio icon.
     * @return
     */
    public SingleChoiceDialog hideRadioIcon() {
        isRadioIconHide = true;
        return this;
    }

    @Override
    public void show() {
        super.show();
        mTvTitle.setText(mTitle);
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTitleTextSize);
        if (TextUtils.isEmpty(mText1)) {
            // Hide the left button
            mDivider2.setVisibility(View.GONE);
            mTv1.setVisibility(View.GONE);
        } else {
            mTv1.setText(mText1);
            mTv1.setBackgroundResource(mLeftBgId);
        }
        if (TextUtils.isEmpty(mText2)) {
            // Hide the right button
            mDivider2.setVisibility(View.GONE);
            mTv2.setVisibility(View.GONE);
        } else {
            mTv2.setText(mText2);
            mTv2.setBackgroundResource(mRightBgId);
        }

        // No Buttons
        if (TextUtils.isEmpty(mText1) && TextUtils.isEmpty(mText2)) {
            mDivider1.setVisibility(View.GONE);
        }
        mTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBtnListener != null) {
                    mBtnListener.onLeftSelect();
                }
            }
        });
        mTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBtnListener != null) {
                    mBtnListener.onRightSelect();
                }
            }
        });

        mAdapter = new SingleChoiceListAdapter(mContext, hasDescription, mChoices, isRadioIconHide);
        mRvChoices.setAdapter(mAdapter);
    }


    public void setOnBtnSelectListener(OnBtnSelectListener listener) {
        this.mBtnListener = listener;
    }

    /**
     * Set the choice select listener <br>
     * This method must be called after the show() method <br>
     *
     * @param listener
     */
    public void setOnSingleChoiceSelectListener(OnSingleChoiceSelectListener listener) {
        this.mAdapter.setOnSingleChoiceSelectListener(listener);
    }

    public interface OnBtnSelectListener {
        /** Select the button on the left */
        void onLeftSelect();

        /** Select the button on the right */
        void onRightSelect();
    }
}

package cc.duduhuo.dialog.smartisan;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * =======================================================
 * Author: liying - liruoer2008@yeah.net <br>
 * Date: 2017/5/13 15:09 <br>
 * Version: 1.0  <br>
 * Description: NormalDialog <br>
 * Remark:   <br>
 * =======================================================
 */
public class NormalDialog extends AlertDialog {
    private TextView mTvTitle;
    private TextView mTvMsg;
    private TextView mTv1;
    private TextView mTv2;
    private View mDivider1;
    private View mDivider2;

    private String mTitle = "";
    private String mMsg = "";
    private String mText1 = "";
    private String mText2 = "";
    private int mGravity = Gravity.CENTER;
    @DrawableRes
    private int mLeftBgId = R.drawable.ddh_sm_selector_dialog_normal_btn_bg;
    @DrawableRes
    private int mRightBgId = R.drawable.ddh_sm_selector_dialog_normal_btn_bg;

    private OnSelectListener mListener;

    NormalDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddh_sm_dialog_normal);
        findView();
    }

    private void findView() {
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvMsg = (TextView) findViewById(R.id.tvMsg);
        mTv1 = (TextView) findViewById(R.id.tv1);
        mTv2 = (TextView) findViewById(R.id.tv2);
        mDivider1 = findViewById(R.id.divider1);
        mDivider2 = findViewById(R.id.divider2);
    }

    /**
     * Set the dialog title
     *
     * @param title dialog title
     * @return This dialog instance
     */
    public NormalDialog setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    /**
     * Set the contents of the dialog
     *
     * @param msg Dialog contents
     * @return This dialog instance
     */
    public NormalDialog setMsg(String msg) {
        this.mMsg = msg;
        return this;
    }

    /**
     * Set the gravity of the message
     *
     * @param gravity The gravity of the message
     * @return This dialog instance
     */
    public NormalDialog setMsgGravity(int gravity) {
        this.mGravity = gravity;
        return this;
    }

    /**
     * Set the text of the left button
     *
     * @param btn1Text The text of the left button
     * @return This dialog instance
     */
    public NormalDialog setLeftBtnText(String btn1Text) {
        this.mText1 = btn1Text;
        return this;
    }

    /**
     * Set the text of the right button
     *
     * @param btn2Text The text of the right button
     * @return This dialog instance
     */
    public NormalDialog setRightBtnText(String btn2Text) {
        this.mText2 = btn2Text;
        return this;
    }

    /**
     * Set the left button background
     *
     * @param resId Background resource id
     * @return This dialog instance
     */
    public NormalDialog setLeftBtnBackground(@DrawableRes int resId) {
        this.mLeftBgId = resId;
        return this;
    }

    /**
     * Set the right button background
     *
     * @param resId Background resource id
     * @return This dialog instance
     */
    public NormalDialog setRightBtnBackground(@DrawableRes int resId) {
        this.mRightBgId = resId;
        return this;
    }

    @Override
    public void show() {
        super.show();
        mTvTitle.setText(mTitle);
        mTvMsg.setText(mMsg);
        mTvMsg.setGravity(mGravity);
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
                if (mListener != null) {
                    mListener.onLeftSelect();
                }
            }
        });
        mTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightSelect();
                }
            }
        });
    }

    public void setOnSelectListener(OnSelectListener listener) {
        this.mListener = listener;
    }

    public interface OnSelectListener {
        /** Select the button on the left */
        void onLeftSelect();

        /** Select the button on the right */
        void onRightSelect();
    }
}

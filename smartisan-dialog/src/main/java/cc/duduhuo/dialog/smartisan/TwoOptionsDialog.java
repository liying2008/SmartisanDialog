package cc.duduhuo.dialog.smartisan;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * =======================================================
 * Author: liying - liruoer2008@yeah.net <br>
 * Date: 2017/5/13 14:07 <br>
 * Version: 1.0  <br>
 * Description: TwoOptionsDialog <br>
 * Remark:   <br>
 * =======================================================
 */
public class TwoOptionsDialog extends AlertDialog {
    private TextView mTvTitle;
    private TextView mTvCancel;
    private TextView mTvOp1;
    private TextView mTvOp2;

    private String mTitle = "";
    private String mCancel = "";
    private String mOp1 = "";
    private String mOp2 = "";

    private OnSelectListener mListener;

    TwoOptionsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddh_sm_dialog_two_options);
        findView();
        Window window = getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.ddh_sm_BottomDialogStyle);
    }

    private void findView() {
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvCancel = (TextView) findViewById(R.id.tvCancel);
        mTvOp1 = (TextView) findViewById(R.id.tvOp1);
        mTvOp2 = (TextView) findViewById(R.id.tvOp2);
    }

    /**
     * Set the dialog title
     *
     * @param title dialog title
     * @return This dialog instance
     */
    public TwoOptionsDialog setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    /**
     * Set cancel button text
     *
     * @param cancel cancel buttton text
     * @return This dialog instance
     */
    public TwoOptionsDialog setCancelButtonText(String cancel) {
        this.mCancel = cancel;
        return this;
    }

    /**
     * Set the text of the first item
     *
     * @param op1 the text of the first item
     * @return This dialog instance
     */
    public TwoOptionsDialog setOp1Text(String op1) {
        this.mOp1 = op1;
        return this;
    }

    /**
     * Set the text of the second item
     *
     * @param op2 the text of the second item
     * @return This dialog instance
     */
    public TwoOptionsDialog setOp2Text(String op2) {
        this.mOp2 = op2;
        return this;
    }

    @Override
    public void show() {
        super.show();
        if (!TextUtils.isEmpty(mTitle)) {
            mTvTitle.setText(mTitle);
        }
        if (!TextUtils.isEmpty(mCancel)) {
            mTvCancel.setText(mCancel);
        }
        mTvOp1.setText(mOp1);
        mTvOp2.setText(mOp2);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onOp1();
                }
            }
        });
        mTvOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onOp2();
                }
            }
        });
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void setOnSelectListener(OnSelectListener listener) {
        this.mListener = listener;
    }

    public interface OnSelectListener {
        /** Select the first option */
        void onOp1();

        /** Select the second option */
        void onOp2();
    }
}

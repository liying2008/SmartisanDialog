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
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net <br>
 * Date: 2017/5/13 19:28 <br>
 * Version: 1.0  <br>
 * Description: ThreeOptionsDialog <br>
 * Remark:   <br>
 * =======================================================
 */
public class ThreeOptionsDialog extends AlertDialog {
    private TextView mTvTitle;
    private TextView mTvCancel;
    private TextView mTvOp1;
    private TextView mTvOp2;
    private TextView mTvOp3;

    private String mTitle = "";
    private String mCancel = "";
    private String mOp1 = "";
    private String mOp2 = "";
    private String mOp3 = "";

    private OnSelectListener mListener;

    ThreeOptionsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddh_sm_dialog_three_options);
        findViews();
        Window window = getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.ddh_sm_BottomDialogStyle);
    }

    private void findViews() {
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvCancel = (TextView) findViewById(R.id.tvCancel);
        mTvOp1 = (TextView) findViewById(R.id.tvOp1);
        mTvOp2 = (TextView) findViewById(R.id.tvOp2);
        mTvOp3 = (TextView) findViewById(R.id.tvOp3);
    }

    /**
     * Set the dialog title
     *
     * @param title dialog title
     * @return This dialog instance
     */
    public ThreeOptionsDialog setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    /**
     * Set cancel button text
     *
     * @param cancel cancel buttton text
     * @return This dialog instance
     */
    public ThreeOptionsDialog setCancelButtonText(String cancel) {
        this.mCancel = cancel;
        return this;
    }

    /**
     * Set the text of the first item
     *
     * @param op1 the text of the first item
     * @return This dialog instance
     */
    public ThreeOptionsDialog setOp1Text(String op1) {
        this.mOp1 = op1;
        return this;
    }

    /**
     * Set the text of the second item
     *
     * @param op2 the text of the second item
     * @return This dialog instance
     */
    public ThreeOptionsDialog setOp2Text(String op2) {
        this.mOp2 = op2;
        return this;
    }

    /**
     * Set the text of the third item
     *
     * @param op3 the text of the third item
     * @return This dialog instance
     */
    public ThreeOptionsDialog setOp3Text(String op3) {
        this.mOp3 = op3;
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
        mTvOp3.setText(mOp3);
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
        mTvOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onOp3();
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

        /** Select the third option */
        void onOp3();
    }
}

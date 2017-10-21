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
 * Date: 2017/5/13 15:56 <br>
 * Version: 1.0  <br>
 * Description: TwoOptionsDialog <br>
 * Remark:   <br>
 * =======================================================
 */
public class WarningDialog extends AlertDialog {
    private TextView mTvTitle;
    private TextView mTvCancel;
    private TextView mTvConfirm;

    private String mTitle = "";
    private String mCancel = "";
    private String mConfirm = "";

    private OnConfirmListener mListener;

    WarningDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddh_sm_dialog_warning);
        findViews();
        Window window = getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.ddh_sm_BottomDialogStyle);
    }

    private void findViews() {
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvCancel = (TextView) findViewById(R.id.tvCancel);
        mTvConfirm = (TextView) findViewById(R.id.tvConfirm);
    }

    /**
     * Set the dialog title
     *
     * @param title dialog title
     * @return This dialog instance
     */
    public WarningDialog setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    /**
     * Set cancel button text
     *
     * @param cancel cancel buttton text
     * @return This dialog instance
     */
    public WarningDialog setCancelButtonText(String cancel) {
        this.mCancel = cancel;
        return this;
    }

    /**
     * Set the text of the Confirm button
     *
     * @param confirm the text of the Confirm button
     * @return This dialog instance
     */
    public WarningDialog setConfirmText(String confirm) {
        this.mConfirm = confirm;
        return this;
    }

    @Override
    public void show() {
        super.show();
        mTvTitle.setText(mTitle);
        if (!TextUtils.isEmpty(mCancel)) {
            mTvCancel.setText(mCancel);
        }
        mTvConfirm.setText(mConfirm);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onConfirm();
                }
            }
        });
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void setOnConfirmListener(OnConfirmListener listener) {
        this.mListener = listener;
    }

    public interface OnConfirmListener {
        /** Click the Confirm button */
        void onConfirm();
    }
}

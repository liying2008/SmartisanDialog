package cc.duduhuo.dialog.smartisan.listener;

/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net <br>
 * Date: 2017/10/21 16:12 <br>
 * Version: 1.0 <br>
 * Description: OnSingleChoiceSelectListener <br>
 * Remark:   <br>
 * =======================================================
 */
public interface OnSingleChoiceSelectListener {
    /**
     * This method will be invoked when a choice in the dialog is clicked.
     * @param position The choice's position that was clicked.
     */
    void onSelect(int position);
}


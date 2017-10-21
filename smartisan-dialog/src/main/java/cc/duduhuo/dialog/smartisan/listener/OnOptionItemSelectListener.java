package cc.duduhuo.dialog.smartisan.listener;

/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net <br>
 * Date: 2017/5/13 17:05 <br>
 * Version: 1.0 <br>
 * Description: OptionItemSelectListener <br>
 * Remark:  <br>
 * =======================================================
 */
public interface OnOptionItemSelectListener {
    /**
     * Select the option
     *
     * @param position The position of the option
     * @param option   The name of the option
     */
    void onSelect(int position, CharSequence option);
}

package cc.duduhuo.dialog.smartisan;


import android.content.Context;

/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net <br>
 * Date: 2017/5/13 14:02 <br>
 * Version: 1.0  <br>
 * Description: SmartisanDialog <br>
 * Remark:   <br>
 * =======================================================
 */
public class SmartisanDialog {
    /**
     * Create a dialog with two options
     *
     * @param context
     * @return
     */
    public static TwoOptionsDialog createTwoOptionsDialog(Context context) {
        return new TwoOptionsDialog(context);
    }

    /**
     * Create a dialog with three options
     *
     * @param context
     * @return
     */
    public static ThreeOptionsDialog createThreeOptionsDialog(Context context) {
        return new ThreeOptionsDialog(context);
    }

    /**
     * Create a normal dialog
     *
     * @param context
     * @return
     */
    public static NormalDialog createNormalDialog(Context context) {
        return new NormalDialog(context);
    }

    /**
     * Create a warning dialog
     *
     * @param context
     * @return
     */
    public static WarningDialog createWarningDialog(Context context) {
        return new WarningDialog(context);
    }

    /**
     * Create an Options List dialog
     *
     * @param context
     * @return
     */
    public static OptionListDialog createOptionListDialog(Context context) {
        return new OptionListDialog(context);
    }

    /**
     * Create a single choice dialog
     *
     * @param context
     * @return
     */
    public static SingleChoiceDialog createSingleChoiceDialog(Context context) {
        return new SingleChoiceDialog(context);
    }

    /**
     * Create a customized dialog
     *
     * @param context
     * @return
     */
    public static CustomizedDialog createCustomizedDialog(Context context) {
        return new CustomizedDialog(context);
    }

}

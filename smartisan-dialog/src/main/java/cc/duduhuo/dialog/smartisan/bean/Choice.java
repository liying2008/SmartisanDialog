package cc.duduhuo.dialog.smartisan.bean;

/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net <br>
 * Date: 2017/10/21 10:51 <br>
 * Version: 1.0 <br>
 * Description:  Choice POJO <br>
 * Remark: <br>
 * =======================================================
 */
public class Choice {
    private CharSequence item;
    private CharSequence description;
    private boolean checked;

    public CharSequence getItem() {
        return item;
    }

    public void setItem(CharSequence item) {
        this.item = item;
    }

    public CharSequence getDescription() {
        return description;
    }

    public void setDescription(CharSequence description) {
        this.description = description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

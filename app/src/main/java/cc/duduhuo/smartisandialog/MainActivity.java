package cc.duduhuo.smartisandialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import cc.duduhuo.dialog.smartisan.CustomizedDialog;
import cc.duduhuo.dialog.smartisan.NormalDialog;
import cc.duduhuo.dialog.smartisan.OptionListDialog;
import cc.duduhuo.dialog.smartisan.SingleChoiceDialog;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.ThreeOptionsDialog;
import cc.duduhuo.dialog.smartisan.TwoOptionsDialog;
import cc.duduhuo.dialog.smartisan.WarningDialog;
import cc.duduhuo.dialog.smartisan.listener.OnOptionItemSelectListener;
import cc.duduhuo.dialog.smartisan.listener.OnSingleChoiceSelectListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 普通的Dialog
     *
     * @param view
     */
    public void normalDialog(View view) {
        final NormalDialog dialog = SmartisanDialog.createNormalDialog(this);
        dialog.setTitle("这是标题")
            .setMsg("对话框信息")
            .setMsgGravity(Gravity.CENTER)
            //.setLeftBtnBackground()
            .setLeftBtnText("确定")   // 设置文本的按钮会显示，不设置文本则不显示
            .setRightBtnText("取消")
            .show();
        // 设置点击监听（下同，不再解释）
        dialog.setOnSelectListener(new NormalDialog.OnSelectListener() {
            @Override
            public void onLeftSelect() {
                Toast.makeText(MainActivity.this, "onLeftSelect", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onRightSelect() {
                Toast.makeText(MainActivity.this, "onRightSelect", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    /**
     * 带两个选项的Dialog
     *
     * @param view
     */
    public void twoOptionsDialog(View view) {
        final TwoOptionsDialog dialog = SmartisanDialog.createTwoOptionsDialog(this);
        dialog.setTitle("选择一个选项")
            .setOp1Text("第一个选项")    // 设置第一个选项的文本
            .setOp2Text("第二个选项")    // 设置第二个选项的文本
            .show();
        dialog.setOnSelectListener(new TwoOptionsDialog.OnSelectListener() {
            @Override
            public void onOp1() {
                Toast.makeText(MainActivity.this, "onOp1", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onOp2() {
                Toast.makeText(MainActivity.this, "onOp2", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    /**
     * 带三个选项的Dialog
     *
     * @param view
     */
    public void threeOptionsDialog(View view) {
        final ThreeOptionsDialog dialog = SmartisanDialog.createThreeOptionsDialog(this);
        dialog.setOp1Text("选项1")
            .setOp2Text("选项2")
            .setOp3Text("选项3")
            .show();
        dialog.setOnSelectListener(new ThreeOptionsDialog.OnSelectListener() {
            @Override
            public void onOp1() {
                Toast.makeText(MainActivity.this, "onOp1", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onOp2() {
                Toast.makeText(MainActivity.this, "onOp2", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onOp3() {
                Toast.makeText(MainActivity.this, "onOp3", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    /**
     * 警告Dialog
     *
     * @param view
     */
    public void warningDialog(View view) {
        final WarningDialog dialog = SmartisanDialog.createWarningDialog(this);
        dialog.setTitle("确定退出登录吗")
            .setConfirmText("退出登录")
            .show();
        dialog.setOnConfirmListener(new WarningDialog.OnConfirmListener() {
            @Override
            public void onConfirm() {
                Toast.makeText(MainActivity.this, "onConfirm", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    /**
     * 选项列表Dialog
     *
     * @param view
     */
    public void optionListDialog(View view) {
        String[] options = new String[]{"选项1", "选项2", "选项3", "选项4", "选项5", "选项6"};
        final OptionListDialog dialog = SmartisanDialog.createOptionListDialog(this);
        dialog.setTitle("请选择一个选项")
            .setOptionList(options)
            .setLastOption("选项5")   // 上次选择的选项
            .setItemGravity(Gravity.CENTER) // Item是居左、居中还是居右
            .setLastColor(0xFF40B64A)   // 上次选择的选项显示的颜色，用于区分
            .show();
        // setOnOptionItemSelectListener()方法必须在show()方法之后调用，否则无效
        dialog.setOnOptionItemSelectListener(new OnOptionItemSelectListener() {
            @Override
            public void onSelect(int position, CharSequence option) {
                Toast.makeText(MainActivity.this, "position = " + position + ", option = " + option, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }

    /**
     * 另一种选项列表
     *
     * @param view
     */
    public void singleChoiceNoRadioDialog(View view) {
        String[] options = {"在浏览器中打开", "复制链接网址", "分享链接"};
        final SingleChoiceDialog dialog = SmartisanDialog.createSingleChoiceDialog(this);
        dialog.setTitle("https://github.com/liying2008")
            .setSingleChoiceItems(options, -1) // -1表示没有默认选中项
            .setTitleTextSize(16)
            .hideRadioIcon()  // 隐藏单选按钮图标
            .show();
        // setOnSingleChoiceSelectListener()方法必须在show()方法之后调用，否则无效
        dialog.setOnSingleChoiceSelectListener(new OnSingleChoiceSelectListener() {
            @Override
            public void onSelect(int position) {
                Toast.makeText(MainActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    /**
     * 带单选按钮的Dialog
     *
     * @param view
     */
    public void singleChoiceDialog(View view) {
        final SingleChoiceDialog dialog = SmartisanDialog.createSingleChoiceDialog(this);
        dialog.setTitle("蜂窝移动数据")
            .setLeftBtnText("取消")
            .setSingleChoiceItems(new String[]{"关", "使用 SIM 卡 1", "使用 SIM 卡 2"}, 0)
            .show();
        dialog.setOnSingleChoiceSelectListener(new OnSingleChoiceSelectListener() {
            @Override
            public void onSelect(int position) {
                Toast.makeText(MainActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.setOnBtnSelectListener(new SingleChoiceDialog.OnBtnSelectListener() {
            @Override
            public void onLeftSelect() {
                dialog.dismiss();
            }

            @Override
            public void onRightSelect() {

            }
        });
    }

    /**
     * 带单选按钮的Dialog2
     *
     * @param view
     */
    public void singleChoiceDescDialog(View view) {
        String[] items = new String[]{"低电量模式", "超低电量模式"};
        String[] descs = new String[]{"禁止后台应用无线网络、移动数据连接", "仅支持接打电话、收发短信"};
        final SingleChoiceDialog dialog = SmartisanDialog.createSingleChoiceDialog(this);
        dialog.setTitle("省电模式")
            .setLeftBtnText("取消")
            .setRightBtnText("确认")
            .setSingleChoiceItems(items, descs, 0)
            .show();
        dialog.setOnSingleChoiceSelectListener(new OnSingleChoiceSelectListener() {
            @Override
            public void onSelect(int position) {
                Toast.makeText(MainActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setOnBtnSelectListener(new SingleChoiceDialog.OnBtnSelectListener() {
            @Override
            public void onLeftSelect() {
                dialog.dismiss();
            }

            @Override
            public void onRightSelect() {
                Toast.makeText(MainActivity.this, "已应用", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    /**
     * 自定义内容视图的Dialog
     *
     * @param view
     */
    public void customizedDialog1(View view) {
        final CustomizedDialog dialog = SmartisanDialog.createCustomizedDialog(this);
        View rootView = getLayoutInflater().inflate(R.layout.test_view, null);
        dialog.addView(rootView)
            .setTitle("自定义内容视图")
            .show();
    }
}

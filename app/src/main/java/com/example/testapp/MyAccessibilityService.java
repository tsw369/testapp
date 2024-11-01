package com.example.testapp;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import java.util.List;

public class MyAccessibilityService extends AccessibilityService {
    /**
     * 当启动服务的时候就会被调用,系统成功绑定该服务时被触发，也就是当你在设置中开启相应的服务，
     * 系统成功的绑定了该服务时会触发，通常我们可以在这里做一些初始化操作
     */
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    /**
     * 通过系统监听窗口变化的回调,sendAccessibiliyEvent()不断的发送AccessibilityEvent到此处
     *
     * @param event
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        List<AccessibilityWindowInfo> accessibilityWindowInfoList = getWindows();
        if (accessibilityWindowInfoList != null && accessibilityWindowInfoList.size() > 0){
            for (AccessibilityWindowInfo accessibilityWindowInfo : accessibilityWindowInfoList){
                if (accessibilityWindowInfo != null){
                    AccessibilityNodeInfo info = accessibilityWindowInfo.getRoot();
                    if (info != null){
                        recycle(info);
                    }
                }
            }
        }
    }

    /**
     * 中断服务时的回调.
     */
    @Override
    public void onInterrupt() {
    }

    /**
     * 查找拥有特定焦点类型的控件
     *
     * @param focus
     * @return
     */
    @Override
    public AccessibilityNodeInfo findFocus(int focus) {
        return super.findFocus(focus);
    }

    /**
     * 如果配置能够获取窗口内容,则会返回当前活动窗口的根结点
     *
     * @return
     */
    @Override
    public AccessibilityNodeInfo getRootInActiveWindow() {
        return super.getRootInActiveWindow();
    }


    /**
     * 获取系统服务
     *
     * @param name
     * @return
     */
    @Override
    public Object getSystemService(String name) {
        return super.getSystemService(name);
    }

    /**
     * 如果允许服务监听按键操作，该方法是按键事件的回调，需要注意，这个过程发生了系统处理按键事件之前
     *
     * @param event
     * @return
     */
    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        return super.onKeyEvent(event);
    }

    private void recycle(AccessibilityNodeInfo info) {
        //Log.i("进来了：", String.valueOf(info.getChildCount()) + "---" + );


        if (info.getChildCount() == 0 && info.getText() != null && info.getText().toString().equals("确定") &&
                info.getClassName() != null && info.getClassName().toString().equals("android.widget.Button") &&
                info.getPackageName() != null && info.getPackageName().toString().equals("com.android.packageinstaller")) {
            info.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
            info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            return;
        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
                if(info.getChild(i)!=null){
                    recycle(info.getChild(i));
                }
            }
        }
    }
}

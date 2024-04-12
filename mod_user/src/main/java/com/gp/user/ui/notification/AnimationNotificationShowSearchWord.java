package com.gp.user.ui.notification;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Author wuleizhenshang
 * @Email wuleizhenshang@163.com
 * @Date 2024/4/11 12:00
 * @Description: TODO
 */
public class AnimationNotificationShowSearchWord {

    /**
     * 指针右旋180
     */
    public static void pointRightRotation(View view){
        float curRotation = view.getRotation();
        // 创建一个旋转动画
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(view, "rotation",
                curRotation, curRotation + 180f);
        rotateAnimator.setDuration(1000); // 设置动画持续时间为1秒
        // 启动动画
        rotateAnimator.start();
        //cancel方法会让对象回到动画前的状态，end反之
        //调用cancel方法不会调用onAnimationEnd()方法，调用end方法会调用onAnimationEnd()方法，然后停止
    }

    /**
     * 指针左旋180
     */
    public static void pointLeftRotation(View view) {
        float curRotation = view.getRotation();
        // 创建一个旋转动画
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(view, "rotation",
                curRotation, curRotation - 180f);
        rotateAnimator.setDuration(1000); // 设置动画持续时间为1秒
        // 启动动画
        rotateAnimator.start();
        //cancel方法会让对象回到动画前的状态，end反之
        //调用cancel方法不会调用onAnimationEnd()方法，调用end方法会调用onAnimationEnd()方法，然后停止
    }

    /**
     * 向上折叠消失
     */
    public static void graduallyFoldUpDisappear(View view) {
        // 设置动画的持续时间
        int duration = 500; // 500毫秒

        // 初始位置
        float startY = view.getTranslationY();
        // 结束位置，将视图向上移动其高度的距离
        float endY = -view.getHeight();

        // 创建垂直移动动画
        ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(view, "translationY", startY, endY);
        translationYAnimator.setDuration(duration);

        // 创建透明度渐变动画
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        alphaAnimator.setDuration(duration);

        // 将两个动画组合成一个动画集，并同时执行
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationYAnimator, alphaAnimator);

        // 开始动画
        animatorSet.start();
    }

    /**
     * 向下显示
     */
    public static void graduallyDownShow(View view) {
        // 设置动画的持续时间
        int duration = 500; // 500毫秒

        // 初始位置，假设视图当前是在视图区域的上方，不可见
        float startY = -view.getHeight();
        // 结束位置，将视图移动到其原始位置
        float endY = 0;

        // 创建垂直移动动画
        ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(view, "translationY", startY, endY);
        translationYAnimator.setDuration(duration);

        // 创建透明度渐变动画
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        alphaAnimator.setDuration(duration);

        // 将两个动画组合成一个动画集，并同时执行
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationYAnimator, alphaAnimator);

        // 开始动画
        animatorSet.start();
    }

    /**
     * 渐现
     */
    public static void graduallyShow(View view) {
        view.setVisibility(View.VISIBLE);
        // 设置动画的持续时间
        int duration = 500; // 500毫秒

        // 创建透明度渐变动画
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        alphaAnimator.setDuration(duration);
        // 开始动画
        alphaAnimator.start();
    }

    /**
     * 渐消失
     */
    public static void graduallyDisappear(View view) {
        view.setVisibility(View.VISIBLE);
        // 设置动画的持续时间
        int duration = 500; // 500毫秒

        // 创建透明度渐变动画
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        alphaAnimator.setDuration(duration);

        alphaAnimator.start();
    }


}

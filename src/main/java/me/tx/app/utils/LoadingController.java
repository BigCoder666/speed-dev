package me.tx.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import me.tx.app.R;

public class LoadingController {
    View loadingView;
    ImageView img;
    Dialog dialog;
    Animation rotateAnimation;

    public interface ILoadSrc{
        int srcLayout();
        int srcImgId();
    }

    public LoadingController(Context context,ILoadSrc iLoadSrc){
        loadingView = LayoutInflater.from(context).inflate(iLoadSrc.srcLayout(),null);
        img = loadingView.findViewById(iLoadSrc.srcImgId());
        dialog = new Dialog(context, R.style.dialog);
        dialog.setContentView(loadingView);
        dialog.setCancelable(true);
        rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
    }

    public void show(){
        if(!dialog.isShowing()) {
            dialog.show();
            img.startAnimation(rotateAnimation);
        }
    }

    public void show(boolean cancelable){
        if(!dialog.isShowing()) {
            dialog.setCancelable(cancelable);
            dialog.show();
            img.startAnimation(rotateAnimation);
        }
    }

    public void dismiss(){
        if(dialog.isShowing()) {
            dialog.dismiss();
            img.clearAnimation();
        }
    }
}

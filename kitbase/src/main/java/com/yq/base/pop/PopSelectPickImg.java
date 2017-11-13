package com.yq.base.pop;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.yq.base.R;


/**
 * 类描述：弹出选图片类型
 */
public class PopSelectPickImg {
    private Context mContext;
    private PopupWindow mPopupWindow;
    private OnSelectPickImgType mOnSelectPickImgType;
    public PopSelectPickImg(Context context, OnSelectPickImgType mOnSelectPickImgType) {
        this.mContext = context;
        this.mOnSelectPickImgType = mOnSelectPickImgType;
        init();
    }


    protected void init() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.pop_select_pick_img, null);
        mPopupWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mPopupWindow.setFocusable(false);
        mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        rootView.findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                mOnSelectPickImgType.onCamera();
            }
        });
        rootView.findViewById(R.id.btn_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                mOnSelectPickImgType.onGallery();
            }
        });
    }

    /**
     * 显示popupwindow
     *
     * @param view
     */
    public void show(View view) {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    }

    /**
     * 关闭popupwindow
     */
    public void dismiss() {
        if (mPopupWindow.isShowing()) mPopupWindow.dismiss();
    }

    public interface OnSelectPickImgType{
        void onCamera();
        void onGallery();
    }

}

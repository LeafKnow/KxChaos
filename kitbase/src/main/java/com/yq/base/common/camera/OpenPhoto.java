package com.yq.base.common.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.yq.base.pop.PopSelectPickImg;

import java.io.File;

/**
 * Created by njh on 2017/11/13.
 * 相机和相册打开，并裁剪（已兼容7.0）
 */
public class OpenPhoto implements TakePhoto.TakeResultListener, InvokeListener, PopSelectPickImg.OnSelectPickImgType {

    private Activity mActivity;

    public OpenPhoto(Activity activity) {
        mActivity = activity;
    }

    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private PopSelectPickImg popSelectPickImg;
    private OnTakeListener onTakeListener;

    public void setOnTakeListener(OnTakeListener onTakeListener) {
        this.onTakeListener = onTakeListener;
    }

    public void openPhoto(View view) {
        if (popSelectPickImg == null) {
            popSelectPickImg = new PopSelectPickImg(mActivity, this);
        }
        popSelectPickImg.show(view);
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(mActivity, this));
        }
        return takePhoto;
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(mActivity), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        this.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(mActivity, type, invokeParam, this);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setWithOwnCrop(true);
        builder.setAspectX(480).setAspectY(480);
        takePhoto.onPickFromCaptureWithCrop(imageUri, builder.create());
    }

    @Override
    public void onGallery() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(480).setAspectY(480);
        takePhoto.onPickFromGalleryWithCrop(imageUri, builder.create());
    }


    @Override
    public void takeSuccess(TResult result) {
        String path = result.getImage().getOriginalPath();
        if (null != onTakeListener) {
            onTakeListener.takeSuccess(result, path);
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {
        if (null != onTakeListener) {
            onTakeListener.takeFail(result, msg);
        }
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void takeCancel() {
        if (null != onTakeListener) {
            onTakeListener.takeCancel();
        }
        Toast.makeText(mActivity, "已取消", Toast.LENGTH_SHORT).show();
    }


    public interface OnTakeListener {
        void takeSuccess(TResult result, String path);

        void takeFail(TResult result, String msg);

        void takeCancel();
    }
}

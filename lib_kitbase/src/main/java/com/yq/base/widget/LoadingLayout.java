package com.yq.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.yq.base.R;


/**
 * Created by niejiahuan on 8/19/15.
 */
public class LoadingLayout extends FrameLayout {

  private int emptyView, errorView, loadingView,noNetworkView;
  private OnloadingListener onloadingListener;

  public LoadingLayout(Context context) {
    this(context, null);
  }

  public LoadingLayout(Context context, AttributeSet attrs) {
    this(context, attrs, -1);
  }

  public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoadingLayout, 0, 0);
    try {
      emptyView = a.getResourceId(R.styleable.LoadingLayout_emptyView, R.layout.empty_view);
      errorView = a.getResourceId(R.styleable.LoadingLayout_errorView, R.layout.error_view);
      loadingView = a.getResourceId(R.styleable.LoadingLayout_loadingView, R.layout.loading_view);
      noNetworkView= a.getResourceId(R.styleable.LoadingLayout_noNetwork, R.layout.no_networking_veiw);

      LayoutInflater inflater = LayoutInflater.from(getContext());
      inflater.inflate(emptyView, this, true);
      inflater.inflate(errorView, this, true);
      inflater.inflate(loadingView, this, true);
      inflater.inflate(noNetworkView, this, true);
    } finally {
      a.recycle();
    }
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    for(int i = 0; i < getChildCount() - 1; i++) {
      getChildAt(i).setVisibility(GONE);
    }

    findViewById(R.id.btn_empty_retry).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null != onloadingListener) {
          onloadingListener.onEmpty();
        }
      }
    });

    findViewById(R.id.btn_error_retry).setOnClickListener(new OnClickListener(){
      @Override
      public void onClick(View v) {
        if (null != onloadingListener) {
          onloadingListener.onError();
        }
      }}
      );
    findViewById(R.id.btn_no_worke_retry).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (null != onloadingListener) {
          onloadingListener.noWorke();
        }
      }
    });
  }

  public void setOnloadingListener(OnloadingListener onloadingListener) {
    this.onloadingListener = onloadingListener;
  }

  public void showEmpty() {
    for(int i = 0; i < this.getChildCount(); i++) {
      View child = this.getChildAt(i);
      if (i == 0) {
        child.setVisibility(VISIBLE);
      } else {
        child.setVisibility(GONE);
      }
    }
  }

  public void showError() {
    for(int i = 0; i < this.getChildCount(); i++) {
      View child = this.getChildAt(i);
      if (i == 1) {
        child.setVisibility(VISIBLE);
      } else {
        child.setVisibility(GONE);
      }
    }
  }

  public void showLoading() {
    for(int i = 0; i < this.getChildCount(); i++) {
      View child = this.getChildAt(i);
      if (i == 2) {
        child.setVisibility(VISIBLE);
      } else {
        child.setVisibility(GONE);
      }
    }
  }

  public void showContent() {
    for(int i = 0; i < this.getChildCount(); i++) {
      View child = this.getChildAt(i);
      if (i == 3) {
        child.setVisibility(VISIBLE);
      } else {
        child.setVisibility(GONE);
      }
    }
  }
  public void showNoNetworke(){
    for(int i = 0; i < this.getChildCount(); i++) {
      View child = this.getChildAt(i);
      if (i == 4) {
        child.setVisibility(VISIBLE);
      } else {
        child.setVisibility(GONE);
      }
    }
  }
  public interface OnloadingListener{
    void onEmpty();
    void onError();
    void noWorke();
  }
}

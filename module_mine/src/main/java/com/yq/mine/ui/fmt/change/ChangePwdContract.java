package com.yq.mine.ui.fmt.change;

import com.yq.base.ui.mvp.BaseModel;
import com.yq.base.ui.mvp.BasePresenter;
import com.yq.base.ui.mvp.BaseView;

/**
 * Created by njh on 2017/11/20.
 */

public interface ChangePwdContract {

    interface Model extends BaseModel {

    }
    interface View extends BaseView {

    }
    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void reqBindSet(String param);
    }
}

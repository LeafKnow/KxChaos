package com.yq.common.model.sys;



import com.yq.common.db.GreenDaoManager;
import com.yq.common.greendao.gen.SystemCountDao;
import com.yq.common.model.BaseModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by njh on 2017/11/13.
 * 系统统计类
 */
@Entity
public class SystemCount extends BaseModel {

    private Long startCount;

    @Generated(hash = 142187748)
    public SystemCount(Long startCount) {
        this.startCount = startCount;
    }

    @Generated(hash = 1753302359)
    public SystemCount() {
    }



    public Long getStartCount() {
        return startCount;
    }

    public void setStartCount(Long startCount) {
        this.startCount = startCount;
    }

    public static SystemCountDao getSystemDao(){
        return GreenDaoManager.getInstance().getmDaoSession().getSystemCountDao();
    }

}

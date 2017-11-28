package com.yq.common.config;

/**
 * Created by njh on 2017/11/13.
 */

public class RouteConfig {
    public static final String APP_ACT="act/";
    public static final String APP_Fmt="fmt/";
    public static final String APP_ACT_GROPE="/kchoase/"+APP_ACT;
    public static final String APP_FMT_GROPE="/kchoase/"+APP_Fmt;
    public class ActConfig {
        public static final String MAIN_ACT = APP_ACT_GROPE + "MainAct";
        public static final String LOGIN_ACT = APP_ACT_GROPE + "LoginAct";
        public static final String FETGET_PWD_ACT = APP_ACT_GROPE + "FetgetPwdAct";
        public static final String REGISTER_ACT = APP_ACT_GROPE + "RegisterAct";
    }
    public class FmtConfig{
        public static final String MAIN_FMT=APP_FMT_GROPE+"/MainFtm";
        public static final String MINE_FMT=APP_FMT_GROPE+"/MineFtm";
    }
}

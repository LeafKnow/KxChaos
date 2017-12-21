package com.yq.action;

import java.util.List;

/**
 * Created by gaoshuqing on 2017/12/21.
 */

public class DateBean {


    /**
     * date : 20171221
     * message : Success !
     * status : 200
     * city : 北京
     * count : 425
     * data : {"shidu":"41%","pm25":28,"pm10":54,"quality":"良","wendu":"-1","ganmao":"极少数敏感人群应减少户外活动","yesterday":{"date":"20日星期三","sunrise":"07:31","high":"高温 7.0℃","low":"低温 -5.0℃","sunset":"16:52","aqi":30,"fx":"北风","fl":"<3级","type":"多云","notice":"悠悠的云里有淡淡的诗"},"forecast":[{"date":"21日星期四","sunrise":"07:32","high":"高温 7.0℃","low":"低温 -6.0℃","sunset":"16:52","aqi":107,"fx":"西南风","fl":"<3级","type":"晴","notice":"晴空万里，去沐浴阳光吧"},{"date":"22日星期五","sunrise":"07:32","high":"高温 8.0℃","low":"低温 -6.0℃","sunset":"16:53","aqi":135,"fx":"西风","fl":"<3级","type":"晴","notice":"晴空万里，去沐浴阳光吧"},{"date":"23日星期六","sunrise":"07:33","high":"高温 6.0℃","low":"低温 -5.0℃","sunset":"16:53","aqi":112,"fx":"西南风","fl":"<3级","type":"多云","notice":"悠悠的云里有淡淡的诗"},{"date":"24日星期日","sunrise":"07:33","high":"高温 4.0℃","low":"低温 -6.0℃","sunset":"16:54","aqi":66,"fx":"西北风","fl":"3-4级","type":"晴","notice":"晴空万里，去沐浴阳光吧"},{"date":"25日星期一","sunrise":"07:34","high":"高温 3.0℃","low":"低温 -6.0℃","sunset":"16:54","aqi":67,"fx":"西南风","fl":"<3级","type":"晴","notice":"lovely sunshine，尽情享受阳光的温暖吧"}]}
     */

    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private DataEntity data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * shidu : 41%
         * pm25 : 28
         * pm10 : 54
         * quality : 良
         * wendu : -1
         * ganmao : 极少数敏感人群应减少户外活动
         * yesterday : {"date":"20日星期三","sunrise":"07:31","high":"高温 7.0℃","low":"低温 -5.0℃","sunset":"16:52","aqi":30,"fx":"北风","fl":"<3级","type":"多云","notice":"悠悠的云里有淡淡的诗"}
         * forecast : [{"date":"21日星期四","sunrise":"07:32","high":"高温 7.0℃","low":"低温 -6.0℃","sunset":"16:52","aqi":107,"fx":"西南风","fl":"<3级","type":"晴","notice":"晴空万里，去沐浴阳光吧"},{"date":"22日星期五","sunrise":"07:32","high":"高温 8.0℃","low":"低温 -6.0℃","sunset":"16:53","aqi":135,"fx":"西风","fl":"<3级","type":"晴","notice":"晴空万里，去沐浴阳光吧"},{"date":"23日星期六","sunrise":"07:33","high":"高温 6.0℃","low":"低温 -5.0℃","sunset":"16:53","aqi":112,"fx":"西南风","fl":"<3级","type":"多云","notice":"悠悠的云里有淡淡的诗"},{"date":"24日星期日","sunrise":"07:33","high":"高温 4.0℃","low":"低温 -6.0℃","sunset":"16:54","aqi":66,"fx":"西北风","fl":"3-4级","type":"晴","notice":"晴空万里，去沐浴阳光吧"},{"date":"25日星期一","sunrise":"07:34","high":"高温 3.0℃","low":"低温 -6.0℃","sunset":"16:54","aqi":67,"fx":"西南风","fl":"<3级","type":"晴","notice":"lovely sunshine，尽情享受阳光的温暖吧"}]
         */

        private String shidu;
        private int pm25;
        private int pm10;
        private String quality;
        private String wendu;
        private String ganmao;
        private YesterdayEntity yesterday;
        private List<ForecastEntity> forecast;

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public int getPm25() {
            return pm25;
        }

        public void setPm25(int pm25) {
            this.pm25 = pm25;
        }

        public int getPm10() {
            return pm10;
        }

        public void setPm10(int pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayEntity getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayEntity yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastEntity> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastEntity> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayEntity {
            /**
             * date : 20日星期三
             * sunrise : 07:31
             * high : 高温 7.0℃
             * low : 低温 -5.0℃
             * sunset : 16:52
             * aqi : 30
             * fx : 北风
             * fl : <3级
             * type : 多云
             * notice : 悠悠的云里有淡淡的诗
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }

        public static class ForecastEntity {
            /**
             * date : 21日星期四
             * sunrise : 07:32
             * high : 高温 7.0℃
             * low : 低温 -6.0℃
             * sunset : 16:52
             * aqi : 107
             * fx : 西南风
             * fl : <3级
             * type : 晴
             * notice : 晴空万里，去沐浴阳光吧
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}

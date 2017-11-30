package com.yq.networke.manager;


import com.yq.networke.bean.ResultBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * Created by niejiahuan on 16/8/1.
 */
public class RetroUtil {
    public static <T> Observable<T> flatResult(final ResultBean<T> result) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                switch (result.getStatus()) {
                    case 200:
                        if (null!=result.getResult()) {
                            subscriber.onNext(result.getResult());
                        }
                        break;
                    case 12:
                    case 13:
                        // EventBus.getDefault().post(new UpDateEvent());
                        break;
                    default:
                        subscriber.onError(new ApiException(result.getStatus()+"", result.getMessage()));
                        break;
                }
                subscriber.onComplete();
            }
        });
    }
}

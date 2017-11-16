package com.yq.networke.manager;


import com.yq.networke.bean.ResultBean;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by niejiahuan on 16/8/1.
 */
public class RetroUtil {
    public static <T> Observable<T> flatResult(final ResultBean<T> result) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                switch (result.getStatus()) {
                    case 200:
                        subscriber.onNext(result.getResult());
                        break;
                    case 12:
                    case 13:
                       // EventBus.getDefault().post(new UpDateEvent());
                        break;
                    default:
                        subscriber.onError(new ApiException(result.getStatus()+"", result.getMessage()));
                        break;
                }
                subscriber.onCompleted();
            }
        });
    }
}

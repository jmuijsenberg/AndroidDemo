package nl.jmuijsenberg.androiddemo.util.java.rxjava;

import rx.Subscriber;

public abstract class RxSubscriberBase<T> extends Subscriber<T>{
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }
}

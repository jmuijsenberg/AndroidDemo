package nl.jmuijsenberg.androiddemo.util.android.rxjava;

import java.util.concurrent.Executors;

import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

// See https://github.com/ReactiveX/RxJava/wiki/The-RxJava-Android-Module
public class RxAndroidSchedulers implements RxSchedulers {
    public RxAndroidSchedulers() {
    }

    @Override
    public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler immediate() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler newThread() {
        return Schedulers.newThread();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler single() {
        return Schedulers.from(Executors.newSingleThreadExecutor());
    }
}

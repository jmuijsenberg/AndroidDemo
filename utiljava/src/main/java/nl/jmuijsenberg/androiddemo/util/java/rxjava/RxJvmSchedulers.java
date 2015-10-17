package nl.jmuijsenberg.androiddemo.util.java.rxjava;

import java.util.concurrent.Executors;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class RxJvmSchedulers implements RxSchedulers {
    public RxJvmSchedulers() {
    }

    @Override
    public Scheduler main() {
        return  Schedulers.immediate();
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

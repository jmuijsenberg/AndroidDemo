package nl.jmuijsenberg.androiddemo.util.java.rxjava;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class RxJvmTestSchedulers implements RxSchedulers {
    @Override
    public Scheduler main() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler immediate() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler newThread() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler io() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler single() {
        return Schedulers.immediate();
    }
}

package nl.jmuijsenberg.androiddemo.util.java.rxjava;

import rx.Scheduler;

public interface RxSchedulers {
    Scheduler main();
    Scheduler immediate();
    Scheduler newThread();
    Scheduler computation();
    Scheduler io();
    Scheduler single();
}

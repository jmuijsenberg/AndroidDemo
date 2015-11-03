package nl.jmuijsenberg.androiddemo.viewmodels.courses;

import java.util.ArrayList;
import java.util.List;

import nl.jmuijsenberg.androiddemo.control.ManageCoursesController;
import nl.jmuijsenberg.androiddemo.control.ManageStudentsController;
import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSubscriberBase;
import rx.Observable;
import rx.Subscriber;

public class CourseOverviewViewModel {
    private final ManageCoursesController mController;
    private final RxSchedulers mSchedulers;
    private CourseOverviewListener mListener;

    public CourseOverviewViewModel(ManageCoursesController controller, RxSchedulers schedulers) {
        mController = controller;
        mSchedulers = schedulers;
    }

    public void attachView(CourseOverviewListener listener)
    {
        mListener = listener;

        mController.getCourses()
                .observeOn(mSchedulers.main())
                .subscribe(new Subscriber<List<Course>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.onException(e);
                    }

                    @Override
                    public void onNext(List<Course> courses) {
                        if (mListener != null) {
                            mListener.onListChanged(courses);
                        }
                    }
                });
    }

    public void detachView()
    {
        mListener = null;
    }

    public interface CourseOverviewListener {
        public void onListChanged(List<Course> courses);
        public void onException(Throwable e);
    }
}

package nl.jmuijsenberg.androiddemo.viewmodels.courses;

import java.util.ArrayList;
import java.util.List;

import nl.jmuijsenberg.androiddemo.control.ManageCoursesController;
import nl.jmuijsenberg.androiddemo.control.ManageCoursesController;
import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSubscriberBase;
import rx.Observable;
import rx.Subscriber;

public class CourseEditViewModel {
    private final ManageCoursesController mController;
    private final RxSchedulers mSchedulers;
    private CourseEditListener mListener;
    private Course mSelectedCourse = new Course();
    
    public CourseEditViewModel(ManageCoursesController controller, RxSchedulers schedulers) {
        mController = controller;
        mSchedulers = schedulers;
    }

    public void attachView(CourseEditListener listener)
    {
        mListener = listener;
    }

    public void detachView()
    {
        mListener = null;
    }

    public void setSelectedCourse(Course course)
    {
        mSelectedCourse = new Course(course);
        if (mListener != null) {
            mListener.onTitleChanged(mSelectedCourse.getTitle());
            mListener.onDescriptionChanged(mSelectedCourse.getDescription());
        }
    }

    public void newCourse() {
        setSelectedCourse(new Course());
    }

    public void saveCourse() {
        Observable<Boolean> action = (mSelectedCourse.getId() == 0) ? mController.addCourse(mSelectedCourse) : mController.updateCourse(mSelectedCourse);

        action.observeOn(mSchedulers.main())
                .subscribe(new RxSubscriberBase<Boolean>() {
                    @Override
                    public void onNext(Boolean success) {

                    }
                });
    }

    public void deleteCourse() {
        if (mSelectedCourse.getId() != 0) {
            Observable<Boolean> action = mController.deleteCourse(mSelectedCourse);

            action.observeOn(mSchedulers.main())
                    .subscribe(new RxSubscriberBase<Boolean>() {
                        @Override
                        public void onNext(Boolean success) {
                            if (success)
                            {
                                newCourse();
                            }
                        }
                    });
        }
    }

    public interface CourseEditListener {
        public void onTitleChanged(String title);

        public void onDescriptionChanged(String description);

        public void onException(Throwable e);
    }
}

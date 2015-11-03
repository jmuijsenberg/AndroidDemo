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

    public CourseOverviewViewModel(ManageCoursesController controller, RxSchedulers schedulers) {
        mController = controller;
        mSchedulers = schedulers;
    }
}

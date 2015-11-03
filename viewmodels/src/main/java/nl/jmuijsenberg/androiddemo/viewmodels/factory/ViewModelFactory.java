package nl.jmuijsenberg.androiddemo.viewmodels.factory;

import nl.jmuijsenberg.androiddemo.control.ControllerFactory;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.viewmodels.courses.CourseEditViewModel;
import nl.jmuijsenberg.androiddemo.viewmodels.courses.CourseOverviewViewModel;
import nl.jmuijsenberg.androiddemo.viewmodels.students.ManageStudentsViewModel;
import nl.jmuijsenberg.androiddemo.viewmodels.students.StudentEditViewModel;
import nl.jmuijsenberg.androiddemo.viewmodels.students.StudentOverviewViewModel;

public class ViewModelFactory {
    private ControllerFactory mControllerFactory;
    private RxSchedulers mSchedulers;

    public ViewModelFactory(ControllerFactory controllerFactory, RxSchedulers schedulers)
    {
        mControllerFactory = controllerFactory;
        mSchedulers = schedulers;
    }

    public CourseEditViewModel getCourseEditViewModel() {
        return new CourseEditViewModel(mControllerFactory.getManageCoursesController(), mSchedulers);
    }

    public CourseOverviewViewModel getCourseOverviewViewModel() {
        return new CourseOverviewViewModel(mControllerFactory.getManageCoursesController(), mSchedulers);
    }

    public StudentEditViewModel getStudentEditViewModel() {
        return new StudentEditViewModel(mControllerFactory.getManageStudentsController(), mSchedulers);
    }

    public StudentOverviewViewModel getStudentOverviewViewModel() {
        return new StudentOverviewViewModel(mControllerFactory.getManageStudentsController(), mSchedulers);
    }

    public ManageStudentsViewModel getManageStudentsViewModel() {
        return new ManageStudentsViewModel(mControllerFactory.getManageStudentsController(), mSchedulers);
    }
}

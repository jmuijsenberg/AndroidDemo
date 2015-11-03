package nl.jmuijsenberg.androiddemo.viewmodels.students;

import java.util.ArrayList;
import java.util.List;

import nl.jmuijsenberg.androiddemo.control.ManageStudentsController;
import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import rx.Subscriber;

public class StudentOverviewViewModel {
    private final ManageStudentsController mController;
    private final RxSchedulers mSchedulers;
    private StudentOverviewListener mListener;

    public StudentOverviewViewModel(ManageStudentsController controller, RxSchedulers schedulers) {
        mController = controller;
        mSchedulers = schedulers;
    }

    public void attachView(StudentOverviewListener listener)
    {
        mListener = listener;

        mController.getStudents()
                .observeOn(mSchedulers.main())
                .subscribe(new Subscriber<List<Student>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.onException(e);
                    }

                    @Override
                    public void onNext(List<Student> students) {
                        if (mListener != null) {
                            mListener.onListChanged(students);
                        }
                    }
                });
    }

    public void detachView()
    {
        mListener = null;
    }

    public interface StudentOverviewListener {
        public void onListChanged(List<Student> students);
        public void onException(Throwable e);
    }
}

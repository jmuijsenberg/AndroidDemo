package nl.jmuijsenberg.androiddemo.viewmodels.students;

import java.util.ArrayList;
import java.util.List;

import nl.jmuijsenberg.androiddemo.control.ManageStudentsController;
import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSubscriberBase;
import rx.Observable;

public class StudentEditViewModel {
    private final ManageStudentsController mController;
    private final RxSchedulers mSchedulers;
    private StudentEditListener mListener;
    private Student mSelectedStudent = new Student();

    public StudentEditViewModel(ManageStudentsController controller, RxSchedulers schedulers) {
        mController = controller;
        mSchedulers = schedulers;
    }

    public void attachView(StudentEditListener listener)
    {
        mListener = listener;
    }

    public void detachView()
    {
        mListener = null;
    }

    public void setSelectedStudent(Student student)
    {
        mSelectedStudent = new Student(student);
        if (mListener != null) {
            mListener.onFirstNameChanged(mSelectedStudent.getFirstName());
            mListener.onLastNameChanged(mSelectedStudent.getLastName());
        }
    }

    public void newStudent() {
        setSelectedStudent(new Student());
    }

    public void saveStudent() {
        Observable<Boolean> action = (mSelectedStudent.getId() == 0) ? mController.addStudent(mSelectedStudent) : mController.updateStudent(mSelectedStudent);

        action.observeOn(mSchedulers.main())
                .subscribe(new RxSubscriberBase<Boolean>() {
                    @Override
                    public void onNext(Boolean success) {

                    }
                });
    }

    public void deleteStudent() {
        if (mSelectedStudent.getId() != 0) {
            Observable<Boolean> action = mController.deleteStudent(mSelectedStudent);

            action.observeOn(mSchedulers.main())
                    .subscribe(new RxSubscriberBase<Boolean>() {
                        @Override
                        public void onNext(Boolean success) {
                            if (success)
                            {
                                newStudent();
                            }
                        }
                    });
        }
    }

    public interface StudentEditListener {
        public void onFirstNameChanged(String firstName);

        public void onLastNameChanged(String lastName);

        public void onGenderChanged(String gender);

        public void onDateOfBirthChanged(String dateOfBirth);

        public void onException(Throwable e);
    }
}

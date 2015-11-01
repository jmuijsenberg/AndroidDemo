package nl.jmuijsenberg.androiddemo.viewmodels.students;

import java.util.ArrayList;
import java.util.List;

import nl.jmuijsenberg.androiddemo.control.ManageStudentsController;
import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSubscriberBase;
import rx.Observable;
import rx.Subscriber;

public class ManageStudentsViewModel {
    private List<Student> mStudentList;
    private Student mSelectedStudent = new Student();
    private final ManageStudentsController mController;
    private StudentDetailListener mStudentDetailListener;
    private StudentListListener mStudentListListener;
    private final RxSchedulers mSchedulers;

    public ManageStudentsViewModel(ManageStudentsController controller, RxSchedulers schedulers) {
        mController = controller;
        mSchedulers = schedulers;
        mStudentList = new ArrayList<>();
    }

    public void attachDetailView(StudentDetailListener studentDetailListener)
    {
        mStudentDetailListener = studentDetailListener;
    }

    public void detachDetailView()
    {
        mStudentDetailListener = null;
    }

    public void attachListView(StudentListListener studentListListener)
    {
        mStudentListListener = studentListListener;

        mController.getStudents()
                .observeOn(mSchedulers.main())
                .subscribe(new Subscriber<List<Student>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mStudentListListener.onException(e);
                    }

                    @Override
                    public void onNext(List<Student> students) {
                        if (mStudentListListener != null) {
                            mStudentListListener.onListChanged(students);
                        }
                    }
                });
    }

    public void detachListView()
    {
        mStudentListListener = null;
    }

    public void setSelectedStudent(Student student)
    {
        mSelectedStudent = new Student(student);
        if (mStudentDetailListener != null) {
            mStudentDetailListener.onFirstNameChanged(mSelectedStudent.getFirstName());
            mStudentDetailListener.onLastNameChanged(mSelectedStudent.getLastName());
        }
    }

    public void newStudent() {
        setSelectedStudent(new Student());
    }

    public Student getSelectedStudent() {
        return mSelectedStudent;
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

    public interface StudentDetailListener {
        public void onFirstNameChanged(String firstName);

        public void onLastNameChanged(String lastName);

        public void onGenderChanged(String gender);

        public void onDateOfBirthChanged(String dateOfBirth);

        public void onException(Throwable e);
    }

    public interface StudentListListener {
        public void onListChanged(List<Student> students);
        public void onException(Throwable e);
    }
}

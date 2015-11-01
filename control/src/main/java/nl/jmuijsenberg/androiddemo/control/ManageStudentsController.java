package nl.jmuijsenberg.androiddemo.control;

import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import rx.Observable;

public class ManageStudentsController {
    private Repository mRepository;
    private RxSchedulers mSchedulers;

    public ManageStudentsController(Repository repository, RxSchedulers schedulers)
    {
        mRepository = repository;
        mSchedulers = schedulers;
    }

    public Observable<List<Student>> getStudents()
    {
        return mRepository.getStudents();
    }

    public Observable<Boolean> addStudent(Student student)
    {
        return mRepository.addStudent(student);
    }

    public Observable<Boolean> deleteStudent(Student student)
    {
        return mRepository.deleteStudent(student);
    }

    public Observable<Boolean> updateStudent(Student student)
    {
        return mRepository.updateStudent(student);
    }
}

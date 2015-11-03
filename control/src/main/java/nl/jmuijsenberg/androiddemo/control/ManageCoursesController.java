package nl.jmuijsenberg.androiddemo.control;

import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import rx.Observable;

public class ManageCoursesController {
    private Repository mRepository;
    private RxSchedulers mSchedulers;

    public ManageCoursesController(Repository repository, RxSchedulers schedulers)
    {
        mRepository = repository;
        mSchedulers = schedulers;
    }

    public Observable<List<Course>> getCourses()
    {
        return mRepository.getCourses();
    }

    public Observable<Boolean> addCourse(Course course)
    {
        return mRepository.addCourse(course);
    }

    public Observable<Boolean> deleteCourse(Course course)
    {
        return mRepository.deleteCourse(course);
    }

    public Observable<Boolean> updateCourse(Course course)
    {
        return mRepository.updateCourse(course);
    }
}
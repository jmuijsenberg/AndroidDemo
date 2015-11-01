package nl.jmuijsenberg.androiddemo.repository;

import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.entities.Student;
import rx.Observable;

public interface Repository {
    Observable<List<Student>> getStudents();
    Observable<Boolean> addStudent(final Student student);
    Observable<Boolean> updateStudent(final Student student);
    Observable<Boolean> deleteStudent(final Student student);

    Observable<List<Course>> getCourses();
    Observable<Boolean> addCourse(final Course course);
    Observable<Boolean> updateCourse(final Course course);
    Observable<Boolean> deleteCourse(final Course course);
}

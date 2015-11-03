package nl.jmuijsenberg.androiddemo.viewmodels.students;

import java.util.ArrayList;
import java.util.List;

import nl.jmuijsenberg.androiddemo.control.ManageStudentsController;
import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;

public class StudentOverviewViewModel {
    private final ManageStudentsController mController;
    private final RxSchedulers mSchedulers;

    public StudentOverviewViewModel(ManageStudentsController controller, RxSchedulers schedulers) {
        mController = controller;
        mSchedulers = schedulers;
    }
}

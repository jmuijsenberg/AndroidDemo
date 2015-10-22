package nl.jmuijsenberg.androiddemo.viewmodels.persons;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nl.jmuijsenberg.androiddemo.control.ManagePersonsController;
import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.viewmodels.base.PropertyField;

public class ManagePersonsViewModel {
    private ManagePersonsController mController;
    private RxSchedulers mSchedulers;
    private PropertyField<PersonViewModel> mSelectedPerson;
    private PropertyField<List<PersonViewModel>> mPersonList;
    private PropertyField<String> mFirstName;
    private PropertyField<String> mLastName;
    private PropertyField<Gender> mGender;
    private PropertyField<Calendar> mDateOfBirth;

    public ManagePersonsViewModel(ManagePersonsController controller, RxSchedulers schedulers) {
        mController = controller;
        mSchedulers = schedulers;
        mSelectedPerson = new PropertyField<>();
        mSelectedPerson.setInternal(new PersonViewModel());
        mSelectedPerson.get().getFirstName().setInternal("Piet");
        mSelectedPerson.get().getLastName().setInternal("Van der KLaas");
        mPersonList = new PropertyField<>();
        mPersonList.setInternal(new ArrayList<PersonViewModel>());
    }

    public PropertyField<PersonViewModel> getSelectedPerson()
    {
        return mSelectedPerson;
    }

    public PropertyField<List<PersonViewModel>> getPersonList()
    {
        return mPersonList;
    }
}

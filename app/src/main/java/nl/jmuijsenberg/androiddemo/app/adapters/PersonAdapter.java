package nl.jmuijsenberg.androiddemo.app.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.util.android.datetime.DateTime;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> implements View.OnClickListener {
    PersonAdapter.OnClickListener mClickListener;
    private List<Person> mPersonList;
    private Person mSelectedPerson = null;

    public PersonAdapter(PersonAdapter.OnClickListener clickListener) {
        mClickListener = clickListener;
        mPersonList = new ArrayList<>();
    }

    public void updatePersonList(List<Person> personList) {
        mPersonList = personList;
        notifyDataSetChanged();
    }

    public Person getItem(int pos) {
        return mPersonList.get(pos);
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }

    @Override
    public void onBindViewHolder(PersonViewHolder viewHolder, int i) {
        Person person = mPersonList.get(i);
        viewHolder.itemView.setTag(person);
        viewHolder.setPerson(person);

        if(person.equals(mSelectedPerson)) {
            viewHolder.itemView.setSelected(true);
        } else {
            viewHolder.itemView.setSelected(false);
        }
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.person_list_item, viewGroup, false);

        return new PersonViewHolder(itemView, this);
    }

    @Override
    public void onClick(View v) {
        Person p = (Person) v.getTag();
        setSelectedPerson(p);
    }

    private int setSelectedPerson(Person person) {
        int oldSelectedIndex = -1;
        int newSelectedIndex = -1;

        for (int i = 0; i < mPersonList.size(); ++i) {
            Person p = mPersonList.get(i);

            if (mSelectedPerson != null && p.equals(mSelectedPerson)) {
                oldSelectedIndex = i;
            }

            if (person != null && p.equals(person)) {
                newSelectedIndex = i;
            }
        }

        mSelectedPerson = person;

        notifyItemChanged(newSelectedIndex);
        notifyItemChanged(oldSelectedIndex);

        return newSelectedIndex;
    }

    public interface OnClickListener {
        void onPersonClick(Person p);
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.personFullName)
        TextView fullnameValue;
        @Bind(R.id.personDateOfBirth)
        TextView dateOfBirthValue;

        public PersonViewHolder(View v, View.OnClickListener clickListener) {
            super(v);
            ButterKnife.bind(this, v);

            v.setOnClickListener(clickListener);
        }

        public void setPerson(Person person)
        {
            itemView.setTag(person);
            fullnameValue.setText(person.getFirstName() + " " + person.getLastName());
            //dateOfBirthValue.setText(DateTime.formatDate(person.getDateOfBirth()));
        }
    }
}

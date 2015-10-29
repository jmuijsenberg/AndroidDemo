package nl.jmuijsenberg.androiddemo.app.adapters;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.entities.Person;

public class PersonAdapter extends RecyclerViewAdapterBase<Person, PersonAdapter.PersonViewHolder>{

    public PersonAdapter(RecyclerViewAdapterBase.OnSelectionChangedListener listener) {
        super(R.layout.person_list_item, listener);
    }

    protected PersonViewHolder createViewHolder(View v, View.OnClickListener clickListener)
    {
        return new PersonViewHolder(v, clickListener);
    }

    public class PersonViewHolder extends RecyclerViewHolderBase<Person> {
        @Bind(R.id.personFullName)
        TextView fullnameValue;
        @Bind(R.id.personDateOfBirth)
        TextView dateOfBirthValue;

        public PersonViewHolder(View v, View.OnClickListener clickListener) {
            super(v, clickListener);
            ButterKnife.bind(this, v);

            v.setOnClickListener(clickListener);
        }

        @Override
        public void setItem(Person item) {
            itemView.setTag(item);
            fullnameValue.setText(item.getFirstName() + " " + item.getLastName());
            //dateOfBirthValue.setText(DateTime.formatDate(person.getDateOfBirth()));
        }
    }
}



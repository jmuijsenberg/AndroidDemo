package nl.jmuijsenberg.androiddemo.app.adapters;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.entities.Person;

public class PersonAdapter extends RecyclerViewAdapterBase<Person, PersonAdapter.PersonViewHolder2>{

    public PersonAdapter(RecyclerViewAdapterBase.OnSelectionChangedListener listener) {
        super(R.layout.person_list_item, listener);
    }

    protected PersonViewHolder2 createViewHolder(View v, View.OnClickListener clickListener)
    {
        return new PersonViewHolder2(v, clickListener);
    }

    public class PersonViewHolder2 extends RecyclerViewHolderBase {
        @Bind(R.id.personFullName)
        TextView fullnameValue;
        @Bind(R.id.personDateOfBirth)
        TextView dateOfBirthValue;

        public PersonViewHolder2(View v, View.OnClickListener clickListener) {
            super(v, clickListener);
            ButterKnife.bind(this, v);

            v.setOnClickListener(clickListener);
        }

        @Override
        public void setItem(Object item) {
            itemView.setTag(item);
            Person person = (Person) item;
            fullnameValue.setText(person.getFirstName() + " " + person.getLastName());
            //dateOfBirthValue.setText(DateTime.formatDate(person.getDateOfBirth()));
        }
    }
}



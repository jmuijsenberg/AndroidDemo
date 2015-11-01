package nl.jmuijsenberg.androiddemo.app.adapters;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.entities.Student;

public class StudentListAdapter extends RecyclerViewAdapterBase<Student, StudentListAdapter.StudentViewHolder>{

    public StudentListAdapter(RecyclerViewAdapterBase.OnSelectionChangedListener listener) {
        super(R.layout.student_list_item, listener);
    }

    protected StudentViewHolder createViewHolder(View v, View.OnClickListener clickListener)
    {
        return new StudentViewHolder(v, clickListener);
    }

    public class StudentViewHolder extends RecyclerViewHolderBase<Student> {
        @Bind(R.id.studentFullName)
        TextView fullnameValue;
        @Bind(R.id.studentDateOfBirth)
        TextView dateOfBirthValue;

        public StudentViewHolder(View v, View.OnClickListener clickListener) {
            super(v, clickListener);
            ButterKnife.bind(this, v);

            v.setOnClickListener(clickListener);
        }

        @Override
        public void setItem(Student item) {
            itemView.setTag(item);
            fullnameValue.setText(item.getFirstName() + " " + item.getLastName());
            //dateOfBirthValue.setText(DateTime.formatDate(student.getDateOfBirth()));
        }
    }
}



package nl.jmuijsenberg.androiddemo.app.adapters;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.entities.Course;

public class CourseListAdapter extends RecyclerViewAdapterBase<Course, CourseListAdapter.CourseViewHolder>{

    public CourseListAdapter(RecyclerViewAdapterBase.OnSelectionChangedListener listener) {
        super(R.layout.course_list_item, listener);
    }

    protected CourseViewHolder createViewHolder(View v, View.OnClickListener clickListener)
    {
        return new CourseViewHolder(v, clickListener);
    }

    public class CourseViewHolder extends RecyclerViewHolderBase<Course> {
        @Bind(R.id.courseTitle)
        TextView titleValue;
        @Bind(R.id.courseDescription)
        TextView descriptionValue;

        public CourseViewHolder(View v, View.OnClickListener clickListener) {
            super(v, clickListener);
            ButterKnife.bind(this, v);

            v.setOnClickListener(clickListener);
        }

        @Override
        public void setItem(Course item) {
            itemView.setTag(item);
            titleValue.setText(item.getTitle());
            descriptionValue.setText(item.getDescription());
        }
    }
}
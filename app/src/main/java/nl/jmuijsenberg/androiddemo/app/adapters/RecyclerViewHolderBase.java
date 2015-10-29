package nl.jmuijsenberg.androiddemo.app.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class RecyclerViewHolderBase<T> extends RecyclerView.ViewHolder {
    public RecyclerViewHolderBase(View v, View.OnClickListener clickListener) {
        super(v);
        v.setOnClickListener(clickListener);
    }

    // Implement update views based on item properties in derived class
    public abstract void setItem(T item);
}

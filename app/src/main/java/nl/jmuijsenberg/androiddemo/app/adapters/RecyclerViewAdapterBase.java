package nl.jmuijsenberg.androiddemo.app.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapterBase<T, VH extends RecyclerViewHolderBase> extends RecyclerView.Adapter<VH> {
    RecyclerViewAdapterBase.OnSelectionChangedListener mClickListener;
    private List<T> mItemList;
    private T mSelectedItem = null;
    private int mItemLayoutResourceId;

    public RecyclerViewAdapterBase(int itemLayoutResourceId, RecyclerViewAdapterBase.OnSelectionChangedListener listener) {
        mItemLayoutResourceId = itemLayoutResourceId;
        mClickListener = listener;
        mItemList = new ArrayList<>();
    }

    public void updatePersonList(List<T> itemList) {
        mItemList = itemList;
        notifyDataSetChanged();
    }

    public T getItem(int pos) {
        return mItemList.get(pos);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int i) {
        T item = mItemList.get(i);
        viewHolder.itemView.setTag(item);
        viewHolder.setItem(item);

        if (item.equals(mSelectedItem)) {
            viewHolder.itemView.setSelected(true);
        } else {
            viewHolder.itemView.setSelected(false);
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                                      .inflate(mItemLayoutResourceId, viewGroup, false);

        return createViewHolder(itemView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T item = (T) v.getTag();
                setSelectedItem(item);
            }
        });
    }

    // Implement construction of view holder in derived class
    protected abstract VH createViewHolder(View v, View.OnClickListener clickListener);

    private int setSelectedItem(T selectedItem) {
        int oldSelectedIndex = -1;
        int newSelectedIndex = -1;

        for (int i = 0; i < mItemList.size(); ++i) {
            T item = mItemList.get(i);

            if (mSelectedItem != null && item.equals(mSelectedItem)) {
                oldSelectedIndex = i;
            }

            if (selectedItem != null && item.equals(selectedItem)) {
                newSelectedIndex = i;
            }
        }

        mSelectedItem = selectedItem;

        if (mClickListener != null) {
            mClickListener.onSelectionChanged(mSelectedItem);
        }

        notifyItemChanged(newSelectedIndex);
        notifyItemChanged(oldSelectedIndex);

        return newSelectedIndex;
    }

    public interface OnSelectionChangedListener<T> {
        void onSelectionChanged(T item);
    }


}



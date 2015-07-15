package com.{company_name}.android.{app_package_name_prefix}.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * {@link android.support.v7.widget.RecyclerView.Adapter} subclass which delegates
 * all viewholder/view-recycling to it's subclass.
 * <p/>
 * Typically this is because the views being displayed are custom views and don't need
 * to use the viewholder pattern
 */
abstract class CustomViewRecyclerViewAdapter<T extends View>
        extends RecyclerView.Adapter<CustomViewRecyclerViewAdapter.ViewHolder> {

    protected abstract T createView(ViewGroup parent, int viewType);

    protected abstract void bindView(T view, int position);

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final T view = createView(parent, viewType);
        return new ViewHolder(view);
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int position) {
        final T view = (T) holder.itemView;
        bindView(view, position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

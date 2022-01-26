package vn.icar.taxicontacts.db;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;


public class ContactsListAdapter extends ListAdapter<Contacts, ContactsViewHolder> {

    public ContactsListAdapter(@NonNull DiffUtil.ItemCallback<Contacts> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ContactsViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        Contacts current = getItem(position);
        holder.bind(current.getName());
        holder.bind(current.getPhone());
        holder.bind(current.getEmail());
        holder.bind(current.getAddress());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Contacts> {

        @Override
        public boolean areItemsTheSame(@NonNull Contacts oldItem, @NonNull Contacts newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contacts oldItem, @NonNull Contacts newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}

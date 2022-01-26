/*
 * Copyright (C) 2020 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vn.icar.taxicontacts.db;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import vn.icar.taxicontacts.R;

class ContactsViewHolder extends RecyclerView.ViewHolder {
    public static AsyncTask databaseWriteExecutor;
    private final TextView wordItemView;

    private ContactsViewHolder(View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.tvName);
    }

    public void bind(String text) {
        wordItemView.setText(text);
    }

    static ContactsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calls, parent, false);
        return new ContactsViewHolder(view);
    }

}

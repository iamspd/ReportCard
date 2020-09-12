package com.example.reportcard.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.reportcard.R;
import com.example.reportcard.model.Subject;

import java.util.ArrayList;

public class SubjectAdapter extends ArrayAdapter<Subject> {

    public SubjectAdapter(Context context, ArrayList<Subject> subjects){
        super(context, 0, subjects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.subject_grade_list_item, parent, false);
        }

        Subject currentSubject = getItem(position);

        TextView studentSubject = listItemView.findViewById(R.id.tvSubject);

        studentSubject.setText(currentSubject.getgSubject());

        TextView studentGrade = listItemView.findViewById(R.id.tvGrade);

        studentGrade.setText(currentSubject.getgGrade());

        return listItemView;
    }
}

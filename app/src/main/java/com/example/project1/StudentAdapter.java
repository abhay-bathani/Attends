package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentAdapter extends ArrayAdapter<Student> {
    private HashMap<Integer, Boolean> attendanceMap;

    public StudentAdapter(Context context, ArrayList<Student> students) {
        super(context, 0, students);
        attendanceMap = new HashMap<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_student_adapter, parent, false);
        }

        Student student = getItem(position);

        TextView studentName = convertView.findViewById(R.id.nameTextView);
        TextView studentRoll = convertView.findViewById(R.id.rollNumberTextView);
        CheckBox presentCheckBox = convertView.findViewById(R.id.presentCheckBox);
        CheckBox absentCheckBox = convertView.findViewById(R.id.absentCheckBox);

        studentName.setText(student.getName());
        studentRoll.setText(student.getRollNumber());


        presentCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                absentCheckBox.setChecked(false);
                attendanceMap.put(student.getId(), true);
            }
        });

        absentCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                presentCheckBox.setChecked(false);
                attendanceMap.put(student.getId(), false);
            }
        });

        return convertView;
    }


    public HashMap<Integer, Boolean> getAttendanceMap() {
        return attendanceMap;
    }
}

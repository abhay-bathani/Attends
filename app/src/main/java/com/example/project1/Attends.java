package com.example.project1;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Attends extends AppCompatActivity {
    DBHelper dbHelper;
    ArrayList<Student> studentList;
    StudentAdapter studentAdapter;
    ListView studentListView;
    Button addStudentButton, deleteStudentButton, doneButton, checkAttendanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attends);

        dbHelper = new DBHelper(this);
        studentListView = findViewById(R.id.studentListView);
        addStudentButton = findViewById(R.id.addStudentButton);
        deleteStudentButton = findViewById(R.id.deleteStudentButton);
        doneButton = findViewById(R.id.doneButton);
        checkAttendanceButton = findViewById(R.id.checkAttendanceButton);

        loadStudents();

        addStudentButton.setOnClickListener(v -> showAddStudentDialog());
        deleteStudentButton.setOnClickListener(v -> showDeleteStudentDialog());
        doneButton.setOnClickListener(v -> calculateAttendance());


        checkAttendanceButton.setOnClickListener(v -> {

            showSelectStudentDialogForAttendance();
        });
    }

    private void loadStudents() {
        Cursor cursor = dbHelper.getAllStudents();
        studentList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String rollNumber = cursor.getString(cursor.getColumnIndexOrThrow("roll"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                studentList.add(new Student(id, rollNumber, name));
            } while (cursor.moveToNext());
            cursor.close();
        }
        studentAdapter = new StudentAdapter(this, studentList);
        studentListView.setAdapter(studentAdapter);
    }

    private void showAttendanceDialog(Student student) {
        float attendancePercentage = dbHelper.getAttendancePercentage(student.getId());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attendance Details");
        builder.setMessage(student.getName() + " has an attendance percentage of " + String.format("%.2f", attendancePercentage) + "%");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showSelectStudentDialogForAttendance() {

        final String[] studentNames = new String[studentList.size()];
        for (int i = 0; i < studentList.size(); i++) {
            studentNames[i] = studentList.get(i).getName();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Student to Check Attendance");

        builder.setSingleChoiceItems(studentNames, -1, (dialog, which) -> {

            Student selectedStudent = studentList.get(which);

            showAttendanceDialog(selectedStudent);
            dialog.dismiss(); // Close the dialog after selecting the student
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    private void calculateAttendance() {
        HashMap<Integer, Boolean> attendanceMap = studentAdapter.getAttendanceMap();
        for (Map.Entry<Integer, Boolean> entry : attendanceMap.entrySet()) {
            dbHelper.updateAttendance(entry.getKey(), entry.getValue());
        }
        Toast.makeText(this, "Attendance Updated", Toast.LENGTH_SHORT).show();
    }

    private void showAddStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Student");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText rollNumberInput = new EditText(this);
        rollNumberInput.setHint("Enter Roll Number");
        layout.addView(rollNumberInput);

        final EditText nameInput = new EditText(this);
        nameInput.setHint("Enter Name");
        layout.addView(nameInput);

        builder.setView(layout);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String rollNumber = rollNumberInput.getText().toString();
            String name = nameInput.getText().toString();
            if (!rollNumber.isEmpty() && !name.isEmpty()) {
                dbHelper.addStudent(rollNumber, name);
                loadStudents();  // Reload the student list
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void showDeleteStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Student");

        final String[] studentNames = new String[studentList.size()];
        final boolean[] checkedItems = new boolean[studentList.size()];

        for (int i = 0; i < studentList.size(); i++) {
            studentNames[i] = studentList.get(i).getName();
            checkedItems[i] = false;
        }

        builder.setMultiChoiceItems(studentNames, checkedItems, (dialog, which, isChecked) -> {
            checkedItems[which] = isChecked;
        });

        builder.setPositiveButton("Delete", (dialog, which) -> {
            for (int i = 0; i < studentList.size(); i++) {
                if (checkedItems[i]) {
                    dbHelper.deleteStudent(studentList.get(i).getId());
                }
            }
            loadStudents();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}

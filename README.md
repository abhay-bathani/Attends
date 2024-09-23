<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1, h2, h3 {
            color: #333;
        }
        ul {
            margin-left: 20px;
        }
        code {
            background-color: #f4f4f4;
            padding: 2px 4px;
            font-size: 90%;
            color: #c7254e;
        }
        pre {
            background-color: #f4f4f4;
            padding: 10px;
            border-left: 4px solid #c7254e;
            overflow-x: auto;
        }
    </style>
</head>
<body>

<h1>Attendance Management System</h1>

<p>This is an Android-based <strong>Attendance Management System</strong> that allows users to manage students and their attendance records. The app also includes a login system and features like adding, deleting, and calculating attendance percentages.</p>

<h2>Features</h2>
    <ul>
        <li><strong>Login System</strong>: Users can sign in using a username and password stored in a local SQLite database.</li>
        <li><strong>Student Management</strong>: Add, view, and delete students with their roll numbers and names.</li>
        <li><strong>Attendance Tracking</strong>: Mark students as present or absent for each class, and calculate their attendance percentage.</li>
        <li><strong>SQLite Database</strong>: All student and attendance data are stored in a local SQLite database.</li>
    </ul>

<h2>Project Structure</h2>
    <ul>
        <li><strong>DBHelper</strong>: Handles SQLite database operations for users and students. It provides methods to add students, update attendance, and calculate attendance percentages.</li>
        <li><strong>LoginP</strong>: The login activity where users can enter their username and password to log in. It also includes an eye button to toggle password visibility.</li>
        <li><strong>StudentAdapter</strong>: A custom adapter that populates the list of students with checkboxes for marking attendance.</li>
        <li><strong>Semesters</strong>: Allows users to navigate to the attendance section.</li>
        <li><strong>Attends</strong>: Manages the attendance process, allowing users to view students, mark attendance, and check attendance percentages.</li>
        <li><strong>Student</strong>: Represents a student object containing <code>id</code>, <code>rollNumber</code>, and <code>name</code>.</li>
    </ul>

<h2>Getting Started</h2>

<h3>Prerequisites</h3>
<ul>
        <li>Android Studio</li>
        <li>Android SDK</li>
        <li>Minimum SDK Version: 21 (Android 5.0 Lollipop)</li>
    </ul>

<h3>Setup</h3>
    <ol>
        <li><strong>Clone the repository</strong>:
            <pre><code>git clone https://github.com/your-username/attendance-management-system.git</code></pre>
        </li>
        <li><strong>Open the project</strong> in Android Studio.</li>
        <li><strong>Sync Gradle</strong> to install dependencies.</li>
        <li><strong>Run the app</strong> on an Android device or emulator.</li>
    </ol>

<h2>Database</h2>
    <p>The app uses <strong>SQLite</strong> for local data storage. The <code>DBHelper</code> class handles database creation, user authentication, and attendance management.</p>
    <p>Two tables are created in the database:</p>
    <ul>
        <li><code>users</code>: Stores the usernames and passwords.</li>
        <li><code>students</code>: Stores the student details and attendance records.</li>
    </ul>

<h2>Functionality</h2>
    <ul>
        <li><strong>Login Page</strong>: Users can log in by entering their username and password. Password visibility can be toggled using the eye button.</li>
        <li><strong>Student Management</strong>: Add new students by entering a roll number and name. Delete students by selecting them from the list.</li>
        <li><strong>Attendance</strong>: Mark students as present or absent using checkboxes. View the attendance percentage for each student.</li>
    </ul>

<h2>Screenshots</h2>

<h3>1. Login Page</h3>
    <img src="screenshots/login.png" alt="Login Page" width="300">

<h3>2. Student List</h3>
    <img src="screenshots/student-list.png" alt="Student List" width="300">

</body>
</html>

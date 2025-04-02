<!DOCTYPE html>
<html>
<head>
    <title>Database</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .button-group {
            margin-top: 10px;
        }
        button {
            margin-right: 10px;
            padding: 5px 15px;
        }
        .form-group {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <form name="student_registration" action="DenemeServlet" method="post">
        <div class="form-group">
            <label>ID</label>
            <input type="text" name="ID"/>
        </div>
        <div class="form-group">
            <label>Name</label>
            <input type="text" name="Name"/>
        </div>
        <div class="form-group">
            <label>Age</label>
            <input type="text" name="Age"/>
        </div>
        <div class="form-group">
            <label>GPA</label>
            <input type="text" name="GPA"/>
        </div>
        <div class="form-group">
            <label>Change Column</label>
            <input type="text" name="Column"/>
        </div>
        <div class="form-group">
            <label>Change Value</label>
            <input type="text" name="Value"/>
        </div>
        <div class="button-group">
            <button type="submit" name="action" value="register">Register Student</button>
            <button type="submit" name="action" value="print">Print Students</button>
            <button type="submit" name="action" value="delete">Delete Student</button>
            <button type="submit" name="action" value="update">Update Student</button>
        </div>
    </form>
</body>
</html>
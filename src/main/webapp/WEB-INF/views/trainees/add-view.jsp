<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj Usera</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<form:form method="post" modelAttribute="user">
    First Name: <form:input path="firstName"/><form:errors path="firstName"/><br/>
    Last Name: <form:input path="lastName"/><form:errors path="lastName"/><br/>
    Password: <form:textarea path="password"/><form:errors path="password"/><br/>
    Role <form:input path="role"/><form:errors path="role"/><br/>
    UserName <form:input path="username"/><form:errors path="username"/><br/>
    Is Trainer <form:checkbox id="isTrainer" path="isTrainer"/><form:errors path="isTrainer"/><br/>
    <div id="ratingSection">
        <p>This is a trainer.</p>
        rating <form:input path="trainer.rating"/><form:errors path="trainer.rating"/><br/>
    </div>
    <div id="notRatingSection">
        <p>This is not a trainer.</p>
    </div>

    <form:button>Dodaj</form:button>
</form:form>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        function handleCheckboxChange() {
            var isTrainerCheckbox = document.getElementById('isTrainer');
            var ratingSection = document.getElementById('ratingSection');
            var notRatingSection = document.getElementById('notRatingSection');

            if (isTrainerCheckbox.checked) {
                ratingSection.style.display = 'block';
                notRatingSection.style.display = 'none';
            } else {
                ratingSection.style.display = 'none';
                notRatingSection.style.display = 'block';
            }

            isTrainerCheckbox.addEventListener('change', function () {
                if (isTrainerCheckbox.checked) {
                    ratingSection.style.display = 'block';
                    notRatingSection.style.display = 'none';
                } else {
                    ratingSection.style.display = 'none';
                    notRatingSection.style.display = 'block';
                }
            });
        }

        handleCheckboxChange();
    });
</script>


</body>
</html>

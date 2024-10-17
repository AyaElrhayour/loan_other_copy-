<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet"  href="${pageContext.request.contextPath}/style/style.css">
  <title>Loan</title>
</head>


<body>

<div class="container">
  <div class="leftcontainer">

    <div class="leftcontainer-topcontainer">
      <div class="NumbersPickers first active" onclick="goToSection(this,1)">
        <h2>1 <span>Simuler mon crédit</span></h2>
      </div>
      <div class="NumbersPickers second" onclick="goToSection(this,2)">
        <h2>2 <span>Mes coordonnées</span></h2>
      </div>
      <div class="NumbersPickers last" onclick="goToSection(this,3)">
        <h2>3 <span>Mes infos personnelles</span></h2>
      </div>
    </div>

    <form action="${pageContext.request.contextPath}/requests?action=insert" method="post">
      <%--@declare id="oldloan"--%><label for="project">Project:</label>
      <input type="text" id="project" name="project" required><br><br>

      <label for="occupation">Occupation:</label>
      <input type="text" id="occupation" name="occupation" required><br><br>

      <label for="amount">Loan Amount:</label>
      <input type="number" step="0.01" id="amount" name="amount" required><br><br>

      <label for="period">Loan Period (months):</label>
      <input type="number" id="period" name="period" required><br><br>

      <label for="monthlyPayment">Monthly Payment:</label>
      <input type="number" step="0.01" id="monthlyPayment" name="monthlyPayment" required><br><br>

      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required><br><br>

      <label for="phone">Phone Number:</label>
      <input type="tel" id="phone" name="phone" required><br><br>

      <label for="title">Title:</label>
      <select id="title" name="title" required>
        <option value="MR">Mr.</option>
        <option value="MRS">Mrs.</option>
        <option value="MS">Ms.</option>
      </select><br><br>

      <label for="name">First Name:</label>
      <input type="text" id="name" name="name" required><br><br>

      <label for="lastName">Last Name:</label>
      <input type="text" id="lastName" name="lastName" required><br><br>

      <label for="idCard">ID Card Number:</label>
      <input type="text" id="idCard" name="idCard" required><br><br>

      <label for="birthdate">Birthdate:</label>
      <input type="date" id="birthdate" name="birthdate" placeholder="yyyy/MM/dd" required><br><br>

      <label for="hiringDate">Hiring Date:</label>
      <input type="date" id="hiringDate" name="hiringDate" placeholder="yyyy/MM/dd" required><br><br>

      <label for="monthlyIncome">Monthly Income:</label>
      <input type="number" step="0.01" id="monthlyIncome" name="monthlyIncome" required><br><br>

      <label for="oldLoan">Do you have an old loan?</label>
      <input type="radio" id="oldLoanYes" name="oldLoan" value="true" required>
      <input type="radio" id="oldLoanNo" name="oldLoan" value="false" required>
      <label for="oldLoanNo">No</label><br><br>

      <button type="submit">Submit</button>
    </form>




    <div class="rightcontainer">
      <h2 id="recaptulatif">Mon récapitulatif</h2>

      <div class="project">
        <h2 id="monproject">Mon projet</h2>
      </div>

      <div class="pretpersonel">
        <h2 id="pret">Prêt Personnel</h2>
      </div>


    </div>




  </div>
</div>
</body>


<script src="${pageContext.request.contextPath}/js/javascrit.js"></script>
<script src="${pageContext.request.contextPath}/js/SecondPage.js"></script>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Demander mon crédit en ligne</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.scss">
</head>

<body>
<div class="container">
  <div class="header">
    <h1>Demander mon crédit en ligne</h1>
    <a href="#" class="back-link">← Retour</a>
  </div>
  <section>
    <div class="steps-content">
      <div class="steps">
        <div class="step active" id="step-1-indicator"><span>1</span> Simuler mon crédit</div>
        <div class="step" id="step-2-indicator"><span>2</span> Mes coordonnées</div>
        <div class="step" id="step-3-indicator"><span>3</span> Mes infos personnelles</div>
      </div>

      <div class="content-wrapper">
        <!-- Step 1: Simulate Loan -->
        <section class="loan-form step-1" id="step-1-form">
          <form action="${pageContext.request.contextPath}/requests?action=insert" method="post" id="loan-form">
            <div class="form-group">
              <label for="project">Mon projet</label>
              <select id="project" name="project" required>
                <option value="personal-loan">J'ai besoin d'argent</option>
                <option value="vehicle-used">Je finance mon véhicule d’occasion</option>
                <option value="unexpected-expenses">Je Gère mes imprévus</option>
                <option value="vehicle-new">Je finance mon véhicule neuf</option>
                <option value="home-equipment">J’équipe ma maison</option>
              </select>
            </div>

            <div class="form-group">
              <label for="occupation">Je suis</label>
              <select id="occupation" name="occupation" required>
                <option value="fonctionnaire">Fonctionnaire</option>
                <option value="secteur-prive">Salarié du secteur privé</option>
                <option value="profession-libre">Profession libérale</option>
                <option value="commercant">Commerçant</option>
                <option value="artisan">Artisan</option>
                <option value="retraite">Retraité</option>
                <option value="autres">Autres professions</option>
              </select>
            </div>

            <div class="form-group">
              <div class="input-wrapper">
                <label for="amount">Montant (en DH)</label>
                <input class="price" type="number" id="amount" name="amount" value="10000" min="1000" max="50000" step="0.01" required>
                <input type="range" id="amount-range" value="10000" min="1000" max="50000">
              </div>
            </div>

            <div class="form-group">
              <div class="input-wrapper">
                <label for="period">Durée (en mois)</label>
                <input class="price" type="number" id="duration" name="period" value="24" min="12" max="60" required>
                <input type="range" id="duration-range" value="24" min="12" max="60">
              </div>
            </div>

            <div class="form-group">
              <div class="input-wrapper">
                <label for="monthlyPayment">Mensualités (en DH)</label>
                <input type="number" id="monthlyPayment" name="monthlyPayment" step="0.01" required />
                <input type="range" id="monthly-range" value="482.95" min="100" max="1000" step="0.01">
              </div>
            </div>

            <button type="submit" class="btn-submit">Continuer<br><span>Sans engagement</span></button>
          </form>
        </section>

        <!-- Step 2: Contact Info -->
        <section class="loan-form step-2" id="step-2-form" style="display: none;">
          <form id="contact-form" action="${pageContext.request.contextPath}/requests?action=insert" method="post">
            <div class="form-group-step2">
              <input type="email" id="email" name="email" placeholder=" " required>
              <label for="email">Email*</label>
            </div>
            <div class="form-group-step2">
              <input type="tel" id="phone" name="phone" placeholder=" " required>
              <label for="phone">Téléphone Mobile*</label>
            </div>
            <button type="submit" class="btn-submit">Continuer<br><span>Sans engagement</span></button>
          </form>
        </section>

        <!-- Step 3: Personal Info -->
        <section class="loan-form step-3" id="step-3-form" style="display: none;">
          <form id="personal-info-form" action="${pageContext.request.contextPath}/requests?action=insert" method="post">
            <label>Civilité</label>
            <div class="radio-group">
              <label>
                <input type="radio" name="title" value="MR" required>
                <span>Monsieur</span>
              </label>
              <label>
                <input type="radio" name="title" value="MRS" required>
                <span>Madame</span>
              </label>
              <label>
                <input type="radio" name="title" value="MS" required>
                <span>Mademoiselle</span>
              </label>
            </div>

            <div class="form-group-step2">
              <input name="name" type="text" id="nom" placeholder=" " required>
              <label for="nom">Nom</label>
            </div>

            <div class="form-group-step2">
              <input name="lastName" type="text" id="prenom" placeholder=" " required>
              <label for="prenom">Prénom</label>
            </div>

            <div class="form-group-step2">
              <input name="idCard" type="text" id="CIN" placeholder=" " required>
              <label for="CIN">Numéro CIN / Carte de séjour</label>
            </div>

            <div class="form-group-step2">
              <input name="birthdate" type="date" id="datenaissance" placeholder="JJ/MM/YYYY" required>
              <label for="datenaissance">Date de naissance</label>
            </div>

            <div class="form-group-step2">
              <input name="hiringDate" type="date" id="datedembauche" placeholder="JJ/MM/YYYY" required>
              <label for="datedembauche">Date d'embauche / début de l'activité</label>
            </div>

            <div class="form-group-step2">
              <input name="monthlyIncome" type="number" id="totalrevenue" placeholder=" " required>
              <label for="totalrevenue">Total revenus mensuels (net en DH)</label>
            </div>

            <label>Avez-vous des crédits en cours?</label>
            <div class="radio-group" id="credit-radio-group">
              <label>
                <input type="radio" name="oldLoan" value="Oui">
                <span>Oui</span>
              </label>
              <label>
                <input type="radio" name="oldLoan" value="Non" checked>
                <span>Non</span>
              </label>
            </div>

            <div id="additional-inputs" style="display: none;">
              <div class="form-group-step2">
                <input type="text" id="additional-input1" placeholder="" >
                <label for="additional-input1"> Mensualité crédit Immo (net en DH)*</label>
              </div>
              <div class="form-group-step2">
                <input type="text" id="additional-input2" placeholder=" " >
                <label for="additional-input2"> Mensualité autres crédits (net en DH)*</label>
              </div>
            </div>

            <div class="form-group-step2 alpha">
              <input type="checkbox" id="mustbechecked" required>
              <p class="checkbox-label">J'ai lu et j'accepte les conditions générales d'utilisation figurant sur les informations légales, notamment la mention relative à la protection des données personnelles</p>
            </div>

            <button type="submit" class="btn-submit" id="final-submit-button">Demande ce crédit</button>
          </form>
        </section>
      </div>

      <section>
        <p class="terms">Simulation à titre indicatif et non contractuelle. La mensualité minimale est de 180 dirhams. Un client Wafasalaf peut bénéficier d'une tarification plus avantageuse en fonction de ses conditions préférentielles.
          <br><br>
          Conformément à la réglementation en vigueur, nous vous rappelons que le crédit vous engage et doit être remboursé.</p>
      </section>
    </div>
  </section>
</div>

<script src="${pageContext.request.contextPath}/js/javascrit.js"></script>
</body>
</html>

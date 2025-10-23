const fornavnInput = document.getElementById("fornavn");
const etternavnInput = document.getElementById("etternavn");
const mobilInput = document.getElementById("tlfNummer")
const password = document.getElementById('password');
const password_rep = document.getElementById('password_rep');
const form = document.getElementById('paamelding_form');
const kjonn = document.querySelector('input[name="kjonn"]:checked');
const paameldKnapp = document.getElementById('paameld');
const paameldt_fornavn = document.getElementById("paameldt_fornavn");
const paameldt_etternavn = document.getElementById("paameldt_etternavn");
const paameldt_kjonn = document.getElementById("paameldt_kjonn");
const paameldt_nr = document.getElementById("paameldt_nr");




function validatePage() {

    form.onsubmit = function (event) {
        fornavnInput.value = fornavnInput.value.charAt(0).toUpperCase() + fornavnInput.value.slice(1);
        etternavnInput.value = etternavnInput.value.charAt(0).toUpperCase() + etternavnInput.value.slice(1);
        if (password_rep.value !== password.value){
            alert("Passordet er ikke det samme!");
            event.preventDefault();
        }



    }



}
validatePage();



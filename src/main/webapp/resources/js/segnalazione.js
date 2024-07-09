function myFunction() {
  // Get the value of the input field with id="numb"
  let x = document.getElementById("dataFine").value;
  // If x is Not a Number or less than one or greater than 10
  let text;
  date = Date.parse(x);
  if (isNaN(x) || x < 1 || x > 10) {
    text = "Input not valid";
  } else {
    text = "Input OK";
  }
  document.getElementById("dataLabel").innerHTML = text;
}

function impostaData(){
    let yourDate = new Date();
    let niceDate = yourDate.toISOString().split('T')[0];
    document.getElementById("dataFine").setAttribute("min", niceDate);
    document.getElementById("dataFine").setAttribute("value", niceDate);
}
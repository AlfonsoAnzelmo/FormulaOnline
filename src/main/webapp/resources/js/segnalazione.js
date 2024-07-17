function impostaData(){
    let yourDate = new Date();
    let niceDate = yourDate.toISOString().split('T')[0];
    document.getElementById("dataFine").setAttribute("min", niceDate);
    document.getElementById("dataFine").setAttribute("value", niceDate);
}
function mostraModificaCampo(el){

    switch(el.getAttribute("name")){
        case "e-mail":
            mostraFormSpecifico("form-modifica-email");
            break;
        case "password":
            mostraFormSpecifico("form-modifica-password");
            break;
        case "nickname":
            mostraFormSpecifico("form-modifica-nickname");
            break;
        case "scuderiaPreferita":
            mostraFormSpecifico("form-modifica-scuderiaPreferita");
            break;

    }
}

function mostraFormSpecifico(formId){
    form = document.getElementById(formId)
    if(form.style.display == "none")
        form.style.display = "block";
    else form.style.display = "none";
}
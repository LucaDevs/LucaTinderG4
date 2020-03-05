$(document).ready(function(){
    $("#hide").on('click', function() {
        $("#contenedorModificar").hide();
        return false;
    });
 
    $("#mostrarContMod").on('click', function() {
        $("#contenedorModificar").show();
        return false;
    });
});
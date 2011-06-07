/*******************************************************************************
 * ****** UTN FRBA - TACS 1ºC 2011 ******** ML4FB - GRUPO Nº1 ********
 ******************************************************************************/

/******************************************************
 Inicializo el .html y le digo que cargue las primeras categorias
 *****************************************************/
 
$(document).ready(function() {

	cargarCategoriasPrincipal($("#main_categories"));
	
	$("#formBuscar").submit( function(){
			buscarArticuloXString("q=" + $("#txtBuscar").val(), $("#listaBusqueda"));
			return false;
	});
});
/******** UTN FRBA - TACS 1ºC 2011
********* ML4FB - GRUPO Nº1
*********
*********/


function cargarCategoria(category_id){
$.getJSON("ml/categories/" + category_id,
  function(data) {
	var nuevo_ul = $("<ul></ul>");
	nuevo_ul.hide();
	$.each(data.children_categories, function(i, categoria){
		lista_item = creadorItem(categoria);
		nuevo_ul.append(lista_item);
	}
	);
	
	$("#main_categories li#"+category_id).attr("cargado", "yes");
	$("#main_categories li#"+category_id).append(nuevo_ul);
	nuevo_ul.slideDown();
	
    });
}; //fin cargarCategoria()

function cargarCategoriasPrincipal(){
	$.getJSON("ml/sites/MLA",
		function(data) {
			$.each(data.categories, function(i, categoria){
				lista_item = creadorItem(categoria);
				$("#main_categories").append(lista_item);
			}
			);
		}
		);
}; // fin cargarCategoriasPrincipal()

function creadorItem(categoria){
	lista_item = $("<li></li>");
	lista_item.attr("id", categoria.id);
	
	var category_id = categoria.id;
	lista_item.click(function(){
		
		if( $("#"+category_id).attr("cargado") == "yes"){
			return true;
		}
		cargarCategoria(category_id)
	});
	
	
	lista_item.append($("<span>"+categoria.name+"</span>"));
	
	return lista_item;
}// fin creadorItem()

function mostrarOcultarSubCategoria(category_id){
	
	$("#" + category_id + " ul").slideToggle();
}


/******* Inicializo el .html y le digo que cargue
		las primeras categorias
		*/
$(document).ready(function(){
	cargarCategoriasPrincipal();
	});
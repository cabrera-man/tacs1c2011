/*******************************************************************************
 * ****** UTN FRBA - TACS 1ºC 2011 ******** ML4FB - GRUPO Nº1 ********
 ******************************************************************************/

function cargarCategoria(category_id) {
	var URL = $("https://api.mercadolibre.com/categories/" + category_id).selector;
	$.ajax({
		type : "GET",
		url : URL,
		timeout : 10000,
		dataType : "jsonp",
		success : function(data, status) {
			var nuevo_ul = $("<ul></ul>");
			nuevo_ul.hide();
			$.each(data[2].children_categories, function(i, categoria) {
				lista_item = creadorItem(categoria);
				nuevo_ul.append(lista_item);
			});

			$("#main_categories li#" + category_id).attr("cargado", "yes");
			$("#main_categories li#" + category_id).append(nuevo_ul);
			nuevo_ul.slideDown();
		}
	});
}; // fin cargarCategoria()

function cargarCategoriasPrincipal() {
	var URL = "https://api.mercadolibre.com/sites/MLA";
	$.ajax({
		type : "GET",
		url : URL,
		timeout : 10000,
		dataType : "jsonp",
		success : function(data, status) {
			$.each(data[2].categories, function(i, categoria) {
				lista_item = creadorItem(categoria);
				$("#main_categories").append(lista_item);
			});
		}
	});
}; // fin cargarCategoriasPrincipal()

function creadorItem(categoria) {
	lista_item = $("<li></li>");
	lista_item.attr("id", categoria.id);

	var category_id = categoria.id;
	lista_item.click(function() {

		if ($("#" + category_id).attr("cargado") == "yes") {
			return true;
		}
		cargarCategoria(category_id)
	});

	lista_item.append($("<span>" + categoria.name + "</span>"));

	return lista_item;
}// fin creadorItem()

/**
 * ***** Inicializo el .html y le digo que cargue las primeras categorias
 */
$(document).ready(function() {
	cargarCategoriasPrincipal();
});
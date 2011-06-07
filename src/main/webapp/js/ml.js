/*******************************************************************************
 * ****** UTN FRBA - TACS 1ºC 2011 ******** ML4FB - GRUPO Nº1 ********
 ******************************************************************************/
 
/**************************
Funcionalidad de cargar el arbol
de categorias dinamicamente
**************************/
function cargarCategoria(category_id, liCategoria, $) {
	//Si no me pasan jQuery por parametro lo configuro por default
	if( $ === undefined){
		$ = jQuery;
	}
	var liDestino = liCategoria;
	var URL = "https://api.mercadolibre.com/categories/" + category_id;
	$.ajax({
		type : "GET",
		url : URL,
		timeout : 10000,
		dataType : "jsonp",
		success : function(data, status) {
			var nuevo_ul = $("<ul></ul>");
			nuevo_ul.hide();
			appendarSubCategorias(data[2].children_categories, nuevo_ul);
			/*$.each(data[2].children_categories, function(i, categoria) {
				lista_item = creadorItem(categoria);
				nuevo_ul.append(lista_item);
			});*/

			liDestino.attr("cargado", "yes");
			liDestino.append(nuevo_ul);
			nuevo_ul.slideDown();
		},
		beforeSend: logoCargandoShowHide,
		complete: logoCargandoShowHide
	});
}; // fin cargarCategoria()

//Esta función se volvió necesaria separarla para poder
//testear unitariamente, ya que esto se hacia dentro de otra función
//y no habia manera de saber su resultado
function appendarSubCategorias(children_categories, ulDestino){
	$.each(children_categories, function(i, categoria){
		lista_item = creadorItem(categoria);
		ulDestino.append(lista_item);
		});
};

function cargarCategoriasPrincipal(ulDondeCargar, $) {
	//Si no me pasan jQuery por parametro lo configuro por default
	if( $ === undefined){
		$ = jQuery;
	}
	var ulDestino = ulDondeCargar;
	var URL = "https://api.mercadolibre.com/sites/MLA";
	$.ajax({
		type : "GET",
		url : URL,
		timeout : 10000,
		dataType : "jsonp",
		success : function(data, status) {
			$.each(data[2].categories, function(i, categoria) {
				lista_item = creadorItem(categoria);
				ulDestino.append(lista_item);
			});
		},
		beforeSend: logoCargandoShowHide,
		complete: logoCargandoShowHide
	});
}; // fin cargarCategoriasPrincipal()

function creadorItem(categoria) {
	var lista_item = $("<li></li>");
	lista_item.attr("id", categoria.id);

	var category_id = categoria.id;
	
	nombreCategoria = $("<span>" + categoria.name + "</span>");
	nombreCategoria.click( 
				function() {
					if(lista_item.attr("cargado") == "yes"){
						return true;
					}
					cargarCategoria(category_id, lista_item);
				});

	lista_item.append(nombreCategoria);
	
	linkMostrar = $("<span>Ver</span>");
	linkMostrar.addClass("linkMostrar");
	linkMostrar.click(function(){
		buscarArticuloXString("category=" + category_id, $("#listaBusqueda"));
	});
	
	lista_item.append(linkMostrar);

	return lista_item;
}// fin creadorItem()

/*********
Animación de espera
*********/
function logoCargandoShowHide(){
	$("#logoCargando").fadeToggle();
}

/***************************************
Funcionalidad de Busqueda
***************************************/
function buscarArticuloXString(strBusqueda, ulDondeCargar, $){
	//Si no me pasan jQuery por parametro lo configuro por default
	if( $ === undefined){
		$ = jQuery;
	}
	var URL = "https://api.mercadolibre.com/sites/MLA/search?" + strBusqueda;
	var ulFinal = ulDondeCargar;
	$.ajax({
		type : "GET",
		url : URL,
		timeout : 10000,
		dataType : "jsonp",
		success : function(data, status) {
			
			ulFinal.empty();
			ulFinal.hide();

			$.each(data[2].results, function(i, resultado) {
				lista_item = crearElementoResultado(resultado);
				ulFinal.append(lista_item);
			});
			
			ulFinal.fadeToggle();
		},
		beforeSend: logoCargandoShowHide,
		complete: logoCargandoShowHide


	});
}

function crearElementoResultado(resultado){
	nuevoItem = $("<li></li>");
	
	imgMiniatura = $("<img />");
	imgMiniatura.attr("src", resultado.thumbnail);
	
	nuevoItem.append(imgMiniatura);
	nuevoItem.append(resultado.title);
	
	return nuevoItem;
}



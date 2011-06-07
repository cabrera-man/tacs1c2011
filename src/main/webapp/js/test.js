/*******************************************************************************
 * ****** UTN FRBA - TACS 1ºC 2011 ******** ML4FB - GRUPO Nº1 ********
 ******************************************************************************/
 
 
var ulMock = $("<ul></ul>");
ulMock.append = function() {
			ok(true, "se 'append' un elemento");	
};



$(document).ready(function(){
    	
	module("Modulo de Busquedas");
	test("Por cada resultado se agrega un elemento al 'ul' pasado como parametro", function() {
		expect(2);
		
		//Creo el jQueryMock y modifico su metodo .ajax para obtener los parametros que recibe
        var options = null;
		jQueryMock = jQuery.sub();
        jQueryMock.ajax = function (param) {
            options = param;
        };
		
        buscarArticuloXString("q=un string ejemplo", ulMock, jQueryMock);
		
        options.success([200,{},
			{
            results : 	[
							{
							title: "primer producto",
							thumbnail: "una url de imagen"
							},
							{
							title: "segundo producto",
							thumbnail: "una url de imagen"
							}
						]
			}
		]);
    });
	

	
	module("Modulo de recorrido categorias");
	test("Por cada 'categoria principal' en un site ML se debe agregar un elemento al 'ul' pasado como parametro",
		function() {
			expect(3);
			
			var options = null;
			jQueryMock = jQuery.sub();
			jQueryMock.ajax = function (param) {
				options = param;
			};
			
			cargarCategoriasPrincipal(ulMock, jQueryMock);
			
			options.success(
				[200, {},
						{
								categories : [
												{id: "ITEM123", name: "Item nº1 de prueba" },
												{id: "ITEM234", name: "Item nº2 de prueba" },
												{id: "ITEM345", name: "Item nº3 de prueba" },
											]
						}
				]
			);
			
		}
	);
	
	test("Por cada 'categoria' se debe agregar un elemento 'ul' (con todas las subcategorias) al 'li' pasado como parametro",
		function() {
			expect(1);
			
			var options = null;
			jQueryMock = jQuery.sub();
			jQueryMock.ajax = function (param) {
				options = param;
			};
			
			liMock = $("<li></li>");
			liMock.append = function(objetoApendado){
								ok(true, "se 'append' un elemento");
							};
			
			cargarCategoria("id categoria de prueba", liMock, jQueryMock);
			
			options.success(
							[200, {},
							{
								children_categories: 
									[
										{id: "id sub-categoria nº1", name: "nombre sub-categoria nº1"},
										{id: "id sub-categoria nº2", name: "nombre sub-categoria nº2"},
										{id: "id sub-categoria nº3", name: "nombre sub-categoria nº3"}
									]
							}
							]
							)
		}
	);
	
	test("Por cada 'subcategoria' en el JSON se debe agregar un 'li' la 'ul' pasado como parametro",
		function() {
			expect(3);
			
			appendarSubCategorias(
									[
										{id: "ID prueba de categoria nº1", name: "nombre categoria nº1"},
										{id: "ID prueba de categoria nº2", name: "nombre categoria nº2"},
										{id: "ID prueba de categoria nº3", name: "nombre categoria nº3"},
									],
									ulMock
									);
		}
	);

	module("Pruebas de integración");
	test("Busqueda de items, integracion con ajax", function() {
		stop(5000);
		
		ulPrueba = $("<ul></ul>");
		
		buscarArticuloXString("q=prueba", ulPrueba);
		
		setTimeout(function(){
				notEqual(ulPrueba.html() , "" , "La lista debe tener contenido");
				start();
			  }, 4500);
	});
	
	test("Cargar categorias principales, integración con ajax", function() {
		stop(5500);
		
		ulPrueba = $("<ul></ul>");
		
		cargarCategoriasPrincipal(ulPrueba);
		
		setTimeout(function(){
								notEqual(ulPrueba.html(), "", "El 'ul' tiene que estar completado con el resultado de la consulta Jsonp");
								start();
							}, 5000	);
	});
	
	test("Cargar las subcategorias, integración con ajax", function(){
		stop(5000);
		
		liPrueba = $("<li></li>");
		
		//El primer parametro debe corresponder con una categoria que SI exista
		//En este caso se corresponde con los procesadores INTEL
		cargarCategoria("MLA7171", liPrueba);
		
		setTimeout(function() {
							notEqual(liPrueba.html(), "", "El 'li' tiene que estar completado con el resultado de la consulta Jsonp");
							start();
						}, 4500);
	
	
	});
		
		
		

});
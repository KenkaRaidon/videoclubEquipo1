$(document).ready(function () {

	$('#tabla').DataTable({
		language: {
			processing: "Tratamiento en curso...",
			search: "Buscar&nbsp;:",
			lengthMenu: "Agrupar de _MENU_ items",
			info: "Mostrando del item _START_ al _END_ de un total de _TOTAL_ items",
			infoEmpty: "No existen datos.",
			infoFiltered: "(filtrado de _MAX_ elementos en total)",
			infoPostFix: "",
			loadingRecords: "Cargando...",
			zeroRecords: "No se encontraron datos con tu busqueda",
			emptyTable: "No hay datos disponibles en la tabla.",
			paginate: {
				first: "Primero",
				previous: "Anterior",
				next: "Siguiente",
				last: "Ultimo"
			},
			aria: {
				sortAscending: ": active para ordenar la columna en orden ascendente",
				sortDescending: ": active para ordenar la columna en orden descendente"
			}
		},

	});


});

function categoria() {
	let valor = $("#selectCategoria").val()
	if (valor == "-1") {
		$("#errorCategoria").html("Selecciona una opcion valida");
	}
	else {
		document.getElementById("formCategoria").submit();
	}
}

function actor() {
	$("#tituloModalCambiar").html("Filtrar por actor");
	$('#formCambiar').attr('action', '/filtroActor');

}

function titulo() {
	$("#tituloModalCambiar").html("Filtrar por ");
	$('#formCambiar').attr('action', '/filtroTitulo');

}

function detalles(filmId) {
	$.get("http://localhost:8666/detallesFilm/" + filmId, function (data, status) {
		console.log(data);
		console.log(status);

		let detalles = data.detalles;
		let categorias = data.categorias;
		let actores = data.actores;

		let categorias1 = "";

		$("#detalleNumPelicula").html(detalles.filmId);
		$("#detalleTitulo").html(detalles.title);
		$("#detallePrecio").html("$" + detalles.rentalRate);
		$("#detalleLanzamiento").html(detalles.releaseYear);
		$("#detalleDuracion").html(detalles.length + " min");

		categorias.forEach(c => categorias1 += c + ", ");
		categorias1 = categorias1.slice(0, categorias1.length - 2)
		$("#detalleCategorias").html(categorias1);
		$("#detalleLenguaje").html(detalles.language.name.trim());
		$("#detalleClasificacion").html(detalles.rating);
		$("#detalleDescripcion").html(detalles.description);

		let lista = document.getElementById("detalleActores");
		lista.innerHTML = "";

		for (let i = 0; i < actores.length; i++) {
			let li = document.createElement("li");
			li.innerHTML = actores[i];
			lista.appendChild(li);
		}

		$("#modalDetalles").modal("show");

	});
}

$("#botonExportarPDF").on("click", function () {
	let filmId = $("#detalleNumPelicula").html();

	var win = window.open($('meta[name=urlBase]').attr("content") + "detallesFilm/exportarpdf/" + filmId, '_blank');
	if (win) {
		//Browser has allowed it to be opened
		win.focus();
	} else {
		//Browser has blocked it
		alert('Please allow popups for this website');
	}

});
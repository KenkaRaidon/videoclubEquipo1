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

function filtrarCiudad() {
	var countryId= $("#selectCountry option").filter(':selected').val()
	console.log(typeof countryId)
	$.get("http://localhost:8666/filtroCiudad/" + parseInt(countryId), function (data, status) {
		console.log(data);
		console.log(status);
		console.log(countryId);
		let cities = data.cities;

	});
}
/*function createCustomer() {
	$("#tituloCreateCustomer").html("Registrar cliente");
	$('#formCreateCustomer').attr('action', '/createCustomer');
}

$("#selectCountry").change(function () {
	if ($(this).val() !== -1) {
		$("#selectCity").prop("disabled", false);
	} else {
		$("#selectCity").prop("disabled", true);
		
	}
});​*/
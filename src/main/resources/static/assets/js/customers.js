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

	/*$("#selectCountry").change(function () {
		if ($(this).val() !== -1) {
			$("#selectCity").prop("disabled", false);
		} else {
			$("#selectCity").prop("disabled", true);
			
		}
	});​*/
});

function filtrarCiudad() {
	var countryId = $("#selectCountry option").filter(':selected').val()
	if ($("#selectCountry option").filter(':selected').val() == -1) {
		$("#selectCity").prop("disabled", true);
	} else {
		$("#selectCity").prop("disabled", false);
	}
	$('#selectCity').empty().append($('<option>').val(-1).text("Selecciona una opción"))
	console.log(typeof countryId)
	$.get("http://localhost:8666/filtroCiudad/" + countryId, function (cities, status) {
		console.log(cities)

		for(var i=0; i<=cities.length-1; i++){
			$('#selectCity').append($('<option>').val(cities[i].cityId).text(cities[i].city))
			console.log(cities[i].cityId)
			console.log(cities[i].city)
		}

	});
}
function registerCustomer() {
	$("#tituloRegisterCustomer").html("Registrar cliente");
	$('#formRegisterCustomer').attr('action', '/registerCustomer');
}


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

	if ($("#selectCountry option").filter(':selected').val() == "") {
		$("#selectCity").prop("disabled", true);
	} else {
		$("#selectCity").prop("disabled", false);
	}

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
	if ($("#selectCountry option").filter(':selected').val() == "") {
		$("#selectCity").prop("disabled", true);
	} else {
		$("#selectCity").prop("disabled", false);
	}
	$('#selectCity').empty().append($('<option>').val("").text("Selecciona una opción"))
	console.log(typeof countryId)
	$.get("http://localhost:8666/filtroCiudad/" + countryId, function (cities, status) {
		console.log(cities)

		for (var i = 0; i <= cities.length - 1; i++) {
			$('#selectCity').append($('<option>').val(cities[i].cityId).text(cities[i].city))
			console.log(cities[i].cityId)
			console.log(cities[i].city)
		}

	});
}

function registerCustomer() {
	$("#tituloRegisterCustomer").html("Registrar cliente");
	$('#formRegisterCustomer').attr('action', '/registerCustomer');
	$.get("http://localhost:8666/registerCustomer", function (cities, status) {
		console.log(cities)
	});
}

function detalles(customerId) {
	$.get("http://localhost:8666/detallesCustomer/" + customerId, function (data, status) {
		console.log(data);
		console.log(status);

		let customer = data.customer;
		let address = data.address;
		let city = data.city;

		$("#customerName").html(customer.firstName+" "+customer.lastName);
		$("#customerId").html(customer.customerId);
		$("#customerEmail").html(customer.email);
		$("#customerAddress").html(address.address+", "+city.city);
		$("#customerPostalCode").html(address.postalCode);
		$("#customerPhone").html(address.phone);
		$("#customerCreateDate").html(customer.create_date);
		if(customer.activeBool)
			$("#customerActive").html("Activo");
		else
			$("#customerActive").html("Desactivado");

		$("#modalDetalles").modal("show");

	});
}


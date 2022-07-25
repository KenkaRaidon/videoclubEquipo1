$(document).ready(function () {

	var x = 1;
	var filmsField = document.getElementById('filmsField');
	var fieldHTML = '<div class="row"><div class="col-8"><input id="inputInventoryId" type="number" name="inventoryId[]" placeholder="Numero de Pelicula" class="form-control" required="required" oninput="findFilm();"/></div></div>';

	var xreturn = 1;
	var filmsFieldReturn = document.getElementById('filmsFieldReturn');
	var fieldHTMLReturn = '<div class="row"><div class="col-8"><input id="inputInventoryIdReturn" type="number" name="inventoryIdReturn[]" placeholder="Numero de Pelicula" class="form-control" required="required" oninput="findFilmReturn();"/></div></div>';



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

	$("#agregarFilm").click(function (e) {
		e.preventDefault();
		if (x <= 5) {
			$(filmsField).append(fieldHTML);
			x++;
		}
	});

	$("#quitarFilm").click(function (e) {
		e.preventDefault();
		if (x > 1) {
			filmsField.removeChild(filmsField.lastElementChild);
			x--;
			findFilm()
			calcularCambio()
		}

	});

	$("#agregarFilmReturn").click(function (e) {
		e.preventDefault();
		if (xreturn <= 5) {
			$(filmsFieldReturn).append(fieldHTMLReturn);
			xreturn++;
		}
	});

	$("#quitarFilmReturn").click(function (e) {
		e.preventDefault();
		if (xreturn > 1) {
			filmsFieldReturn.removeChild(filmsFieldReturn.lastElementChild);
			xreturn--;
			findFilmReturn()
			//calcularCambio() 
		}

	});
}); 

function findCustomer() {
	var customerId = $("#inputCustomerId").val()
	console.log(customerId)
	$.get("http://localhost:8666/findCustomerById/" + customerId, function (customer, status) {
		if (customer != null) {
			console.log(customer)
			$("#inputCustomerName").val(customer.firstName + ' ' + customer.lastName)
		}else{
			$("#inputCustomerName").val('')
		}

	});
}

function findFilm() {
	var inventoryId = document.getElementsByName("inventoryId[]");
	var subtotal = 0
	var iva = 0
	var total = 0
	for (i = 0; i < inventoryId.length; i++) {
		console.log(typeof inventoryId[i].value)
		$('#tablaFilms tr').not(':first').remove();
		if (Number(inventoryId[i].value) > 0) {
			$.get("http://localhost:8666/obtenerInventario/" + Number(inventoryId[i].value), function (inventory, status) {
				if (inventory.inventory_id != undefined) {
					console.log("ID del inventario" + inventory.inventory_id)
					$("<tr scope='row'><td>" + inventory.title + "</td><td>" + inventory.rental_rate + "</td></tr>").appendTo("#tablaFilms")
					subtotal += inventory.rental_rate
					iva = subtotal * .08
					total = subtotal + iva
					console.log("Subtotal es " + subtotal)
					$("#inputSubtotal").val(subtotal.toFixed(2))
					$("#inputIVA").val(iva.toFixed(2))
					$("#inputTotal").val(total.toFixed(2))
				} else {
					console.log("Vacio JAJAJAJ")
				}
				$('#tablaFilms tr').first().after('');
			});
		}

	}
}

function findFilmReturn() {
	var inventoryId = document.getElementsByName("inventoryIdReturn[]");
	var subtotal = 0
	var iva = 0
	var total = 0
	for (i = 0; i < inventoryId.length; i++) {
		$('#tablaFilmsReturn tr').not(':first').remove();
		if (Number(inventoryId[i].value) > 0) {
			$.get("http://localhost:8666/obtenerInventarioReturn/" + Number(inventoryId[i].value), function (returnIndex, status) {
				var returnDate = new Date(returnIndex.returnDate)
				var dateNow = new Date();
				
				console.log("Id inventario " + returnIndex)
				console.log(returnDate.toLocaleString('en-US'))
				console.log(dateNow.toLocaleString('en-US'))
				$("<tr scope='row'><td>" + returnIndex.title + "</td><td>" + returnDate.toLocaleString('en-US') + "</td></tr>").appendTo("#tablaFilmsReturn")
				$('#tablaFilmsReturn tr').first().after('');
				if (dateNow < returnDate) {
					console.log("Estas a tiempo")
				} else {
					var multaDay = Math.round((dateNow - returnDate) / (1000 * 60 * 60 * 24))
					console.log("Tienes Multa " + " con " + multaDay + " dias")
					subtotal += .50 * multaDay
					iva = subtotal * .08
					total = subtotal + iva
					$("#inputMontoReturn").prop('required', true)
				}
				if(subtotal==0){
					$("#inputMontoReturn").prop('required', false)
				}
				$("#inputSubtotalReturn").val(subtotal.toFixed(2))
				$("#inputIVAReturn").val(iva.toFixed(2))
				$("#inputTotalReturn").val(total.toFixed(2))
			});
		}

	}
}

function calcularCambio() {
	var monto = $("#inputMonto").val()
	var total = $("#inputTotal").val()
	var cambio = 0
	console.log("Calcular cambio")
	if (Number(monto) >= Number(total)) {
		cambio = monto - total
		$("#inpuCambio").val(cambio.toFixed(2))
		$('#botonGuardar2').prop('disabled', false)
	} else if (Number(monto) < Number(total)) {
		$('#botonGuardar2').prop('disabled', true)
	}

}

function calcularMulta() {
	var monto = $("#inputMontoReturn").val()
	var total = $("#inputTotalReturn").val()
	var cambio = 0
	console.log("Calcular cambio")
	if (Number(monto) >= Number(total)) {
		cambio = monto - total
		$("#inpuCambioReturn").val(cambio.toFixed(2))
		$('#botonGuardarReturn').prop('disabled', false)
	} else if (Number(monto) < Number(total)) {
		$('#botonGuardarReturn').prop('disabled', true)
	}

}
$(document).ready(function () {
	var x = 1;
	var filmsField = document.getElementById('filmsField');
	var fieldHTML = '<div class="row"><div class="col-8"><input id="inputInventoryId" type="number" name="inventoryId[]" placeholder="Numero de Pelicula" class="form-control" required="required" oninput="findFilm();"/><div class="invalid-feedback">Introduce un numero de pelicula.</div><br></div></div>';

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
			$("#inputMonto").val("")
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
			$("#inputMonto").val("")
		}

	});

});

function timedRefresh() {
	console.log("Refrescando")
	var inventoryId = document.getElementsByName("inventoryId[]");
	var timeWait
	switch(inventoryId.length){
		case 1:
			timeWait=1000
			break;
		case 2:
			timeWait=2000
			break
		case 3:
			timeWait=3000
			break
		case 4:
			timeWait=4000
			break
		case 5:
			timeWait=5000
			break
		case 6:
			timeWait=6000
			break
	}
	console.log("Refrecando pagina timepo de espera "+ timeWait)
	$('#botonGuardar2').prop('disabled', true)
	setTimeout(window.location.reload(),1000);
}
//  
function findCustomer() {
	var customerId = $("#inputCustomerId").val()
	var inventoryId = document.getElementsByName("inventoryId[]");
	console.log(customerId)
	$("#rentalAlert").empty();
	$.get("http://localhost:8666/findCustomerById/" + customerId, function (customer, status) {
		if (customer != null) {
			console.log(customer)
			if (customer.activeBool == false) {
				$('#inputMonto').prop('readonly', true)
				$('#botonGuardar2').prop('disabled', true)
			}
			else {
				$('#inputMonto').prop('readonly', false)
				$('#botonGuardar2').prop('disabled', false)
				for (i = 0; i < inventoryId.length; i++) {
					inventoryId[i].disabled = false;
				}

			}
			$("#inputCustomerName").val(customer.firstName + ' ' + customer.lastName)
		} else {
			for (i = 0; i < inventoryId.length; i++) {
				inventoryId[i].disabled = true;
			}
			$("#inputCustomerName").val('')
			$('#inputMonto').prop('readonly', true)
			$('#botonGuardar2').prop('disabled', true)
			$("#rentalAlert").append('<div class="alert alert-danger" id="mialerta" role="alert">El numero de cliente introducido no existe</div>');
			Swal.fire({
				position: 'center',
				icon: 'info',
				title: 'El numero de cliente introducido no existe',
				showConfirmButton: false,
				timer: 1000
			  })
		}

	});
}

function findFilm() {
	var inventoryId = document.getElementsByName("inventoryId[]");
	var array = []
	var subtotal = 0
	var iva = 0
	var total = 0
	var existencia = true
	$("#rentalAlert").empty();
	$("#inputMonto").val("")
	for (i = 0; i < inventoryId.length; i++) {
		array.push(inventoryId[i].value)
		console.log(array)
		//var haber=findRepeat(array)
		//console.log(haber)
		try {
			array.forEach(
				function callback(currentValue, index, array) {
					console.log("current " + currentValue + " index " + index + " array " + array)
					for (i = 0; i < index; i++) {
						if (currentValue == array[i]) {
							console.log("Repetido con el valor " + currentValue)
							$("#rentalAlert").append('<div class="alert alert-danger" id="mialerta" role="alert">El numero ' + currentValue + ' de inventario introducido esta repetido</div>');
							$('#agregarFilm').prop('disabled', true)
							existencia = false
							throw BreakException;
						} else {
							console.log("No repetido")
							$('#agregarFilm').prop('disabled', false)
						}
					}

				})
		} catch (e) {
			if (e !== BreakException) throw e;
		}
		$('#tablaFilms tr').not(':first').remove();
		if (Number(inventoryId[i].value) > 0) {
			$.get("http://localhost:8666/obtenerInventario/" + Number(inventoryId[i].value), function (inventory, status) {
				console.log(inventory)
				if (inventory.inventory_id != undefined) {
					console.log("ID del inventario" + inventory.inventory_id)
					$("<tr scope='row'><td>" + inventory.title + "</td><td>" + inventory.rental_rate + "</td></tr>").appendTo("#tablaFilms")
					subtotal += inventory.rental_rate
					iva = subtotal * .08
					total = subtotal + iva
					console.log("Subtotal es " + subtotal)
					$('#agregarFilm').prop('disabled', false)
					$('#botonGuardar2').prop('disabled', false)
					$("#inputMonto").prop('disabled', false)
				} else {
					$("#rentalAlert").append('<div class="alert alert-danger" id="mialerta" role="alert">El numero de inventario introducido no existe o esta rentado</div>');
					existencia = false
				}
				$('#tablaFilms tr').first().after('');
				if (existencia == false) {
					subtotal = 0
					iva = 0
					total = 0
					$('#agregarFilm').prop('disabled', true)
					$('#botonGuardar2').prop('disabled', true)
					$("#inputMonto").prop('disabled', true)
				}
				$("#inputSubtotal").val(subtotal.toFixed(2))
				$("#inputIVA").val(iva.toFixed(2))
				$("#inputTotal").val(total.toFixed(2))
			});
		} else {
			$("#rentalAlert").append('<div class="alert alert-danger" id="mialerta" role="alert">El numero ' + inventoryId[i].value + ' introducido no es valido</div>');
			$('#agregarFilm').prop('disabled', true)
			$('#botonGuardar2').prop('disabled', true)
			break
		}
	}
}

function findRepeat(array) {
	try {
		array.forEach(
			function callback(currentValue, index, array) {
				console.log("current " + currentValue + " index " + index + " array " + array)
				for (i = 0; i < index; i++) {
					if (currentValue == array[i]) {
						console.log("Repetido con el valor " + currentValue)
						$("#rentalAlert").append('<div class="alert alert-danger" id="mialerta" role="alert">El numero ' + currentValue + ' de inventario introducido esta repetido</div>');
						$('#agregarFilm').prop('disabled', true)
						throw BreakException;
					} else {
						console.log("No repetido")
						$('#agregarFilm').prop('disabled', false)
					}
				}

			})
	} catch (e) {
		if (e !== BreakException) throw e;
	}
}

/*$('input[name="inventoryId[]"]').on('input', function (e) {
	e.preventDefault();
	var inventoryId =$(this).val();
	console.log("En esta funcion inventario es "+ inventoryId)
	if ($('#inputInventoryId').length) {
		$('#inputInventoryId').each(function () {
			console.log($(this).val())
			if ($(this).val() == inventoryId) {
				console.log("Repetido Idiota")
				$("#alertas").append('<div class="alert alert-danger" id="mialerta" role="alert">El numero ' + inventoryId + ' ya esta en la lista!!</div>');
				return;
			}
		});
	}
});*/

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
				if (subtotal == 0) {
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
		$("#inpuCambio").val(0)
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



//tavo funciones
$("#agregapelicularetorno").click(function (e) {
	e.preventDefault();
	var invId = $("#inputInventoryIdReturn").val();

	$("#alertas").empty();
	const r = /[0-9]+$/;
	if (!r.test(invId)) {
		$("#alertas").append('<div class="alert alert-danger" id="mialerta" role="alert">Solo numeros enteros positivos!</div>');
		return;
	}

	
	if (invId > 0) {
		
		if ($('#ListaInv').length) {
		$('#ListaInv').each(function () {
			console.log($(this).val())
			if ($(this).val() == invId) {
				$("#alertas").append('<div class="alert alert-danger" id="mialerta" role="alert">El numero ' + invId + ' ya esta en la lista!!</div>');
				invId = 0;
				return;
			}
		});
	}
		
		$.get("http://localhost:8666/obtenerInventarioReturn/" + invId, function (returnIndex, status) {

          console.log(status);
			console.log(returnIndex.rentada)

			if (!returnIndex) {
				$("#alertas").append('<div class="alert alert-danger" id="mialerta" role="alert">El numero ' + invId + ' de inventario de pelicula no existe!!</div>');
				invId = 0;
				return;
			}
			if (!returnIndex.rentada) {
				$("#alertas").append('<div class="alert alert-danger" id="mialerta" role="alert">La pelicula con numero ' + invId + ' no esta rentada!!<br>No se puede hacer devolución.</div>');
				return;
			}
			console.log("Mi retorno"+returnIndex.cliente.customerId);
			var fecharegreso = $("#fecharegreso").val();
			if(fecharegreso)
			{
				$("#fecharegreso").attr("readonly",true);
				
			}
			else{
				 $("#fecharegreso").attr("readonly",false);
				 $("#alertas").append('<div class="alert alert-danger" id="mialerta" role="alert">Ingresa una fecha!!<br>No se puede hacer devolución.</div>');
				invId = 0;
				return;
				 
			}


   var Fullclient=returnIndex.cliente.customerId + "-" + returnIndex.cliente.firstName + " " + returnIndex.cliente.lastName;



			var lista = document.getElementById("MiListaDevo");
			$(lista).append("<li class='list-group-item' id='Miinventario'><input type='checkbox'  value="+ invId+"></input><input id='ListaInv'  name='milistainv[]' value="+ invId+"></input></li>");

			var dayReturn = new Date(returnIndex.returnDate);
			$("<tr scope='row' class='delate'  id="+invId+"><td>" + returnIndex.title + "</td><td>" + dayReturn.toLocaleString('en-US') + "</td><td>" + returnIndex.cliente.customerId + "</td></tr>").appendTo("#tablaFilmsReturn");


			$("#inputInventoryIdReturn").val("");//para limpiar imput
			
			
			console.log("Mi fecha"+fecharegreso)
			var dayact = new Date(fecharegreso);
			if (dayReturn < dayact) {

				var difference = Math.abs(dayact - dayReturn);
				days = difference / (1000 * 3600 * 24)
				var multa = days.toFixed(1) * 0.50;

				if (multa > 1000) {
					multa = multa / 10
					multa = multa.toFixed(1);
				}

				console.log(returnIndex.cliente.firstName);
				var multlist = "<tr scope='row' class='delate' id="+invId+"><td>" + returnIndex.title + "</td> <td><input id='multa' name='listamulta[]' type='number' class='form-control' readonly value=" + multa + "></input></td><td id='cliente'>" + returnIndex.cliente.customerId + "</td></tr>";
				$("#tablaFilmsMulta").append(multlist);
				var sub = $("#inputSubtotalReturn").val();
				sub = parseFloat(sub) + multa;
			
				//$("#inputIVAReturn").val(sub * 0.16)
				//$("#inputTotalReturn").val(sub + sub * 0.16)
				
				calcularTotal(Fullclient,multa);
				
			}
			else {

				
				var multlist = "<tr scope='row' class='delate' id="+invId+"><td>" + returnIndex.title + "</td> <td><input id='multa' name='listamulta[]' type='number' class='form-control' readonly value='0'></input></td><td id='cliente'>" + returnIndex.cliente.customerId+ "</td></tr>";
				$("#tablaFilmsMulta").append(multlist);
				calcularTotal(Fullclient,0);
	
			  
		
			}
			
			

		});
		//tabla multas
		

	}
	else{
		
		$("#alertas").append('<div class="alert alert-danger" id="mialerta" role="alert">El 0 no es un valor permitido</div>');
		return;
	
	}

});


function calcularTotal(Fullclient,multa){
	console.log("Tabla total   "+$("#tablaTotal input").length);
	
	var sub=0
	//$('#tablaTotal tr').not(':first').remove();
      if($("#tablaTotal input").length==0){
	           console.log("Primera ves")
				var multlist = "<tr scope='row' class='delate'><td id='cliente'name="+parseInt(Fullclient)+">"+Fullclient+"</td> <td><input id='total'  type='number' class='form-control' readonly value=" + multa + "></input></td></tr>";
				$("#tablaTotal").append(multlist);
	
        }
        else{
	
	var value=$("#tablaTotal tr.delate").find("#cliente[name="+parseInt(Fullclient)+"]")
	if(value )
	{
		console.log("lo encontro "+value.text())
	}
	
	
	 $("#tablaTotal tr.delate").each(function(){
		
		
		var cliente=$(this).find("#cliente").text();
			sub = $(this).find("#total").val();
			console.log("por valie por tabla "+sub);
			
			console.log("Mi cliente de la tabla"+cliente)
			console.log("Mi cliente mandadoooo"+Fullclient)
		if(parseInt(cliente)==parseInt(Fullclient))
		{
			
		
			var aux=parseFloat(sub)+parseFloat(multa);
			console.log("por cliente"+aux);
			$(this).find("#total").val(aux);
			
		}else{
			//nada
		}
		
		$("#tablaTotal").append(multlist);
		
		
	});
	
	
}
        
       
}




$("#quitarpelicularetorno").click(function (e) {
	e.preventDefault();
	
	const milista=document.querySelector('#ListaInv');
	
	
	$("#MiListaDevo input").each(function () {
		
		
		if(this.checked){
			//borra item
			console.log($(this).val());
			borrarItem(parseInt($(this).val()));
			$(this).parent().remove();
		}
		
		
		
	});
/*


*/
});


function borrarItem(item){
	
	
	$("#tablaFilmsMulta tr.delate").each(function(){
		//var value=$(this).find("#"+item);
		var id=parseInt($(this).attr('id'));
		if(id==item){
			var multa = $(this).find("#multa").val();
			var cliente = $(this).find("#cliente").text();
			console.log("ayuda diosito")
			console.log(multa);
			RestarTotal(cliente,multa);
			 $(this).remove();
			

		}
		
	});
	
	$("#tablaFilmsReturn tr.delate").each(function(){
		var id=parseInt($(this).attr('id'));
		if(id==item)
		 {
			$(this).remove();
		}
		
	});
	/*
	var x = $("#tablaFilmsMulta tr.delate").each(function () {
		var multa = $(this).find("#multa").text();




		var sub = $("#inputSubtotalReturn").val();
		sub = parseFloat(sub) - parseFloat(multa);
		$("#inputSubtotalReturn").val(sub);
		$("#inputIVAReturn").val(sub * 0.16)
		$("#inputTotalReturn").val(sub + sub * 0.16)

	});
*/
	//$("#tablaFilmsReturn tr.delate:last").remove();

	//$("#tablaFilmsMulta tr.delate:last").remove();
}



function RestarTotal(Fullclient,multa) {
console.log("Mi cliente mandado"+Fullclient)
 $("#tablaTotal  tr.delate").each(function(){
		
		
		var cliente=$(this).find("#cliente").text();
			var sub = $(this).find("#total").val();
			
				console.log("Mi resta subtotal"+sub)
				console.log("Mi resta sub full cli "+Fullclient)
		if(parseInt(cliente)==parseInt(Fullclient)){
			
			
		
			var aux=parseFloat(sub)-parseFloat(multa);
			console.log("Mi resultado es "+aux);
			if(aux==0)
			{
			   $(this).remove();
			}
			else{
			console.log("por cliente"+aux);
			$(this).find("#total").val(aux.toFixed(1));
			}
			
		}

});
}

//end tavo funciones

(function () {
	'use strict'

	// Fetch all the forms we want to apply custom Bootstrap validation styles to
	var forms = document.querySelectorAll('.needs-validation')

	// Loop over them and prevent submission
	Array.prototype.slice.call(forms)
		.forEach(function (form) {
			form.addEventListener('submit', function (event) {
				if (!form.checkValidity()) {
					event.preventDefault()
					event.stopPropagation()
				}

				form.classList.add('was-validated')
			}, false)
		})
})()
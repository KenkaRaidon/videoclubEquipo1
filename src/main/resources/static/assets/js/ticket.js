
								$(document).ready(function(){
									$("#alertaPago").submit(function(){
										alert("Se pago exitosamente");
									});
								});
								
	
var customerId;
function findByCustomerId() {
	var amount = 0
	customerId = $('#inputCustomerId').val();
	$("#inputTotal").val("")
	console.log(customerId);
	$.get("http://localhost:8666/findTicketCusById/" + customerId, function (customer, status) {
		if (customer != null) {
			console.log(customer)
			$("#inputNumeroId").val(customer.customerId)
			$("#inputCustomerName").val(customer.firstName + ' ' + customer.lastName)
			
		} else {
			$("#inputCustomerName").val('')
			$('#botonGuardar2').prop('disabled', true)
		}
	});

	$.get("http://localhost:8666/findTicketByCusId/" + customerId, function (ticket, status) {
		if (ticket.length == 0){
			$('#botonGuardar2').prop('disabled', true);
			$('#modalMultas').modal('hide');
			console.log("entre en este caso");
			if(window.confirm("No se encontraron multas haga click en ok para continuar")){
				window.location.href = "http://localhost:8666/multa";
			}else{
				window.location.href = "http://localhost:8666/multa";
			}
		}else{
			$('#botonGuardar2').prop('disabled', false)
		console.log(ticket);
		$('#tablita tr').not(':first').remove();
		for (var i = 0; i < ticket.length; i++) {
			var ticketTable = "<tr scope='row' class='delate'><td><input id='inputTicketId' name='listTicket[]' type='number' class='form-control' readonly value=" + ticket[i].ticketId + "></input></td> <td>" + ticket[i].rentalId + "</td> <td>" + "$"+ticket[i].amount+ "</td></tr>";
			$("#tablita").append(ticketTable);
			amount += ticket[i].amount
		}
		$("#inputAmount").val(amount)
}
	});

}

function calcularCambio() {
	//inputTotal
	var monto = $("#inputAmount").val()
	var total = $("#inputTotal").val()
	var cambio = 0
	console.log("Calcular cambio")
	if (Number(total) >= Number(monto) ) {
		cambio = total - monto
		$("#inputCambio").val(cambio.toFixed(2))
		$('#botonGuardar2').prop('disabled', false)
		console.log("Enabled")
	} else if(total.length != 0){
		$('#botonGuardar2').prop('disabled', true)
		$("#inputCambio").val(0)
		console.log("Disabled")
		
	}
	
	function alertaPago(){
		if(window.confirm("Se pago exitosamente")){
			$("#formPago").submit();
		}
	}

}
function botonSubmit(){
	const myTimeout = setTimeout(redirigir, 1000);

	//window.location.href = "/index";
	console.log("Si jalo!");
}
function redirigir(){
		var id = $('#inputCustomerId').val();
		$(location).attr('href', '/success');
		sessionStorage.setItem("id", id);
}

function validador(){
	var numero = document.getElementById('inputCustomerId').value.length;
	if(numero == 0){
		$("#enviar").prop('disabled', true);
	}else{
		$("#enviar").prop('disabled', false);
	}

}
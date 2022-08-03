/**
 * 
 */	function prueba(){
		var id = sessionStorage.getItem("id");
		console.log(id);
		detalles(id);
		
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
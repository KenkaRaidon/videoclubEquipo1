/**
 * 
 */
 
 function agregarpelicula()
 {
	let val=$("#numeropelicula").val()
	
	
	$("#list-tab").append( "<li class='list-group-item ' id='list-profile-list' data-toggle='list'  role='tab' >"+val+"<button id='delete' class='btn btn-danger'>-</button></li>");
	
}
 
 
$(".btn-danger").on('click',function () {
    $(this).closest("li").remove();
    alert("clicked");
   });
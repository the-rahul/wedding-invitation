$(document).ready(function(){

	$(document).on('click', 'a.open-image', function(e){
		e.preventDefault();
	    $("#myModal").find('.modal-content').attr('src', $(this).attr('href'));
	    $("#myModal").show();
	})
	
	$(document).on('click', '.close', function(e){
		$("#myModal").hide();
	})
	
	$(document).on('click', '.download-invite', function(){
		$.ajax({
			url: "/invite/download",
			type: "GET",
			success: function(data){
				window.open(data, "_blank");
			},
			error: function(jqXHR, exception){
				alert('error occurred');
			}
		})
	})
	
	$(document).on('click', '.view-invite', function(){
		let win = window.open("/invite/view", "Rahul-Khushboo-Wedding-Invitation");
	})
	
})
 $(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}

$("#alertError").hide();

})


$(document).on("click", "#btnSave", function(event) 
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
	// Form validation-------------------
	var status = validateItemForm(); 
	if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	 } 
	// If valid------------------------
	var type = ($("#hidInvoiceIDSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
		 url : "InvoiceAPI", 
		 type : type, 
		 data : $("#formInvoice").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
	 	{ 
	 	 	onItemSaveComplete(response.responseText, status); 
	 	} 
	 }); 
});



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidInvoiceIDSave").val($(this).data('id')); 
		 $("#cus_nic").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#month").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#unit_calculation").val($(this).closest("tr").find('td:eq(2)').text()); 
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "InvoiceAPI", 
		 type : "DELETE", 
		 data : "id=" + $(this).data("id"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onInvoiceDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
		function onInvoiceDeleteComplete(response, status) {
			if (status == "success") {
				var resultSet = JSON.parse(response);
				if (resultSet.status.trim() == "success") {
					$("#alertSuccess").text("Successfully deleted.");
					$("#alertSuccess").show();
					$("#divInvoicesGrid").html(resultSet.data);
				} else if (resultSet.status.trim() == "error") {
					$("#alertError").text(resultSet.data);
					$("#alertError").show();
				}
			} else if (status == "error") {
				$("#alertError").text("Error while deleting.");
				$("#alertError").show();
			} else {
				$("#alertError").text("Unknown error while deleting..");
				$("#alertError").show();
			}
		}


		// CLIENT-MODEL================================================================
		function validateInvoiceForm() {
			// NIC
			if ($("#cus_nic").val().trim() == "") {
				return "Insert NIC.";
			}
			// Month
			if ($("#month").val().trim() == "") {
				return "Insert Month.";
			}
			//No of units
			if ($("#unit_calculation").val().trim() == "") {
				return "Insert Unit calculation.";
			}
		
		
			return true;
		}
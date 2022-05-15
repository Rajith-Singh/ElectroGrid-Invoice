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



//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
// Form validation-------------------
	var status = validateInvoiceForm(); 
	if (status != true) 
	 { 
	 	$("#alertError").text(status); 
	 	$("#alertError").show(); 
	 	return; 
	 }
	 
// If valid------------------------
var t = ($("#hidInvoiceIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
	 url : "InvoiceAPI", 
	 type : t, 
	 data : $("#formInvoice").serialize(), 
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onInvoiceSaveComplete(response.responseText, status); 
	 }
	 }); 
 }); 	  

function onInvoiceSaveComplete(response, status)
{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 	$("#alertSuccess").text("Successfully saved."); 
	 	$("#alertSuccess").show(); 
	 	$("#divInvoicesGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 	$("#alertError").text(resultSet.data); 
	 	$("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 	$("#alertError").text("Error while saving."); 
	 	$("#alertError").show(); 
	 } else
	 { 
	 	$("#alertError").text("Unknown error while saving.."); 
	 	$("#alertError").show(); 
	 }
	$("#hidInvoiceIDSave").val(""); 
	$("#formInvoice")[0].reset(); 
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidInvoiceIDSave").val($(this).data('serviceid')); 
		 $("#AccountNo").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#Address").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#Inquiry").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#Status").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#TelNo").val($(this).closest("tr").find('td:eq(4)').text()); 
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "InvoiceAPI", 
		 type : "DELETE", 
		 data : "ServiceId=" + $(this).data("serviceid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onInvoiceDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onInvoiceDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divInvoicesGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}





// CLIENT-MODEL================================================================
function validateInvoiceForm()
{
	// NIC
	if ($("#cus-nic").val().trim() == "")
	{
	return "Insert NIC.";
	}
	// Month
	if ($("#month").val().trim() == "")
	{
	return "Insert Month.";
	}
	//No of units
	if ($("#unit_calculation").val().trim() == "")
	{
	return "Insert Unit calculation.";
	}

 
	return true;
}
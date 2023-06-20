
getArr();
var isPage = "arr"
var isDepFirst=true;
var isArrFirst=false;

function getArr(){	
	$.ajax({
		type: "GET",
		url:"/arr/all",
		success: function (result) {
			const data = JSON.stringify(result);
			var obj = jQuery.parseJSON(data);
			if ($.fn.DataTable.isDataTable('#tableArr')) {
				//console.log("destroy arr");
			  	$('#tableArr').DataTable().destroy();
			}
			setTimeout(function() {
				if(obj != '') {
					$("#myBody1").empty();
					$.each(obj, function(key, val) {
						/*Create a new Date object with the desired time*/
						var time = new Date(val.time);
						/*Get the time in the format of "HH:MM"*/
						var hours = time.getHours();
						var minutes = time.getMinutes();
						var timeFormatted = (hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes;
						/*Get the current date in the format of "DD"*/
						var date = time.getDate();
						var timeDate = timeFormatted + "/" + date
						
						if(val.type === "D"){
							val.type = "DOM"
						} else {
							val.type = "INT"
						}
						
						if(val.remark === null){
							val.remark = ""
						}
							
						var tr = "<tr>";
						tr = tr + "<td class='d-none'>" + val.time.trim() + "</td>";
						tr = tr + "<td class='d-none'>" + val.id.trim() + "</td>";
						tr = tr + "<td>" + timeDate.trim() + "</td>";
						tr = tr + "<td>" + val.from.trim() + "</td>";
						tr = tr + "<td>" + val.flight.trim() + "</td>";
						tr = tr + "<td>" + val.belt.trim() + "</td>";
						tr = tr + "<td>" + val.type.trim() + "</td>";
						tr = tr + "<td>" + val.nature.trim() + "</td>";
						tr = tr + "<td>" + val.remark.trim() + "</td>";
						tr = tr + '<td><button type="button" class="btn btn-outline-primary editbtn_arr"><i class="fas fa-edit"></i> Edit</button></td>';
						tr = tr + '<td><button type="button" class="btn btn-outline-danger deletebtn_arr"><i class="fa fa-trash"></i> Delete</button></td>';
						tr = tr + "</tr>";
						$('#tableArr > tbody:last').append(tr);
					});
					var tableArr = $('#tableArr').DataTable({	
					    "drawCallback": function( settings ) {
					        btnEdit();btnDelete();
					    },
					    "columnDefs": [
					        { "targets": 2, "orderData": 0},
				            { "orderable": false, "targets": 9 },
				            { "orderable": false, "targets": 10 }
				        ]
					    //"order": [[1, 'ASC']]
					});
				}
			},150);
		}
	});
	$.ajax({
		type: "GET",
		url: "/fidsairport/all",
		contentType: "application/json; charset=utf-8",
		success: function(result) {
			nameApsn = result;
		}
	}); 
	$.ajax({
		type: "GET",
		url: "/fidtab/all",
		contentType: "application/json; charset=utf-8",
		success: function(result) {
			codeRemark = result;
		}
	}); 
}

function getDep(){
	$.ajax({
		type: "GET",
		url:"/dep/all",
		success: function (result) {
			const data = JSON.stringify(result);
			var obj = jQuery.parseJSON(data);
			if ($.fn.DataTable.isDataTable('#tableDep')) {
				//console.log("destroy dep");
			  	$('#tableDep').DataTable().destroy();
			}
			setTimeout(function() {
				if(obj != '') {
					$("#myBody2").empty();
					$.each(obj, function(key, val) {
						/*Create a new Date object with the desired time*/
						var time = new Date(val.time);
						/*Get the time in the format of "HH:MM"*/
						var hours = time.getHours();
						var minutes = time.getMinutes();
						var timeFormatted = (hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes;
						/*Get the current date in the format of "DD"*/
						var date = time.getDate();
						var timeDate = timeFormatted + "/" + date
						
						if(val.type === "D"){
							val.type = "DOM"
						} else {
							val.type = "INT"
						}
						
						if(val.remark === null){
							val.remark = ""
						}
						
						var tr = "<tr>";
						tr = tr + "<td class='d-none'>" + val.time.trim() + "</td>";
						tr = tr + "<td class='d-none'>" + val.id.trim() + "</td>";
						tr = tr + "<td>" + timeDate.trim() + "</td>";
						tr = tr + "<td>" + val.via.trim() + "</td>";
						tr = tr + "<td>" + val.to.trim() + "</td>";
						tr = tr + "<td>" + val.flight.trim() + "</td>";
						tr = tr + "<td>" + val.counter.trim() + "</td>";
						tr = tr + "<td>" + val.gate.trim() + "</td>";
						tr = tr + "<td>" + val.type.trim() + "</td>";
						tr = tr + "<td>" + val.nature.trim() + "</td>";
						tr = tr + "<td>" + val.remark.trim() + "</td>";
						tr = tr + '<td><button type="button" class="btn btn-outline-primary editbtn_dep"><i class="fas fa-edit"></i> Edit</button></td>';
						tr = tr + '<td><button type="button" class="btn btn-outline-danger deletebtn_dep"><i class="fa fa-trash"></i> Delete</button></td>';
						tr = tr + "</tr>";
						$('#tableDep > tbody:last').append(tr);
					});
					$("#tableDep").DataTable({
					    "drawCallback": function( settings ) {
					        btnEdit();btnDelete();
					    },
					    "columnDefs": [
					    	{ "targets": 2, "orderData": 0},
				            { "orderable": false, "targets": 11 },
				            { "orderable": false, "targets": 12 }
				        ]
					    //"order": [[1, 'ASC']]
					});	
				}
			},150);
		}
	});
}

function btnEdit() {
	//console.log("btnEdit run");
    $('.editbtn_arr').on('click', function () {
    	$.ajax({
    		type: "GET",
    		url:"/fidtab/all",
    		success: function (result) {
    			const data = JSON.stringify(result);
    			var obj = jQuery.parseJSON(data);
    			if(obj != '') {
    				$("#edit_arr_remark_list").empty();
    			      var options = '';
    			      $.each(obj, function(i, item) {
    			    		  options += '<option value="'+item["beme"].trim()+'">'+item["beme"].trim()+'</option>';
    			      });
    			      $("#edit_arr_remark_list").html(options);
    			}
    			
    			var manu = document.getElementById("edit_arr_remark");
    			var manu_list = document.getElementById("edit_arr_remark_list");
    			
    			for (let option of manu_list.options) {
      				option.onclick = function() {
      					manu.value = option.value;
      			    	manu_list.style.display = 'none';
      			    	manu.focus();
      			    	manu.blur();
      			  }
      			};

      			function searchAndDisplayOptions() {
      			  text = manu.value.toUpperCase();
      			  for (let option of manu_list.options) {
      				if (option.value.toUpperCase().indexOf(text) > -1) {
      			    	var found = true;
      			    	option.style.display = "block";
      			    } else {
      			    	option.style.display = "none";
      			    }
      			    if (found){
      			    	manu_list.style.display = 'block';
      			    } else {
      			    	manu_list.style.display = 'none';
      			    }
      			  }
      			}
      			
      			manu.addEventListener("click", searchAndDisplayOptions);
      			manu.oninput = searchAndDisplayOptions;
      			
      			document.addEventListener("click", function (event) {
    				if (!manu.contains(event.target)) {
      			  		manu_list.style.display = 'none';
    				}
    			});	

    		}
    	});
    	
        $('#editmodalArr').modal('show');
        $tr = $(this).closest('tr');;
        var data = $tr.children("td").map(function () {
            return $(this).text();
        }).get();
        $('#edit_arr_time').val(data[0]);
        $('#edit_arr_id').val(data[1]);
        $('#edit_arr_from').val(data[3]);
        $('#edit_arr_flight').val(data[4]);
        $('#edit_arr_belt').val(data[5]);
        $('#edit_arr_type').val(data[6]);
        $('#edit_arr_nature').val(data[7]);
        $('#edit_arr_remark').val(data[8]);
    });
    
    $('.editbtn_dep').on('click', function () {
    	$.ajax({
    		type: "GET",
    		url:"/fidtab/all",
    		success: function (result) {
    			const data = JSON.stringify(result);
    			var obj = jQuery.parseJSON(data);
    			if(obj != '') {
    				$("#edit_dep_remark_list").empty();
    			      var options = '';
    			      $.each(obj, function(i, item) {
    			    		  options += '<option value="'+item["beme"].trim()+'">'+item["beme"].trim()+'</option>';
    			      });
    			      $("#edit_dep_remark_list").html(options);
    			}
    			
    			var manu = document.getElementById("edit_dep_remark");
    			var manu_list = document.getElementById("edit_dep_remark_list");
    			
    			for (let option of manu_list.options) {
      				option.onclick = function() {
      					manu.value = option.value;
      			    	manu_list.style.display = 'none';
      			    	manu.focus();
      			    	manu.blur();
      			  }
      			};

      			function searchAndDisplayOptions() {
      			  text = manu.value.toUpperCase();
      			  for (let option of manu_list.options) {
      			    if (option.value.toUpperCase().indexOf(text) > -1) {
      			    	var found = true;
      			    	option.style.display = "block";
      			    } else {
      			    	option.style.display = "none";
      			    }
      			    if (found){
      			    	manu_list.style.display = 'block';
      			    } else {
      			    	manu_list.style.display = 'none';
      			    }
      			  }
      			}
      			
      			manu.addEventListener("click", searchAndDisplayOptions);
      			manu.oninput = searchAndDisplayOptions;
      			
      			document.addEventListener("click", function (event) {
    				if (!manu.contains(event.target)) {
      			  		manu_list.style.display = 'none';
    				}
    			});		

    		}
    	});
    	
        $('#editmodalDep').modal('show');
        $tr = $(this).closest('tr');;
        var data = $tr.children("td").map(function () {
            return $(this).text();
        }).get();
        $('#edit_dep_time').val(data[0]);
        $('#edit_dep_id').val(data[1]);
        $('#edit_dep_via').val(data[3]);
        $('#edit_dep_to').val(data[4]);
        $('#edit_dep_flight').val(data[5]);
        $('#edit_dep_counter').val(data[6]);
        $('#edit_dep_gate').val(data[7]);
        $('#edit_dep_type').val(data[8]);
        $('#edit_dep_nature').val(data[9]);
        $('#edit_dep_remark').val(data[10]);
    });
}

function btnDelete() {
	//console.log("btnDelete run");
   	$('.deletebtn_arr').off('click').on('click', function () {
   		var c = confirm("Do you want to delete?");
		if (c == true) {
	        $tr = $(this).closest('tr');
	        var data = $tr.children("td").map(function () {
	            return $(this).text();
	        }).get();     
	        console.log(data);
	        var arr_id = data[1]; 
	        $.ajax({
				type: "DELETE",
				url:"/arr/delete",
				contentType: "application/json; charset=utf-8",
				data:JSON.stringify({"id": arr_id}),
				success: function (data) {
				 	getArr();
				}
			});     
		}                 
	});
   	
   	$('.deletebtn_dep').off('click').on('click', function () {
   		var c = confirm("Do you want to delete?");
		if (c == true) {
	        $tr = $(this).closest('tr');
	        var data = $tr.children("td").map(function () {
	            return $(this).text();
	        }).get();     
	        console.log(data);
	        var arr_id = data[1]; 
	        $.ajax({
				type: "DELETE",
				url:"/dep/delete",
				contentType: "application/json; charset=utf-8",
				data:JSON.stringify({"id": arr_id}),
				success: function (data) {
				 	getDep();
				}
			});     
		}                 
	});
}

$(document).ready(function updateArrDep(){
	$('#update_arr').click(function(){
		if($("#form_arr_edit").valid()){
			var arr_id=$('#edit_arr_id').val(); 
			var arr_time=$('#edit_arr_time').val(); 
			var arr_from=$('#edit_arr_from').val();
			var arr_flight=$('#edit_arr_flight').val();
			var arr_belt=$('#edit_arr_belt').val().toUpperCase();
			var arr_type=$('#edit_arr_type').val();
			var arr_nature=$('#edit_arr_nature').val();
			var arr_ramark=$('#edit_arr_remark').val();
			
			if(arr_type === "DOM"){
				arr_type = "D"
			} else {
				arr_type = "I"
			}
				
   	 	    $.ajax({
	   	 	    type: "PUT",
	 			url:"/arr/update",
	 			contentType: "application/json; charset=utf-8",
	 			data: JSON.stringify({"id":arr_id, "time":arr_time, "from":arr_from, "flight":arr_flight, "belt":arr_belt, "type":arr_type, "nature":arr_nature, "remark":arr_ramark}),
   	 	 		success: function (data) {
   	  	 	          	getArr();
   	  	 	           	$('#editmodalArr').modal('hide'); 
   	 	     	}
   	 		});  	
		}                
 	});
	
	$('#update_dep').click(function(){
		if($("#form_dep_edit").valid()){
			var dep_id=$('#edit_dep_id').val();
			var dep_time=$('#edit_dep_time').val();
			var dep_via=$('#edit_dep_via').val();
			var dep_to=$('#edit_dep_to').val();
			var dep_flight=$('#edit_dep_flight').val();
			var dep_counter=$('#edit_dep_counter').val().toUpperCase();
			var dep_gate=$('#edit_dep_gate').val().toUpperCase();
			var dep_type=$('#edit_dep_type').val();
			var dep_nature=$('#edit_dep_nature').val();
			var dep_remark=$('#edit_dep_remark').val();
			
			if(dep_type === "DOM"){
				dep_type = "D"
			} else {
				dep_type = "I"
			}
			
   	 	    $.ajax({
	   	 	    type: "PUT",
	 			url:"/dep/update",
	 			contentType: "application/json; charset=utf-8",
	 			data: JSON.stringify({"id":dep_id, "time":dep_time, "via":dep_via, "to":dep_to, "flight":dep_flight, "counter":dep_counter, "gate":dep_gate, "type":dep_type, "nature":dep_nature, "remark":dep_remark}),
   	 	 		success: function (data) {
   	  	 	          	getDep();
   	  	 	           	$('#editmodalDep').modal('hide'); 
   	 	     	}
   	 		});  	
		}            
	});
});


$(document).ready(function uploadFileArrDep() {
    $('#createfileArrbtn').on('click', function () {
    	 /*Method to upload a valid excel file*/
    	  var files = document.getElementById('fileArr').files;
    	  if(files.length==0){
    	    alert("Please choose any file...");
    	    return;
    	  }
    	  var filename = files[0].name;
    	  var extension = filename.substring(filename.lastIndexOf(".")).toUpperCase();
    	  if (extension == '.XLS' || extension == '.XLSX') {
    	      /*Here calling another method to read excel file into json*/
    	      excelFileToJSON(files[0]);
    	  }else{
    	      alert("Please select a valid excel file.");
    	  }
    });
    
    $('#createfileDepbtn').on('click', function () {  
    	  /*Method to upload a valid excel file*/
    	  var files = document.getElementById('fileDep').files;
    	  if(files.length==0){
    	    alert("Please choose any file...");
    	    return;
    	  }
    	  var filename = files[0].name;
    	  var extension = filename.substring(filename.lastIndexOf(".")).toUpperCase();
    	  if (extension == '.XLS' || extension == '.XLSX') {
    	      /*Here calling another method to read excel file into json*/
    	      excelFileToJSON(files[0]);
    	  }else{
    	      alert("Please select a valid excel file.");
    	  }
    });
    
    /*Method to read excel file and convert it into JSON*/ 
    function excelFileToJSON(file){
    	if(isPage == "dep"){
    		try {
                var reader = new FileReader();
                reader.readAsBinaryString(file);
                reader.onload = function(e) {
                    var data = e.target.result;
                    var workbook = XLSX.read(data, {
                        type : 'binary'
                    });
                    var result = {};
                    var firstSheetName = workbook.SheetNames[0];
                    /*reading only first sheet data*/
                    var jsonData = XLSX.utils.sheet_to_json(workbook.Sheets[firstSheetName],{ range: 0 });
                    displayJsonToHtmlTable(jsonData);
                }
            }catch(e){
                console.error(e);
            }
    	} else {
    		try {
                var reader = new FileReader();
                reader.readAsBinaryString(file);
                reader.onload = function(e) {
                    var data = e.target.result;
                    var workbook = XLSX.read(data, {
                        type : 'binary'
                    });
                    var result = {};
                    var firstSheetName = workbook.SheetNames[0];
                    /*reading only first sheet data*/
                    var jsonData = XLSX.utils.sheet_to_json(workbook.Sheets[firstSheetName],{ range: 0 });
                    displayJsonToHtmlTable(jsonData);
                }
            }catch(e){
                console.error(e);
            }
    	}
              
    }
});

/*Method to display the data in HTML Table*/
function displayJsonToHtmlTable(jsonData){
	var natureFild = ["01", "04", "05", "06", "15", "16", "31", "33", "41", "43", "44", "45", "46", "52", "56", "61", "65", "66"];
	var myArray = [];
	var checkCreate = false;
	var timeformat = false;
	if(isPage == "dep"){
		if(jsonData.length>0){
	        for(var i=0;i<jsonData.length;i++){
	        	var row=jsonData[i];
		           
	            var time_create = (row["D. SOBT_LOCAL"] === undefined) ? "" :row["D. SOBT_LOCAL"];
				var via_create = (row["D. VIA"] === undefined) ? "" :row["D. VIA"];
				var to_create = (row["D. DEST(IATA)"] === undefined) ? "" :row["D. DEST(IATA)"];
				var flight_create = (row["D. FLIGHT"] === undefined) ? "" :row["D. FLIGHT"];
				var counter_create = "";
				var gate_create = (row["D. Gate"] === undefined) ? "" :row["D. Gate"];
				var gate2_create = (row["D. Gate2"] === undefined) ? "" :row["D. Gate2"];
				var nature_create =  (row["D. Nature"] === undefined) ? "" :row["D. Nature"];
				var type_create =  (row["D. FLTI"] === undefined) ? "" :row["D. FLTI"];
				var remark_create =  (row["D. REMP"] === undefined) ? "" :row["D. REMP"];
				
				var natureFound = natureFild.find(element => element === nature_create.trim());
				
				if (natureFound){
					var nameAirport = nameApsn.find(name => name.apcthree === via_create.trim());
					if (nameAirport) {
						via_create = nameAirport.apsn;
					} else {
						via_create = via_create;
					}
	
					nameAirport = nameApsn.find(name => name.apcthree === to_create);
					if (nameAirport) {
						to_create = nameAirport.apsn;
					} else {
						to_create = to_create;
					}


					remarkFound = codeRemark.find(element => element.code === remark_create);
					if (remarkFound) {
						remark_create = remarkFound.beme;
					} else {
						remark_create = remark_create;
					}
					
	
	//				if(type_create === "D"){
	//					type_create = "DOM"
	//				} else {
	//					type_create = "INT"
	//				}
	
					if (gate2_create.trim() !== "") {
						gate_create = gate_create.trim() + "," + gate2_create;
					}
					
					if ((via_create !== "") || (to_create !== "") || (flight_create !== "") || (counter_create !== "") || (gate_create !== "") || (nature_create !== "") || (remark_create !== "")) {
						checkCreate = true;
						var newObj = {time: time_create, via: via_create, to: to_create, flight: flight_create, counter: counter_create, gate: gate_create, nature: nature_create, type: type_create, remark: remark_create};
			            myArray.push(newObj);
					}
		        }
	        }
	    } else {
	    	console.log("jsonData.length = 0");
	    }
		
		console.log(myArray);
		
		if (checkCreate){
		    $.ajax({
			    type: "POST",
				url:"/dep/createall",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(myArray),
				success: function (data) {
		 			if(data){
		 				alert("Succeed.");
		 				getDep();
					 	$('#filemodalDep').modal('hide');
					 	$('#fileDep').val("");
		 			} 
		 		}
			}); 
		} else {
			if (timeformat == false) {
				alert("Time format error. (HH:mm/dd)");
			} else {
				alert("Unable to read due to mismatched column headers.");
			}	
		}
	} else {
		if(jsonData.length>0){
	        for(var i=0;i<jsonData.length;i++){
	        	
	            var row=jsonData[i];
	           
	            var time_create = (row["A. SIBT_LOCAL"] === undefined) ? "" :row["A. SIBT_LOCAL"];
				var from_create = (row["A. ORG(IATA)"] === undefined) ? "" :row["A. ORG(IATA)"];
				var flight_create = (row["A. FLIGHT"] === undefined) ? "" :row["A. FLIGHT"];
				var belt_create = (row["A. Belt"] === undefined) ? "" :row["A. Belt"];
				var belt2_create = (row["A. Belt2"] === undefined) ? "" :row["A. Belt2"];
				var nature_create =  (row["A. Nature"] === undefined) ? "" :row["A. Nature"];
				var type_create =  (row["A. FLTI"] === undefined) ? "" :row["A. FLTI"];
				var remark_create =  (row["A. REMP"] === undefined) ? "" :row["A. REMP"];
				
				var natureFound = natureFild.find(element => element === nature_create.trim());
				
				if (natureFound){
				
					var nameAirport = nameApsn.find(name => name.apcthree === from_create);
					if (nameAirport) {
						from_create = nameAirport.apsn;
					} else {
						from_create = from_create;
					}
	
					
					
					remarkFound = codeRemark.find(element => element.code === remark_create);
					if (remarkFound) {
						remark_create = remarkFound.beme;
					} else {
						remark_create = remark_create;
					}
					
	//				if(type_create === "D"){
	//					type_create = "DOM"
	//				} else {
	//					type_create = "INT"
	//				}
		
					if (belt2_create.trim() !== "") {
						belt_create = belt_create.trim() + "," + belt2_create;
					}
					
					if ((from_create !== "") || (flight_create !== "") || (belt_create !== "") || (nature_create !== "") || (remark_create !== "")) {
						checkCreate = true;
						let newObj = {time: time_create, from: from_create, flight: flight_create,belt: belt_create, nature: nature_create, type: type_create, remark: remark_create};
			            myArray.push(newObj);
					}
		        }
	        }
	    } else {
	    	console.log("jsonData.length = 0");
	    }
		
		console.log(myArray);
		
		if (checkCreate){
		    $.ajax({
				type: "POST",
				url:"/arr/createall",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(myArray),
				success: function (data) {
		 			if(data){
		 				alert("Succeed.");
		 				getArr();
					 	$('#filemodalArr').modal('hide');
					 	$('#fileArr').val("");
		 			}
			 	}
			}); 
		} else {
			if (timeformat == false) {
				alert("Time format error. (HH:mm/dd)");
			} else {
				alert("Unable to read due to mismatched column headers.");
			}	
		}
	}
}



$(document).ready(function syncArrDep() {
    $('.sync').on('click', function () {
    	var c = confirm("Do you want to syncScreen?");
		if (c == true) {
			$('.sync').prop('disabled', true );
			$('.sync').text('Syncing');
			
			// สร้าง Object วันที่
			var date = new Date();

			// ปรับเปลี่ยนเวลาให้เป็น ICT
			date.setHours(date.getHours() + 7);

			// ดึงค่าเวลาในรูปแบบ YYYY-MM-DD hh:mm:ss
			var time = date.toISOString().replace("T", " ").replace(/\.\d{3}Z$/, "");

			$.ajax({
				type: "POST",
				url:"/syncScreen",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify({"id":1, "status_sync":"1", "time":time}),
				success: function (data) {
					
					console.log(data);
				}
			});  
		}
    });
});

////////////////////////////////////////////////////////////////////

$(document).ready(function reFreshbtn() {
    $('.refreshbtn').on('click', function () {  
    	isDepFirst=true;
    	isArrFirst=true;
        getArr();
        getDep();
//        $.ajax({
//    	    url: "/syncStatus",
//    	    method: "POST",
//    	    success: function(result) {
//    	    	var jsonObject = JSON.parse(result);
//    	    	if (jsonObject.efid_enforce){
//    	    		$("#statusEfids").removeClass("bg-danger").addClass("bg-success");
//    	    		$("#status").text("Emergency status on");
//    	    	}
//    	    }
//    	});
    });
});

$(document).ready(function prepareFrom() {
	$('.createbtn').on('click', function () {  
	    if(isPage == "dep") {
	    	
	    	$('#datepicker_create_dep_date').datetimepicker({
	            format:'d/m/Y',
	            timepicker:false
	        });
	    	$("#datepicker_create_dep_date").on("change", function() {
	    		  var date = $(this).val();
	    		  var formattedDate = moment(date, "DDMMYYYY").format("DD/MM/YYYY");
	    		  if (formattedDate !== "Invalid date"){
	    			  $(this).val(formattedDate);
	    		  }
	    		  
	    		});
	    	
	    	$('#timepicker_create_dep_time').datetimepicker({
	            format:'H:i',
	            datepicker:false
	        });
	    	
	    	$.ajax({
	    		type: "GET",
	    		url:"/fidsairport/all",
	    		success: function (result) {
	    			const data = JSON.stringify(result);
	    			var obj = jQuery.parseJSON(data);
	    			if(obj != '') {
	    				$("#create_dep_via_list").empty();
	    			      var options = '';
	    			      $.each(obj, function(i, item) {
	    			          if (item["apcthree"] !== null && item["apcthree"].trim() !== ""){
	    			        	  options += '<option value="'+item["apcthree"].trim()+'">'+item["apcthree"].trim()+'</option>';
	    			    	  }
	    			      });
	    			      $("#create_dep_via_list").html(options);
	    			}
	    			
	    			var manu = document.getElementById("create_dep_via");
	    			var manu_list = document.getElementById("create_dep_via_list");
	    			var manu_list_detail = document.getElementById("create_dep_via_detail");
	    			
	    			var text;
	    			for (let option of manu_list.options) {
	      				option.onclick = function() {
	      					manu.value = option.value;
	      					var nameAirport = nameApsn.find(name => name.apcthree === option.value);
	    					if (nameAirport) {
	    						manu_list_detail.value = nameAirport.apsn.trim();
	    					}
	      			    	manu_list.style.display = 'none';
	      			    	manu.focus();
	      			    	manu.blur();
	      			  }
	      			};

	      			function searchAndDisplayOptions() {
	      			  text = manu.value.toUpperCase();
	      			  for (let option of manu_list.options) {
	      			    if (option.value.indexOf(text) > -1) {
	      			    	var found = true;
	      			    	option.style.display = "block";
	      			    } else {
	      			    	option.style.display = "none";
	      			    }
	      			    if (found){
	      			    	manu_list.style.display = 'block';
	      			    } else {
	      			    	manu_list.style.display = 'none';
	      			    }
	      			  }
	      			}
	      			
	      			manu.addEventListener("click", searchAndDisplayOptions);
	      			manu.oninput = searchAndDisplayOptions;
	      			
	      			document.addEventListener("click", function (event) {
	    				if (!manu.contains(event.target)) {
	    					var nameAirport = nameApsn.find(name => name.apcthree === text);
	    					if (nameAirport) {
	    						manu_list_detail.value = nameAirport.apsn.trim();
	    					}
	      			  		manu_list.style.display = 'none';
	    				}
	    			});		
	    		}
	    	});
	    	
	    	$.ajax({
	    		type: "GET",
	    		url:"/fidsairport/all",
	    		success: function (result) {
	    			const data = JSON.stringify(result);
	    			var obj = jQuery.parseJSON(data);
	    			if(obj != '') {
	    				$("#create_dep_to_list").empty();
	    			      var options = '';
	    			      $.each(obj, function(i, item) {
	    			          if (item["apcthree"] !== null && item["apcthree"].trim() !== ""){
	    			        	  options += '<option value="'+item["apcthree"].trim()+'">'+item["apcthree"].trim()+'</option>';
	    			    	  }
	    			      });
	    			      $("#create_dep_to_list").html(options);
	    			}
	    			
	    			var manu = document.getElementById("create_dep_to");
	    			var manu_list = document.getElementById("create_dep_to_list");
	    			var manu_list_detail = document.getElementById("create_dep_to_detail");
	    			
	    			var text;
	    			for (let option of manu_list.options) {
	      				option.onclick = function() {
	      					manu.value = option.value;
	      					var nameAirport = nameApsn.find(name => name.apcthree === option.value);
	    					if (nameAirport) {
	    						manu_list_detail.value = nameAirport.apsn.trim();
	    					}
	      			    	manu_list.style.display = 'none';
	      			    	manu.focus();
	      			    	manu.blur();
	      			  }
	      			};

	      			function searchAndDisplayOptions() {
	      			  text = manu.value.toUpperCase();
	      			  for (let option of manu_list.options) {
	      			    if (option.value.indexOf(text) > -1) {
	      			    	var found = true;
	      			    	option.style.display = "block";
	      			    } else {
	      			    	option.style.display = "none";
	      			    }
	      			    if (found){
	      			    	manu_list.style.display = 'block';
	      			    } else {
	      			    	manu_list.style.display = 'none';
	      			    }
	      			  }
	      			}
	      			
	      			manu.addEventListener("click", searchAndDisplayOptions);
	      			manu.oninput = searchAndDisplayOptions;
	      			
	      			document.addEventListener("click", function (event) {
	    				if (!manu.contains(event.target)) {
	    					var nameAirport = nameApsn.find(name => name.apcthree === text);
	    					if (nameAirport) {
	    						manu_list_detail.value = nameAirport.apsn.trim();
	    					}
	      			  		manu_list.style.display = 'none';
	    				}
	    			});	

	    		}
	    	});
	    	
	    	$.ajax({
		    	type: "GET",
	    		url:"/airline/all",
	    		success: function (result) {
	    			const data = JSON.stringify(result);
	    			var obj = jQuery.parseJSON(data);
	    			if(obj != '') {
	    				$("#create_dep_flight_list").empty();
	    				
	    				  var objSort = [];
	    				  
	    				  $.each(obj, function(i, item) {
	    					  if (item["alc"] === null || item["alc"].trim() === ""){
	    						  objSort.push(item["alc3"].trim());
	    			    	  } else {
	    			    		  objSort.push(item["alc"]);
	    			    	  }
	    				  });
	    				  
	    				  objSort.sort(function(a, b) {
	    					  return a.length - b.length;
	    					});
	    				  
	    			      var options = '';
	    			      $.each(objSort, function(i, item) {
	    			    		  options += '<option value="'+item+'">'+item+'</option>';
	    			      });
	    			      $("#create_dep_flight_list").html(options);
	    			}
	    			
	    			var manu = document.getElementById("create_dep_flight");
	    			var manu_list = document.getElementById("create_dep_flight_list");

	    			for (let option of manu_list.options) {
	      				option.onclick = function() {
	      					manu.value = option.value;
	      			    	manu_list.style.display = 'none';
	      			    	manu.focus();
	      			    	manu.blur();
	      			  }
	      			};

	      			function searchAndDisplayOptions() {
	      			  text = manu.value.toUpperCase();
	      			  for (let option of manu_list.options) {
	      				if (option.value.toUpperCase().indexOf(text) > -1) {
	      			    	var found = true;
	      			    	option.style.display = "block";
	      			    } else {
	      			    	option.style.display = "none";
	      			    }
	      			    if (found){
	      			    	manu_list.style.display = 'block';
	      			    } else {
	      			    	manu_list.style.display = 'none';
	      			    }
	      			  }
	      			}
	      			
	      			manu.addEventListener("click", searchAndDisplayOptions);
	      			manu.oninput = searchAndDisplayOptions;
	      			
	      			document.addEventListener("click", function (event) {
	    				if (!manu.contains(event.target)) {
	      			  		manu_list.style.display = 'none';
	    				}
	    			});	
	
	    		}
	    	});
	    	
	    	$.ajax({
	    		type: "GET",
	    		url:"/fidtab/all",
	    		success: function (result) {
	    			const data = JSON.stringify(result);
	    			var obj = jQuery.parseJSON(data);
	    			if(obj != '') {
	    				$("#create_dep_remark_list").empty();
	    			      var options = '';
	    			      $.each(obj, function(i, item) {
	    			    		  options += '<option value="'+item["beme"].trim()+'">'+item["beme"].trim()+'</option>';
	    			      });
	    			      $("#create_dep_remark_list").html(options);
	    			}
	    			
	    			var manu = document.getElementById("create_dep_remark");
	    			var manu_list = document.getElementById("create_dep_remark_list");
	    			
	    			for (let option of manu_list.options) {
	      				option.onclick = function() {
	      					manu.value = option.value;
	      			    	manu_list.style.display = 'none';
	      			    	manu.focus();
	      			    	manu.blur();
	      			  }
	      			};

	      			function searchAndDisplayOptions() {
	      			  text = manu.value.toUpperCase();
	      			  for (let option of manu_list.options) {
	      				if (option.value.toUpperCase().indexOf(text) > -1) {
	      			    	var found = true;
	      			    	option.style.display = "block";
	      			    } else {
	      			    	option.style.display = "none";
	      			    }
	      			    if (found){
	      			    	manu_list.style.display = 'block';
	      			    } else {
	      			    	manu_list.style.display = 'none';
	      			    }
	      			  }
	      			}
	      			
	      			manu.addEventListener("click", searchAndDisplayOptions);
	      			manu.oninput = searchAndDisplayOptions;
	      			
	      			document.addEventListener("click", function (event) {
	    				if (!manu.contains(event.target)) {
	      			  		manu_list.style.display = 'none';
	    				}
	    			});	
	    		}
	    	});
	
	        $('#createmodalDep').modal('show');
	    } else {
	    
	    	
	    	$('#datepicker_create_arr_date').datetimepicker({
	            format:'d/m/Y',
	            timepicker:false
	        });
	    	$("#datepicker_create_arr_date").on("change", function() {
	    		  var date = $(this).val();
	    		  var formattedDate = moment(date, "DDMMYYYY").format("DD/MM/YYYY");
	    		  if (formattedDate !== "Invalid date"){
	    			  $(this).val(formattedDate);
	    		  }
	    		  
	    		});
	    	
	    	$('#timepicker_create_arr_time').datetimepicker({
	            format:'H:i',
	            datepicker:false
	        });
	    	
	    	$.ajax({
	    		type: "GET",
	    		url:"/fidsairport/all",
	    		success: function (result) {
	    			const data = JSON.stringify(result);
	    			var obj = jQuery.parseJSON(data);
	    			if(obj != '') {
	    				$("#create_arr_from_list").empty();
	    			      var options = '';
	    			      $.each(obj, function(i, item) {
	    			          if (item["apcthree"] !== null && item["apcthree"].trim() !== ""){
	    			        	  options += '<option value="'+item["apcthree"].trim()+'">'+item["apcthree"].trim()+'</option>';
	    			    	  }
	    			      });
	    			      $("#create_arr_from_list").html(options);
	    			}

	    			var manu = document.getElementById("create_arr_from");
	    			var manu_list = document.getElementById("create_arr_from_list");
	    			var manu_list_detail = document.getElementById("create_arr_from_detail");
	    			
	    			var text;
	    			for (let option of manu_list.options) {
	      				option.onclick = function() {
	      					manu.value = option.value;
	      					var nameAirport = nameApsn.find(name => name.apcthree === option.value);
	    					if (nameAirport) {
	    						manu_list_detail.value = nameAirport.apsn.trim();
	    					}
	      			    	manu_list.style.display = 'none';
	      			    	manu.focus();
	      			    	manu.blur();
	      			  }
	      			};

	      			function searchAndDisplayOptions() {
	      			  text = manu.value.toUpperCase();
	      			  for (let option of manu_list.options) {
	      			    if (option.value.indexOf(text) > -1) {
	      			    	var found = true;
	      			    	option.style.display = "block";
	      			    } else {
	      			    	option.style.display = "none";
	      			    }
	      			    if (found){
	      			    	manu_list.style.display = 'block';
	      			    } else {
	      			    	manu_list.style.display = 'none';
	      			    }
	      			  }
	      			}
	      			
	      			manu.addEventListener("click", searchAndDisplayOptions);
	      			manu.oninput = searchAndDisplayOptions;
	      			
	      			document.addEventListener("click", function (event) {
	    				if (!manu.contains(event.target)) {
	    					var nameAirport = nameApsn.find(name => name.apcthree === text);
	    					if (nameAirport) {
	    						manu_list_detail.value = nameAirport.apsn.trim();
	    					}
	      			  		manu_list.style.display = 'none';
	    				}
	    			});		

	    		}
	    	});
	    	
	    	$.ajax({
	    		type: "GET",
	    		url:"/airline/all",
	    		success: function (result) {
	    			const data = JSON.stringify(result);
	    			var obj = jQuery.parseJSON(data);
	    			if(obj != '') {
	    				
	    				$("#create_arr_flight_list").empty();
	    				
	    				  var objSort = [];
	    				  
	    				  $.each(obj, function(i, item) {
	    					  if (item["alc"] === null || item["alc"].trim() === ""){
	    						  objSort.push(item["alc3"].trim());
	    			    	  } else {
	    			    		  objSort.push(item["alc"]);
	    			    	  }
	    				  });
	    				  
	    				  objSort.sort(function(a, b) {
	    					  return a.length - b.length;
	    					});
	    				  
	    			      var options = '';
	    			      $.each(objSort, function(i, item) {
	    			    		  options += '<option value="'+item+'">'+item+'</option>';
	    			      });
	    			      $("#create_arr_flight_list").html(options);
	    			      
	    			      
	    			}
	    			
	    			var manu = document.getElementById("create_arr_flight");
	    			var manu_list = document.getElementById("create_arr_flight_list");
	    			
	    			for (let option of manu_list.options) {
	      				option.onclick = function() {
	      					manu.value = option.value;
	      			    	manu_list.style.display = 'none';
	      			    	manu.focus();
	      			    	manu.blur();
	      			  }
	      			};

	      			function searchAndDisplayOptions() {
	      			  text = manu.value.toUpperCase();
	      			  for (let option of manu_list.options) {
	      				if (option.value.toUpperCase().indexOf(text) > -1) {
	      			    	var found = true;
	      			    	option.style.display = "block";
	      			    } else {
	      			    	option.style.display = "none";
	      			    }
	      			    if (found){
	      			    	manu_list.style.display = 'block';
	      			    } else {
	      			    	manu_list.style.display = 'none';
	      			    }
	      			  }
	      			}
	      			
	      			manu.addEventListener("click", searchAndDisplayOptions);
	      			manu.oninput = searchAndDisplayOptions;
	      			
	      			document.addEventListener("click", function (event) {
	    				if (!manu.contains(event.target)) {
	      			  		manu_list.style.display = 'none';
	    				}
	    			});		

	    		}
	    	});
	    	

	    	$.ajax({
	    		type: "GET",
	    		url:"/fidtab/all",
	    		success: function (result) {
	    			const data = JSON.stringify(result);
	    			var obj = jQuery.parseJSON(data);
	    			if(obj != '') {
	    				$("#create_arr_remark_list").empty();
	    			      var options = '';
	    			      $.each(obj, function(i, item) {
	    			    		  options += '<option value="'+item["beme"].trim()+'">'+item["beme"].trim()+'</option>';
	    			      });
	    			      $("#create_arr_remark_list").html(options);
	    			}
	    			
	    			var manu = document.getElementById("create_arr_remark");
	    			var manu_list = document.getElementById("create_arr_remark_list");
	    			
	    			for (let option of manu_list.options) {
	      				option.onclick = function() {
	      					manu.value = option.value;
	      			    	manu_list.style.display = 'none';
	      			    	manu.focus();
	      			    	manu.blur();
	      			  }
	      			};

	      			function searchAndDisplayOptions() {
	      			  text = manu.value.toUpperCase();
	      			  for (let option of manu_list.options) {
	      				if (option.value.toUpperCase().indexOf(text) > -1) {
	      			    	var found = true;
	      			    	option.style.display = "block";
	      			    } else {
	      			    	option.style.display = "none";
	      			    }
	      			    if (found){
	      			    	manu_list.style.display = 'block';
	      			    } else {
	      			    	manu_list.style.display = 'none';
	      			    }
	      			  }
	      			}
	      			
	      			manu.addEventListener("click", searchAndDisplayOptions);
	      			manu.oninput = searchAndDisplayOptions;
	      			
	      			document.addEventListener("click", function (event) {
	    				if (!manu.contains(event.target)) {
	      			  		manu_list.style.display = 'none';
	    				}
	    			});		

	    		}
	    	});
	    	
	    	$('#createmodalArr').modal('show');
		}
	});
});

$(document).ready(function validateFrom() {
	
	$.validator.addMethod("natureCheck", function(value, element) {
	    if (/^\d{2}$/.test(value)) {
	      return true;
	    }
	    return false;
	}, "Please specify only numbers.");
	
	$.validator.addMethod("numberCheck", function(value, element) {
	    if (/^\d{4}$/.test(value) || /^\d{3}$/.test(value)) {
	      return true;
	    }
	    return false;
	}, "Please specify only numbers.");
	
	$("#form_arr_create").validate({
		errorClass: 'errors',
        rules: {
            create_arr_from_list: {
            	required: true
   	        },
   	        create_arr_flight_list: {
        	    required: true
    	    },
    	    create_arr_flight_number: {
        	    required: true,
        	    numberCheck: true
    	    },
           	 create_arr_nature: {
             	required: true,
             	natureCheck: true
         	},
           	 create_arr_type: {
             	required: true,
         	},
         	 datepicker_create_arr_date: {
              	required: true,
          	},
          	 timepicker_create_arr_time: {
              	required: true,
          	}
        },
        messages: {
            create_arr_from_list:{
                required: "Please specify From."
            },
            create_arr_flight_list:{
                required: "Please specify Flight."
            },
            create_arr_flight_number:{
                required: "Please specify Flight number."
            },
            create_arr_nature:{
                required: "Please specify Nature."
            },
            create_arr_type:{
                required: "Please specify Type."
            },
            datepicker_create_arr_date:{
                required: "Please specify Date."
            },
            timepicker_create_arr_time:{
                required: "Please specify Time."
            }
        }
    });

	$("#form_dep_create").validate({
		errorClass: 'errors',
        rules: {
        	create_dep_to_list: {
        	    required: true
        	    },
        	create_dep_flight_list: {
            	required: true
            	},
            create_dep_flight_number:{
            	required: true,
            	numberCheck: true
                },
       	 	create_dep_nature: {
             	required: true,
             	natureCheck: true
             	},
           	create_dep_type: {
             	required: true,
             	},
            datepicker_create_dep_date: {
                   	required: true,
               	},
            timepicker_create_dep_time: {
                   	required: true,
               	}
        },
        messages: {
            create_dep_to_list:{
                required: "Please specify To."
            },
            create_dep_flight_list:{
                required: "Please specify Flight."
                    },
            create_dep_flight_number:{
                required: "Please specify Flight number."
            		},
            create_dep_nature:{
                required: "Please specify Nature."
            },
            create_dep_type:{
                required: "Please specify Type."
            },
            datepicker_create_dep_date:{
                required: "Please specify Date."
            },
            timepicker_create_dep_time:{
                required: "Please specify Time."
            }
        }
    });
});

$(document).ready(function createArrDep() {
	$('#create_arr').click(function(){
		if($("#form_arr_create").valid()){
			var arr_time=$('#datepicker_create_arr_date').val() + " " + $('#timepicker_create_arr_time').val();
			// แยกวันที่และเวลาออกจากกัน
			var dateTimeParts = arr_time.split(' ');
			// แยกวันที่ออกเป็นส่วนย่อย ๆ
			var dateParts = dateTimeParts[0].split('/');
			var day = dateParts[0];
			var month = dateParts[1];
			var year = dateParts[2];
			// กำหนดรูปแบบใหม่ของวันที่และเวลา
			var arr_time = year + '-' + month + '-' + day + ' ' + dateTimeParts[1];

			var arr_from=$('#create_arr_from_detail').val().toUpperCase();
			var arr_flight=$('#create_arr_flight').val();
			var arr_flight_number=$('#create_arr_flight_number').val();
			var arr_belt=$('#create_arr_belt').val().toUpperCase();
			var arr_nature=$('#create_arr_nature').val();
			var arr_type=$('#create_arr_type').val();
			var arr_remark=$('#create_arr_remark').val();
			arr_flight += (" "+arr_flight_number);
			$.ajax({
				type: "POST",
				url:"/arr/create",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify({"time":arr_time, "from":arr_from, "flight":arr_flight, "belt":arr_belt, "nature":arr_nature, "type":arr_type, "remark":arr_remark}),
				success: function (data) {
					if(data){
						getArr();
					 	$('#createmodalArr').modal('hide');
					 	$('#datepicker_create_arr_date').val("");
					 	$('#timepicker_create_arr_time').val("");
						$('#create_arr_from').val("");
						$('#create_arr_from_detail').val("");
						$('#create_arr_flight').val("");
						$('#create_arr_belt').val("");
						$('#create_arr_nature').val("");
						$('#create_arr_type').val("");
						$('#create_arr_remark').val("");
					} else {
						alert("Duplicate information");
					}	
				}
			});   
		}
	});
	
	$('#create_dep').click(function(){
		if($("#form_dep_create").valid()){
			var dep_time=$('#datepicker_create_dep_date').val() + " " + $('#timepicker_create_dep_time').val();
			var dateTimeParts = dep_time.split(' ');
			var dateParts = dateTimeParts[0].split('/');
			var day = dateParts[0];
			var month = dateParts[1];
			var year = dateParts[2];
			var dep_time = year + '-' + month + '-' + day + ' ' + dateTimeParts[1];
			
			var dep_via=$('#create_dep_via_detail').val().toUpperCase();
			var dep_to=$('#create_dep_to_detail').val().toUpperCase();
			var dep_flight=$('#create_dep_flight').val();
			var dep_flight_number=$('#create_dep_flight_number').val();
			var dep_counter=$('#create_dep_counter').val().toUpperCase();
			var dep_gate=$('#create_dep_gate').val().toUpperCase();
			var dep_nature=$('#create_dep_nature').val();
			var dep_type=$('#create_dep_type').val();
			var dep_remark=$('#create_dep_remark').val();
			dep_flight += (" "+dep_flight_number);
			$.ajax({
				type: "POST",
				url:"/dep/create",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify({"time":dep_time, "via":dep_via, "to":dep_to, "flight":dep_flight, "counter":dep_counter, "gate":dep_gate, "nature":dep_nature, "type":dep_type, "remark":dep_remark}),
				success: function (data) {
					if(data){
						getDep();
					 	$('#createmodalDep').modal('hide');
					 	$('#datepicker_create_dep_date').val("");
					 	$('#timepicker_create_dep_time').val("");
						$('#create_dep_via').val("");
						$('#create_dep_via_detail').val("");
						$('#create_dep_to').val("");
						$('#create_dep_to_detail').val("");
						$('#create_dep_flight').val("");
						$('#create_dep_flight_number').val("");
						$('#create_dep_counter').val("");
						$('#create_dep_gate').val("");
						$('#create_dep_nature').val("");
						$('#create_dep_type').val("");
						$('#create_dep_remark').val("");
					} else {
						alert("Duplicate information");
					}
		 		}
			});  
		} 
	});
});

$(document).ready(function prepareFromFile() {
	$('.filebtn').on('click', function () {  
	    if(isPage == "dep") {
	    	$('#fileDep').val("");
	        $('#filemodalDep').modal('show');
	    } else {
	    	$('#fileArr').val("");
	    	$('#filemodalArr').modal('show');
		}
	});
});

function SyncandStatus(){
	$.ajax({
	    url: "/syncStatus",
	    type: "GET",
	    success: function(result) {
	    	var jsonObject = JSON.parse(result);
	    	$("#last_sync").text("Last sync : " + jsonObject[0].time);
	    	if (jsonObject[0].status_emer === "1"){
	    		$("#statusEfids").removeClass("bg-danger").addClass("bg-success");
	    		$("#status").text("Emergency status On");
	    		$("#last_status").text("Last off : " + jsonObject[0].offlast);
	    	} else {
	    		$("#statusEfids").removeClass("bg-danger").addClass("bg-danger");
	    		$("#status").text("Emergency status Off");
	    		$("#last_status").text("Last on : " + jsonObject[0].onlast);
			}
	    	if (jsonObject[0].status_sync === "1"){
	    		$('.sync').prop('disabled', true );
	    		$('.sync').text('Syncing');
	    	} else {
	    		$('.sync').prop('disabled', false );
	    		$('.sync').text('Sync');
			}
	    }
	});
}

$(document).ready(function selectPage() {
	
	SyncandStatus();
	setInterval(SyncandStatus, 2000);
	
	

	
	
	$('#Arr-tab').on('click', function () {  
		isPage = "arr";
		console.log(isPage);
		if (isArrFirst==true){
			setTimeout(function() {getArr();},100)
			isArrFirst=false;
		}
	});
	$('#Dep-tab').on('click', function () {  
		isPage = "dep";
		console.log(isPage);
		if (isDepFirst==true){
			setTimeout(function() {getDep();},100)
			isDepFirst=false;
		}
	});
});



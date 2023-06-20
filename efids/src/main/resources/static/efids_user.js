
getArr();
var isPage = "arr"
var isDepFirst=true;
var isArrFirst=false;
var arrBefore;
var arrAfter;
var depBefore;
var depAfter;

var page_current_arr = 0;
var page_current_dep = 0;
var tableArr;
var tableDep;
var lengthArr = 10;
var lengthDep = 10;

function getArr(){	
	$.ajax({
		type: "GET",
		url:"/arr/all",
		success: function (result) {
			const data = JSON.stringify(result);
			arrBefore = data;
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
						tr = tr + "</tr>";
						$('#tableArr > tbody:last').append(tr);
					});
					console.log(lengthArr);
					tableArr = $('#tableArr').DataTable({	
						pageLength: lengthArr,
					    "columnDefs": [
					        { "targets": 2, "orderData": 0}
				        ]
					    //"order": [[1, 'ASC']]
					});
					if (tableArr.page.info().pages < (page_current_arr + 1)) {
						page_current_arr = page_current_arr - 1;
					}
					
					tableArr.page(page_current_arr).draw(false);
				}
			},150);
		}
	});
	setTimeout(function() {pageCurrent_arr();},500)
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
			depBefore = data;
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
						tr = tr + "</tr>";
						$('#tableDep > tbody:last').append(tr);
					});
					tableDep = $("#tableDep").DataTable({
						pageLength: lengthDep,
					    "columnDefs": [
					    	{ "targets": 2, "orderData": 0}
				        ]
					    //"order": [[1, 'ASC']]
					});	
					if (tableDep.page.info().pages < (page_current_dep + 1)) {
						page_current_dep = page_current_dep - 1;
					}
					tableDep.page(page_current_dep).draw(false);
				}
			},150);
		}
	});
	setTimeout(function() {pageCurrent_dep();},500)
}










////////////////////////////////////////////////////////////////////










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

function ArrDepRealtime(){
	if (isPage == "dep"){
		$.ajax({
			type: "GET",
			url:"/dep/all",
			success: function (result) {		
				const data = JSON.stringify(result);
				var obj = jQuery.parseJSON(data);
				depAfter = data;
				if (depAfter !== depBefore) {
					depBefore = depAfter;
					getDep();
				}
			}
		});
	}
	if (isPage == "arr"){
		$.ajax({
			type: "GET",
			url:"/arr/all",
			success: function (result) {		
				const data = JSON.stringify(result);
				var obj = jQuery.parseJSON(data);
				arrAfter = data;
				if (arrAfter !== arrBefore) {
					arrBefore = arrAfter;
					getArr();
				}
			}
		});
	}
}

$(document).ready(function runCheck() {
	setInterval(SyncandStatus, 5000);
	setInterval(ArrDepRealtime, 15000);
});


function createExcel(data) {
	  // สร้าง workbook ใหม่
	  var workbook = XLSX.utils.book_new();

	  // ลบคอลัม 'id' จากข้อมูล
	  var modifiedData = data.map(function(item) {
	    // สร้างสำเนาของอ็อบเจ็กต์ item โดยลบคอลัม 'id'
	    var modifiedItem = Object.assign({}, item);
	    delete modifiedItem.id;
	    return modifiedItem;
	  });

	  var worksheet = XLSX.utils.json_to_sheet(modifiedData);

	  // เพิ่ม worksheet เข้าสู่ workbook
	  XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet 1');

	  // บันทึกไฟล์ Excel
	  var wbout = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
	  var blob = new Blob([wbout], { type: 'application/octet-stream' });
	  if (isPage == "arr"){
		  saveAs(blob, 'ARR.xlsx');
	  }
	  if (isPage == "dep"){
		  saveAs(blob, 'DEP.xlsx');
	  }
	  
}

$(document).ready(function saveExcel() {
	$('#excel').on('click', function () { 
		if (isPage == "arr"){
			$.ajax({
				type: "GET",
				url:"/arr/all",
				success: function (result) {
					//if (Array.isArray(result)) {
					    // เรียกฟังก์ชันสร้าง Excel
					  createExcel(result);
					//}
				}
			});
		}
		if (isPage == "dep"){
			$.ajax({
				type: "GET",
				url:"/dep/all",
				success: function (result) {
					//if (Array.isArray(result)) {
					    // เรียกฟังก์ชันสร้าง Excel
					  createExcel(result);
					//}
				}
			});
		}
	});
});

function convert64(imageUrl, callback) {
	  var xhr = new XMLHttpRequest();
	  xhr.onload = function() {
	    var reader = new FileReader();
	    reader.onloadend = function() {
	      callback(reader.result);
	    };
	    reader.readAsDataURL(xhr.response);
	  };
	  xhr.open('GET', imageUrl);
	  xhr.responseType = 'blob';
	  xhr.send();
}

$(document).ready(function savePdf() {
	$('#pdf').on('click', function () { 
		if (isPage == "arr"){
			$.ajax({
				type: "GET",
				url:"/arr/all",
				success: function (result) {
					const data = JSON.stringify(result);
					var obj = jQuery.parseJSON(data);

					// สร้าง Object วันที่
					var date = new Date();

					// ปรับเปลี่ยนเวลาให้เป็น ICT
					date.setHours(date.getHours() + 7);

					// ดึงค่าเวลาในรูปแบบ YYYY-MM-DD hh:mm:ss
					var time = date.toISOString().replace("T", " ").replace(/\.\d{3}Z$/, "");
			
					convert64("/image/aot_logo.jpg", function (logo){
						
						var logoUrl = logo;
						
						// สร้างเอกสาร PDF
						var docDefinition = {
							pageSize: 'A4',
						  	pageOrientation: 'landscape', // เพิ่มคุณสมบัติ pageOrientation
						  	content: [
						  		{
					  		        image: logoUrl,
					  		        alignment: 'left',
					  		        width: 150,
					  		        height: 70
					  		        	
						  		},
						  		{ text: "\n" },
								{
						  			columns: [
								  		{
							  		        text: "Efids : Arrival",
							  		        style: "header",
							  		        alignment: 'left',
								  		} ,
								  		{ 
								  			text: "Export Date : " + time,
								  			alignment: 'right',
								  			bold: true
								  		},
							  		]
								},
								{ text: "\n" },
						  		{ 
									style: 'tableExample',
						  			table: {
							  			widths: [95, '*', 50, 30, 30, 50, '*'], // กำหนดความกว้างของคอลัมน์
							    		headerRows: 1,
							    		body: [
							    			[{text:'SCH', style: 'header'},{text: 'FROM', style: 'header'},{text: 'FLIGHT', style: 'header'},{text: 'BELT', style: 'header'},{text: 'TYPE', style: 'header'},{text: 'NATURE', style: 'header'},{text: 'REMARK', style: 'header'}], // เพิ่มแถวสำหรับหัวตาราง
										    ...obj.map(item => [{text:item.time, fontSize: 10}, {text:item.from, fontSize: 10}, {text:item.flight, fontSize: 10}, {text:item.belt, fontSize: 10}, {text:item.type, fontSize: 10}, {text:item.nature, fontSize: 10}, {text:item.remark, fontSize: 10}])
										  ],

							  		},
									layout: {
										fillColor: function (rowIndex, node, columnIndex) {
											return (rowIndex % 2 === 0) ? '#CCCCCC' : null;
									}
								},
						  		}
						    ],
						    styles: {
						        header: {
						          fontSize: 12,
						          bold: true,
						          alignment: 'center'
						        },
						        date: {
						          alignment: 'right'
						        }
						    },
						    
						    footer: function(currentPage, pageCount) {
								return {
									text: currentPage.toString() + ' of ' + pageCount,
									alignment: 'center'
								};
							}
						    
						};
						  
						// เรียกใช้งาน createPdf และดาวน์โหลดไฟล์ PDF
						pdfMake.createPdf(docDefinition).download('ARR.pdf');
					});
				}
			});
		}
		
		if (isPage == "dep"){
			$.ajax({
				type: "GET",
				url:"/dep/all",
				success: function (result) {

					const data = JSON.stringify(result);
					var obj = jQuery.parseJSON(data);

					// สร้าง Object วันที่
					var date = new Date();

					// ปรับเปลี่ยนเวลาให้เป็น ICT
					date.setHours(date.getHours() + 7);

					// ดึงค่าเวลาในรูปแบบ YYYY-MM-DD hh:mm:ss
					var time = date.toISOString().replace("T", " ").replace(/\.\d{3}Z$/, "");
			
					convert64("/image/aot_logo.jpg", function (logo){
						
						var logoUrl = logo;
						
						// สร้างเอกสาร PDF
						var docDefinition = {
							pageSize: 'A4',
						  	pageOrientation: 'landscape', // เพิ่มคุณสมบัติ pageOrientation
						  	content: [
						  		{
					  		        image: logoUrl,
					  		        alignment: 'left',
					  		        width: 150,
					  		        height: 70
					  		        	
						  		},
						  		{ text: "\n" },
								{
						  			columns: [
								  		{
							  		        text: "Efids : Departure",
							  		        style: "header",
							  		        alignment: 'left',
								  		} ,
								  		{ 
								  			text: "Export Date : " + time,
								  			alignment: 'right',
								  			bold: true
								  		},
							  		]
								},
								{ text: "\n" },
						  		{ 
									style: 'tableExample',
						  			table: {
							  			widths: [95, '*', '*', 50, 60, 30, 30, 50, '*'], // กำหนดความกว้างของคอลัมน์
							    		headerRows: 1,
							    		body: [
											[{text:'SCH', style: 'header'}, {text:'VIA', style: 'header'}, {text:'TO', style: 'header'}, {text:'FLIGHT', style: 'header'}, {text:'COUNTER', style: 'header'}, {text:'GATE', style: 'header'}, {text:'TYPE', style: 'header'}, {text:'NATURE', style: 'header'}, {text:'REMARK', style: 'header'}], // เพิ่มแถวสำหรับหัวตาราง
											    ...obj.map(item => [{text:item.time, fontSize: 10}, {text:item.via, fontSize: 10}, {text:item.to, fontSize: 10}, {text:item.flight, fontSize: 10}, {text:item.counter, fontSize: 10}, {text:item.gate, fontSize: 10}, {text:item.type, fontSize: 10}, {text:item.nature, fontSize: 10}, {text:item.remark, fontSize: 10}])
											  ],

							  		},
									layout: {
										fillColor: function (rowIndex, node, columnIndex) {
											return (rowIndex % 2 === 0) ? '#CCCCCC' : null;
									}
								},
						  		}
						    ],
						    styles: {
						        header: {
						          fontSize: 12,
						          bold: true,
						          alignment: 'center'
						        },
						        date: {
						          alignment: 'right'
						        }
						    },
						    
						    footer: function(currentPage, pageCount) {
								return {
									text: currentPage.toString() + ' of ' + pageCount,
									alignment: 'center'
								};
							}
						    
						};
						  
						// เรียกใช้งาน createPdf และดาวน์โหลดไฟล์ PDF
						pdfMake.createPdf(docDefinition).download('DEP.pdf');
					});
				}
			});
		}
	});
});

function pageCurrent_arr(){
	$( document ).ready(function() {
		$('#tableArr_paginate').on('click', function () {  
			page_current_arr = tableArr.page.info().page;
			console.log(page_current_arr + 1);
		});
	});
	$(document).ready(function() {
		$('#tableArr_length').change(function () {  
			lengthArr = tableArr.page.info().length;
		});
	});
}



function pageCurrent_dep(){
	$(document).ready(function() {
		$('#tableDep_paginate').on('click', function () {  
			page_current_dep = tableDep.page.info().page;
			console.log(page_current_dep + 1);
		});
	});
	$(document).ready(function() {
		$('#tableDep_length').change(function () {  
			lengthDep = tableDep.page.info().length;
		});
	});
}

$(document).ready(function selectPage() {
	SyncandStatus();
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



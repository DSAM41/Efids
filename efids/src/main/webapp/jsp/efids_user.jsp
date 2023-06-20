<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = " efids.efids.model.User_login " %>
<%

    if (session == null || session.getAttribute("user") == null) {
        // Redirect to login page or show error message
        response.sendRedirect("/efids/login");
    } else {
    	User_login user = (User_login)session.getAttribute("user");  
    	if (user.getRule().equals("admin")) {
    		// Redirect to login page or show error message
            response.sendRedirect("/efids/admin");
    	}
    }
%>
<!DOCTYPE html>
<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Efids Page</title>

<!-- CSS -->
<link rel="stylesheet" href="/bootstrap-4.2.1-dist/css/bootstrap.min.css">

<!-- AJAX -->
<script src="/jquery3.6.0/jquery.min.js"></script>
<!-- MODAL -->
<script src="/bootstrap-4.2.1-dist/js/bootstrap.min.js"></script>
<!-- VALIDATE -->
<script src="/jquery-validation-1.19.5/dist/jquery.validate.js"></script>
<!-- DATABLE -->
<link rel="stylesheet" href="/DataTables/DataTables-1.13.4/css/jquery.dataTables.min.css">
<script src="/DataTables/DataTables-1.13.4/js/jquery.dataTables.min.js"></script>
<!-- FILE -->
<script src="/sheetjs-0.17.5/dist/xlsx.full.min.js"></script>
<script src="/efids_user.js"></script>
<link rel="stylesheet" href="/efids_css.css">
<!-- ICON -->
<link rel="stylesheet" href="/fontawesome-free-6.3.0-web/css/all.css" >
<!-- DATETIME -->
<link rel="stylesheet" type="text/css" href="/datetimepicker-master/build/jquery.datetimepicker.min.css"/>
<script src="/datetimepicker-master/build/jquery.datetimepicker.full.min.js"></script>
<!-- AUTOFORMATDATE -->
<script src="/moment/moment.min.js"></script>


<script src="/FileSaver.js"></script>
<script src="/pdfmake-master/build/pdfmake.min.js"></script>
<script src="/pdfmake-master/build/vfs_fonts.js"></script>


<style>
.errors {
	color: red;
}
</style>

</head>

<body>
	<!-- body (Bootstrap MODAL) -->
	<div class="container-fluid">
	<div class="row mt-4 mr-2 ml-1">
		<div class="col pt-4 pb-4">
			<img src="/image/aot_logo.jpg" alt="Logo" width="280" height="120">
		</div>
		<div class="col text-right pt-1">
			<a class="mr-auto" href="../user/logout">
				<i class="fa fa-sign-out" style="font-size: 24px"></i>
			</a>
		</div>
	</div>
	    <ul class="nav nav-tabs align-items-end" id="myTab" role="tablist">
		  <li class="nav-item">
		    <a class="nav-link active" id="Arr-tab" data-toggle="tab" href="#Arr" role="tab" aria-controls="Arr" aria-selected="true"><i class="fa-solid fa-plane-arrival"></i> Arrival</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link" id="Dep-tab" data-toggle="tab" href="#Dep" role="tab" aria-controls="Dep" aria-selected="false"><i class="fas fa-plane-departure"></i> Departure</a>
		  </li>
		  <li class="nav-item ml-auto d-flex">
		    <div class="justify-content-end">
				<div class="d-flex align-items-center">
				    <div id="statusEfids" class="bg-danger rounded-circle" style="width: 30px; height: 30px;"></div>
				    <div id="status" class="ml-1 ">Emergency status</div>
				</div>	
				<div>
				    <div id="last_status" class="ml-1">Last status</div>
				</div>	
				<div>
				    <div id="last_sync" class="ml-1">Last sync</div>
				</div>	
			</div>
		  </li>
		</ul>
		
		<div class="tab-content" id="myTabContent">
		  <div class="tab-pane fade show active" id="Arr" role="tabpanel" aria-labelledby="Arr-tab">
		  <div class="text-right my-2"></div>
		  	<table id="tableArr" class="table table-hover">
				<thead>
					<tr>
						<th class="d-none">Timefull</th>
						<th class="d-none">Id</th>
						<th>Time</th>
						<th>From</th>
						<th>Flight</th>
						<th>Belt</th>
						<th>Flight Type</th>
						<th>Nature</th>
						<th>Ramark</th>
					</tr>
				</thead>
				
				<tbody id="myBody1">	
				</tbody>
				
			</table>
		  </div>
		  
		  <div class="tab-pane fade" id="Dep" role="tabpanel" aria-labelledby="Dep-tab">
		  <div class="text-right my-2"></div>   
			<table id="tableDep" class="table table-hover">
				<thead>
					<tr>
						<th class="d-none">Timefull</th>
						<th class="d-none">Id</th>
						<th>Time</th>
						<th>Via</th>
						<th>To</th>
						<th>Flight</th>
						<th>Counter</th>
						<th>Gate</th>
						<th>Flight Type</th>
						<th>Nature</th>
						<th>Ramark</th>
					</tr>
				</thead>
				
				<tbody id="myBody2">	
				</tbody>

			</table>
		  </div>
		  <div  class="text-right my-2">
					<button id="excel" type="button" class="btn btn-outline-dark mr-auto excel"><i class="fa-solid fa-file-excel"></i> Excel</button>
					<button id="pdf" type="button" class="btn btn-outline-dark mr-auto pdf"><i class="fa-solid fa-file-pdf"></i> PDF</button>
		  </div>
		  <hr>
		</div>
	</div>

	
	<!-- CreatemodalArr (Bootstrap MODAL) -->
    <div class="modal fade" id="createmodalArr" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><i class="fa-solid fa-plane-arrival"></i>  New Arrival </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <form id="form_arr_create" name="form_arr_create" autocomplete="off">

                    <div class="modal-body">
                    
                    	<div class="form-group">
						    <div class="row">
						    	<div class="col">
						    		<label> Date </label>
							    	<input type="text" name="datepicker_create_arr_date" id="datepicker_create_arr_date" class="form-control" placeholder="dd/mm/yyyy">
							    </div>
							    <div class="col">
							    	<label> Time </label>
							    	<input type="text" name="timepicker_create_arr_time" id="timepicker_create_arr_time"class="form-control" placeholder="--:--">
							    </div>
						    </div>
						</div>
						<div class="form-group">
                            <label> From </label>
                            <div class="row">
	                            <div class="col">
									<input type="text" name="create_arr_from_list" id="create_arr_from" class="form-control" placeholder="Enter From" style="text-transform:uppercase" maxlength="3">
		                            <datalist id="create_arr_from_list">
		                            </datalist>
		                        </div>
		                        <div class="col">
		                        	<input type="text" name="create_arr_from_detail" id="create_arr_from_detail" class="form-control" style="text-transform:uppercase">
		                        </div>
                            </div>
                        </div>

						<div class="form-group">
                            <label> Flight </label>
                             <div class="row">
						      <div class="col">
						      	<input type="text" name="create_arr_flight_list" id="create_arr_flight" class="form-control" placeholder="Enter Flight" maxlength="3" style="text-transform:uppercase">
	                            <datalist id="create_arr_flight_list"></datalist>
						      </div>
						      <div class="col">
						        <input type="text" name="create_arr_flight_number" id="create_arr_flight_number" class="form-control" placeholder="Enter Flight number" minlength="3" maxlength="4" >
						      </div>
						    </div>
                        </div>

                        <div class="form-group">
                            <label> Belt ( Ex. XX or XX,XX ) </label>
                            <input type="text" name="create_arr_belt" id="create_arr_belt" class="form-control" placeholder="Enter Belt" style="text-transform:uppercase">
                        </div>
                        
                        <div class="form-group">
                            <label> DOM/INT </label>
<!--                             <input type="text" name="create_arr_type" id="create_arr_type" class="form-control" placeholder="Enter Type"> -->
                            <select class="form-control" id="create_arr_type" name="create_arr_type" >
						      <option value=""></option>
						      <option value="I">INT</option>
						      <option value="D">DOM</option>
						      <option value="I">MIX</option>
						    </select>
                        </div>
                        
                        <div class="form-group">
                            <label> Nature </label>
                            <input type="text" name="create_arr_nature" id="create_arr_nature" class="form-control" placeholder="Enter Nature" minlength="2" maxlength="2">
                        </div>
                        
                        <div class="form-group">
                            <label> Remark </label>
                            <input type="text" name="create_arr_remark_list" id="create_arr_remark" class="form-control" placeholder="Enter Remark">
                            <datalist id="create_arr_remark_list"></datalist>
                        </div>

                    </div>
                    <div class="modal-footer">
                    	<button type="button" id="create_arr" name="createdata" class="btn btn-primary"><i class="fas fa-save"></i> Save</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-times"></i> Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- CreatemodalDep (Bootstrap MODAL) -->
    <div class="modal fade" id="createmodalDep" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><i class="fas fa-plane-departure"></i> New Departure </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <form id="form_dep_create" name="form_dep_create" autocomplete="off">

                    <div class="modal-body">
 	
						<div class="form-group">
						    <div class="row">
						    	<div class="col">
						    		<label> Date </label>
							    	<input type="text" name="datepicker_create_dep_date" id="datepicker_create_dep_date" class="form-control" placeholder="dd/mm/yyyy">
							    </div>
							    <div class="col">
							    	<label> Time </label>
							    	<input type="text" name="timepicker_create_dep_time" id="timepicker_create_dep_time"class="form-control" placeholder="--:--">
							    </div>
						    </div>
						</div>
						
						<div class="form-group">
                            <label> Via </label>
                            <div class="row">
                            	<div class="col">
		                            <input type="text" name="create_dep_via_list" id=create_dep_via class="form-control" placeholder="Enter Via" style="text-transform:uppercase" maxlength="3">
		                        	<datalist id="create_dep_via_list"></datalist>
		                        </div>
		                        <div class="col">
		                        	<input type="text" name="create_dep_via_detail" id="create_dep_via_detail" class="form-control" style="text-transform:uppercase">
		                        </div>
	                        </div>
                        </div>
                        
                        <div class="form-group">
                        	<label> To </label>
	                        <div class="row">
	                            <div class="col">
		                            <input type="text" name="create_dep_to_list" id="create_dep_to" class="form-control" placeholder="Enter To" style="text-transform:uppercase" maxlength="3">
		                        	<datalist id="create_dep_to_list"></datalist>
	                        	</div>
	                        	<div class="col">
	                        		<input type="text" name="create_dep_to_detail" id="create_dep_to_detail" class="form-control" style="text-transform:uppercase">
	                        	</div>
	                        </div>
                        </div>
                                 
                        <div class="form-group">
                            <label> Flight </label>
                             <div class="row">
						      <div class="col">
						      	<input type="text" name="create_dep_flight_list" id="create_dep_flight" class="form-control" placeholder="Enter Flight" maxlength="3" style="text-transform:uppercase">
	                            <datalist id="create_dep_flight_list"></datalist>
						      </div>
						      <div class="col">
						        <input type="text" name="create_dep_flight_number" id="create_dep_flight_number" class="form-control" placeholder="Enter Flight number" minlength="3" maxlength="4" >
						      </div>
						    </div>
                        </div>
                        
                        <div class="form-group">
                            <label> Counter ( Ex. X , XX or XXX ) </label>
                            <input type="text" name="create_dep_counter" id="create_dep_counter" class="form-control" placeholder="Enter Counter"  style="text-transform:uppercase">
                        </div>
                        
                        <div class="form-group">
                            <label> Gate ( Ex. X1 , X1X or X1,X2 ) </label>
                            <input type="text" name="create_dep_gate" id="create_dep_gate" class="form-control" placeholder="Enter Gate"  style="text-transform:uppercase">
                        </div>
                        
                        <div class="form-group">
                            <label> DOM/INT </label>
                            <select class="form-control" id="create_dep_type" name="create_dep_type">
						      <option value=""></option>
						      <option value="I">INT</option>
						      <option value="D">DOM</option>
						      <option value="I">MIX</option>
						    </select>
                        </div>
                        
                        <div class="form-group">
                            <label> Nature </label>
                            <input type="text" name="create_dep_nature" id="create_dep_nature" class="form-control" placeholder="Enter Nature" minlength="2" maxlength="2">
                        </div>
                        
                        
                        <div class="form-group">
                            <label> Remark </label>
                            <input type="text" name="create_dep_remark_list" id="create_dep_remark" class="form-control" placeholder="Enter Remark">
                        	<datalist id="create_dep_remark_list"></datalist>
                        </div>

				

                    </div>
                    <div class="modal-footer">
                    	<button type="button" id="create_dep" name="createdata" class="btn btn-primary"><i class="fas fa-save"></i> Save</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-times"></i> Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
       
    <!-- EditmodalArr (Bootstrap MODAL) -->
    <div class="modal fade" id="editmodalArr" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
            <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><i class="fa-solid fa-plane-arrival"></i> Edit Arrival </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <form id="form_arr_edit" name="form_arr_edit" autocomplete="off">
                
                	<input type="hidden" name="edit_arr_id" id="edit_arr_id">

                    <div class="modal-body">
                    
						<div class="form-group">
						    <label> Time </label>
<!-- 						    <input type="text" name="edit_arr_time" id="edit_arr_time" class="form-control" disabled="disabled"> -->
						    <input type="datetime-local" name="edit_arr_time" id="edit_arr_time" class="form-control" disabled="disabled">
						</div>

						<div class="form-group">
                            <label> From </label>
                            <input type="text" name="edit_arr_from" id="edit_arr_from" class="form-control" placeholder="Enter From" disabled="disabled">
                        </div>

						<div class="form-group">
                            <label> Flight </label>
                            <input type="text" name="edit_arr_flight" id="edit_arr_flight" class="form-control" placeholder="Enter Flight" disabled="disabled">
                        </div>

                        <div class="form-group">
                            <label> Belt (Ex. XX or XX,XX) </label>
                            <input type="text" name="edit_arr_belt" id="edit_arr_belt" class="form-control" placeholder="Enter Belt" style="text-transform:uppercase">
                        </div>
                        
                        <div class="form-group">
                            <label> Flight Type </label>
                            <input type="text" name="edit_arr_type" id="edit_arr_type" class="form-control" disabled="disabled">
                        </div>
                        
                        <div class="form-group">
                            <label> Nature </label>
                            <input type="text" name="edit_arr_nature" id="edit_arr_nature" class="form-control" placeholder="Enter Nature" disabled="disabled">
                        </div>
                        
                        <div class="form-group">
                            <label> Remark </label>
                            <input type="text" name="edit_arr_remark_list" id="edit_arr_remark" class="form-control" placeholder="Enter Remark">
                        	<datalist id="edit_arr_remark_list"></datalist>
                        </div>
                        

                    </div>
                    <div class="modal-footer">
                    	<button type="button" id="update_arr" name="update_arr" class="btn btn-primary"><i class="fas fa-save"></i> Save</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-times"></i> Cancel</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    
    <!-- EditmodalDep (Bootstrap MODAL) -->
    <div class="modal fade" id="editmodalDep" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><i class="fas fa-plane-departure"></i> Edit Departure </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <form id="form_dep_edit" name="form_dep_edit" autocomplete="off">

                    <div class="modal-body">
                    	<br>
						<input type="hidden" name="edit_dep_id" id="edit_dep_id">
						
						<div class="form-group">
						    <label> Time </label>
						    <input type="datetime-local" name="edit_dep_time" id="edit_dep_time" class="form-control" disabled="disabled">
						</div>
						
						<div class="form-group">
                            <label> Via </label>
                            <input type="text" name="edit_dep_via" id="edit_dep_via" class="form-control" disabled="disabled">
                        </div>
                        
                        <div class="form-group">
                            <label> To </label>
                            <input type="text" name="edit_dep_to" id="edit_dep_to" class="form-control" placeholder="Enter To" disabled="disabled">
                        </div>
                        
                        <div class="form-group">
                            <label> Flight </label>
                            <input type="text" name="edit_dep_flight" id="edit_dep_flight" class="form-control" placeholder="Enter Flight" disabled="disabled">
                        </div>
                        
                        <div class="form-group">
                            <label> Counter (Ex. X , XX or XXX) </label>
                            <input type="text" name="edit_dep_counter" id="edit_dep_counter" class="form-control" placeholder="Enter Counter" style="text-transform:uppercase">
                        </div>
                        
                        <div class="form-group">
                            <label> Gate (Ex. X1 , X1X or X1,X2) </label>
                            <input type="text" name="edit_dep_gate" id="edit_dep_gate" class="form-control" placeholder="Enter Gate" style="text-transform:uppercase">
                        </div>
                        
                        <div class="form-group">
                            <label> Flight Type </label>
                            <input type="text" name="edit_dep_type" id="edit_dep_type" class="form-control" disabled="disabled">
                        </div>
                        
                        <div class="form-group">
                            <label> Nature </label>
                            <input type="text" name="edit_dep_nature" id="edit_dep_nature" class="form-control" placeholder="Enter Nature" disabled="disabled">
                        </div>
                        
                        <div class="form-group">
                            <label> Remark </label>
                            <input type="text" name="edit_dep_remark_list" id="edit_dep_remark" class="form-control" placeholder="Enter Remark">
                        	<datalist id="edit_dep_remark_list"></datalist>
                        </div>
                    </div>
                    
                    <div class="modal-footer">
                    	<button type="button" id="update_dep" name="update_dep" class="btn btn-primary"><i class="fas fa-save"></i> Save</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-times"></i> Cancel</button>
                    </div>
                    
                </form>

            </div>
        </div>
    </div>
    
    
        <!-- FilemodalArr (Bootstrap MODAL) -->
    <div class="modal fade" id="filemodalArr" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><i class="fa-solid fa-plane-arrival"></i> Upload File Arrival </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <form id="form_create_file_arr" name="form_create_file_arr" autocomplete="off">

                    <div class="modal-body">
                    	<br>
						<div class="form-group row">
						<div class="col-sm-3"></div>
						<!-- <label class="col-sm-2 col-form-label" for="exampleFormControlFile1">File</label> -->
						    <input type="file" class="col-sm-9 form-control-file" id="fileArr">
						</div>

                    </div>
                    <div class="modal-footer">
                    	<button type="button" id="createfileArrbtn" name="createfileArrbtn" class="btn btn-primary"><i class="fas fa-file-upload"></i> Upload</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-times"></i> Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- FilemodalDep (Bootstrap MODAL) -->
    <div class="modal fade" id="filemodalDep" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><i class="fas fa-plane-departure"></i> Upload File Departure </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <form id="form_create" name="form_create" autocomplete="off">

                    <div class="modal-body">
                    	<br>
						<div class="form-group row">
							<!-- <div class="col-sm-1"></div> -->
							<div class="col-sm-3"></div>
							<!-- <label class="col-sm-2 col-form-label" for="exampleFormControlFile1">File</label> -->
						    <input type="file" class="col-sm-9 form-control-file" id="fileDep">
						</div>

                    </div>
                    <div class="modal-footer">
                    	<button type="button" id="createfileDepbtn" name="createfileDepbtn" class="btn btn-primary"><i class="fas fa-file-upload"></i> Upload</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-times"></i> Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

<!-- CSS -->
<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"> -->
<!-- AJAX -->
<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> -->
<!-- DATABLE -->
<!-- <link rel="stylesheet" href="//cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css"> -->
<!-- <script src="//cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script> -->
<!-- MODAL -->
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script> -->
<!-- FILE -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.5/xlsx.min.js"></script> -->
<!-- <script async  src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.5/xlsx.min.js"></script> -->
<!-- VALIDATE -->
<!-- <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.19.2/jquery.validate.min.js"></script> -->
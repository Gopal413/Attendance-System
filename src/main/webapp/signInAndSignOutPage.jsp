<%@ page import="java.io.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!------ Include the above in your HEAD tag ---------->
<html>
<head>

<!-- Bootstrap 5 CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
<!-- Data Table CSS -->
<link rel='stylesheet'
	href='https://cdn.datatables.net/1.13.5/css/dataTables.bootstrap5.min.css'>
<!-- Font Awesome CSS -->
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css'>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src='https://code.jquery.com/jquery-3.7.0.js'></script>
<!-- Data Table JS -->
<script
	src='https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js'></script>
<script
	src='https://cdn.datatables.net/responsive/2.1.0/js/dataTables.responsive.min.js'></script>
<script
	src='https://cdn.datatables.net/1.13.5/js/dataTables.bootstrap5.min.js'></script>

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<style type="text/css">
.note {
	text-align: center;
	height: 80px;
	background: -webkit-linear-gradient(left, #0072ff, #8811c5);
	color: #fff;
	font-weight: bold;
	line-height: 80px;
}

.form-content {
	padding: 5%;
	border: 1px solid #ced4da;
	margin-bottom: 2%;
}

.form-control {
	border-radius: 1.5rem;
}

.btnSubmit {
	border: none;
	border-radius: 1.5rem;
	padding: 1%;
	width: 20%;
	cursor: pointer;
	background: #0062cc;
	color: #fff;
}
</style>
</head>
<body>
	<form action="/signOut" id="signInSignOutFormId"
		name="signInSignOutForm" method="post">
		<div class="container register-form">
			<div class="form">
				<div class="note">
					<p>Login Page</p>
				</div>

				<div class="form-content">
					<div class="row">
						<div class="col-md-3"></div>
						<div class="col-md-6">
							<div class="form-group">
								<h2>
									<c:out value="${currentDate}"></c:out>
								</h2>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-6">
							<c:if test="${status!='I'}">
								<button type="submit"
									onclick="document.getElementById('signInSignOutFormId').action='/signIn'"
									class="btnSubmit">Sign In</button>
							</c:if>
							<c:if test="${status!='O' }">
								<button type="submit"
									onclick="document.getElementById('signInSignOutFormId').action='/signOut'"
									class="btnSubmit">Sign Out</button> 
									<button type="submit"
									onclick="document.getElementById('signInSignOutFormId').action='/loginPage'"
									class="btnSubmit">Login Out</button>
							</c:if>
						
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-6">
							<button type="submit"
								onclick="document.getElementById('signInSignOutFormId').action='/viewReport'"
								class="btnSubmit">View Report</button>
						</div>
					</div>
					<c:if test="${ report!=null}">
						<div class="row">
							<div class="col-md-12">

								<table id="example" class="table"
									style="width: 100%">
									<thead>
										<tr>
											<th>Report</th>
											
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${report}" var="att">
											<tr>
												<td>${att.attDate }<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Sign In &nbsp;&nbsp; : ${att.signIn }<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Sign Out : ${att.signOut }</td>
											</tr>

										</c:forEach>
									</tbody>
								</table>

							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#example')
									.DataTable(
											{
												//disable sorting on last column
												"columnDefs" : [ {
													"orderable" : false
												} ],
												language : {
													//customize pagination prev and next buttons: use arrows instead of words
													'paginate' : {
														'previous' : '<span class="fa fa-chevron-left"></span>',
														'next' : '<span class="fa fa-chevron-right"></span>'
													},
													//customize number of elements to be displayed
													"lengthMenu" : 'Display <select class="form-control input-sm">'
															+ '<option value="10">10</option>'
															+ '<option value="20">20</option>'
															+ '<option value="30">30</option>'
															+ '<option value="40">40</option>'
															+ '<option value="50">50</option>'
															+ '<option value="-1">All</option>'
															+ '</select> results'
												}
											})
						});
	</script>
	<!-- jQuery -->

</body>
</html>
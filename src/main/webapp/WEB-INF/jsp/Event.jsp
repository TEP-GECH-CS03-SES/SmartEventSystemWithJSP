<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%
session = request.getSession();
System.out.println(session.getAttribute("adminUser"));
if (session.getAttribute("adminUser").equals("admin")) {

} else {
	response.sendRedirect("/logout");
}
%>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="../assets/images/logo.ico" type="image/ico" />
<title>${event.getEvent_name()}</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<!-- Bootstrap -->
<link href="../assets/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="../assets/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="../assets/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="../assets/vendors/iCheck/skins/flat/green.css" rel="stylesheet">

<!-- bootstrap-progressbar -->
<link
	href="../assets/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- JQVMap -->
<link href="../assets/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet" />
<!-- bootstrap-daterangepicker -->
<link
	href="../assets/vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="../assets/build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="AdminHome" class="site_title"><span>Smart
								Event System</span></a>
					</div>

					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile clearfix">
						<div class="profile_info">
							<span>Welcome,</span>
							<h2><%=session.getAttribute("adminUser")%>
							</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<ul class="nav side-menu">
								<li><a href="../AdminHome"><i class="fa fa-home"></i> Home
								</a></li>
								<li><a href="../eventDetail"><i class="fa fa-download"></i>
										Event Details </a></li>

							</ul>
						</div>
					</div>
					<!-- /sidebar menu -->
				</div>
			</div>
			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<div class="nav toggle">
						<a id="menu_toggle"><i class="fa fa-bars"></i></a>
					</div>
					<nav class="nav navbar-nav">
						<ul class=" navbar-right">
							<li class="nav-item open" style="padding-left: 15px;"><a
								class="dropdown-item" href="../logout"><i
									class="fa fa-sign-out"></i> Log Out</a></li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<!-- top tiles -->
				<div class="row">
					<div class="col-md-4">
						<button class="btn btn-round btn-warning clf"
							data-toggle="collapse" data-target="#collapseOne"
							onclick="showu()">
							<i class="fa fa-folder-o pr-2" aria-hidden="true"></i>Update
							Event
						</button>
						<!-- /col-md-4 end -->
					</div>
					<div class="col-md-4">
						<a href="delete/${event.getId()}" class="btn btn-round btn-danger clf"> <i class="fa fa-folder-o pr-2"></i>
										Delete Event</a>
						<!-- /col-md-4 end -->
					</div>
					<!-- /row end -->
				</div>
				<div class="row">
				<div class="col-md-12">
				<div class="collapse x_content" id="collapseOne"
							style="display: none;">
							<!--Panel-->
							<div class="card card-body ml-1"
								style="background: none; width: auto;">
								<h4 class="card-title">Update Event</h4>
								<form:form method="GET" action="../updateEvent/${event.getId() }"
									modelAttribute="event" class="form-label-left input_mask">
									<div class="col-md-6 col-sm-6  form-group has-feedback">
										<label for="event_name"><form:label path="event_name">Event  Name</form:label></label>
										<form:input id="event_name" path="event_name"
											class="form-control" type="text" value="${event.getEvent_name()}" required="true"
											autofocus="true" />
										<div class="invalid-feedback">
											<form:errors path="event_name" class="help-inline" />
											Event Name is invalid
										</div>
									</div>
									<div class="col-md-6 col-sm-6  form-group has-feedback">
										<label for="event_type"><form:label path="event_type">Event Type
										</form:label> </label>
<%-- 										${event.getEvent_type()} --%>
										<form:select class="form-control" path="event_type"
											id="ddlModels" onchange="EnableDisableTextBox(this)">
											<form:option value="Seminar" label="Seminar" />
											<form:option value="Paper Presentation"
												label="Paper Presentation" />
											<form:option value="BirthDay" label="BirthDay" />
											<form:option value="Marriage" label="Marriage" />
											<form:option value="Other" label="Other" />
										</form:select>
										<div class="invalid-feedback">
											<form:errors path="event_type" class="help-inline" />
											Event type is required
										</div>
									</div>
									<div class="col-md-6 col-sm-6  form-group has-feedback">
										<label for="event_start_date"><form:label
												path="event_start_date">Event Start Date
										</form:label> </label>
										<form:input id="event_start_date" path="event_start_date"
											class="form-control" type="date" name="eventstartdate"
											value="${event.getEvent_start_date()}" required="true" data-eye="true" />
										<div class="invalid-feedback">
											<form:errors path="event_start_date" class="help-inline" />
											Event Start Date is required
										</div>
									</div>
									<div class="col-md-6 col-sm-6  form-group has-feedback">
										<label for="event_end_date"><form:label
												path="event_end_date">Event End Date
										</form:label> </label>
										<form:input id="event_end_date" path="event_end_date"
											class="form-control" type="date" name="eventenddate" value="${event.getEvent_end_date()}"
											required="true" data-eye="true" />
										<div class="invalid-feedback">
											<form:errors path="event_end_date" class="help-inline" />
											Event End Date is required
										</div>
									</div>
									<div class="col-md-6 col-sm-6  form-group has-feedback">
										<label for="event_start_time"><form:label
												path="event_start_time">
												Event Start Time
										</form:label> </label>
										<form:input id="event_start_time" path="event_start_time"
											class="form-control" type="time" name="eventstarttime"
											value="${event.getEvent_start_time()}" required="true" data-eye="true" />
										<div class="invalid-feedback">
											<form:errors path="event_start_time" class="help-inline" />
											Event Start Time is required
										</div>
									</div>
									<div class="col-md-6 col-sm-6  form-group has-feedback">
										<label for="event_end_time"><form:label
												path="event_end_time">Event End Time
										</form:label> </label>
										<form:input id="event_end_time" path="event_end_time"
											class="form-control" type="time" name="eventendtime" value="${event.getEvent_end_time()}"
											required="true" data-eye="true" />
										<div class="invalid-feedback">
											<form:errors path="event_end_time" class="help-inline" />
											Event End Time is required
										</div>
									</div>
									<div class="col-md-6 col-sm-6  form-group has-feedback">
										<label for="participents_count"><form:label
												path="participents_count">Participants Count
										</form:label> </label>
										<form:input id="participents_count" path="participents_count"
											class="form-control" type="int" name="participentscount"
											value="${event.getParticipents_count()}" required="true" data-eye="true" />
										<div class="invalid-feedback">
											<form:errors path="participents_count" cssClass="error"
												class="help-inline" />
											Participants Count is required
										</div>
									</div>
									<div class="col-md-6 col-sm-6  form-group has-feedback">
										<label for="event_loacation"><form:label
												path="event_loacation">Location
										</form:label> </label>
										<form:input id="event_loacation" path="event_loacation"
											class="form-control" type="text" name="event_loacation"
											value="${event.getEvent_loacation()}" required="true" data-eye="true" />
										<div class="invalid-feedback">
											<form:errors path="event_loacation" cssClass="error"
												class="help-inline" />
											Location is required
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-6 col-sm-6  offset-md-6">
											<form:button class="btn btn-primary btn-block">
                                  Update Event
                                  </form:button>
										</div>
									</div>

								</form:form>
							</div>

						</div>
				
				</div>
				</div>
				<!-- /top tiles -->
			</div>
			<!-- /page content -->

		</div>
	</div>

	<!-- jQuery -->
	<script src="../assets/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="../assets/vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<!-- FastClick -->
	<script src="../assets/vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="../assets/vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script src="../assets/vendors/Chart.js/dist/Chart.min.js"></script>
	<!-- gauge.js -->
	<script src="../assets/vendors/gauge.js/dist/gauge.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="../assets/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- iCheck -->
	<script src="../assets/vendors/iCheck/icheck.min.js"></script>
	<!-- Skycons -->
	<script src="../assets/vendors/skycons/skycons.js"></script>
	<!-- Flot -->
	<script src="../assets/vendors/Flot/jquery.flot.js"></script>
	<script src="../assets/vendors/Flot/jquery.flot.pie.js"></script>
	<script src="../assets/vendors/Flot/jquery.flot.time.js"></script>
	<script src="../assets/vendors/Flot/jquery.flot.stack.js"></script>
	<script src="../assets/vendors/Flot/jquery.flot.resize.js"></script>
	<!-- Flot plugins -->
	<script src="../assets/vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="../assets/vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
	<script src="../assets/vendors/flot.curvedlines/curvedLines.js"></script>
	<!-- DateJS -->
	<script src="../assets/vendors/DateJS/build/date.js"></script>
	<!-- JQVMap -->
	<script src="../assets/vendors/jqvmap/dist/jquery.vmap.js"></script>
	<script src="../assets/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
	<script
		src="../assets/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="../assets/vendors/moment/min/moment.min.js"></script>
	<script
		src="../assets/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="../assets/build/js/custom.min.js"></script>

</body>
<script type="text/javascript" charset="utf-8">
function showu() {
	var x = document.getElementById("collapseOne");
	if (x.style.display === "none") {
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
	var y = document.getElementById("collapseTwo");
	y.style.display = "none";
}
function showd() {
	var x = document.getElementById("collapseTwo");
	if (x.style.display === "none") {
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
	var y = document.getElementById("collapseOne");
	y.style.display = "none";
}
</script>
</html>

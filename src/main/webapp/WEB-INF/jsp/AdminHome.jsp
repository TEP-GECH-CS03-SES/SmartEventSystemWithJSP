<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<link rel="icon" href="assets/images/logo.ico" type="image/ico" />
<title>Admin</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<!-- Bootstrap -->
<link href="assets/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="assets/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="assets/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="assets/vendors/iCheck/skins/flat/green.css" rel="stylesheet">

<!-- bootstrap-progressbar -->
<link
	href="assets/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- JQVMap -->
<link href="assets/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet" />
<!-- bootstrap-daterangepicker -->
<link
	href="assets/vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="assets/build/css/custom.min.css" rel="stylesheet">
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
								<li><a href="AdminHome"><i class="fa fa-home"></i> Home
								</a></li>
								<li><a href="instancesms"><i class="fa fa-comments-o"></i>
										Instance SMS </a></li>
								<li><a href="spreadsheet"><i class="fa fa-file-excel-o"></i>
										Google Sheets</a></li>
								<li><a href="sendQrcode"><i class="fa fa-comments-o"></i>
										Share QrCode </a></li>

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
								class="dropdown-item" href="logout"><i
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
						<button class="btn btn-round btn-success clf"
							data-toggle="collapse" data-target="#collapseOne"
							onclick="showf()">
							<i class="fa fa-folder-o pr-2" aria-hidden="true"></i>Create
							Event
						</button>
						<!-- /col-md-4 end -->
					</div>
					<div class="col-md-4">
						<button class="btn btn-round clf  btn-info"
							data-toggle="collapseupdate" data-target="#collapsetwo"
							onclick="showu()">
							<i class="fa fa-folder-o pr-2" aria-hidden="true"></i>Update
							Event
						</button>
						<!-- /col-md-4 end -->
					</div>
					<div class="col-md-4">
						<button class="btn btn-round 	clf btn-danger"
							data-toggle="collapsedelete" data-target="#collapsethree"
							onclick="showd()">
							<i class="fa fa-folder-o pr-2" aria-hidden="true"></i>Delete
							Event
						</button>
						<!-- /col-md-4 end -->
					</div>
					<!-- /row end -->
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="collapse x_content" id="collapseOne"
							style="display: none;">
							<h1>Hello</h1>
						</div>

					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="collapse x_content" id="collapsetwo"
							style="display: none;">
							<h2>dbdjkdvjvd</h2>
						</div>

					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="collapse x_content" id="collapsethree"
							style="display: none;">
							<h3>djbvfdskbvdbfj</h3>
						</div>

					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-lg-4">
						<form method="post" action="folder" class="btn btn-default fol">
							<input type="hidden" name="foldername"
								value="<?php echo $row['folder_name'];?>"> <i
								class="fa fa-folder-open btn btn-round btn-primary"> <input
								type="submit" class="btn btn-primary inp" name="view" value=""></i>
						</form>
					</div>

				</div>
				<!-- /top tiles -->
			</div>
			<!-- /page content -->

		</div>
	</div>

	<!-- jQuery -->
	<script src="assets/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="assets/vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<!-- FastClick -->
	<script src="assets/vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="assets/vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script src="assets/vendors/Chart.js/dist/Chart.min.js"></script>
	<!-- gauge.js -->
	<script src="assets/vendors/gauge.js/dist/gauge.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="assets/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- iCheck -->
	<script src="assets/vendors/iCheck/icheck.min.js"></script>
	<!-- Skycons -->
	<script src="assets/vendors/skycons/skycons.js"></script>
	<!-- Flot -->
	<script src="assets/vendors/Flot/jquery.flot.js"></script>
	<script src="assets/vendors/Flot/jquery.flot.pie.js"></script>
	<script src="assets/vendors/Flot/jquery.flot.time.js"></script>
	<script src="assets/vendors/Flot/jquery.flot.stack.js"></script>
	<script src="assets/vendors/Flot/jquery.flot.resize.js"></script>
	<!-- Flot plugins -->
	<script src="assets/vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="assets/vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
	<script src="assets/vendors/flot.curvedlines/curvedLines.js"></script>
	<!-- DateJS -->
	<script src="assets/vendors/DateJS/build/date.js"></script>
	<!-- JQVMap -->
	<script src="assets/vendors/jqvmap/dist/jquery.vmap.js"></script>
	<script src="assets/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
	<script
		src="assets/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="assets/vendors/moment/min/moment.min.js"></script>
	<script
		src="assets/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="assets/build/js/custom.min.js"></script>

</body>
<script type="text/javascript" charset="utf-8">
	function showf() {
		var x = document.getElementById("collapseOne");
		if (x.style.display === "none") {
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
		var y = document.getElementById("collapsetwo");
		y.style.display = "none";
		var z = document.getElementById("collapsethree");
		z.style.display = "none";
	}
	function showu() {
		var x = document.getElementById("collapseOne");
		x.style.display = "none";
		var y = document.getElementById("collapsetwo");
		if (y.style.display === "none") {
			y.style.display = "block";
		} else {
			y.style.display = "none";
		}
		var z = document.getElementById("collapsethree");
		z.style.display = "none";
	}
	function showd() {
		var x = document.getElementById("collapseOne");
		x.style.display = "none";
		var y = document.getElementById("collapsetwo");
		y.style.display = "none";
		var z = document.getElementById("collapsethree");
		if (z.style.display === "none") {
			z.style.display = "block";
		} else {
			z.style.display = "none";
		}
	}
</script>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="logic"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<html>
<head>
	<title>Stock Market</title>	
	<link rel="stylesheet" href="css/bootstrap.min.css">
	
	<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css"> 	
	
</head>
<body>
	<div class="container">
		<div>
			<h2 style="text-align:center">STOCK MARKET</h2>				
			<br>			
			<table id="stockDetails" class="table table-bordered table-stripped">
				<thead>
					<tr>					
						<th>Stock Id</th>
						<th>Company Name</th>
						<th>Last Traded Price</th>						
						<th>Percentage</th>	
						<th>Volume</th>		
						<th>Updated</th>		
					</tr>
				</thead>
				<tbody>
				    <logic:forEach var="stock" items="${stockList}">
						<tr>						
							<td>${stock.stockId}</td>
							<td><a href="${stock.stockUrl}" target="_blank">${stock.stockName}</a></td> 						
							<td>${stock.lastTradedPrice}</td>							
							<td>${stock.percentage}%</td>
							<td>${stock.volume}</td>
							<td>${stock.updated}</td>
						</tr>
					</logic:forEach>
				</tbody>		
			</table><br />		
	</div>
</div>
	
	 <script type="text/javascript" charset="utf8" src="js/jquery-3.5.1.js"></script> 
     <script type="text/javascript" charset="utf8" src="js/jquery.dataTables.min.js"></script>     

     <script>
      $(document).ready(function() {    	        
    	    $('#stockDetails').DataTable( {
    	        "lengthMenu": [[100, 250, -1], [100, 250, "All"]],
    	        "sPaginationType": "full_numbers"
    	    } );
      });
      
     </script>
	
</body>
</html>
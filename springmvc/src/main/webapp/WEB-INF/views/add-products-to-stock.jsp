<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add products to stock</title>
</head>
<body>
	<h2>Please fill below</h2>
	<p><font color="blue">${addProductsSuccessful}</font></p>
	<form action="/auth-add-products" method="post">
		<table>
			<tr>
				<td>Product Name: </td><td><input type="text" name="productTypeName" required="required"></td>
			</tr>
			<tr>
				<td>Price: </td><td><input type="number" name="productTypePrice" min="0" step="0.01" required="required"></td>
			</tr>
			<tr>
				<td>Quantity: </td><td><input type="number" name="quantityInStock" min="0" step="1" required="required"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="right"><input type="submit" value="Add Products"></td>
			</tr>
		</table>
	
	</form>
	<p>Go back to <a href="/home">home page</a>.</p>
</body>
</html>
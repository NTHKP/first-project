<%@ include file="templates/navigation.jspf"%>

<c:if test="${listOfOrders == null || listOfOrders.isEmpty() == true}">
	<p style="color:blue;">You have not placed any orders with us.</p>
</c:if>

<c:forEach items="${listOfOrders}" var="order">
<div class="orders-product-div">
	<p><strong>Order Ref.</strong>: #${order.orderId}</p>
	<p><strong>Order Date</strong>: ${order.orderDate}</p>
	<table class="orders-product-table">
 		<thead>
			<tr>
				<th align="left">Product Name</th> 
 				<th align="right">Unit Price</th> 
 				<th align="right">Quantity</th> 
 				<th align="right">Amount</th> 
 				
 			</tr> 
 		</thead> 
 		<tbody> 
			<c:forEach items="${order.products}" var="product">
 				<tr> 
					<td>${product.productName}</td>
					<td align="right">${product.productPrice}</td>
					<td align="right">${product.productQuantity}</td>
					<td align="right">${product.productTotalPrice}</td>
 					
 				</tr> 
			</c:forEach>
 		</tbody> 
 		<tfoot>
 			<tr>
 				<td colspan="3" align="right"><strong>Total:</strong></td>
 				<td align="right"><strong>$ ${order.totalPrice}</strong></td>
 			</tr>
 		</tfoot>
	
 	</table>
</div> 
</c:forEach>

<script type="text/javascript">
function changeActivePage() {
	document.getElementById("nav-orders").className = "active";
}
</script>
</body>
</html>
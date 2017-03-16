<%@ include file="templates/navigation.jspf" %>
	
	<c:forEach items="${productsChosen}" var="productsChosen" varStatus="loop">
		<div class="products">
			<form action="/remove-from-cart" method="post"><ul>
				<li><img src="/resources/images/${productsChosen.productName}.png" alt="${productsChosen.productName}" width="200" height="200"></li>
				<li>Product Name: <span>${productsChosen.productName}</span></li>
					<li><input type="hidden" name="productName" value="${productsChosen.productName}"/></li>
				<li>Price: $ <span>${productsChosen.productPrice}</span></li>
				<li>Quantity: <span>${productsChosen.productQuantity}</span></li>
					<li><input type="hidden" name="quantity" value="${productsChosen.productQuantity}"/></li>
				<li>Total: $ <span>${productsChosen.productTotalPrice}</span></li>
				<li><input type="submit" value="Remove from Cart" onclick="alert('The product has been removed from Shopping Cart!')"/></li>
			</ul></form>
		</div>
	</c:forEach>
	
	<div class="total">
		<c:choose>
			<c:when test="${productsChosen == null || productsChosen.isEmpty() == true}">
				<p style="color:blue;">You have not added any products to your Shopping Cart.</p>
			</c:when>
			<c:otherwise>
				<form action="/add-to-orders">
					<p style="color:blue; margin:16px;"><b>Total Amount: $ <span>${totalAmount}</span></b>  <input type="submit" value="Confirm Order"
					onclick="alert('Your order has been saved. Thank you for your patronage!')"/></p>
				</form>
			</c:otherwise>
		</c:choose>
	</div>

	
<!-- 	<table id="productList"> -->
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<th>Product Name</th> -->
<!-- 				<th>Unit Price</th> -->
<!-- 				<th>Quantity</th> -->
<!-- 				<th>Total</th> -->
<!-- 				<th></th> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<!-- 		<tbody> -->
<%-- 			<c:forEach items="${productTypes}" var="productType" varStatus="loop"> --%>
<!-- 				<tr> -->
<%-- 					<td>${productType.productTypeName}</td> --%>
<%-- 					<td id="price${loop.index }"><fmt:formatNumber value="${productType.productTypePrice}" type="number"  pattern="#,###.##"/></td> --%>
<%-- 					<td><input type="number" id="productQuantity${loop.index }"  min="0" step="1" value="1" onchange="multiply(${loop.index })"/></td> --%>
<%-- 					<td id="total${loop.index }" style="">$ <fmt:formatNumber value="${productType.productTypePrice}" type="number"  pattern="#,###.##"/></td> --%>
<!-- 					<td><input type="submit" value="Add to Cart"/></td> -->
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>
<!-- 		</tbody> -->
	
<!-- 	</table> -->
	
	<script type="text/javascript">
		function multiply(loopIndex) {
			var x = parseFloat(document.getElementById("price" + loopIndex).innerHTML) * parseFloat(document.getElementById("productQuantity" + loopIndex).value);
			document.getElementById("total" + loopIndex).innerHTML = convertNumber(x);
		}
		
		function changeActivePage() {
			document.getElementById("nav-shopping-cart").className = "active";
		}
		
		function convertNumber(x) {
			return x.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
		}
	</script>
</body>
</html>
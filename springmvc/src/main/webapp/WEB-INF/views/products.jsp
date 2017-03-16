<%@ include file="templates/navigation.jspf"%>
	
	<form action="/products" method="get">
		<p>Specify the number of products you want to display on each page: <input type="number" name="numProductsPerPage" min="1" step="1" value="${numProductsPerPage}"/> <input type="submit"/></p>
	</form>
	
	<c:forEach items="${productTypes}" var="productType" varStatus="loop">
		<div class="products">
			<form action="/add-to-cart" method="post"><ul>
				<li><img src="/resources/images/${productType.productTypeName}.png" alt="${productType.productTypeName}" width="200" height="200"></li>
				<li>Product Name: <span>${productType.productTypeName}</span></li>
					<li><input type="hidden" name="productName" value="${productType.productTypeName}"/></li>
				<li>Price: $ <span id="price${loop.index }">${productType.productTypePrice}</span></li>
				<li>Quantity: <input type="number" name="quantity" id="productQuantity${loop.index }"  min="0" step="1" value="1" onchange="multiply(${loop.index })"/></li>
				<li>Total: $ <span id="total${loop.index }">${productType.productTypePrice}</span></li>
				<security:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
					<li><input type="submit" value="Add to Cart" onclick="alert('The product has been added to Shopping Cart!')"/></li>
				</security:authorize>
				<security:authorize access="!hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
					<li style="color:red;"><i>Please <a href="login">log in</a> to purchase this product.</i></li>
				</security:authorize>
			</ul></form>
		</div>
	</c:forEach>
	
	
	
	<div class="products-pagination" style="text-align:center;">
		<c:choose>
			<c:when test="${pageNum - 1 == 0}">
				<a href="/products?numProductsPerPage=${numProductsPerPage}&pageNum=${pageNum}">&laquo;</a>
			</c:when>
			<c:otherwise>
				<a href="/products?numProductsPerPage=${numProductsPerPage}&pageNum=${pageNum-1}">&laquo;</a>
			</c:otherwise>
		</c:choose>
			<c:choose>
				<c:when test="${allProductTypes.size() % numProductsPerPage == 0}">
					<c:forEach var="i" begin="1" end="${allProductTypes.size() / numProductsPerPage}" varStatus="loopPagination">
						<a id="pagination${loopPagination.index}" href="/products?numProductsPerPage=${numProductsPerPage}&pageNum=${i}">${i}</a>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="1" end="${allProductTypes.size() / numProductsPerPage + 1}" varStatus="loopPagination">
						<a id="pagination${loopPagination.index}" href="/products?numProductsPerPage=${numProductsPerPage}&pageNum=${i}">${i}</a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		<c:choose>
			<c:when test="${allProductTypes.size() % numProductsPerPage == 0}">
				<c:choose>
					<c:when test="${pageNum + 1 > allProductTypes.size() / numProductsPerPage}">
						<a href="/products?numProductsPerPage=${numProductsPerPage}&pageNum=${pageNum}">&raquo;</a>
					</c:when>
					<c:otherwise>
						<a href="/products?numProductsPerPage=${numProductsPerPage}&pageNum=${pageNum + 1}">&raquo;</a>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${pageNum + 1 > allProductTypes.size() / numProductsPerPage + 1}">
						<a href="/products?numProductsPerPage=${numProductsPerPage}&pageNum=${pageNum}">&raquo;</a>
					</c:when>
					<c:otherwise>
						<a href="/products?numProductsPerPage=${numProductsPerPage}&pageNum=${pageNum + 1}">&raquo;</a>
					</c:otherwise>
				</c:choose>
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
			document.getElementById("nav-products").className = "active";
		}
		
		function changeActivePagination(pageNum) {
			document.getElementById("pagination" + pageNum).className = "active";
		}
		
		function convertNumber(x) {
			return x.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
		}
	</script>
</body>
</html>
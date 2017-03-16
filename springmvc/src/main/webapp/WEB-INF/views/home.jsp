<%@ include file="templates/navigation.jspf"%>

<security:authorize access="!hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
	<p>Welcome, <strong>Guest</strong>. If you want to buy our products, please <a href="/login">log in</a> if you already have an
	account. Otherwise you can <a href="/signup">sign up an account</a>.</p>
	<p>If you are an admin, you may perform advanced tasks, such as adding new products to be sold. (Please log in
	 with Username: <strong>hieu</strong> and Password: <strong>1234</strong> to access admin account)</p>
</security:authorize>

<security:authorize access="hasRole('ROLE_USER') && !hasRole('ROLE_ADMIN')">
	<p>Welcome, <strong>${user.getUsername()}</strong>. You are now ready to purchase our products.</p>
	<p>You can also log in as an admin if you want to perform advanced tasks, such as adding new products to be sold. 
	(Please log in with Username: <strong>hieu</strong> and Password: <strong>1234</strong> to access admin account)</p>
</security:authorize>

<security:authorize access="hasRole('ROLE_ADMIN')">
	<p>Welcome, <strong>${user.getUsername()}</strong>. You are an admin of this website. You can perform advanced tasks, such as <a href="/add-products-to-stock">adding new products to be sold</a>.</p>
</security:authorize>

<script type="text/javascript">
function changeActivePage() {
	document.getElementById("nav-home").className = "active";
}
</script>
</body>
</html>
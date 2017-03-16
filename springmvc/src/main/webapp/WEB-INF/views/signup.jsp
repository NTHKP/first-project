<%@ include file="templates/navigation.jspf"%>

<div class="login-div">
	<div class="login-div-header"><h2>Sign up</h2></div>
	<p><font color="red">${invalidUsername}</font></p>
	<form action="/auth-signup" method="post">
		<table>
			<tr>
				<td>First Name: </td><td align="right"><input type="text" name="firstName" required="required" size="40"></td>
			</tr>
			<tr>
				<td>Last Name: </td><td align="right"><input type="text" name="lastName" required="required" size="40"></td>
			</tr>
			<tr>
				<td>Username: </td><td align="right"><input type="text" name="username" required="required" size="40"></td>
			</tr>
			<tr>
				<td>Password: </td><td align="right"><input type="password" name="password" required="required" size="40"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="Sign up"></td>
			</tr>
		</table>
	
	</form>
</div>

<script type="text/javascript">
function changeActivePage() {
	document.getElementById("nav-login").className = "active";
}
</script>
</body>
</html>
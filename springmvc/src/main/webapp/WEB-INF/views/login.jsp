<%@ include file="templates/navigation.jspf"%>

<div class="login-div">
	<div class="login-div-header"><h2>Log in</h2></div>
    <p><font color="red">${error}</font></p>
    <p><font color="blue">${signUpSuccessful}</font></p>
    <p><font color="blue">${logout}</font></p>
    <form action="/login" method="post">
        <table>
        	<tr>
        		<td>Username:</td><td align="right"><input name="username" type="text" required="required" size="40"/></td>
        	</tr>
        	<tr>
        		<td>Password:</td><td align="right"><input name="password" type="password" required="required" size="40"/></td>
        	</tr>
        	
        	<tr>
        		<td colspan="2" align="right"><input  type="submit" value="  Log in  " /></td>
        	</tr>
        	<tr>
        		<td colspan="2" align="right"><a href="/signup">Don't have an account? Click here to sign up!</a></td>
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
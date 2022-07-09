<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Add New Account</title>
</head>

<body>

<g:render template="/layouts/flash"/>

<div class="add-new-account-form-container">
    <g:form action="processNewAccount" id="save-new-account">
        <p>All new accounts opened will contain £200 once the account has been successfully processed.</p>
        <div class="form-group">
            <label for="accountName">Account Name</label>
            <input type="text" class="form-control" id="accountName" maxlength="30" name="accountName" aria-describedby="accountNameHelp" placeholder="Enter Account Name...">
            <small id="accountNameHelp" class="form-text text-muted">Please Provide an Account Name</small>
        </div>
        <div class="form-group">
            <label for="emailAddress">Email Address</label>
            <input type="text" class="form-control" id="emailAddress" name="emailAddress" aria-describedby="emailAddressHelp" placeholder="Enter Email Address...">
            <small id="emailAddressHelp" class="form-text text-muted">Please Provide an Email Address</small>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </g:form>
</div>

</body>
</html>
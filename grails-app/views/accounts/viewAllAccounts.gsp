<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Add New Account</title>
</head>

<body>

<g:render template="/layouts/flash"/>


<table class="table">
    <thead>
        <tr>
            <th scope="col">Account Name</th>
            <th scope="col">Balance</th>
            <th scope="col">Email Address</th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${allAccounts}" var="account" status="i">
            <tr>
                <td>${account.getAccountName()}</td>
                <td>${account.getBalance()}</td>
                <td>${account.getEmailAddress()}</td>
            </tr>
        </g:each>
    </tbody>
</table>

</body>
</html>
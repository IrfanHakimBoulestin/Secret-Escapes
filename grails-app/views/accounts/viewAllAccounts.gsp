<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>View All Accounts</title>
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
                <td>
                    <a href="/transactions/viewTransactions?id=${account.getId()}" class="btn btn-primary mr-1">View Transactions</a>
                    <a href="/payments/transferForm?id=${account.getId()}" class="btn btn-primary">Make a Transfer</a>
                </td>
            </tr>
        </g:each>
    </tbody>
</table>

</body>
</html>
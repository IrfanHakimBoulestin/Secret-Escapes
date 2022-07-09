<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>View All Transactions</title>
</head>

<body>

<g:render template="/layouts/flash"/>

<table class="table">
    <thead>
        <tr>
            <th scope="col">Sender</th>
            <th scope="col">Transaction Amount</th>
            <th scope="col">Receiver</th>
        </tr>
    </thead>
    <tbody>
        <g:each in="${transactions}" var="transaction" status="i">
            <tr>
                <td>${transaction.getAccount().getAccountName()}</td>
                <td>${transaction.getTransactionAmount()}</td>
                <td>${transaction.getReceiverAccount().getAccountName()}</td>
            </tr>
        </g:each>
    </tbody>
</table>

</body>
</html>
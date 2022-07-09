<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Add New Account</title>
</head>

<body>

<g:render template="/layouts/flash"/>

<div class="make-a-transfer-container">
    <g:form action="processTransfer" id="process-transfer-request">
        <div class="form-group">
            <g:select from="${account}" name="accountFrom" value="${account.getId()}" optionValue="accountName"/>
        </div>
        <div class="form-group">
            <label for="transactionAmount">Transaction Amount</label>
            <input type="number" class="form-control" id="transactionAmount" name="transactionAmount" aria-describedby="transactionAmountHelp">
            <small id="transactionAmountHelp" class="form-text text-muted">Transaction Amount</small>
        </div>
        <div class="form-group">
            <g:select from="${toAccounts}" name="accountTo" value="${account.getId()}" optionValue="accountName"/>
        </div>
        <button type="submit" class="btn btn-primary">Submit Transfer</button>
    </g:form>
</div>

</body>
</html>
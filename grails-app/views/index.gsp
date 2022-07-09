<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Accounts Dashboard</title>
</head>
<body>

<g:render template="/layouts/flash"/>

<div class="card centered card-space">
    <svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-person-fill accounts-icon" viewBox="0 0 16 16">
        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
    </svg>
    <div class="card-body text-center">
        <h5 class="card-title text-center">Account Actions</h5>
        <a href="/accounts/viewAllAccounts" class="btn btn-primary">View All Accounts</a>
        <a href="/accounts/addNewAccount" class="btn btn-success">Add New Account</a>
    </div>
</div>

</body>
</html>

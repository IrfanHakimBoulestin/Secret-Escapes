# SEI
Secret Escapes Web Application Interview

<b>Assumptions</b> 
* Accounts have an account holder name, balance and email address
* All accounts have a starting balance of £200
* There are no overdrafts
* Users interact with the system through a web UI in a browser
* The system has no security or user management functionality
* The system is designed to rely on a database to store account and transaction data. For the sake of simplicity, it could
be an in-memory database, such as H2.

<b>Acceptance Criteria</b>
* Given a number of accounts exist, when I view the account page, I see a list of all accounts and their balances.
* Given there is a list of accounts on the accounts page, when I select an account from the list, then I see all transactions for the account selected (amount of transaction & Account name the fund was paid
  into/from)
* Given I want to make a payment to another account, when I visit 'Make a Payment', then there’s a form to transfer a sum from one
  account to another. The form contains the following fields: (From account, To account, Amount)
* Given there is a transfer form on the pay screen, when I select a From account, a different To account and specify an amount less than or equal to the balance of the From account and submit the form, then ->
  1. The balance of the From account decreases by the amount specified.
  2. The balance of the To account increases by the amount specified.
  3. An email is sent to both account holders confirming the transfer.
  4. I see a confirmation message on the screen verifying that the transfer was successful.
* Given there is a transfer form on the pay screen, when I select a From account, a different To account and specify an amount greater than the balance of the From account and submit the form, then ->
  1. I see an error message informing me that the transaction was not successful.
  2. The balance of the From account remains unchanged.
  3. The balance of the To account remains unchanged
  4. No email is sent to either account.

<b>Start Up Instructions</b>
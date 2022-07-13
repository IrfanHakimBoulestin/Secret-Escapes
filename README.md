# SEI
Secret Escapes Web Application Interview

<b>Start Up Instructions</b>

To start up the application, simply run the Gradle task <b>bootrun</b>. This is accessible via the following URL: http://localhost:9191/

To run all the unit tests, simply run the Gradle task <b>test</b>

To view the H2 in memory database used for the application, this is accessible via: http://localhost:9191/h2-console/.
* Username/ password for this is hakim/hakim.

Emails are being sent used Mailtrap as a simulated SMTP Server and Grails Mail Plugin to send the actual emails. 
Please note that these emails will not be sent to the email addresses provided but instead will be captured by Mailtrap. 
* Please contact if login details are required!

Please note that this is a random generated Password which will be changed once repo has been reviewed.

<b>Areas For Improvements</b>
* Automated Test Covering AC's using either Geb or Selenium
* Security handling of request params / preventing malicious intent by manipulating request params.

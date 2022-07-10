package secret.escapes

import grails.gorm.transactions.Transactional

@Transactional
class EmailService {

    def mailService

    def sendTransferCompleteEmail(Integer accountFrom, Integer accountTo) {
        String accountFromEmail = Account.get(accountFrom).emailAddress
        String accountToEmail = Account.get(accountTo).emailAddress
        sendEmail(
                accountToEmail,
                accountFromEmail,
                "Transfer Has Been Completed!",
                "Transfer has been successfully completed, Please log in to check your accounts balance!"
        )
    }

    def sendEmail(String toEmail, String fromEmail, String title, String content){
        mailService.sendMail {
            to toEmail
            from fromEmail
            subject title
            text content
        }
    }
}

package bridge

fun main(){
    val aesImpl: IMessageEncrypt= DefaultMessageEncrypImpl(AESEncryptAlgorithm())
    val desImpl: IMessageEncrypt= DefaultMessageEncrypImpl(DESEncryptAlgorithm())
    val noImpl: IMessageEncrypt= DefaultMessageEncrypImpl(NoEncryptAlgorithm())
    try {
        val message= "{\"fullname\":\"Almaraz Chavez Emmanuel\",\"age\":48}"

        val messageAES = aesImpl.encryptMessage(message,"HG58YZ3CR9123456")
        println("messageAES > SmessageAES\n");
        val messageDES = desImpl.encryptMessage(message,  "XMz0dG4083CKm2Ix")
        println("messageDES > $messageDES\n");

        val messageNO = noImpl.encryptMessage(message, "")

        println("messageNO > $messageNO\n");

    } catch (e: Exception) {

        e.printStackTrace()
    }
}
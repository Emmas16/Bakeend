package bridge

class NoEncryptAlgoritmo: IEncryptAlgorithm {
    override fun encrypt(message: String, password: String):String{
        return message
    }
}
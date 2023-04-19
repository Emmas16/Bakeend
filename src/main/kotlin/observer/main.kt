package observer

import java.text.DecimalFormat
import java.text.SimpleDateFormat

fun main(args: Array<String>) {
    val conf = ConfigurationManager.getInstance()

    // Se establecen los valores por defecto.
    conf.setDefaultDateFormat(SimpleDateFormat("yyyy/MM/dd"))
    conf.setMoneyFormat(DecimalFormat("##.00"))
    println("Configuración establecida")

    // Se dan de alta los observers.
    val dateFormatObserver = DateFormatObserver()
    val moneyFormatObserver = MoneyFormatObserver()
    conf.addObserver(dateFormatObserver)
    conf.addObserver(moneyFormatObserver)
    println()

    // Se cambia la configuración.
    conf.setDefaultDateFormat(SimpleDateFormat("dd/MM/yyyy"))
    conf.setMoneyFormat(DecimalFormat("###,#00.00"))
    println()

    // Se realiza otro cambio en la configuración.
    conf.setDefaultDateFormat(SimpleDateFormat("MM/yyyy/dd"))
    conf.setMoneyFormat(DecimalFormat("###,#00"))
    conf.removeObserver(dateFormatObserver)
    conf.removeObserver(moneyFormatObserver)
    println()

    // Se realiza otro cambio en la configuración.
    conf.setDefaultDateFormat(SimpleDateFormat("MM/yyyy"))
    conf.setMoneyFormat(DecimalFormat("###,##0.00"))
}
package observer

import java.text.NumberFormat
import java.text.SimpleDateFormat

class ConfigurationManager: IObservable  {

    private var defaultDateFormat: SimpleDateFormat? = null
    private var moneyFormat: NumberFormat? = null
    private var observers: MutableList<IObserver> = mutableListOf()
    companion object {
        private var configurationManager: ConfigurationManager? = null


        fun getInstance(): ConfigurationManager {
            if (configurationManager == null) {
                configurationManager = ConfigurationManager()
            }
            return configurationManager!!
        }
    }

    fun getDefaultDateFormat(): SimpleDateFormat? {
        return defaultDateFormat
    }

    fun setDefaultDateFormat(defaultDateFormat: SimpleDateFormat?) {
        this.defaultDateFormat = defaultDateFormat
        notifyAllObservers("defaultDateFormat", this)
    }

    fun getMoneyFormat(): NumberFormat? {
        return moneyFormat
    }

    fun setMoneyFormat(moneyFormat: NumberFormat?) {
        this.moneyFormat = moneyFormat
        notifyAllObservers("moneyFormat", this)
    }

    override fun addObserver(observer: MoneyFormatObserver) {
        observers.add(observer)
    }

    override fun addObserver(observer: DateFormatObserver) {
        observers.add(observer)
    }

    override fun removeObserver(observer: MoneyFormatObserver) {
        observers.remove(observer)
    }

    override fun removeObserver(observer: DateFormatObserver) {
        observers.remove(observer)
    }

    override fun notifyAllObservers(command: String, source: Any) {
        observers.forEach { it.notifyObserver(command, source) }
    }
}
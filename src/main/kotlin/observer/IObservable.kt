package observer

interface IObservable {
    fun addObserver(observer: MoneyFormatObserver)
    fun addObserver(observer: DateFormatObserver)
    fun removeObserver(observer: MoneyFormatObserver)
    fun removeObserver(observer: DateFormatObserver)
    fun notifyAllObservers(command: String, source: Any)

}
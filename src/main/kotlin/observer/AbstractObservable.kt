package observer


abstract class AbstractObservable   {
    private val observers: MutableList<IObserver> = ArrayList<IObserver>()


    open fun addObserver(observer: IObserver) {
        observers.add(observer)
    }


    open fun removeObserver(observer: IObserver) {
        observers.remove(observer)
    }


    open fun notifyAllObservers(command: String?, source: Any?) {
        for (observer in observers) {
            observer.notifyObserver(command, source)
        }
    }
}
package etty.qweinke.qwrewards.managers

interface Manager {
    fun init(): Boolean

    fun shutdown(): Boolean
}
package etty.qweinke.qwrewards.managers.child

import etty.qweinke.qwrewards.Plugin
import etty.qweinke.qwrewards.managers.Manager
import org.bukkit.Bukkit
import org.bukkit.event.Listener

class ListenerManager(vararg listeners: Listener) : Manager {
    private val listenersList = listeners.toList() as MutableList
    private val plugin = Plugin.instance
    private val pluginManager = Bukkit.getPluginManager()

    override fun init(): Boolean {
        listenersList.forEach {
            listener -> pluginManager.registerEvents(listener, plugin)
        }

        return true
    }

    override fun shutdown(): Boolean {
        listenersList.clear()
        return true
    }
}
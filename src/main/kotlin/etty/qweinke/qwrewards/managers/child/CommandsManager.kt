package etty.qweinke.qwrewards.managers.child

import etty.qweinke.qwrewards.Plugin
import etty.qweinke.qwrewards.managers.Manager

class CommandsManager() : Manager {
    val plugin = Plugin.instance

    override fun init(): Boolean {
        plugin.getCommand().setExecutor()
        plugin.getCommand().setExecutor()
        plugin.getCommand().setExecutor()

    }

    override fun shutdown(): Boolean {
        TODO("Not yet implemented")
    }
}
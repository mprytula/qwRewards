package etty.qweinke.qwrewards

import etty.qweinke.qwrewards.managers.CentralManager
import org.bukkit.plugin.java.JavaPlugin

class Plugin : JavaPlugin() {
    companion object {
        val instance by lazy { Plugin() }
    }

    private val centralManager = CentralManager()

    override fun onLoad() {
        super.onLoad()
    }

    override fun onEnable() {
        centralManager.init()
    }

    override fun onDisable() {
        centralManager.shutdown()
    }
}
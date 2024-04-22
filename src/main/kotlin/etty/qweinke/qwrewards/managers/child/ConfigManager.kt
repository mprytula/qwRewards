package etty.qweinke.qwrewards.managers.child

import etty.qweinke.qwrewards.Plugin
import etty.qweinke.qwrewards.managers.Manager
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigManager private constructor(private val fileName: String) : Manager {
    private val plugin = Plugin.instance
    var config: YamlConfiguration? = null

    companion object {
        private var instance: ConfigManager? = null

        fun getInstance(fileName: String): ConfigManager {
            if (instance == null) instance = ConfigManager(fileName)
            return instance!!
        }
    }

    override fun init(): Boolean {
        load()
        return true
    }

    fun load(): Boolean {
        val configFile = try {
            File(plugin.dataFolder, fileName)
        } catch (e: NullPointerException) {
            saveDefaultConfig(fileName)
        }
        config = YamlConfiguration.loadConfiguration(configFile)

        return true
    }

    override fun shutdown(): Boolean {
        config = null
        return true
    }

    public fun reloadConfig(): Boolean {
        return init()
    }

    private fun saveDefaultConfig(fileName: String): File {
        plugin.saveResource(fileName, false)
        return File(plugin.dataFolder, fileName)
    }
}
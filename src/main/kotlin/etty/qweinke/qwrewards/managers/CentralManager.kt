package etty.qweinke.qwrewards.managers

import etty.qweinke.qwrewards.managers.child.*
import etty.qweinke.qwrewards.managers.exceptions.ManagerInitializationException

class CentralManager() : Manager {
    private val managerList = listOf(
        ConfigManager.getInstance("config.yml"), ConnectionManager(),
        DataBaseManager(), CacheManager(),
        ListenerManager(), CommandsManager()
    )

    override fun init(): Boolean {
        try {
            managerList.forEach(Manager::init)
        } catch (e: ManagerInitializationException) {
            return false
        }

        return true
    }

    override fun shutdown(): Boolean {
        try {
            managerList.forEach(Manager::shutdown)
        } catch (e: ManagerInitializationException) {
            return false
        }

        return true
    }
}
package etty.qweinke.qwrewards.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerRecipeDiscoverEvent

class PlayerDisconnectEventListener : Listener {
    @EventHandler
    fun onPlayerDisconnect(event: PlayerRecipeDiscoverEvent) {
        TODO("NOT IMPLEMENTED YET")
    }
}
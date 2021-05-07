package stomee.terrain

import net.minestom.server.entity.Player
import net.minestom.server.event.player.PlayerLoginEvent
import stomee.terrain.generator.instance
import world.cepi.kstom.addEventCallback

fun hook(player: Player) {
    player.addEventCallback<PlayerLoginEvent> {
        setSpawningInstance(instance)
    }
}
package stomee.terrain

import net.minestom.server.extensions.Extension;
import world.cepi.kstom.Manager

class Terrain : Extension() {

    override fun initialize() {

        Manager.connection.addPlayerInitialization(::hook)

        logger.info("[Terrain] has been enabled!")
    }

    override fun terminate() {

        Manager.connection.removePlayerInitialization(::hook)

        logger.info("[Terrain] has been disabled!")
    }

}
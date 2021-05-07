package stomee.terrain

import net.minestom.server.extensions.Extension;

class Terrain : Extension() {

    override fun initialize() {
        logger.info("[Terrain] has been enabled!")
    }

    override fun terminate() {
        logger.info("[Terrain] has been disabled!")
    }

}
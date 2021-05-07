package stomee.terrain.generator

import net.minestom.server.instance.Instance
import world.cepi.kstom.Manager

val instance: Instance by lazy {
    val instanceManager = Manager.instance
    val instance = instanceManager.createInstanceContainer()
    instance.chunkGenerator = StomChunkGenerator
    instance.enableAutoChunkLoad(true)

    return@lazy instance
}
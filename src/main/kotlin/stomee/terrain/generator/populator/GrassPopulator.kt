package stomee.terrain.generator.populator

import de.articdive.jnoise.JNoise
import de.articdive.jnoise.interpolation.InterpolationType
import net.minestom.server.instance.Chunk
import net.minestom.server.instance.ChunkPopulator
import net.minestom.server.instance.batch.ChunkBatch
import net.minestom.server.instance.block.Block
import net.minestom.server.utils.BlockPosition
import stomee.terrain.generator.StomChunkGenerator
import stomee.terrain.generator.Structure
import world.cepi.kstom.util.getBlockStateId

class GrassPopulator : ChunkPopulator {

    private val jNoise =
        JNoise.newBuilder().perlin().setInterpolation(InterpolationType.LINEAR).setSeed(StomChunkGenerator.random.nextInt().toLong())
            .setFrequency(0.9).build()

    private val grass: Structure = Structure()

    override fun populateChunk(batch: ChunkBatch, chunk: Chunk) {
        for (i in -2..17) {
            for (j in -2..17) {
                if (jNoise.getNoise(
                        (i + chunk.chunkX * 16).toDouble(),
                        (j + chunk.chunkZ * 16).toDouble()
                    ) > 0.2
                ) {
                    val y = StomChunkGenerator.getHeight(i + chunk.chunkX * 16, j + chunk.chunkZ * 16)

                    val position = BlockPosition(i, y, j)

                    if (chunk.getBlockStateId(position) != Block.AIR.blockId)
                        continue

                    grass.build(batch, BlockPosition(i, y, j))
                }
            }
        }
    }

    init {
        grass.addBlock(Block.GRASS, 0, 0, 0)
    }
}
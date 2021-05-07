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

class TreePopulator : ChunkPopulator {

    private val jNoise =
        JNoise.newBuilder().perlin().setInterpolation(InterpolationType.LINEAR).setSeed(StomChunkGenerator.random.nextInt().toLong())
            .setFrequency(0.6).build()

    val tree: Structure = Structure()

    //todo improve
    override fun populateChunk(batch: ChunkBatch, chunk: Chunk) {
        for (i in -2..17) {
            for (j in -2..17) {
                if (jNoise.getNoise(
                        (i + chunk.chunkX * 16).toDouble(),
                        (j + chunk.chunkZ * 16).toDouble()
                    ) > 0.75
                ) {
                    val y = StomChunkGenerator.getHeight(i + chunk.chunkX * 16, j + chunk.chunkZ * 16)
                    tree.build(batch, BlockPosition(i, y, j))
                }
            }
        }
    }

    init {
        tree.addBlock(Block.DIRT, 0, -1, 0)
        tree.addBlock(Block.OAK_LOG, 0, 0, 0)
        tree.addBlock(Block.OAK_LOG, 0, 1, 0)
        tree.addBlock(Block.OAK_LOG, 0, 2, 0)
        tree.addBlock(Block.OAK_LOG, 0, 3, 0)
        tree.addBlock(Block.OAK_LEAVES, 1, 1, 0)
        tree.addBlock(Block.OAK_LEAVES, 2, 1, 0)
        tree.addBlock(Block.OAK_LEAVES, -1, 1, 0)
        tree.addBlock(Block.OAK_LEAVES, -2, 1, 0)
        tree.addBlock(Block.OAK_LEAVES, 1, 1, 1)
        tree.addBlock(Block.OAK_LEAVES, 2, 1, 1)
        tree.addBlock(Block.OAK_LEAVES, 0, 1, 1)
        tree.addBlock(Block.OAK_LEAVES, -1, 1, 1)
        tree.addBlock(Block.OAK_LEAVES, -2, 1, 1)
        tree.addBlock(Block.OAK_LEAVES, 1, 1, 2)
        tree.addBlock(Block.OAK_LEAVES, 2, 1, 2)
        tree.addBlock(Block.OAK_LEAVES, 0, 1, 2)
        tree.addBlock(Block.OAK_LEAVES, -1, 1, 2)
        tree.addBlock(Block.OAK_LEAVES, -2, 1, 2)
        tree.addBlock(Block.OAK_LEAVES, 1, 1, -1)
        tree.addBlock(Block.OAK_LEAVES, 2, 1, -1)
        tree.addBlock(Block.OAK_LEAVES, 0, 1, -1)
        tree.addBlock(Block.OAK_LEAVES, -1, 1, -1)
        tree.addBlock(Block.OAK_LEAVES, -2, 1, -1)
        tree.addBlock(Block.OAK_LEAVES, 1, 1, -2)
        tree.addBlock(Block.OAK_LEAVES, 2, 1, -2)
        tree.addBlock(Block.OAK_LEAVES, 0, 1, -2)
        tree.addBlock(Block.OAK_LEAVES, -1, 1, -2)
        tree.addBlock(Block.OAK_LEAVES, -2, 1, -2)
        tree.addBlock(Block.OAK_LEAVES, 1, 2, 0)
        tree.addBlock(Block.OAK_LEAVES, 2, 2, 0)
        tree.addBlock(Block.OAK_LEAVES, -1, 2, 0)
        tree.addBlock(Block.OAK_LEAVES, -2, 2, 0)
        tree.addBlock(Block.OAK_LEAVES, 1, 2, 1)
        tree.addBlock(Block.OAK_LEAVES, 2, 2, 1)
        tree.addBlock(Block.OAK_LEAVES, 0, 2, 1)
        tree.addBlock(Block.OAK_LEAVES, -1, 2, 1)
        tree.addBlock(Block.OAK_LEAVES, -2, 2, 1)
        tree.addBlock(Block.OAK_LEAVES, 1, 2, 2)
        tree.addBlock(Block.OAK_LEAVES, 2, 2, 2)
        tree.addBlock(Block.OAK_LEAVES, 0, 2, 2)
        tree.addBlock(Block.OAK_LEAVES, -1, 2, 2)
        tree.addBlock(Block.OAK_LEAVES, -2, 2, 2)
        tree.addBlock(Block.OAK_LEAVES, 1, 2, -1)
        tree.addBlock(Block.OAK_LEAVES, 2, 2, -1)
        tree.addBlock(Block.OAK_LEAVES, 0, 2, -1)
        tree.addBlock(Block.OAK_LEAVES, -1, 2, -1)
        tree.addBlock(Block.OAK_LEAVES, -2, 2, -1)
        tree.addBlock(Block.OAK_LEAVES, 1, 2, -2)
        tree.addBlock(Block.OAK_LEAVES, 2, 2, -2)
        tree.addBlock(Block.OAK_LEAVES, 0, 2, -2)
        tree.addBlock(Block.OAK_LEAVES, -1, 2, -2)
        tree.addBlock(Block.OAK_LEAVES, -2, 2, -2)
        tree.addBlock(Block.OAK_LEAVES, 1, 3, 0)
        tree.addBlock(Block.OAK_LEAVES, -1, 3, 0)
        tree.addBlock(Block.OAK_LEAVES, 1, 3, 1)
        tree.addBlock(Block.OAK_LEAVES, 0, 3, 1)
        tree.addBlock(Block.OAK_LEAVES, -1, 3, 1)
        tree.addBlock(Block.OAK_LEAVES, 1, 3, -1)
        tree.addBlock(Block.OAK_LEAVES, 0, 3, -1)
        tree.addBlock(Block.OAK_LEAVES, -1, 3, -1)
        tree.addBlock(Block.OAK_LEAVES, 1, 4, 0)
        tree.addBlock(Block.OAK_LEAVES, 0, 4, 0)
        tree.addBlock(Block.OAK_LEAVES, -1, 4, 0)
        tree.addBlock(Block.OAK_LEAVES, 0, 4, 1)
        tree.addBlock(Block.OAK_LEAVES, 0, 4, -1)
        tree.addBlock(Block.OAK_LEAVES, -1, 4, -1)
    }
}
package stomee.terrain.generator

import de.articdive.jnoise.JNoise
import de.articdive.jnoise.interpolation.InterpolationType
import net.minestom.server.MinecraftServer
import net.minestom.server.instance.Chunk
import net.minestom.server.instance.ChunkGenerator
import net.minestom.server.instance.ChunkPopulator
import net.minestom.server.instance.batch.ChunkBatch
import net.minestom.server.instance.block.Block
import net.minestom.server.utils.BlockPosition
import net.minestom.server.world.biomes.Biome
import java.util.*

object StomChunkGenerator : ChunkGenerator {

    private val random = Random()

    private val jNoise =
        JNoise.newBuilder().perlin().setInterpolation(InterpolationType.LINEAR).setSeed(random.nextInt().toLong())
            .setFrequency(0.4).build()

    private val jNoise2 =
        JNoise.newBuilder().perlin().setInterpolation(InterpolationType.LINEAR).setSeed(random.nextInt().toLong())
            .setFrequency(0.6).build()

    private val treeGen: TreePopulator = TreePopulator()

    fun getHeight(x: Int, z: Int): Int {
        val preHeight = jNoise.getNoise(x / 16.0, z / 16.0)
        return ((if (preHeight > 0) preHeight * 6 else preHeight * 4) + 64).toInt()
    }

    override fun generateChunkData(batch: ChunkBatch, chunkX: Int, chunkZ: Int) {
        for (x in 0 until Chunk.CHUNK_SIZE_X) {
            for (z in 0 until Chunk.CHUNK_SIZE_Z) {
                val height = getHeight(x + chunkX * 16, z + chunkZ * 16)
                for (y in 0 until height) {
                    if (y == 0) {
                        batch.setBlock(x, y, z, Block.BEDROCK)
                    } else if (y == height - 1) {
                        batch.setBlock(x, y, z, Block.GRASS_BLOCK)
                    } else if (y > height - 7) {
                        batch.setBlockStateId(x, y, z, Block.DIRT.blockId)
                    } else {
                        batch.setBlock(x, y, z, Block.STONE)
                    }
                }
                if (height < 61) {
                    batch.setBlock(x, height - 1, z, Block.DIRT)
                    for (y in 0 until 61 - height) {
                        batch.setBlock(x, y + height, z, Block.WATER)
                    }
                }
            }
        }
    }

    override fun fillBiomes(biomes: Array<Biome>, chunkX: Int, chunkZ: Int) {
        Arrays.fill(biomes, MinecraftServer.getBiomeManager().getById(0))
    }

    override fun getPopulators(): List<ChunkPopulator> {
        val list: MutableList<ChunkPopulator> = ArrayList()
        list.add(treeGen)
        return list
    }

    private class TreePopulator : ChunkPopulator {
        val tree: Structure = Structure()

        //todo improve
        override fun populateChunk(batch: ChunkBatch, chunk: Chunk) {
            for (i in -2..17) {
                for (j in -2..17) {
                    if (jNoise2.getNoise(
                            (i + chunk.chunkX * 16).toDouble(),
                            (j + chunk.chunkZ * 16).toDouble()
                        ) > 0.75
                    ) {
                        val y = getHeight(i + chunk.chunkX * 16, j + chunk.chunkZ * 16)
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
}
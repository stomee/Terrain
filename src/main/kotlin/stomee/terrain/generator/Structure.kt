package stomee.terrain.generator

import net.minestom.server.instance.Chunk
import net.minestom.server.instance.batch.ChunkBatch
import net.minestom.server.instance.block.Block
import net.minestom.server.utils.BlockPosition
import java.util.HashMap


class Structure {
    private val blocks: MutableMap<BlockPosition, Block> = HashMap<BlockPosition, Block>()
    fun build(batch: ChunkBatch, pos: BlockPosition) {
        blocks.forEach { (bPos: BlockPosition, block: Block?) ->
            if (bPos.x + pos.x >= Chunk.CHUNK_SIZE_X || bPos.x + pos.x < 0) return@forEach
            if (bPos.z + pos.z >= Chunk.CHUNK_SIZE_Z || bPos.z + pos.z < 0) return@forEach
            batch.setBlock(bPos.clone().add(pos), block)
        }
    }

    fun addBlock(block: Block, localX: Int, localY: Int, localZ: Int) {
        blocks[BlockPosition(localX, localY, localZ)] = block
    }
}

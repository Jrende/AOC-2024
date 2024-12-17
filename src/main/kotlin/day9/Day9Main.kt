package day9

import util.FileUtil
import java.math.BigInteger

class Block(val id: Int, val space: Int) {
    override fun toString(): String {
        return "Block(id=$id, space=$space)"
    }
}

class FileSystem(private val blocks: MutableList<Block> = mutableListOf()) {

    fun toDecompressed(): String {
        return this.blocks.joinToString("") {
            (if (it.id == -1) "." else it.id.toString()).repeat(it.space)
        }
    }

    override fun toString(): String {
        return toDecompressed();
    }

    private fun firstFreeSpace(): Block {
        return this.blocks.find { it.id == -1 }!!
    }

    private fun lastFile(): Block {
        return this.blocks.findLast { it.id != -1 }!!
    }

    fun defrag() {
        if (blocks.last().id != -1) blocks.add(Block(-1, 0))
        val totalFreeSpace = this.blocks.filter { it.id == -1 }.sumOf { it.space }
        var iterations = 0;
        while (blocks.last().space != totalFreeSpace) {
            val free = firstFreeSpace();
            val file = lastFile()
            move(file, free)
            if (iterations % 100 == 0) {
                println("${blocks.last().space}/$totalFreeSpace")
            }
            iterations++
        }
    }

    //00...111...2...333.44.5555.6666.777.888899
    //00992111777.44.333....5555.6666.....8888..
    fun defragWholeFiles() {
        var currentId = blocks.findLast { it.id != -1 }!!.id
        while(currentId > 0) {
            val file = blocks.find{ it.id == currentId }!!
            tryMoveFile(file)
            combineBlocks()
            currentId--
        }
    }

    private fun tryMoveFile(file: Block) {
        val freeSpace = blocks.find{ it.id == -1 && it.space >= file.space }
        if(freeSpace == null || blocks.indexOf(freeSpace) > blocks.indexOf(file)) {
            return
        }
        val freeSpaceIndex = blocks.indexOf(freeSpace)
        val fileIndex = blocks.indexOf(file)
        blocks[fileIndex] = Block(-1, file.space)
        blocks.remove(file)


        blocks.add(freeSpaceIndex, file)
        val newFreeSpace =  Block(-1, freeSpace.space - file.space)
        if(newFreeSpace.space > 0) {
            setBlock(freeSpace, newFreeSpace)
        } else {
            blocks.remove(freeSpace)
        }
    }

    fun checksum(): BigInteger {
        var index = 0;
        var checksum = BigInteger.ZERO;
        blocks
            .forEach { block ->
                for (i in 0 until block.space) {
                    val value = block.id;
                    if(value == -1) {
                        index++
                        continue
                    }
                    val checksumValue = BigInteger((value * index++).toString())
                    checksum = checksum.add(checksumValue)
                }
            }
        return checksum
    }

    private fun setBlock(old: Block, new: Block) {
        blocks[blocks.indexOf(old)] = new
    }

    private fun move(file: Block, freeSpace: Block) {
        if (freeSpace.space > file.space) {
            blocks.add(blocks.indexOf(freeSpace), Block(file.id, file.space))
            setBlock(freeSpace, Block(-1, freeSpace.space - file.space))
            setBlock(file, Block(-1, file.space))
        } else {
            setBlock(freeSpace, Block(file.id, freeSpace.space))
            setBlock(file, Block(file.id, file.space - freeSpace.space))
            val end = blocks.last()
            setBlock(end, Block(-1, end.space + freeSpace.space))
        }
        combineBlocks()
    }

    private fun combineBlocks() {
        for (i in (blocks.size - 2) downTo 0) {
            if (blocks[i].id == blocks[i + 1].id) {
                setBlock(blocks[i], Block(blocks[i].id, blocks[i].space + blocks[i + 1].space))
                blocks.remove(blocks[i + 1])
            }
        }
    }
}

fun parse(input: String): FileSystem {
    val fileSystem = mutableListOf<Block>()
    var id = 0;
    input.map { it.digitToInt() }.forEachIndexed { index, value ->
        val isFreeSpace = index % 2 != 0;
        if (!isFreeSpace) {
            fileSystem.add(Block(id++, value))
        } else if (value > 0) {
            fileSystem.add(Block(-1, value))
        }
    }
    return FileSystem(fileSystem)
}


fun main() {
    val input = FileUtil.getInput(9, sample = false)[0]
    val fileSystem = parse(input)
    fileSystem.defragWholeFiles();
    println(fileSystem.toDecompressed())
    println("checksum: ${fileSystem.checksum()}")
}
// 00992111777.44.333....5555.6666.....8888..
// 00992111777.44.333....5555.6666.....8888..
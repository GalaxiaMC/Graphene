// Original code by the PR below, licensed under GNU Lesser General Public License v3.0
// You can find the original code on https://github.com/CaffeineMC/lithium-fabric/pull/123 (Yarn mappings)

package com.notsatvrn.graphene.cache;

import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongList;
import java.util.Iterator;
import net.minecraft.core.BlockPos;

/**
 * @author 2No2Name
 */
public class LongList2BlockPosMutableIterable implements Iterable<BlockPos> {
    private final LongList positions;
    private final int xOffset, yOffset, zOffset;

    public LongList2BlockPosMutableIterable(BlockPos offset, LongList posList) {
        this.xOffset = offset.getX();
        this.yOffset = offset.getY();
        this.zOffset = offset.getZ();
        this.positions = posList;
    }

    @Override
    public Iterator<BlockPos> iterator() {
        return new Iterator<BlockPos>() {
            private final LongIterator it = LongList2BlockPosMutableIterable.this.positions.iterator();
            private final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public net.minecraft.core.BlockPos next() {
                long nextPos = this.it.nextLong();
                return this.pos.set(
                    LongList2BlockPosMutableIterable.this.xOffset + BlockPos.getX(nextPos),
                    LongList2BlockPosMutableIterable.this.yOffset + BlockPos.getY(nextPos),
                    LongList2BlockPosMutableIterable.this.zOffset + BlockPos.getZ(nextPos)
                );
            }
        };
    }
}


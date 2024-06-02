package me.zaksen.manafactory.util;

import net.minecraft.util.math.BlockPos;

public class BlocksHelper {

    public static BlockPos[] getBounding(BlockPos pos) {
        BlockPos[] result = new BlockPos[6];
        result[0] = pos.up();
        result[1] = pos.down();
        result[2] = pos.west();
        result[3] = pos.east();
        result[4] = pos.south();
        result[5] = pos.north();
        return result;
    }
}

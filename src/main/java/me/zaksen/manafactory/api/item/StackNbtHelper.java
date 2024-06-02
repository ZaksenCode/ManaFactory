package me.zaksen.manafactory.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class StackNbtHelper {

    private final ItemStack configuratorStack;

    public StackNbtHelper(ItemStack stack) {
        this.configuratorStack = stack;
    }

    public ItemStack getConfiguratorStack() {
        return configuratorStack;
    }

    public void writeBlockPos(BlockPos pos) {
        writeBlockPos(pos.getX(), pos.getY(), pos.getZ());
    }

    public void writeBlockPos(int x, int y, int z) {
        NbtCompound nbt = getOrCreateNbt();
        nbt.putInt("block_x", x);
        nbt.putInt("block_y", y);
        nbt.putInt("block_z", z);
        writeNbt(nbt);
    }

    public BlockPos readBlockPos() {
        NbtCompound nbt = getOrCreateNbt();
        if(!nbt.contains("block_x") || !nbt.contains("block_y") || !nbt.contains("block_z")) {
            return null;
        }
        return new BlockPos(nbt.getInt("block_x"), nbt.getInt("block_y"), nbt.getInt("block_z"));
    }

    public NbtCompound getOrCreateNbt() {
        NbtCompound nbt = configuratorStack.getNbt();
        if(nbt == null) {
            nbt = new NbtCompound();
        }
        return nbt;
    }

    public void writeNbt(NbtCompound nbt) {
        configuratorStack.setNbt(nbt);
    }
}

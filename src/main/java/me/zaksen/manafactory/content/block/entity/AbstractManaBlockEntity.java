package me.zaksen.manafactory.content.block.entity;

import me.zaksen.manafactory.api.item.StackNbtHelper;
import me.zaksen.manafactory.mana.ManaContainer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractManaBlockEntity extends BlockEntity implements ManaContainer {
    private final int maxCapacity;
    private int capacity;

    public AbstractManaBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int maxCapacity) {
        super(type, pos, state);
        this.maxCapacity = maxCapacity;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public int getFreeCapacity() {
        return getMaxCapacity() - getCapacity();
    }

    @Override
    public void setCapacity(int amount) {
        this.capacity = amount;
        markDirty();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        capacity = nbt.getInt("mana_capacity");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("mana_capacity", capacity);
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        // Override to custom logic
    }

    public abstract ActionResult onConfiguratorUsed(ItemUsageContext context, StackNbtHelper helper);
}

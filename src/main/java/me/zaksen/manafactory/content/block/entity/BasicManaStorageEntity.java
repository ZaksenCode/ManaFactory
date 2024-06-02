package me.zaksen.manafactory.content.block.entity;

import me.zaksen.manafactory.api.item.StackNbtHelper;
import me.zaksen.manafactory.content.block.MFBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class BasicManaStorageEntity extends AbstractManaBlockEntity {

    public BasicManaStorageEntity(BlockPos pos, BlockState state) {
        super(MFBlockEntities.BASIC_MANA_STORAGE, pos, state, 2048);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public ActionResult onConfiguratorUsed(ItemUsageContext context, StackNbtHelper helper) {
        helper.writeBlockPos(pos);
        return ActionResult.SUCCESS;
    }
}

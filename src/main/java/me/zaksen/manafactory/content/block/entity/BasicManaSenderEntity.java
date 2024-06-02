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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BasicManaSenderEntity extends AbstractManaBlockEntity {

    private static final int MAX_SEND_TICK = 40;
    private static final int SEND_AMOUNT = 32;

    private BlockPos sendPos;
    private int sendTick = 0;


    public BasicManaSenderEntity(BlockPos pos, BlockState state) {
        super(MFBlockEntities.BASIC_MANA_SENDER, pos, state, 512);
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
    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

        sendTick++;
        if(sendTick >= MAX_SEND_TICK) {
            sendTick = 0;
            if(sendPos == null) {
                return;
            }
            if(world.getBlockEntity(sendPos) instanceof AbstractManaBlockEntity manaEntity) {
                send(SEND_AMOUNT, manaEntity);
            }
        }
    }

    @Override
    public ActionResult onConfiguratorUsed(ItemUsageContext context, StackNbtHelper helper) {
        BlockPos pos = helper.readBlockPos();

        if(pos != null) {
            sendPos = pos;
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}

package me.zaksen.manafactory.content.block.entity;

import me.zaksen.manafactory.api.item.StackNbtHelper;
import me.zaksen.manafactory.content.block.MFBlockEntities;
import me.zaksen.manafactory.util.BlocksHelper;
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

public class BasicManaGeneratorEntity extends AbstractManaBlockEntity {

    private final static int SEND_AMOUNT = 32;
    private final static int GENERATE_AMOUNT = 64;

    private final static int MAX_SEND_TICK = 20;
    private int sendTick = 0;

    private final static int MAX_GENERATE_TICK = 20;
    private int generateTick;

    public BasicManaGeneratorEntity(BlockPos pos, BlockState state) {
        super(MFBlockEntities.BASIC_MANA_STORAGE, pos, state, 512);
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
        generateTick++;

        if(sendTick >= MAX_SEND_TICK) {
            sendTick = 0;
            for(BlockPos tryPos : BlocksHelper.getBounding(pos)) {
                if(world.getBlockEntity(tryPos) instanceof AbstractManaBlockEntity manaEntity) {
                    send(SEND_AMOUNT, manaEntity);
                }
            }
        }

        if(generateTick >= MAX_GENERATE_TICK) {
            generateTick = 0;
            recieve(GENERATE_AMOUNT, this);
        }
    }

    @Override
    public ActionResult onConfiguratorUsed(ItemUsageContext context, StackNbtHelper helper) {
        helper.writeBlockPos(pos);
        return ActionResult.SUCCESS;
    }
}

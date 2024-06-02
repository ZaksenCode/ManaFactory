package me.zaksen.manafactory.content.block.custom;

import me.zaksen.manafactory.content.block.MFBlockEntities;
import me.zaksen.manafactory.content.block.entity.BasicManaGeneratorEntity;
import me.zaksen.manafactory.content.block.entity.BasicManaStorageEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BasicManaGenerator extends BlockWithEntity {
    public BasicManaGenerator(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BasicManaGeneratorEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, MFBlockEntities.BASIC_MANA_GENERATOR, (_world, pos, _state, be) -> be.tick(_world, pos, _state));
    }
}

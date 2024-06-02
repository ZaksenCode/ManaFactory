package me.zaksen.manafactory.content.block;

import me.zaksen.manafactory.content.block.entity.BasicManaGeneratorEntity;
import me.zaksen.manafactory.content.block.entity.BasicManaSenderEntity;
import me.zaksen.manafactory.content.block.entity.BasicManaStorageEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MFBlockEntities {

    public static final BlockEntityType<BasicManaStorageEntity> BASIC_MANA_STORAGE = registerEntity(
            "basic_mana_storage",
            FabricBlockEntityTypeBuilder.create(BasicManaStorageEntity::new, MFBlocks.BASIC_MANA_STORAGE)
    );

    public static final BlockEntityType<BasicManaSenderEntity> BASIC_MANA_SENDER = registerEntity(
            "basic_mana_sender",
            FabricBlockEntityTypeBuilder.create(BasicManaSenderEntity::new, MFBlocks.BASIC_MANA_SENDER)
    );

    public static final BlockEntityType<BasicManaGeneratorEntity> BASIC_MANA_GENERATOR = registerEntity(
            "basic_mana_generator",
            FabricBlockEntityTypeBuilder.create(BasicManaGeneratorEntity::new, MFBlocks.BASIC_MANA_GENERATOR)
    );

    private static <T extends BlockEntity> BlockEntityType<T> registerEntity(String id, FabricBlockEntityTypeBuilder builder) {
        return Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier("manafactory", id),
            builder.build()
        );
    }

    public static void register() {

    }
}

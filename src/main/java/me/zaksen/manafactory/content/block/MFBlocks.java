package me.zaksen.manafactory.content.block;

import me.zaksen.manafactory.content.block.custom.BasicManaGenerator;
import me.zaksen.manafactory.content.block.custom.BasicManaSender;
import me.zaksen.manafactory.content.block.custom.BasicManaStorage;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MFBlocks {

    public static final BasicManaStorage BASIC_MANA_STORAGE = registerBlock(
            "basic_mana_storage",
            new BasicManaStorage(FabricBlockSettings.create())
    );

    public static final BasicManaSender BASIC_MANA_SENDER = registerBlock(
            "basic_mana_sender",
            new BasicManaSender(FabricBlockSettings.create())
    );

    public static final BasicManaGenerator BASIC_MANA_GENERATOR = registerBlock(
            "basic_mana_generator",
            new BasicManaGenerator(FabricBlockSettings.create())
    );

    public static <T extends Block> T registerBlock(String id, T block) {
        return Registry.register(
                Registries.BLOCK,
                new Identifier("manafactory", id),
                block
        );
    }

    public static void register() {

    }
}

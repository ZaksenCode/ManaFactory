package me.zaksen.manafactory.content.item;

import me.zaksen.manafactory.content.block.MFBlocks;
import me.zaksen.manafactory.content.item.custom.ManaAnalyzer;
import me.zaksen.manafactory.content.item.custom.ManaConfigurator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MFItems {

    public static final BlockItem BASIC_MANA_STORAGE = registerItem(
            "basic_mana_storage",
            new BlockItem(MFBlocks.BASIC_MANA_STORAGE, new FabricItemSettings())
    );

    public static final BlockItem BASIC_MANA_SENDER = registerItem(
            "basic_mana_sender",
            new BlockItem(MFBlocks.BASIC_MANA_SENDER, new FabricItemSettings())
    );

    public static final BlockItem BASIC_MANA_GENERATOR = registerItem(
            "basic_mana_generator",
            new BlockItem(MFBlocks.BASIC_MANA_GENERATOR, new FabricItemSettings())
    );

    public static final Item MANA_CONFIGURATOR = registerItem("mana_configurator", new ManaConfigurator(new FabricItemSettings()));
    public static final Item MANA_ANALYZER = registerItem("mana_analyzer", new ManaAnalyzer(new FabricItemSettings()));

    private static <T extends Item> T registerItem(String id, T item) {
        return Registry.register(
                Registries.ITEM,
                new Identifier("manafactory", id),
                item
        );
    }

    public static void register() {

    }
}

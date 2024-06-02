package me.zaksen.manafactory.content;

import me.zaksen.manafactory.content.block.MFBlockEntities;
import me.zaksen.manafactory.content.block.MFBlocks;
import me.zaksen.manafactory.content.item.MFItems;

public class MFContent {

    public static void register() {
        MFItems.register();
        MFBlocks.register();
        MFBlockEntities.register();
    }
}

package me.zaksen.manafactory;

import me.zaksen.manafactory.content.MFContent;
import net.fabricmc.api.ModInitializer;

public class ManaFactory implements ModInitializer {
    @Override
    public void onInitialize() {
        MFContent.register();
    }
}

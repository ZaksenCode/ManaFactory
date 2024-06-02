package me.zaksen.manafactory.content.item.custom;

import me.zaksen.manafactory.content.block.entity.AbstractManaBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ManaAnalyzer extends Item {

    public ManaAnalyzer(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();

        if(world.isClient()) {
            return ActionResult.PASS;
        }

        if(world.getBlockEntity(blockPos) instanceof AbstractManaBlockEntity manaEntity) {
            context.getPlayer().sendMessage(Text.of(String.format("%s / %s", manaEntity.getCapacity(), manaEntity.getMaxCapacity())), true);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}

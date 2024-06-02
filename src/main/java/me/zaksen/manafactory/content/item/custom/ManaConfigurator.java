package me.zaksen.manafactory.content.item.custom;

import me.zaksen.manafactory.api.item.StackNbtHelper;
import me.zaksen.manafactory.content.block.entity.AbstractManaBlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ManaConfigurator extends Item {

    public ManaConfigurator(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos usePos = context.getBlockPos();

        if(world.isClient()) {
            return ActionResult.PASS;
        }

        if(world.getBlockEntity(usePos) instanceof AbstractManaBlockEntity manaEntity) {
            StackNbtHelper helper = new StackNbtHelper(context.getStack());
            return manaEntity.onConfiguratorUsed(context, helper);
        }

        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        StackNbtHelper helper = new StackNbtHelper(stack);
        BlockPos pos = helper.readBlockPos();

        if(pos != null) {
            tooltip.add(
                    Text.literal("Contains block pos: ")
                    .append(String.valueOf(pos.getX()))
                    .append(" ")
                    .append(String.valueOf(pos.getY()))
                    .append(" ")
                    .append(String.valueOf(pos.getZ()))
            );
        }
    }
}

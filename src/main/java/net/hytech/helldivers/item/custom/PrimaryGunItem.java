package net.hytech.helldivers.item.custom;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class PrimaryGunItem extends Item {

    public PrimaryGunItem(Properties pProperties) {
        super(pProperties);

    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide) {
            Player player = pContext.getPlayer();

        }

        return InteractionResult.SUCCESS;
    }
}

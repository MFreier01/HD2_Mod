package net.hytech.helldivers.event;

import net.hytech.helldivers.Helldivers;
import net.hytech.helldivers.entity.ModEntityTypes;
import net.hytech.helldivers.entity.custom.ScavengerEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = Helldivers.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.SCAVENGER.get(), ScavengerEntity.setAttributes());
    }
}

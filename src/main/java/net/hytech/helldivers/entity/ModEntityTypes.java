package net.hytech.helldivers.entity;

import net.hytech.helldivers.Helldivers;
import net.hytech.helldivers.entity.custom.ScavengerEntity;
import net.minecraft.ResourceLocationException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;
import javax.swing.text.html.parser.Entity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Helldivers.MOD_ID);

    public static final RegistryObject<EntityType<ScavengerEntity>> SCAVENGER =
            ENTITY_TYPES.register("scavenger",
                    () -> EntityType.Builder.of(ScavengerEntity::new, MobCategory.CREATURE)
                            .sized(1.0f,0.5f)
                            .build(new ResourceLocation(Helldivers.MOD_ID, "scavenger").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register((eventBus));
    }
}

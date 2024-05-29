package net.hytech.helldivers.item.client;

import net.hytech.helldivers.Helldivers;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import net.hytech.helldivers.item.custom.ar23_liberatorItem;

public class ar23_liberatorModel extends GeoModel<ar23_liberatorItem> {
    @Override
    public ResourceLocation getModelResource(ar23_liberatorItem ar23LiberatorItem) {
        return new ResourceLocation(Helldivers.MOD_ID, "geo/ar23_liberator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ar23_liberatorItem ar23LiberatorItem) {
        return new ResourceLocation(Helldivers.MOD_ID, "textures/item/ar23_liberator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ar23_liberatorItem ar23LiberatorItem) {
        return new ResourceLocation(Helldivers.MOD_ID, "animations/ar23_liberator.animation.json");
    }
}

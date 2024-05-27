package net.hytech.helldivers.entity.client;

import net.hytech.helldivers.Helldivers;
import net.hytech.helldivers.entity.custom.ScavengerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class scavengermodel extends GeoModel<ScavengerEntity> {
    @Override
    public ResourceLocation getModelResource(ScavengerEntity scavengerEntity) {
        return new ResourceLocation(Helldivers.MOD_ID, "geo/scavenger.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ScavengerEntity scavengerEntity) {
        return new ResourceLocation(Helldivers.MOD_ID, "textures/entity/scavenger.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ScavengerEntity scavengerEntity) {
        return new ResourceLocation(Helldivers.MOD_ID, "animations/scavenger.animation.json");
    }

    @Override
    public void setCustomAnimations(ScavengerEntity animatable, long instanceId, AnimationState<ScavengerEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("all_head");

        if (head != null) {
            EntityModelData entityData = animationState.getData((DataTickets.ENTITY_MODEL_DATA));

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}

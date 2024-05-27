package net.hytech.helldivers.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hytech.helldivers.Helldivers;
import net.hytech.helldivers.entity.custom.ScavengerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class scavengerRenderer extends GeoEntityRenderer<ScavengerEntity> {
    public scavengerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new scavengermodel());
    }

    @Override
    public ResourceLocation getTextureLocation(ScavengerEntity animatable) {
        return new ResourceLocation(Helldivers.MOD_ID, "textures/entity/scavenger.png");
    }

    @Override
    public void render(ScavengerEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

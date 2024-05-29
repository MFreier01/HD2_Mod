package net.hytech.helldivers.item.custom;

import net.hytech.helldivers.item.client.ar23_liberatorRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;


import java.util.function.Consumer;

public class ar23_liberatorItem extends Item implements  GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public ar23_liberatorItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>( this, "controller", 0 , this::predicate));
    }

    private PlayState predicate(AnimationState<ar23_liberatorItem> ar23LiberatorItemAnimationState) {

        ar23LiberatorItemAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.ar23.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public double getTick(Object itemStack){
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private ar23_liberatorRenderer renderer;


            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
               if(this.renderer == null){
                   renderer = new ar23_liberatorRenderer();
               }
                return this.renderer;
            }
        });
    }
}

package net.hytech.helldivers.entity.projectile;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class grenade_explosion extends ThrowableItemProjectile {

    public grenade_explosion(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public grenade_explosion(Level pLevel, LivingEntity pShooter) {
        super(EntityType.SNOWBALL, pShooter, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    private ParticleOptions getParticle() {
        ItemStack $$0 = this.getItemRaw();
        return (ParticleOptions)($$0.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, $$0));
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            ParticleOptions $$1 = this.getParticle();

            for(int $$2 = 0; $$2 < 8; ++$$2) {
                this.level().addParticle($$1, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level().isClientSide) {
            Entity entity = pResult.getEntity();
            Entity entity1 = this.getOwner();
            boolean flag;
            LivingEntity livingentity1;
            if (entity1 instanceof LivingEntity) {
                livingentity1 = (LivingEntity) entity1;
                flag = entity.hurt(this.damageSources().generic(), 4.0F);
                if (flag) {
                        this.doEnchantDamageEffects(livingentity1, entity);
                }
            }
            else {
                flag = entity.hurt(this.damageSources().magic(), 5.0F);
            }


        }
    }

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 4.0F, false, Level.ExplosionInteraction.MOB);
            this.discard();
        }

    }





}

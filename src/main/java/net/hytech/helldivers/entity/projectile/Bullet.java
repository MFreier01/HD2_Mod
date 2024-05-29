//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hytech.helldivers.entity.projectile;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class Bullet extends AbstractArrow {
    private static final int EXPOSED_POTION_DECAY_TIME = 600;
    private static final int NO_EFFECT_COLOR = -1;
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR;
    private static final byte EVENT_POTION_PUFF = 0;
    private Potion potion;
    private final Set<MobEffectInstance> effects;
    private boolean fixedColor;

    public Bullet(EntityType<? extends Bullet> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.potion = Potions.EMPTY;
        this.effects = Sets.newHashSet();
    }

    public Bullet(Level pLevel, double pX, double pY, double pZ) {
        super(EntityType.ARROW, pX, pY, pZ, pLevel);
        this.potion = Potions.EMPTY;
        this.effects = Sets.newHashSet();
    }

    public Bullet(Level pLevel, LivingEntity pShooter) {
        super(EntityType.ARROW, pShooter, pLevel);
        this.potion = Potions.EMPTY;
        this.effects = Sets.newHashSet();
    }

    public void setEffectsFromItem(ItemStack pStack) {
        if (pStack.is(Items.TIPPED_ARROW)) {
            this.potion = PotionUtils.getPotion(pStack);
            Collection<MobEffectInstance> $$1 = PotionUtils.getCustomEffects(pStack);
            if (!$$1.isEmpty()) {
                Iterator var3 = $$1.iterator();

                while(var3.hasNext()) {
                    MobEffectInstance $$2 = (MobEffectInstance)var3.next();
                    this.effects.add(new MobEffectInstance($$2));
                }
            }

            int $$3 = getCustomColor(pStack);
            if ($$3 == -1) {
                this.updateColor();
            } else {
                this.setFixedColor($$3);
            }
        } else if (pStack.is(Items.ARROW)) {
            this.potion = Potions.EMPTY;
            this.effects.clear();
            this.entityData.set(ID_EFFECT_COLOR, -1);
        }

    }

    public static int getCustomColor(ItemStack pStack) {
        CompoundTag $$1 = pStack.getTag();
        return $$1 != null && $$1.contains("CustomPotionColor", 99) ? $$1.getInt("CustomPotionColor") : -1;
    }

    private void updateColor() {
        this.fixedColor = false;
        if (this.potion == Potions.EMPTY && this.effects.isEmpty()) {
            this.entityData.set(ID_EFFECT_COLOR, -1);
        } else {
            this.entityData.set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
        }

    }

    public void addEffect(MobEffectInstance pEffectInstance) {
        this.effects.add(pEffectInstance);
        this.getEntityData().set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_EFFECT_COLOR, -1);
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.makeParticle(1);
                }
            } else {
                this.makeParticle(2);
            }
        } else if (this.inGround && this.inGroundTime != 0 && !this.effects.isEmpty() && this.inGroundTime >= 600) {
            this.level().broadcastEntityEvent(this, (byte)0);
            this.potion = Potions.EMPTY;
            this.effects.clear();
            this.entityData.set(ID_EFFECT_COLOR, -1);
        }

    }

    private void makeParticle(int pParticleAmount) {
        int $$1 = this.getColor();
        if ($$1 != -1 && pParticleAmount > 0) {
            double $$2 = (double)($$1 >> 16 & 255) / 255.0;
            double $$3 = (double)($$1 >> 8 & 255) / 255.0;
            double $$4 = (double)($$1 >> 0 & 255) / 255.0;

            for(int $$5 = 0; $$5 < pParticleAmount; ++$$5) {
                this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), $$2, $$3, $$4);
            }

        }
    }

    public int getColor() {
        return (Integer)this.entityData.get(ID_EFFECT_COLOR);
    }

    private void setFixedColor(int pFixedColor) {
        this.fixedColor = true;
        this.entityData.set(ID_EFFECT_COLOR, pFixedColor);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.potion != Potions.EMPTY) {
            pCompound.putString("Potion", BuiltInRegistries.POTION.getKey(this.potion).toString());
        }

        if (this.fixedColor) {
            pCompound.putInt("Color", this.getColor());
        }

        if (!this.effects.isEmpty()) {
            ListTag $$1 = new ListTag();
            Iterator var3 = this.effects.iterator();

            while(var3.hasNext()) {
                MobEffectInstance $$2 = (MobEffectInstance)var3.next();
                $$1.add($$2.save(new CompoundTag()));
            }

            pCompound.put("CustomPotionEffects", $$1);
        }

    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Potion", 8)) {
            this.potion = PotionUtils.getPotion(pCompound);
        }

        Iterator var2 = PotionUtils.getCustomEffects(pCompound).iterator();

        while(var2.hasNext()) {
            MobEffectInstance $$1 = (MobEffectInstance)var2.next();
            this.addEffect($$1);
        }

        if (pCompound.contains("Color", 99)) {
            this.setFixedColor(pCompound.getInt("Color"));
        } else {
            this.updateColor();
        }

    }

    protected void doPostHurtEffects(LivingEntity pLiving) {
        super.doPostHurtEffects(pLiving);
        Entity $$1 = this.getEffectSource();
        Iterator var3 = this.potion.getEffects().iterator();

        MobEffectInstance $$3;
        while(var3.hasNext()) {
            $$3 = (MobEffectInstance)var3.next();
            pLiving.addEffect(new MobEffectInstance($$3.getEffect(), Math.max($$3.mapDuration((p_268168_) -> {
                return p_268168_ / 8;
            }), 1), $$3.getAmplifier(), $$3.isAmbient(), $$3.isVisible()), $$1);
        }

        if (!this.effects.isEmpty()) {
            var3 = this.effects.iterator();

            while(var3.hasNext()) {
                $$3 = (MobEffectInstance)var3.next();
                pLiving.addEffect($$3, $$1);
            }
        }

    }

    protected ItemStack getPickupItem() {
        if (this.effects.isEmpty() && this.potion == Potions.EMPTY) {
            return new ItemStack(Items.ARROW);
        } else {
            ItemStack $$0 = new ItemStack(Items.TIPPED_ARROW);
            PotionUtils.setPotion($$0, this.potion);
            PotionUtils.setCustomEffects($$0, this.effects);
            if (this.fixedColor) {
                $$0.getOrCreateTag().putInt("CustomPotionColor", this.getColor());
            }

            return $$0;
        }
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 0) {
            int $$1 = this.getColor();
            if ($$1 != -1) {
                double $$2 = (double)($$1 >> 16 & 255) / 255.0;
                double $$3 = (double)($$1 >> 8 & 255) / 255.0;
                double $$4 = (double)($$1 >> 0 & 255) / 255.0;

                for(int $$5 = 0; $$5 < 20; ++$$5) {
                    this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), $$2, $$3, $$4);
                }
            }
        } else {
            super.handleEntityEvent(pId);
        }

    }

    static {
        ID_EFFECT_COLOR = SynchedEntityData.defineId(Bullet.class, EntityDataSerializers.INT);
    }
}

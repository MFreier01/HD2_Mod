package net.hytech.helldivers.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

import static net.minecraft.world.level.block.TntBlock.explode;

public class automatoncontactmineBlock extends Block {
    public automatoncontactmineBlock(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        explode(pLevel, pPos, null);
        pLevel.removeBlock(pPos, false);

        super.stepOn(pLevel, pPos, pState, pEntity);
    }


    private static void explode(Level pLevel, BlockPos pPos, @Nullable LivingEntity pEntity) {
        if (!pLevel.isClientSide) {
            //Explosion explosion = new Explosion(pLevel,pEntity, (double)pPos.getX(), (double)pPos.getY(),(double)pPos.getZ(), 4.0f,false, Explosion.BlockInteraction.DESTROY);
            PrimedTnt primedtnt = new PrimedTnt(pLevel, (double)pPos.getX() + 0.5, (double)pPos.getY(), (double)pPos.getZ() + 0.5, pEntity);
            pLevel.explode(pEntity, (double)pPos.getX(), (double)pPos.getY() + 1,(double)pPos.getZ(), 4.0f, Level.ExplosionInteraction.BLOCK);
            pLevel.playSound((Player)null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);

        }

    }

    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        if (!pLevel.isClientSide) {
            BlockPos blockpos = pHit.getBlockPos();
            Entity entity = pProjectile.getOwner();
            if (pProjectile.mayInteract(pLevel, blockpos)) {
                explode(pLevel, blockpos, null);
                pLevel.removeBlock(blockpos, false);
            }
        }
    }

    public boolean dropFromExplosion(Explosion pExplosion) {
        return false;
    }

}

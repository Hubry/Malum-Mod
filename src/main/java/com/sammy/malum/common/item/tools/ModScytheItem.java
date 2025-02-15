package com.sammy.malum.common.item.tools;

import com.sammy.malum.core.helper.ItemHelper;
import com.sammy.malum.core.setup.DamageSourceRegistry;
import com.sammy.malum.core.setup.ParticleRegistry;
import com.sammy.malum.core.setup.SoundRegistry;
import com.sammy.malum.core.setup.item.ItemRegistry;
import com.sammy.malum.core.systems.item.IEventResponderItem;
import com.sammy.malum.core.systems.item.ModCombatItem;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ModScytheItem extends ModCombatItem implements IEventResponderItem {

    public ModScytheItem(Tier tier, float attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn + 3, attackSpeedIn - 3.2f, builderIn);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player) {
            SoundEvent sound;
            if (ItemHelper.hasCurioEquipped(attacker, ItemRegistry.NECKLACE_OF_THE_NARROW_EDGE)) {
                spawnSweepParticles((Player) attacker, ParticleRegistry.SCYTHE_CUT_ATTACK_PARTICLE.get());
                sound = SoundRegistry.SCYTHE_CUT;
            } else {
                spawnSweepParticles((Player) attacker, ParticleRegistry.SCYTHE_SWEEP_ATTACK_PARTICLE.get());
                sound = SoundEvents.PLAYER_ATTACK_SWEEP;
            }
            attacker.level.playSound(null, target.getX(), target.getY(), target.getZ(), sound, attacker.getSoundSource(), 1, 1);
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void hurtEvent(LivingHurtEvent event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (ItemHelper.hasCurioEquipped(attacker, ItemRegistry.NECKLACE_OF_THE_NARROW_EDGE)) {
            return;
        }
        float damage = event.getAmount() * (0.5f + EnchantmentHelper.getSweepingDamageRatio(attacker));
        target.level.getEntities(attacker, target.getBoundingBox().inflate(1)).forEach(e -> {
            if (e instanceof LivingEntity livingEntity) {
                if (livingEntity.isAlive() && !event.getSource().getMsgId().equals(DamageSourceRegistry.SCYTHE_SWEEP_DAMAGE)) {
                    livingEntity.hurt(DamageSourceRegistry.scytheSweepDamage(attacker), damage);
                    livingEntity.knockback(0.4F, Mth.sin(attacker.getYRot() * ((float) Math.PI / 180F)), (-Mth.cos(attacker.getYRot() * ((float) Math.PI / 180F))));
                }
            }
        });
    }

    public void spawnSweepParticles(Player player, SimpleParticleType type) {
        double d0 = (-Mth.sin(player.getYRot() * ((float) Math.PI / 180F)));
        double d1 = Mth.cos(player.getYRot() * ((float) Math.PI / 180F));
        if (player.level instanceof ServerLevel) {
            ((ServerLevel) player.level).sendParticles(type, player.getX() + d0, player.getY(0.5D), player.getZ() + d1, 0, d0, 0.0D, d1, 0.0D);
        }
    }
}
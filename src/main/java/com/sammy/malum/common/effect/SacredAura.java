package com.sammy.malum.common.effect;

import com.sammy.malum.core.helper.ClientHelper;
import com.sammy.malum.core.registry.content.SpiritTypeRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SacredAura extends MobEffect
{
    public SacredAura()
    {
        super(MobEffectCategory.BENEFICIAL, ClientHelper.getColor(SpiritTypeRegistry.SACRED_SPIRIT_COLOR));
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier)
    {
        if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth())
        {
            entityLivingBaseIn.heal(1.0F);
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier)
    {
        int k = 50 >> amplifier;
        if (k > 0)
        {
            return duration % k == 0;
        }
        else
        {
            return true;
        }
    }
}
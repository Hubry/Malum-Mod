package com.sammy.malum.common.enchantment;

import com.sammy.malum.common.entity.boomerang.ScytheBoomerangEntity;
import com.sammy.malum.core.setup.AttributeRegistry;
import com.sammy.malum.core.setup.enchantment.MalumEnchantments;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ReboundEnchantment extends Enchantment
{
    public ReboundEnchantment()
    {
        super(Rarity.UNCOMMON, MalumEnchantments.REBOUND_SCYTHE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }
    
    @Override
    public int getMaxLevel()
    {
        return 3;
    }

    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getEntityLiving() instanceof Player player) {
            ItemStack stack = event.getItemStack();
            int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(MalumEnchantments.REBOUND.get(), stack);
            if (enchantmentLevel > 0) {
                Level level = player.level;
                if (!level.isClientSide) {
                    player.setItemInHand(event.getHand(), ItemStack.EMPTY);
                    double baseDamage = player.getAttributes().getValue(Attributes.ATTACK_DAMAGE);
                    float multiplier = 1.2f;
                    double damage = 1.0F + baseDamage * multiplier;

                    int slot = event.getHand() == InteractionHand.OFF_HAND ? player.getInventory().getContainerSize() - 1 : player.getInventory().selected;
                    ScytheBoomerangEntity entity = new ScytheBoomerangEntity(level);
                    entity.setPos(player.position().x, player.position().y + player.getBbHeight() / 2f, player.position().z);

                    entity.setData((float) damage, player.getUUID(), slot, stack);
                    entity.getEntityData().set(ScytheBoomerangEntity.SCYTHE, stack);

                    entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, (float) (1.5F + player.getAttributeValue(AttributeRegistry.SCYTHE_PROFICIENCY.get()) * 0.125f), 0F);
                    level.addFreshEntity(entity);
                }
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
            }
        }
    }
}

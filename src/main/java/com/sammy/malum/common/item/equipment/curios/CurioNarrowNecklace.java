package com.sammy.malum.common.item.equipment.curios;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.sammy.malum.core.setup.AttributeRegistry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class CurioNarrowNecklace extends MalumCurioItem
{
    private static final UUID ATTRIBUTE_UUID = UUID.fromString("2b21fa84-f9f2-4fa0-a225-0997206ee54b");
    public CurioNarrowNecklace(Properties builder)
    {
        super(builder);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(String identifier, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> map = HashMultimap.create();
        map.put(AttributeRegistry.SCYTHE_PROFICIENCY.get(), new AttributeModifier(ATTRIBUTE_UUID, "Curio scythe proficiency", 4f, AttributeModifier.Operation.ADDITION));
        return map;
    }
    @Override
    public boolean isOrnate()
    {
        return true;
    }

}
package com.sammy.malum.core.init;

import com.sammy.malum.MalumHelper;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class MalumItemTags
{
    public static ITag.INamedTag<Item> UNHOLY_TRINKET;
    public static ITag.INamedTag<Item> SUN_KISSED_LOGS;
    public static ITag.INamedTag<Item> TAINTED_LOGS;
    public static ITag.INamedTag<Item> LAVENDER;
    public static ITag.INamedTag<Item> SPIRITED_STEEL_BLOCKS;
    public static ITag.INamedTag<Item> TRANSMISSIVE_METAL_BLOCKS;
    public static ITag.INamedTag<Item> SAPBALLS;
    
    public static ITag.INamedTag<Item> makeWrapperTag(String id)
    {
        return ItemTags.createOptional(MalumHelper.prefix(id));
    }
    
    public static void init()
    {
        UNHOLY_TRINKET = makeWrapperTag("unholy_trinket");
        SUN_KISSED_LOGS = makeWrapperTag("sun_logs");
        TAINTED_LOGS = makeWrapperTag("tainted_logs");
        LAVENDER = makeWrapperTag("lavender");
        SPIRITED_STEEL_BLOCKS = makeWrapperTag("spirited_steel_blocks");
        TRANSMISSIVE_METAL_BLOCKS = makeWrapperTag("transmissive_metal_blocks");
        SAPBALLS = makeWrapperTag("sapballs");
    }
}
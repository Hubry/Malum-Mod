package com.sammy.malum.core.data;

import com.sammy.malum.core.data.builder.NBTCarryRecipeBuilder;
import com.sammy.malum.core.setup.item.ItemRegistry;
import com.sammy.malum.core.setup.item.ItemTagRegistry;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static com.sammy.malum.core.helper.DataHelper.prefix;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;
import static net.minecraft.data.recipes.SimpleCookingRecipeBuilder.blasting;
import static net.minecraft.data.recipes.SimpleCookingRecipeBuilder.smelting;
import static net.minecraft.data.recipes.SingleItemRecipeBuilder.*;

public class MalumRecipes extends RecipeProvider
{
    public MalumRecipes(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    public String getName()
    {
        return "Malum Recipe Provider";
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapelessRecipeBuilder.shapeless(ItemRegistry.ENCYCLOPEDIA_ARCANA.get()).requires(Items.BOOK).requires(ItemRegistry.PROCESSED_SOULSTONE.get()).unlockedBy("has_soulstone", has(ItemRegistry.PROCESSED_SOULSTONE.get())).save(consumer);
        shapeless(ItemRegistry.COAL_FRAGMENT.get(),8).requires(Items.COAL).unlockedBy("has_coal", has(Items.COAL)).save(consumer);

        shaped(Items.COPPER_INGOT).define('#', ItemRegistry.COPPER_NUGGET.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_copper", has(Items.COPPER_INGOT)).save(consumer, prefix("copper_ingot"));

        smelting(Ingredient.of(ItemRegistry.BLAZING_QUARTZ_ORE.get()), ItemRegistry.BLAZING_QUARTZ.get(),0.25f,200).unlockedBy("has_blaze_quartz", has(ItemRegistry.BLAZING_QUARTZ.get())).save(consumer);
        blasting(Ingredient.of(ItemRegistry.BLAZING_QUARTZ_ORE.get()), ItemRegistry.BLAZING_QUARTZ.get(),0.25f,100).unlockedBy("has_blaze_quartz", has(ItemRegistry.BLAZING_QUARTZ.get())).save(consumer, prefix("blazing_quartz_from_blasting"));
        shaped(ItemRegistry.BLOCK_OF_BLAZING_QUARTZ.get()).define('#', ItemRegistry.BLAZING_QUARTZ.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_blaze_quartz", has(ItemRegistry.BLAZING_QUARTZ.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ItemRegistry.BLAZING_QUARTZ.get(), 9).requires(ItemRegistry.BLOCK_OF_BLAZING_QUARTZ.get()).unlockedBy("has_blaze_quartz", has(ItemRegistry.BLAZING_QUARTZ.get())).save(consumer, prefix("blazing_quartz_from_block"));
        shaped(Items.NETHERRACK, 2).define('Z', ItemRegistry.BLAZING_QUARTZ.get()).define('Y', Tags.Items.COBBLESTONE).pattern("ZY").pattern("YZ").unlockedBy("has_blazing_quartz", has(ItemRegistry.BLAZING_QUARTZ.get())).save(consumer, prefix("netherrack_from_blazing_quartz"));

        shapeless(ItemRegistry.BLAZING_QUARTZ_FRAGMENT.get(),8).requires(ItemRegistry.BLAZING_QUARTZ.get()).unlockedBy("has_blazing_quartz", has(ItemRegistry.BLAZING_QUARTZ.get())).save(consumer);
        shapeless(ItemRegistry.CHARCOAL_FRAGMENT.get(),8).requires(Items.CHARCOAL).unlockedBy("has_charcoal", has(Items.CHARCOAL)).save(consumer);

        shaped(ItemRegistry.BLOCK_OF_ARCANE_CHARCOAL.get()).define('#', ItemRegistry.ARCANE_CHARCOAL.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_arcane_charcoal", has(ItemRegistry.ARCANE_CHARCOAL.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ItemRegistry.ARCANE_CHARCOAL.get(), 9).requires(ItemRegistry.BLOCK_OF_ARCANE_CHARCOAL.get()).unlockedBy("has_arcane_charcoal", has(ItemRegistry.ARCANE_CHARCOAL.get())).save(consumer, prefix("arcane_charcoal_from_block"));
        shapeless(ItemRegistry.ARCANE_CHARCOAL_FRAGMENT.get(),8).requires(ItemRegistry.ARCANE_CHARCOAL.get()).unlockedBy("has_arcane_charcoal", has(ItemRegistry.ARCANE_CHARCOAL.get())).save(consumer);

        smelting(Ingredient.of(ItemTagRegistry.RUNEWOOD_LOGS), ItemRegistry.ARCANE_CHARCOAL.get(),0.1f,200).unlockedBy("has_runewood_planks", has(ItemTagRegistry.RUNEWOOD_LOGS)).save(consumer);
        smelting(Ingredient.of(ItemTagRegistry.SOULWOOD_LOGS), ItemRegistry.ARCANE_CHARCOAL.get(),0.1f,200).unlockedBy("has_soulwood_planks", has(ItemTagRegistry.SOULWOOD_LOGS)).save(consumer, prefix("arcane_charcoal_from_soulwood"));

        shaped(ItemRegistry.SPIRIT_ALTAR.get()).define('Z', Tags.Items.INGOTS_GOLD).define('Y', ItemRegistry.PROCESSED_SOULSTONE.get()).define('X', ItemRegistry.RUNEWOOD_PLANKS.get()).pattern(" Y ").pattern("ZXZ").pattern("XXX").unlockedBy("has_soulstone", has(ItemRegistry.SOULSTONE_CLUSTER.get())).save(consumer);
        shaped(ItemRegistry.SPIRIT_JAR.get()).define('Z', ItemRegistry.HALLOWED_GOLD_INGOT.get()).define('Y', Tags.Items.GLASS_PANES).pattern("YZY").pattern("Y Y").pattern("YYY").unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get())).save(consumer);

        smelting(Ingredient.of(ItemRegistry.BRILLIANT_STONE.get()), ItemRegistry.BRILLIANCE_CHUNK.get(),0.25f,200).unlockedBy("has_brilliance", has(ItemRegistry.BRILLIANCE_CLUSTER.get())).save(consumer);
        blasting(Ingredient.of(ItemRegistry.BRILLIANT_STONE.get()), ItemRegistry.BRILLIANCE_CHUNK.get(),0.25f,100).unlockedBy("has_brilliance", has(ItemRegistry.BRILLIANCE_CLUSTER.get())).save(consumer, prefix("brilliance_from_blasting"));
        smelting(Ingredient.of(ItemRegistry.BRILLIANT_DEEPSLATE.get()), ItemRegistry.BRILLIANCE_CHUNK.get(),0.25f,200).unlockedBy("has_brilliance", has(ItemRegistry.BRILLIANCE_CLUSTER.get())).save(consumer, prefix("brilliance_from_deepslate"));
        blasting(Ingredient.of(ItemRegistry.BRILLIANT_DEEPSLATE.get()), ItemRegistry.BRILLIANCE_CHUNK.get(),0.25f,100).unlockedBy("has_brilliance", has(ItemRegistry.BRILLIANCE_CLUSTER.get())).save(consumer, prefix("brilliance_from_deepslate_blasting"));
        shaped(ItemRegistry.BLOCK_OF_BRILLIANCE.get()).define('#', ItemRegistry.BRILLIANCE_CHUNK.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_brilliance", has(ItemRegistry.BRILLIANCE_CLUSTER.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ItemRegistry.BRILLIANCE_CHUNK.get(), 9).requires(ItemRegistry.BLOCK_OF_BRILLIANCE.get()).unlockedBy("has_brilliance", has(ItemRegistry.BRILLIANCE_CLUSTER.get())).save(consumer, prefix("brilliance_from_block"));

        smelting(Ingredient.of(ItemRegistry.SOULSTONE_ORE.get()), ItemRegistry.PROCESSED_SOULSTONE.get(),0.25f,200).unlockedBy("has_soulstone", has(ItemRegistry.SOULSTONE_CLUSTER.get())).save(consumer);
        blasting(Ingredient.of(ItemRegistry.SOULSTONE_ORE.get()), ItemRegistry.PROCESSED_SOULSTONE.get(),0.25f,100).unlockedBy("has_soulstone", has(ItemRegistry.SOULSTONE_CLUSTER.get())).save(consumer, prefix("soulstone_from_blasting"));
        smelting(Ingredient.of(ItemRegistry.DEEPSLATE_SOULSTONE_ORE.get()), ItemRegistry.PROCESSED_SOULSTONE.get(),0.25f,200).unlockedBy("has_soulstone", has(ItemRegistry.SOULSTONE_CLUSTER.get())).save(consumer, prefix("soulstone_from_deepslate"));
        blasting(Ingredient.of(ItemRegistry.DEEPSLATE_SOULSTONE_ORE.get()), ItemRegistry.PROCESSED_SOULSTONE.get(),0.25f,100).unlockedBy("has_soulstone", has(ItemRegistry.SOULSTONE_CLUSTER.get())).save(consumer, prefix("soulstone_from_deepslate_blasting"));
        shaped(ItemRegistry.BLOCK_OF_SOULSTONE.get()).define('#', ItemRegistry.PROCESSED_SOULSTONE.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_soulstone", has(ItemRegistry.SOULSTONE_CLUSTER.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ItemRegistry.PROCESSED_SOULSTONE.get(), 9).requires(ItemRegistry.BLOCK_OF_SOULSTONE.get()).unlockedBy("has_soulstone", has(ItemRegistry.SOULSTONE_CLUSTER.get())).save(consumer, prefix("soulstone_from_block"));

        ShapelessRecipeBuilder.shapeless(ItemRegistry.HOLY_SAPBALL.get(), 3).requires(ItemRegistry.HOLY_SAP.get()).requires(Items.SLIME_BALL).unlockedBy("has_holy_extract", has(ItemRegistry.HOLY_SAP.get())).save(consumer);
        smelting(Ingredient.of(ItemRegistry.HOLY_SAP.get()), ItemRegistry.HOLY_SYRUP.get(),0.1f,200).unlockedBy("has_holy_extract", has(ItemRegistry.HOLY_SAP.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(ItemRegistry.UNHOLY_SAPBALL.get(), 3).requires(ItemRegistry.UNHOLY_SAP.get()).requires(Items.SLIME_BALL).unlockedBy("has_unholy_extract", has(ItemRegistry.UNHOLY_SAP.get())).save(consumer);
        smelting(Ingredient.of(ItemRegistry.UNHOLY_SAP.get()), ItemRegistry.UNHOLY_SYRUP.get(),0.1f,200).unlockedBy("has_unholy_extract", has(ItemRegistry.UNHOLY_SAP.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(Items.MAGMA_CREAM).requires(Items.BLAZE_POWDER).requires(ItemTagRegistry.SAPBALLS).unlockedBy("has_sapball", has(ItemTagRegistry.SAPBALLS)).save(consumer, prefix("magma_cream_from_sapballs"));
        shaped(Blocks.STICKY_PISTON).define('P', Blocks.PISTON).define('S', ItemTagRegistry.SAPBALLS).pattern("S").pattern("P").unlockedBy("has_sapball", has(ItemTagRegistry.SAPBALLS)).save(consumer, prefix("sticky_piston_from_sapballs"));
        shaped(Items.LEAD, 2).define('~', Tags.Items.STRING).define('O', ItemTagRegistry.SAPBALLS).pattern("~~ ").pattern("~O ").pattern("  ~").unlockedBy("has_sapball", has(ItemTagRegistry.SAPBALLS)).save(consumer, prefix("lead_from_sapballs"));

        shaped(ItemRegistry.GILDED_BELT.get()).define('#', ItemRegistry.HALLOWED_GOLD_INGOT.get()).define('X', Tags.Items.LEATHER).define('Y', ItemRegistry.PROCESSED_SOULSTONE.get()).pattern("XXX").pattern("#Y#").pattern(" # ").unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get())).save(consumer);
        shaped(ItemRegistry.GILDED_RING.get()).define('#', ItemRegistry.HALLOWED_GOLD_INGOT.get()).define('X', Tags.Items.LEATHER).pattern(" X#").pattern("X X").pattern(" X ").unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get())).save(consumer);

        shaped(ItemRegistry.ORNATE_NECKLACE.get()).define('#', ItemRegistry.SOUL_STAINED_STEEL_INGOT.get()).define('X', Tags.Items.STRING).pattern(" X ").pattern("X X").pattern(" # ").unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer);
        shaped(ItemRegistry.ORNATE_RING.get()).define('#', ItemRegistry.SOUL_STAINED_STEEL_INGOT.get()).define('X', Tags.Items.LEATHER).pattern(" X#").pattern("X X").pattern(" X ").unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer);

        shaped(ItemRegistry.SPIRIT_POUCH.get()).define('X', Tags.Items.STRING).define('Y', ItemRegistry.SPIRIT_FABRIC.get()).define('Z', ItemTags.SOUL_FIRE_BASE_BLOCKS).pattern(" X ").pattern("YZY").pattern(" Y ").unlockedBy("has_spirit_fabric", has(ItemRegistry.SPIRIT_FABRIC.get())).save(consumer);

        shaped(ItemRegistry.CRUDE_SCYTHE.get()).define('#', Items.STICK).define('Y', ItemRegistry.PROCESSED_SOULSTONE.get()).define('X', Tags.Items.INGOTS_IRON).pattern("XXY").pattern(" #X").pattern("#  ").unlockedBy("has_soulstone", has(ItemRegistry.SOULSTONE_CLUSTER.get())).save(consumer);
        shaped(ItemRegistry.SOUL_STAINED_STEEL_HOE.get()).define('#', Items.STICK).define('X', ItemRegistry.SOUL_STAINED_STEEL_INGOT.get()).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer);
        shaped(ItemRegistry.SOUL_STAINED_STEEL_PICKAXE.get()).define('#', Items.STICK).define('X', ItemRegistry.SOUL_STAINED_STEEL_INGOT.get()).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer);
        shaped(ItemRegistry.SOUL_STAINED_STEEL_AXE.get()).define('#', Items.STICK).define('X', ItemRegistry.SOUL_STAINED_STEEL_INGOT.get()).pattern("XX ").pattern("X# ").pattern(" # ").unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer);
        shaped(ItemRegistry.SOUL_STAINED_STEEL_SHOVEL.get()).define('#', Items.STICK).define('X', ItemRegistry.SOUL_STAINED_STEEL_INGOT.get()).pattern("X").pattern("#").pattern("#").unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer);
        shaped(ItemRegistry.SOUL_STAINED_STEEL_SWORD.get()).define('#', Items.STICK).define('X', ItemRegistry.SOUL_STAINED_STEEL_INGOT.get()).pattern("X").pattern("X").pattern("#").unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer);

        shaped(ItemRegistry.BLOCK_OF_SOUL_STAINED_STEEL.get()).define('#', ItemRegistry.SOUL_STAINED_STEEL_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer);
        shaped(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get()).define('#', ItemRegistry.SOUL_STAINED_STEEL_NUGGET.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer, prefix("soul_stained_steel_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(ItemRegistry.SOUL_STAINED_STEEL_NUGGET.get(), 9).requires(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get()).unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get(), 9).requires(ItemRegistry.BLOCK_OF_SOUL_STAINED_STEEL.get()).unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer, prefix("soul_stained_steel_from_block"));
        shaped(ItemRegistry.STAINED_SPIRIT_RESONATOR.get()).define('#', ItemRegistry.SOUL_STAINED_STEEL_INGOT.get()).define('X', ItemRegistry.RUNEWOOD_PLANKS.get()).define('Y', Tags.Items.GEMS_QUARTZ).pattern(" X ").pattern("#Y#").pattern(" X ").unlockedBy("has_soul_stained_steel", has(ItemRegistry.SOUL_STAINED_STEEL_INGOT.get())).save(consumer);

        shaped(ItemRegistry.BLOCK_OF_HALLOWED_GOLD.get()).define('#', ItemRegistry.HALLOWED_GOLD_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get())).save(consumer);
        shaped(ItemRegistry.HALLOWED_GOLD_INGOT.get()).define('#', ItemRegistry.HALLOWED_GOLD_NUGGET.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get())).save(consumer, prefix("hallowed_gold_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(ItemRegistry.HALLOWED_GOLD_NUGGET.get(), 9).requires(ItemRegistry.HALLOWED_GOLD_INGOT.get()).unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ItemRegistry.HALLOWED_GOLD_INGOT.get(),9).requires(ItemRegistry.BLOCK_OF_HALLOWED_GOLD.get()).unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get())).save(consumer, prefix("hallowed_gold_from_block"));
        shaped(ItemRegistry.HALLOWED_SPIRIT_RESONATOR.get()).define('#', ItemRegistry.HALLOWED_GOLD_INGOT.get()).define('X', ItemRegistry.RUNEWOOD_PLANKS.get()).define('Y', Tags.Items.GEMS_QUARTZ).pattern(" X ").pattern("#Y#").pattern(" X ").unlockedBy("has_hallowed_gold", has(ItemRegistry.HALLOWED_GOLD_INGOT.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(Items.EXPERIENCE_BOTTLE).requires(ItemRegistry.BRILLIANCE_CHUNK.get()).requires(Items.GLASS_BOTTLE).unlockedBy("has_brilliance", has(ItemRegistry.BRILLIANCE_CHUNK.get())).save(consumer, prefix("experience_bottle_from_brilliance"));

        shapelessPlanks(consumer, ItemRegistry.RUNEWOOD_PLANKS.get(), ItemTagRegistry.RUNEWOOD_LOGS);
        shapelessWood(consumer, ItemRegistry.RUNEWOOD.get(), ItemRegistry.RUNEWOOD_LOG.get());
        shapelessWood(consumer, ItemRegistry.STRIPPED_RUNEWOOD.get(), ItemRegistry.STRIPPED_RUNEWOOD_LOG.get());
        shapelessButton(consumer, ItemRegistry.RUNEWOOD_PLANKS_BUTTON.get(), ItemRegistry.RUNEWOOD_PLANKS.get());
        shapedDoor(consumer, ItemRegistry.RUNEWOOD_DOOR.get(), ItemRegistry.RUNEWOOD_PLANKS.get());
        shapedFence(consumer, ItemRegistry.RUNEWOOD_PLANKS_FENCE.get(), ItemRegistry.RUNEWOOD_PLANKS.get());
        shapedFenceGate(consumer, ItemRegistry.RUNEWOOD_PLANKS_FENCE_GATE.get(), ItemRegistry.RUNEWOOD_PLANKS.get());
        shapedPressurePlate(consumer, ItemRegistry.RUNEWOOD_PLANKS_PRESSURE_PLATE.get(), ItemRegistry.RUNEWOOD_PLANKS.get());
        shapedSlab(consumer, ItemRegistry.RUNEWOOD_PLANKS_SLAB.get(), ItemRegistry.RUNEWOOD_PLANKS.get());
        shapedStairs(consumer, ItemRegistry.RUNEWOOD_PLANKS_STAIRS.get(), ItemRegistry.RUNEWOOD_PLANKS.get());
        shapedTrapdoor(consumer, ItemRegistry.RUNEWOOD_TRAPDOOR.get(), ItemRegistry.RUNEWOOD_PLANKS.get());
        shapelessSolidTrapdoor(consumer, ItemRegistry.SOLID_RUNEWOOD_TRAPDOOR.get(), ItemRegistry.RUNEWOOD_TRAPDOOR.get());
        shapedSign(consumer, ItemRegistry.RUNEWOOD_SIGN.get(), ItemRegistry.RUNEWOOD_PLANKS.get());
        shaped(ItemRegistry.RUNEWOOD_BOAT.get()).define('#', ItemRegistry.RUNEWOOD_PLANKS.get()).pattern("# #").pattern("###").unlockedBy("has_runewood_planks", has(ItemRegistry.RUNEWOOD_PLANKS.get())).save(consumer);

        shaped(ItemRegistry.VERTICAL_RUNEWOOD_PLANKS.get(),3).define('#', ItemRegistry.RUNEWOOD_PLANKS.get()).pattern("#").pattern("#").pattern("#").unlockedBy("has_runewood_planks", has(ItemRegistry.RUNEWOOD_PLANKS.get())).save(consumer);
        shapedSlab(consumer, ItemRegistry.VERTICAL_RUNEWOOD_PLANKS_SLAB.get(), ItemRegistry.VERTICAL_RUNEWOOD_PLANKS.get());
        shapedStairs(consumer, ItemRegistry.VERTICAL_RUNEWOOD_PLANKS_STAIRS.get(), ItemRegistry.VERTICAL_RUNEWOOD_PLANKS.get());

        shaped(ItemRegistry.RUNEWOOD_PANEL.get(),4).define('#', ItemRegistry.RUNEWOOD_PLANKS.get()).pattern(" # ").pattern("# #").pattern(" # ").unlockedBy("has_runewood_planks", has(ItemRegistry.RUNEWOOD_PLANKS.get())).save(consumer);
        shapedSlab(consumer, ItemRegistry.RUNEWOOD_PANEL_SLAB.get(), ItemRegistry.RUNEWOOD_PANEL.get());
        shapedStairs(consumer, ItemRegistry.RUNEWOOD_PANEL_STAIRS.get(), ItemRegistry.RUNEWOOD_PANEL.get());

        shaped(ItemRegistry.RUNEWOOD_TILES.get(),4).define('#', ItemRegistry.RUNEWOOD_PANEL.get()).pattern(" # ").pattern("# #").pattern(" # ").unlockedBy("has_runewood_planks", has(ItemRegistry.RUNEWOOD_PLANKS.get())).save(consumer);
        shapedSlab(consumer, ItemRegistry.RUNEWOOD_TILES_SLAB.get(), ItemRegistry.RUNEWOOD_TILES.get());
        shapedStairs(consumer, ItemRegistry.RUNEWOOD_TILES_STAIRS.get(), ItemRegistry.RUNEWOOD_TILES.get());

        shaped(ItemRegistry.CUT_RUNEWOOD_PLANKS.get(),2).define('#', ItemRegistry.RUNEWOOD_PANEL.get()).define('X', ItemRegistry.RUNEWOOD_PLANKS.get()).pattern("#").pattern("X").unlockedBy("has_runewood_planks", has(ItemRegistry.RUNEWOOD_PLANKS.get())).save(consumer);
        shaped(ItemRegistry.RUNEWOOD_BEAM.get(),3).define('#', ItemRegistry.VERTICAL_RUNEWOOD_PLANKS.get()).pattern("#").pattern("#").pattern("#").unlockedBy("has_runewood_planks", has(ItemRegistry.RUNEWOOD_PLANKS.get())).save(consumer);

        shaped(ItemRegistry.RUNEWOOD_ITEM_STAND.get(), 2).define('X', ItemRegistry.RUNEWOOD_PLANKS.get()).define('Y', ItemRegistry.RUNEWOOD_PLANKS_SLAB.get()).pattern("YYY").pattern("XXX").unlockedBy("has_runewood_planks", has(ItemRegistry.RUNEWOOD_PLANKS.get())).save(consumer);
        shaped(ItemRegistry.RUNEWOOD_ITEM_PEDESTAL.get()).define('X', ItemRegistry.RUNEWOOD_PLANKS.get()).define('Y', ItemRegistry.RUNEWOOD_PLANKS_SLAB.get()).pattern("YYY").pattern(" X ").pattern("YYY").unlockedBy("has_runewood_planks", has(ItemRegistry.RUNEWOOD_PLANKS.get())).save(consumer);

        shapelessPlanks(consumer, ItemRegistry.SOULWOOD_PLANKS.get(), ItemTagRegistry.SOULWOOD_LOGS);
        shapelessWood(consumer, ItemRegistry.SOULWOOD.get(), ItemRegistry.SOULWOOD_LOG.get());
        shapelessWood(consumer, ItemRegistry.STRIPPED_SOULWOOD.get(), ItemRegistry.STRIPPED_SOULWOOD_LOG.get());
        shapelessButton(consumer, ItemRegistry.SOULWOOD_PLANKS_BUTTON.get(), ItemRegistry.SOULWOOD_PLANKS.get());
        shapedDoor(consumer, ItemRegistry.SOULWOOD_DOOR.get(), ItemRegistry.SOULWOOD_PLANKS.get());
        shapedFence(consumer, ItemRegistry.SOULWOOD_PLANKS_FENCE.get(), ItemRegistry.SOULWOOD_PLANKS.get());
        shapedFenceGate(consumer, ItemRegistry.SOULWOOD_PLANKS_FENCE_GATE.get(), ItemRegistry.SOULWOOD_PLANKS.get());
        shapedPressurePlate(consumer, ItemRegistry.SOULWOOD_PLANKS_PRESSURE_PLATE.get(), ItemRegistry.SOULWOOD_PLANKS.get());
        shapedSlab(consumer, ItemRegistry.SOULWOOD_PLANKS_SLAB.get(), ItemRegistry.SOULWOOD_PLANKS.get());
        shapedStairs(consumer, ItemRegistry.SOULWOOD_PLANKS_STAIRS.get(), ItemRegistry.SOULWOOD_PLANKS.get());
        shapedTrapdoor(consumer, ItemRegistry.SOULWOOD_TRAPDOOR.get(), ItemRegistry.SOULWOOD_PLANKS.get());
        shapelessSolidTrapdoor(consumer, ItemRegistry.SOLID_SOULWOOD_TRAPDOOR.get(), ItemRegistry.SOULWOOD_TRAPDOOR.get());
        shapedSign(consumer, ItemRegistry.SOULWOOD_SIGN.get(), ItemRegistry.SOULWOOD_PLANKS.get());
        shaped(ItemRegistry.SOULWOOD_BOAT.get()).define('#', ItemRegistry.SOULWOOD_PLANKS.get()).pattern("# #").pattern("###").unlockedBy("has_soulwood_planks", has(ItemRegistry.SOULWOOD_PLANKS.get())).save(consumer);

        shaped(ItemRegistry.VERTICAL_SOULWOOD_PLANKS.get(),3).define('#', ItemRegistry.SOULWOOD_PLANKS.get()).pattern("#").pattern("#").pattern("#").unlockedBy("has_soulwood_planks", has(ItemRegistry.SOULWOOD_PLANKS.get())).save(consumer);
        shapedSlab(consumer, ItemRegistry.VERTICAL_SOULWOOD_PLANKS_SLAB.get(), ItemRegistry.VERTICAL_SOULWOOD_PLANKS.get());
        shapedStairs(consumer, ItemRegistry.VERTICAL_SOULWOOD_PLANKS_STAIRS.get(), ItemRegistry.VERTICAL_SOULWOOD_PLANKS.get());

        shaped(ItemRegistry.SOULWOOD_PANEL.get(),4).define('#', ItemRegistry.SOULWOOD_PLANKS.get()).pattern(" # ").pattern("# #").pattern(" # ").unlockedBy("has_soulwood_planks", has(ItemRegistry.SOULWOOD_PLANKS.get())).save(consumer);
        shapedSlab(consumer, ItemRegistry.SOULWOOD_PANEL_SLAB.get(), ItemRegistry.SOULWOOD_PANEL.get());
        shapedStairs(consumer, ItemRegistry.SOULWOOD_PANEL_STAIRS.get(), ItemRegistry.SOULWOOD_PANEL.get());

        shaped(ItemRegistry.SOULWOOD_TILES.get(),4).define('#', ItemRegistry.SOULWOOD_PANEL.get()).pattern(" # ").pattern("# #").pattern(" # ").unlockedBy("has_soulwood_planks", has(ItemRegistry.SOULWOOD_PLANKS.get())).save(consumer);
        shapedSlab(consumer, ItemRegistry.SOULWOOD_TILES_SLAB.get(), ItemRegistry.SOULWOOD_TILES.get());
        shapedStairs(consumer, ItemRegistry.SOULWOOD_TILES_STAIRS.get(), ItemRegistry.SOULWOOD_TILES.get());

        shaped(ItemRegistry.CUT_SOULWOOD_PLANKS.get(),2).define('#', ItemRegistry.SOULWOOD_PANEL.get()).define('X', ItemRegistry.SOULWOOD_PLANKS.get()).pattern("#").pattern("X").unlockedBy("has_soulwood_planks", has(ItemRegistry.SOULWOOD_PLANKS.get())).save(consumer);
        shaped(ItemRegistry.SOULWOOD_BEAM.get(),3).define('#', ItemRegistry.VERTICAL_SOULWOOD_PLANKS.get()).pattern("#").pattern("#").pattern("#").unlockedBy("has_soulwood_planks", has(ItemRegistry.SOULWOOD_PLANKS.get())).save(consumer);

        shaped(ItemRegistry.SOULWOOD_ITEM_STAND.get(), 2).define('X', ItemRegistry.SOULWOOD_PLANKS.get()).define('Y', ItemRegistry.SOULWOOD_PLANKS_SLAB.get()).pattern("YYY").pattern("XXX").unlockedBy("has_soulwood_planks", has(ItemRegistry.SOULWOOD_PLANKS.get())).save(consumer);
        shaped(ItemRegistry.SOULWOOD_ITEM_PEDESTAL.get()).define('X', ItemRegistry.SOULWOOD_PLANKS.get()).define('Y', ItemRegistry.SOULWOOD_PLANKS_SLAB.get()).pattern("YYY").pattern(" X ").pattern("YYY").unlockedBy("has_soulwood_planks", has(ItemRegistry.SOULWOOD_PLANKS.get())).save(consumer);

        shaped(ItemRegistry.TAINTED_ROCK_WALL.get(), 6).define('#', ItemRegistry.TAINTED_ROCK.get()).pattern("###").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_SLAB.get(), 6).define('#', ItemRegistry.TAINTED_ROCK.get()).pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_STAIRS.get(), 4).define('#', ItemRegistry.TAINTED_ROCK.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.TAINTED_ROCK_SLAB.get(), 2).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.TAINTED_ROCK_STAIRS.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.TAINTED_ROCK_WALL.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_wall_stonecutting"));

        shaped(ItemRegistry.POLISHED_TAINTED_ROCK.get(),9).define('#', ItemRegistry.TAINTED_ROCK.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.POLISHED_TAINTED_ROCK_SLAB.get(), 6).define('#', ItemRegistry.POLISHED_TAINTED_ROCK.get()).pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.POLISHED_TAINTED_ROCK_STAIRS.get(), 4).define('#', ItemRegistry.POLISHED_TAINTED_ROCK.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.POLISHED_TAINTED_ROCK.get()), ItemRegistry.POLISHED_TAINTED_ROCK_SLAB.get(), 2).unlockedBy("has_polished_tainted_rock", has(ItemRegistry.POLISHED_TAINTED_ROCK.get())).save(consumer, prefix("polished_tainted_rock_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.POLISHED_TAINTED_ROCK.get()), ItemRegistry.POLISHED_TAINTED_ROCK_STAIRS.get()).unlockedBy("has_polished_tainted_rock", has(ItemRegistry.POLISHED_TAINTED_ROCK.get())).save(consumer, prefix("polished_tainted_rock_stairs_stonecutting"));

        smelting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.SMOOTH_TAINTED_ROCK.get(),0.1f,200).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.SMOOTH_TAINTED_ROCK_SLAB.get(), 6).define('#', ItemRegistry.SMOOTH_TAINTED_ROCK.get()).pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.SMOOTH_TAINTED_ROCK_STAIRS.get(), 4).define('#', ItemRegistry.SMOOTH_TAINTED_ROCK.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.SMOOTH_TAINTED_ROCK.get()), ItemRegistry.SMOOTH_TAINTED_ROCK_SLAB.get(), 2).unlockedBy("has_smooth_tainted_rock", has(ItemRegistry.POLISHED_TAINTED_ROCK.get())).save(consumer, prefix("smooth_tainted_rock_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.SMOOTH_TAINTED_ROCK.get()), ItemRegistry.SMOOTH_TAINTED_ROCK_STAIRS.get()).unlockedBy("has_smooth_tainted_rock", has(ItemRegistry.POLISHED_TAINTED_ROCK.get())).save(consumer, prefix("smooth_tainted_rock_stairs_stonecutting"));

        shaped(ItemRegistry.TAINTED_ROCK_BRICKS.get(),4).define('#', ItemRegistry.TAINTED_ROCK.get()).pattern("##").pattern("##").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_BRICKS_SLAB.get(), 6).define('#', ItemRegistry.TAINTED_ROCK_BRICKS.get()).pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_BRICKS_STAIRS.get(), 4).define('#', ItemRegistry.TAINTED_ROCK_BRICKS.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_BRICKS_WALL.get(), 6).define('#', ItemRegistry.TAINTED_ROCK_BRICKS.get()).pattern("###").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_BRICKS.get()), ItemRegistry.TAINTED_ROCK_BRICKS_SLAB.get(), 2).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_bricks_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_BRICKS.get()), ItemRegistry.TAINTED_ROCK_BRICKS_STAIRS.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_bricks_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_BRICKS.get()), ItemRegistry.TAINTED_ROCK_BRICKS_WALL.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_brick_wall_stonecutting"));

        shaped(ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS_WALL.get(), 6).define('#', ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS.get()).pattern("###").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        smelting(Ingredient.of(ItemRegistry.TAINTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS.get(),0.1f,200).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS_SLAB.get(), 6).define('#', ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS.get()).pattern("###").unlockedBy("has_cracked_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS_STAIRS.get(), 4).define('#', ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_cracked_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS_SLAB.get(), 2).unlockedBy("has_cracked_tainted_rock_bricks", has(ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS.get())).save(consumer, prefix("cracked_tainted_rock_bricks_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS_STAIRS.get()).unlockedBy("has_cracked_tainted_rock_bricks", has(ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS.get())).save(consumer, prefix("cracked_tainted_rock_bricks_stairs_stonecutting"));

        shaped(ItemRegistry.TAINTED_ROCK_TILES.get(),4).define('#', ItemRegistry.TAINTED_ROCK_BRICKS.get()).pattern("##").pattern("##").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_TILES_SLAB.get(), 6).define('#', ItemRegistry.TAINTED_ROCK_TILES.get()).pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_TILES_STAIRS.get(), 4).define('#', ItemRegistry.TAINTED_ROCK_TILES.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_TILES_WALL.get(), 6).define('#', ItemRegistry.TAINTED_ROCK_TILES.get()).pattern("###").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.TAINTED_ROCK_TILES.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_tiles_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_BRICKS.get()), ItemRegistry.TAINTED_ROCK_TILES.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_tiles_stonecutting_alt"));;
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_TILES.get()), ItemRegistry.TAINTED_ROCK_TILES_SLAB.get(), 2).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_tiles_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_TILES.get()), ItemRegistry.TAINTED_ROCK_TILES_STAIRS.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_tiles_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_TILES.get()), ItemRegistry.TAINTED_ROCK_TILES_WALL.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_tiles_wall_stonecutting"));

        smelting(Ingredient.of(ItemRegistry.TAINTED_ROCK_TILES.get()), ItemRegistry.CRACKED_TAINTED_ROCK_TILES.get(),0.1f,200).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_TAINTED_ROCK_TILES_SLAB.get(), 6).define('#', ItemRegistry.CRACKED_TAINTED_ROCK_TILES.get()).pattern("###").unlockedBy("has_cracked_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_TAINTED_ROCK_TILES_STAIRS.get(), 4).define('#', ItemRegistry.CRACKED_TAINTED_ROCK_TILES.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_cracked_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_TAINTED_ROCK_TILES_WALL.get(), 6).define('#', ItemRegistry.CRACKED_TAINTED_ROCK_TILES.get()).pattern("###").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TAINTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_TAINTED_ROCK_TILES.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("cracked_tainted_rock_tiles_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TAINTED_ROCK_TILES.get()), ItemRegistry.CRACKED_TAINTED_ROCK_TILES_SLAB.get(), 2).unlockedBy("has_cracked_tainted_rock_tiles", has(ItemRegistry.CRACKED_TAINTED_ROCK_TILES.get())).save(consumer, prefix("cracked_tainted_rock_tiles_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TAINTED_ROCK_TILES.get()), ItemRegistry.CRACKED_TAINTED_ROCK_TILES_STAIRS.get()).unlockedBy("has_cracked_tainted_rock_tiles", has(ItemRegistry.CRACKED_TAINTED_ROCK_TILES.get())).save(consumer, prefix("cracked_tainted_rock_tiles_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TAINTED_ROCK_TILES.get()), ItemRegistry.CRACKED_TAINTED_ROCK_TILES_WALL.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("cracked_tainted_rock_tiles_wall_stonecutting"));

        shaped(ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get(),4).define('#', ItemRegistry.TAINTED_ROCK_TILES.get()).pattern("##").pattern("##").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.SMALL_TAINTED_ROCK_BRICKS_SLAB.get(), 6).define('#', ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()).pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.SMALL_TAINTED_ROCK_BRICKS_STAIRS.get(), 4).define('#', ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.SMALL_TAINTED_ROCK_BRICKS_WALL.get(), 6).define('#', ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()).pattern("###").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("small_tainted_rock_bricks_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_BRICKS.get()), ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("small_tainted_rock_bricks_stonecutting_alt"));;
        stonecutting(Ingredient.of(ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()), ItemRegistry.SMALL_TAINTED_ROCK_BRICKS_SLAB.get(), 2).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("small_tainted_rock_bricks_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()), ItemRegistry.SMALL_TAINTED_ROCK_BRICKS_STAIRS.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("small_tainted_rock_bricks_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()), ItemRegistry.SMALL_TAINTED_ROCK_BRICKS_WALL.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("small_tainted_rock_bricks_wall_stonecutting"));

        shaped(ItemRegistry.TAINTED_ROCK_BRICKS.get(),4).define('#', ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()).pattern("##").pattern("##").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_bricks_from_small_bricks"));
        smelting(Ingredient.of(ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS.get(),0.1f,200).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("cracked_small_tainted_rock_bricks_smelting"));
        shaped(ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS.get(),4).define('#', ItemRegistry.CRACKED_TAINTED_ROCK_TILES.get()).pattern("##").pattern("##").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_SLAB.get(), 6).define('#', ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS.get()).pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_STAIRS.get(), 4).define('#', ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_WALL.get(), 6).define('#', ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS.get()).pattern("###").pattern("###").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("cracked_small_tainted_rock_bricks_stonecutting_alt"));;
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_SLAB.get(), 2).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("cracked_small_tainted_rock_bricks_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_STAIRS.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("cracked_small_tainted_rock_bricks_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_WALL.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("cracked_small_tainted_rock_bricks_wall_stonecutting"));

        shaped(ItemRegistry.CHISELED_TAINTED_ROCK.get()).define('#', ItemRegistry.TAINTED_ROCK_SLAB.get()).pattern("#").pattern("#").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_PILLAR.get(),2).define('#', ItemRegistry.TAINTED_ROCK.get()).pattern("#").pattern("#").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_COLUMN.get(),2).define('#', ItemRegistry.TAINTED_ROCK_BRICKS.get()).pattern("#").pattern("#").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_PILLAR_CAP.get()).define('$', ItemRegistry.TAINTED_ROCK_PILLAR.get()).define('#', ItemRegistry.TAINTED_ROCK.get()).pattern("$").pattern("#").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_COLUMN_CAP.get()).define('$', ItemRegistry.TAINTED_ROCK_COLUMN.get()).define('#', ItemRegistry.TAINTED_ROCK.get()).pattern("$").pattern("#").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);

        shaped(ItemRegistry.CUT_TAINTED_ROCK.get(),4).define('#', ItemRegistry.SMOOTH_TAINTED_ROCK.get()).pattern("##").pattern("##").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.CUT_TAINTED_ROCK.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("cut_tainted_rock_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.TAINTED_ROCK_PILLAR.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_pillar_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.TAINTED_ROCK_PILLAR_CAP.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_pillar_cap_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.TAINTED_ROCK_COLUMN.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_column_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.TAINTED_ROCK_COLUMN_CAP.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_column_cap_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.TAINTED_ROCK_BRICKS.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("tainted_rock_bricks_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.SMOOTH_TAINTED_ROCK.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("smooth_tainted_rock_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.POLISHED_TAINTED_ROCK.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("polished_tainted_rock_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK.get()), ItemRegistry.CHISELED_TAINTED_ROCK.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("chiseled_tainted_rock_bricks_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_BRICKS.get()), ItemRegistry.CHISELED_TAINTED_ROCK.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("chiseled_tainted_rock_bricks_stonecutting_alt"));;
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_BRICKS.get()), ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("small_tainted_rock_bricks_stonecutting_from_bricks"));
        stonecutting(Ingredient.of(ItemRegistry.TAINTED_ROCK_TILES.get()), ItemRegistry.SMALL_TAINTED_ROCK_BRICKS.get()).unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer, prefix("small_tainted_rock_bricks_stonecutting_from_tiles"));

        shaped(ItemRegistry.TAINTED_ROCK_ITEM_STAND.get(), 2).define('X', ItemRegistry.TAINTED_ROCK.get()).define('Y', ItemRegistry.TAINTED_ROCK_SLAB.get()).pattern("YYY").pattern("XXX").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TAINTED_ROCK_ITEM_PEDESTAL.get()).define('X', ItemRegistry.TAINTED_ROCK.get()).define('Y', ItemRegistry.TAINTED_ROCK_SLAB.get()).pattern("YYY").pattern(" X ").pattern("YYY").unlockedBy("has_tainted_rock", has(ItemRegistry.TAINTED_ROCK.get())).save(consumer);

        shaped(ItemRegistry.TWISTED_ROCK_WALL.get(), 6).define('#', ItemRegistry.TWISTED_ROCK.get()).pattern("###").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_SLAB.get(), 6).define('#', ItemRegistry.TWISTED_ROCK.get()).pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_STAIRS.get(), 4).define('#', ItemRegistry.TWISTED_ROCK.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.TWISTED_ROCK_SLAB.get(), 2).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.TWISTED_ROCK_STAIRS.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.TWISTED_ROCK_WALL.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_wall_stonecutting"));

        shaped(ItemRegistry.POLISHED_TWISTED_ROCK.get(),9).define('#', ItemRegistry.TWISTED_ROCK.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.POLISHED_TWISTED_ROCK_SLAB.get(), 6).define('#', ItemRegistry.POLISHED_TWISTED_ROCK.get()).pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.POLISHED_TWISTED_ROCK_STAIRS.get(), 4).define('#', ItemRegistry.POLISHED_TWISTED_ROCK.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.POLISHED_TWISTED_ROCK.get()), ItemRegistry.POLISHED_TWISTED_ROCK_SLAB.get(), 2).unlockedBy("has_polished_twisted_rock", has(ItemRegistry.POLISHED_TWISTED_ROCK.get())).save(consumer, prefix("polished_twisted_rock_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.POLISHED_TWISTED_ROCK.get()), ItemRegistry.POLISHED_TWISTED_ROCK_STAIRS.get()).unlockedBy("has_polished_twisted_rock", has(ItemRegistry.POLISHED_TWISTED_ROCK.get())).save(consumer, prefix("polished_twisted_rock_stairs_stonecutting"));

        smelting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.SMOOTH_TWISTED_ROCK.get(),0.1f,200).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.SMOOTH_TWISTED_ROCK_SLAB.get(), 6).define('#', ItemRegistry.SMOOTH_TWISTED_ROCK.get()).pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.SMOOTH_TWISTED_ROCK_STAIRS.get(), 4).define('#', ItemRegistry.SMOOTH_TWISTED_ROCK.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.SMOOTH_TWISTED_ROCK.get()), ItemRegistry.SMOOTH_TWISTED_ROCK_SLAB.get(), 2).unlockedBy("has_smooth_twisted_rock", has(ItemRegistry.POLISHED_TWISTED_ROCK.get())).save(consumer, prefix("smooth_twisted_rock_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.SMOOTH_TWISTED_ROCK.get()), ItemRegistry.SMOOTH_TWISTED_ROCK_STAIRS.get()).unlockedBy("has_smooth_twisted_rock", has(ItemRegistry.POLISHED_TWISTED_ROCK.get())).save(consumer, prefix("smooth_twisted_rock_stairs_stonecutting"));

        shaped(ItemRegistry.TWISTED_ROCK_BRICKS.get(),4).define('#', ItemRegistry.TWISTED_ROCK.get()).pattern("##").pattern("##").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_BRICKS_SLAB.get(), 6).define('#', ItemRegistry.TWISTED_ROCK_BRICKS.get()).pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_BRICKS_STAIRS.get(), 4).define('#', ItemRegistry.TWISTED_ROCK_BRICKS.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_BRICKS_WALL.get(), 6).define('#', ItemRegistry.TWISTED_ROCK_BRICKS.get()).pattern("###").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_BRICKS.get()), ItemRegistry.TWISTED_ROCK_BRICKS_SLAB.get(), 2).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_bricks_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_BRICKS.get()), ItemRegistry.TWISTED_ROCK_BRICKS_STAIRS.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_bricks_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_BRICKS.get()), ItemRegistry.TWISTED_ROCK_BRICKS_WALL.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_brick_wall_stonecutting"));

        shaped(ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS_WALL.get(), 6).define('#', ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS.get()).pattern("###").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        smelting(Ingredient.of(ItemRegistry.TWISTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS.get(),0.1f,200).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS_SLAB.get(), 6).define('#', ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS.get()).pattern("###").unlockedBy("has_cracked_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS_STAIRS.get(), 4).define('#', ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_cracked_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS_SLAB.get(), 2).unlockedBy("has_cracked_twisted_rock_bricks", has(ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS.get())).save(consumer, prefix("cracked_twisted_rock_bricks_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS_STAIRS.get()).unlockedBy("has_cracked_twisted_rock_bricks", has(ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS.get())).save(consumer, prefix("cracked_twisted_rock_bricks_stairs_stonecutting"));

        shaped(ItemRegistry.TWISTED_ROCK_TILES.get(),4).define('#', ItemRegistry.TWISTED_ROCK_BRICKS.get()).pattern("##").pattern("##").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_TILES_SLAB.get(), 6).define('#', ItemRegistry.TWISTED_ROCK_TILES.get()).pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_TILES_STAIRS.get(), 4).define('#', ItemRegistry.TWISTED_ROCK_TILES.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_TILES_WALL.get(), 6).define('#', ItemRegistry.TWISTED_ROCK_TILES.get()).pattern("###").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.TWISTED_ROCK_TILES.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_tiles_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_BRICKS.get()), ItemRegistry.TWISTED_ROCK_TILES.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_tiles_stonecutting_alt"));;
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_TILES.get()), ItemRegistry.TWISTED_ROCK_TILES_SLAB.get(), 2).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_tiles_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_TILES.get()), ItemRegistry.TWISTED_ROCK_TILES_STAIRS.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_tiles_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_TILES.get()), ItemRegistry.TWISTED_ROCK_TILES_WALL.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_tiles_wall_stonecutting"));

        smelting(Ingredient.of(ItemRegistry.TWISTED_ROCK_TILES.get()), ItemRegistry.CRACKED_TWISTED_ROCK_TILES.get(),0.1f,200).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_TWISTED_ROCK_TILES_SLAB.get(), 6).define('#', ItemRegistry.CRACKED_TWISTED_ROCK_TILES.get()).pattern("###").unlockedBy("has_cracked_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_TWISTED_ROCK_TILES_STAIRS.get(), 4).define('#', ItemRegistry.CRACKED_TWISTED_ROCK_TILES.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_cracked_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_TWISTED_ROCK_TILES_WALL.get(), 6).define('#', ItemRegistry.CRACKED_TWISTED_ROCK_TILES.get()).pattern("###").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TWISTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_TWISTED_ROCK_TILES.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("cracked_twisted_rock_tiles_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TWISTED_ROCK_TILES.get()), ItemRegistry.CRACKED_TWISTED_ROCK_TILES_SLAB.get(), 2).unlockedBy("has_cracked_twisted_rock_tiles", has(ItemRegistry.CRACKED_TWISTED_ROCK_TILES.get())).save(consumer, prefix("cracked_twisted_rock_tiles_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TWISTED_ROCK_TILES.get()), ItemRegistry.CRACKED_TWISTED_ROCK_TILES_STAIRS.get()).unlockedBy("has_cracked_twisted_rock_tiles", has(ItemRegistry.CRACKED_TWISTED_ROCK_TILES.get())).save(consumer, prefix("cracked_twisted_rock_tiles_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_TWISTED_ROCK_TILES.get()), ItemRegistry.CRACKED_TWISTED_ROCK_TILES_WALL.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("cracked_twisted_rock_tiles_wall_stonecutting"));

        shaped(ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get(),4).define('#', ItemRegistry.TWISTED_ROCK_TILES.get()).pattern("##").pattern("##").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.SMALL_TWISTED_ROCK_BRICKS_SLAB.get(), 6).define('#', ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()).pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.SMALL_TWISTED_ROCK_BRICKS_STAIRS.get(), 4).define('#', ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.SMALL_TWISTED_ROCK_BRICKS_WALL.get(), 6).define('#', ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()).pattern("###").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("small_twisted_rock_bricks_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_BRICKS.get()), ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("small_twisted_rock_bricks_stonecutting_alt"));;
        stonecutting(Ingredient.of(ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()), ItemRegistry.SMALL_TWISTED_ROCK_BRICKS_SLAB.get(), 2).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("small_twisted_rock_bricks_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()), ItemRegistry.SMALL_TWISTED_ROCK_BRICKS_STAIRS.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("small_twisted_rock_bricks_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()), ItemRegistry.SMALL_TWISTED_ROCK_BRICKS_WALL.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("small_twisted_rock_bricks_wall_stonecutting"));

        shaped(ItemRegistry.TWISTED_ROCK_BRICKS.get(),4).define('#', ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()).pattern("##").pattern("##").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_bricks_from_small_bricks"));

        smelting(Ingredient.of(ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS.get(),0.1f,200).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("cracked_small_twisted_rock_bricks_smelting"));
        shaped(ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS.get(),4).define('#', ItemRegistry.CRACKED_TWISTED_ROCK_TILES.get()).pattern("##").pattern("##").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_SLAB.get(), 6).define('#', ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS.get()).pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_STAIRS.get(), 4).define('#', ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS.get()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_WALL.get(), 6).define('#', ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS.get()).pattern("###").pattern("###").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("cracked_small_twisted_rock_bricks_stonecutting_alt"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_SLAB.get(), 2).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("cracked_small_twisted_rock_bricks_slab_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_STAIRS.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("cracked_small_twisted_rock_bricks_stairs_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS.get()), ItemRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_WALL.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("cracked_small_twisted_rock_bricks_wall_stonecutting"));

        shaped(ItemRegistry.CHISELED_TWISTED_ROCK.get()).define('#', ItemRegistry.TWISTED_ROCK_SLAB.get()).pattern("#").pattern("#").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_PILLAR.get(),2).define('#', ItemRegistry.TWISTED_ROCK.get()).pattern("#").pattern("#").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_COLUMN.get(),2).define('#', ItemRegistry.TWISTED_ROCK_BRICKS.get()).pattern("#").pattern("#").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_PILLAR_CAP.get()).define('$', ItemRegistry.TWISTED_ROCK_PILLAR.get()).define('#', ItemRegistry.TWISTED_ROCK.get()).pattern("$").pattern("#").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_COLUMN_CAP.get()).define('$', ItemRegistry.TWISTED_ROCK_COLUMN.get()).define('#', ItemRegistry.TWISTED_ROCK.get()).pattern("$").pattern("#").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);

        shaped(ItemRegistry.CUT_TWISTED_ROCK.get(),4).define('#', ItemRegistry.SMOOTH_TWISTED_ROCK.get()).pattern("##").pattern("##").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.CUT_TWISTED_ROCK.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("cut_twisted_rock_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.TWISTED_ROCK_PILLAR.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_pillar_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.TWISTED_ROCK_PILLAR_CAP.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_pillar_cap_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.TWISTED_ROCK_COLUMN.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_column_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.TWISTED_ROCK_COLUMN_CAP.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_column_cap_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.TWISTED_ROCK_BRICKS.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("twisted_rock_bricks_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.SMOOTH_TWISTED_ROCK.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("smooth_twisted_rock_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.POLISHED_TWISTED_ROCK.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("polished_twisted_rock_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK.get()), ItemRegistry.CHISELED_TWISTED_ROCK.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("chiseled_twisted_rock_bricks_stonecutting"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_BRICKS.get()), ItemRegistry.CHISELED_TWISTED_ROCK.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("chiseled_twisted_rock_bricks_stonecutting_alt"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_BRICKS.get()), ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("small_twisted_rock_bricks_stonecutting_from_bricks"));
        stonecutting(Ingredient.of(ItemRegistry.TWISTED_ROCK_TILES.get()), ItemRegistry.SMALL_TWISTED_ROCK_BRICKS.get()).unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer, prefix("small_twisted_rock_bricks_stonecutting_from_tiles"));

        shaped(ItemRegistry.TWISTED_ROCK_ITEM_STAND.get(), 2).define('X', ItemRegistry.TWISTED_ROCK.get()).define('Y', ItemRegistry.TWISTED_ROCK_SLAB.get()).pattern("YYY").pattern("XXX").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);
        shaped(ItemRegistry.TWISTED_ROCK_ITEM_PEDESTAL.get()).define('X', ItemRegistry.TWISTED_ROCK.get()).define('Y', ItemRegistry.TWISTED_ROCK_SLAB.get()).pattern("YYY").pattern(" X ").pattern("YYY").unlockedBy("has_twisted_rock", has(ItemRegistry.TWISTED_ROCK.get())).save(consumer);

        etherTorch(consumer, ItemRegistry.ETHER_TORCH.get(), ItemRegistry.ETHER.get());
        etherBrazier(consumer, ItemRegistry.TAINTED_ETHER_BRAZIER.get(), ItemRegistry.TAINTED_ROCK.get(), ItemRegistry.ETHER.get());
        etherBrazier(consumer, ItemRegistry.TWISTED_ETHER_BRAZIER.get(), ItemRegistry.TWISTED_ROCK.get(), ItemRegistry.ETHER.get());
        etherTorch(consumer, ItemRegistry.IRIDESCENT_ETHER_TORCH.get(), ItemRegistry.IRIDESCENT_ETHER.get());
        etherBrazier(consumer, ItemRegistry.TAINTED_IRIDESCENT_ETHER_BRAZIER.get(), ItemRegistry.TAINTED_ROCK.get(), ItemRegistry.IRIDESCENT_ETHER.get());
        etherBrazier(consumer, ItemRegistry.TWISTED_IRIDESCENT_ETHER_BRAZIER.get(), ItemRegistry.TWISTED_ROCK.get(), ItemRegistry.IRIDESCENT_ETHER.get());
    }
    private static void etherBrazier(Consumer<FinishedRecipe> recipeConsumer, ItemLike output, ItemLike rock, ItemLike ether)
    {
        NBTCarryRecipeBuilder.shapedRecipe(output,2,Ingredient.of(ether)).key('#', rock).key('S', Items.STICK).key('X', ether).patternLine("#X#").patternLine("S#S").addCriterion("has_ether", has(ItemRegistry.ETHER.get())).build(recipeConsumer, output.asItem().getRegistryName().getPath());
    }
    private static void etherTorch(Consumer<FinishedRecipe> recipeConsumer, ItemLike output, ItemLike ether)
    {
        NBTCarryRecipeBuilder.shapedRecipe(output, 4,Ingredient.of(ether)).key('#', Items.STICK).key('X', ether).patternLine("X").patternLine("#").addCriterion("has_ether", has(ItemRegistry.ETHER.get())).build(recipeConsumer, output.asItem().getRegistryName().getPath() + "_alternative");
    }
    private static void shapelessPlanks(Consumer<FinishedRecipe> recipeConsumer, ItemLike planks, Tag<Item> input)
    {
        shapeless(planks, 4).requires(input).group("planks").unlockedBy("has_logs", has(input)).save(recipeConsumer);
    }
    private static void shapelessWood(Consumer<FinishedRecipe> recipeConsumer, ItemLike stripped, ItemLike input)
    {
        shaped(stripped, 3).define('#', input).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(input)).save(recipeConsumer);
    }
    private static void shapelessButton(Consumer<FinishedRecipe> recipeConsumer, ItemLike button, ItemLike input)
    {
        shapeless(button).requires(input).unlockedBy("has_input", has(input)).save(recipeConsumer);
    }
    private static void shapedDoor(Consumer<FinishedRecipe> recipeConsumer, ItemLike door, ItemLike input)
    {
        shaped(door, 3).define('#', input).pattern("##").pattern("##").pattern("##").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }
    private static void shapedFence(Consumer<FinishedRecipe> recipeConsumer, ItemLike fence, ItemLike input)
    {
        shaped(fence, 3).define('#', Items.STICK).define('W', input).pattern("W#W").pattern("W#W").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }
    private static void shapedFenceGate(Consumer<FinishedRecipe> recipeConsumer, ItemLike fenceGate, ItemLike input)
    {
        shaped(fenceGate).define('#', Items.STICK).define('W', input).pattern("#W#").pattern("#W#").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }
    private static void shapedPressurePlate(Consumer<FinishedRecipe> recipeConsumer, ItemLike pressurePlate, ItemLike input)
    {
        shaped(pressurePlate).define('#', input).pattern("##").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }
    private static void shapedSlab(Consumer<FinishedRecipe> recipeConsumer, ItemLike slab, ItemLike input)
    {
        shaped(slab, 6).define('#', input).pattern("###").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }
    private static void shapedStairs(Consumer<FinishedRecipe> recipeConsumer, ItemLike stairs, ItemLike input)
    {
        shaped(stairs, 4).define('#', input).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }
    private static void shapelessSolidTrapdoor(Consumer<FinishedRecipe> recipeConsumer, ItemLike solid, ItemLike normal)
    {
        shapeless(solid).requires(normal).unlockedBy("has_input", has(normal)).save(recipeConsumer);
    }
    private static void shapedTrapdoor(Consumer<FinishedRecipe> recipeConsumer, ItemLike trapdoor, ItemLike input)
    {
        shaped(trapdoor, 2).define('#', input).pattern("###").pattern("###").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }
    private static void shapedSign(Consumer<FinishedRecipe> recipeConsumer, ItemLike sign, ItemLike input)
    {
        String s = Registry.ITEM.getKey(input.asItem()).getPath();
        shaped(sign, 3).group("sign").define('#', input).define('X', Items.STICK).pattern("###").pattern("###").pattern(" X ").unlockedBy("has_" + s, has(input)).save(recipeConsumer);
    }
    private static void netheriteSmithing(Consumer<FinishedRecipe> p_240469_0_, Item p_240469_1_, Item p_240469_2_) {
        UpgradeRecipeBuilder.smithing(Ingredient.of(p_240469_1_), Ingredient.of(Items.NETHERITE_INGOT), p_240469_2_).unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT)).save(p_240469_0_, Registry.ITEM.getKey(p_240469_2_.asItem()).getPath() + "_smithing");
    }

    private static void planksFromLog(Consumer<FinishedRecipe> p_240470_0_, ItemLike p_240470_1_, Tag<Item> p_240470_2_) {
        ShapelessRecipeBuilder.shapeless(p_240470_1_, 4).requires(p_240470_2_).group("planks").unlockedBy("has_log", has(p_240470_2_)).save(p_240470_0_);
    }

    private static void planksFromLogs(Consumer<FinishedRecipe> p_240472_0_, ItemLike p_240472_1_, Tag<Item> p_240472_2_) {
        ShapelessRecipeBuilder.shapeless(p_240472_1_, 4).requires(p_240472_2_).group("planks").unlockedBy("has_logs", has(p_240472_2_)).save(p_240472_0_);
    }

    private static void woodFromLogs(Consumer<FinishedRecipe> p_240471_0_, ItemLike p_240471_1_, ItemLike p_240471_2_) {
        shaped(p_240471_1_, 3).define('#', p_240471_2_).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(p_240471_2_)).save(p_240471_0_);
    }

    private static void woodenBoat(Consumer<FinishedRecipe> p_240473_0_, ItemLike p_240473_1_, ItemLike p_240473_2_) {
        shaped(p_240473_1_).define('#', p_240473_2_).pattern("# #").pattern("###").group("boat").unlockedBy("in_water", insideOf(Blocks.WATER)).save(p_240473_0_);
    }

    private static void woodenButton(Consumer<FinishedRecipe> p_240474_0_, ItemLike p_240474_1_, ItemLike p_240474_2_) {
        ShapelessRecipeBuilder.shapeless(p_240474_1_).requires(p_240474_2_).group("wooden_button").unlockedBy("has_planks", has(p_240474_2_)).save(p_240474_0_);
    }

    private static void woodenDoor(Consumer<FinishedRecipe> p_240475_0_, ItemLike p_240475_1_, ItemLike p_240475_2_) {
        shaped(p_240475_1_, 3).define('#', p_240475_2_).pattern("##").pattern("##").pattern("##").group("wooden_door").unlockedBy("has_planks", has(p_240475_2_)).save(p_240475_0_);
    }

    private static void woodenFence(Consumer<FinishedRecipe> p_240476_0_, ItemLike p_240476_1_, ItemLike p_240476_2_) {
        shaped(p_240476_1_, 3).define('#', Items.STICK).define('W', p_240476_2_).pattern("W#W").pattern("W#W").group("wooden_fence").unlockedBy("has_planks", has(p_240476_2_)).save(p_240476_0_);
    }

    private static void woodenFenceGate(Consumer<FinishedRecipe> p_240477_0_, ItemLike p_240477_1_, ItemLike p_240477_2_) {
        shaped(p_240477_1_).define('#', Items.STICK).define('W', p_240477_2_).pattern("#W#").pattern("#W#").group("wooden_fence_gate").unlockedBy("has_planks", has(p_240477_2_)).save(p_240477_0_);
    }

    private static void woodenPressurePlate(Consumer<FinishedRecipe> p_240478_0_, ItemLike p_240478_1_, ItemLike p_240478_2_) {
        shaped(p_240478_1_).define('#', p_240478_2_).pattern("##").group("wooden_pressure_plate").unlockedBy("has_planks", has(p_240478_2_)).save(p_240478_0_);
    }

    private static void woodenSlab(Consumer<FinishedRecipe> p_240479_0_, ItemLike p_240479_1_, ItemLike p_240479_2_) {
        shaped(p_240479_1_, 6).define('#', p_240479_2_).pattern("###").group("wooden_slab").unlockedBy("has_planks", has(p_240479_2_)).save(p_240479_0_);
    }

    private static void woodenStairs(Consumer<FinishedRecipe> p_240480_0_, ItemLike p_240480_1_, ItemLike p_240480_2_) {
        shaped(p_240480_1_, 4).define('#', p_240480_2_).pattern("#  ").pattern("## ").pattern("###").group("wooden_stairs").unlockedBy("has_planks", has(p_240480_2_)).save(p_240480_0_);
    }

    private static void woodenTrapdoor(Consumer<FinishedRecipe> p_240481_0_, ItemLike p_240481_1_, ItemLike p_240481_2_) {
        shaped(p_240481_1_, 2).define('#', p_240481_2_).pattern("###").pattern("###").group("wooden_trapdoor").unlockedBy("has_planks", has(p_240481_2_)).save(p_240481_0_);
    }

    private static void woodenSign(Consumer<FinishedRecipe> p_240482_0_, ItemLike p_240482_1_, ItemLike p_240482_2_) {
        String s = Registry.ITEM.getKey(p_240482_2_.asItem()).getPath();
        shaped(p_240482_1_, 3).group("sign").define('#', p_240482_2_).define('X', Items.STICK).pattern("###").pattern("###").pattern(" X ").unlockedBy("has_" + s, has(p_240482_2_)).save(p_240482_0_);
    }

    private static void coloredWoolFromWhiteWoolAndDye(Consumer<FinishedRecipe> p_240483_0_, ItemLike p_240483_1_, ItemLike p_240483_2_) {
        ShapelessRecipeBuilder.shapeless(p_240483_1_).requires(p_240483_2_).requires(Blocks.WHITE_WOOL).group("wool").unlockedBy("has_white_wool", has(Blocks.WHITE_WOOL)).save(p_240483_0_);
    }

    private static void carpetFromWool(Consumer<FinishedRecipe> p_240484_0_, ItemLike p_240484_1_, ItemLike p_240484_2_) {
        String s = Registry.ITEM.getKey(p_240484_2_.asItem()).getPath();
        shaped(p_240484_1_, 3).define('#', p_240484_2_).pattern("##").group("carpet").unlockedBy("has_" + s, has(p_240484_2_)).save(p_240484_0_);
    }

    private static void coloredCarpetFromWhiteCarpetAndDye(Consumer<FinishedRecipe> p_240485_0_, ItemLike p_240485_1_, ItemLike p_240485_2_) {
        String s = Registry.ITEM.getKey(p_240485_1_.asItem()).getPath();
        String s1 = Registry.ITEM.getKey(p_240485_2_.asItem()).getPath();
        shaped(p_240485_1_, 8).define('#', Blocks.WHITE_CARPET).define('$', p_240485_2_).pattern("###").pattern("#$#").pattern("###").group("carpet").unlockedBy("has_white_carpet", has(Blocks.WHITE_CARPET)).unlockedBy("has_" + s1, has(p_240485_2_)).save(p_240485_0_, s + "_from_white_carpet");
    }

    private static void bedFromPlanksAndWool(Consumer<FinishedRecipe> p_240486_0_, ItemLike p_240486_1_, ItemLike p_240486_2_) {
        String s = Registry.ITEM.getKey(p_240486_2_.asItem()).getPath();
        shaped(p_240486_1_).define('#', p_240486_2_).define('X', ItemTags.PLANKS).pattern("###").pattern("XXX").group("bed").unlockedBy("has_" + s, has(p_240486_2_)).save(p_240486_0_);
    }

    private static void bedFromWhiteBedAndDye(Consumer<FinishedRecipe> p_240487_0_, ItemLike p_240487_1_, ItemLike p_240487_2_) {
        String s = Registry.ITEM.getKey(p_240487_1_.asItem()).getPath();
        ShapelessRecipeBuilder.shapeless(p_240487_1_).requires(Items.WHITE_BED).requires(p_240487_2_).group("dyed_bed").unlockedBy("has_bed", has(Items.WHITE_BED)).save(p_240487_0_, s + "_from_white_bed");
    }

    private static void banner(Consumer<FinishedRecipe> p_240488_0_, ItemLike p_240488_1_, ItemLike p_240488_2_) {
        String s = Registry.ITEM.getKey(p_240488_2_.asItem()).getPath();
        shaped(p_240488_1_).define('#', p_240488_2_).define('|', Items.STICK).pattern("###").pattern("###").pattern(" | ").group("banner").unlockedBy("has_" + s, has(p_240488_2_)).save(p_240488_0_);
    }

    private static void stainedGlassFromGlassAndDye(Consumer<FinishedRecipe> p_240489_0_, ItemLike p_240489_1_, ItemLike p_240489_2_) {
        shaped(p_240489_1_, 8).define('#', Blocks.GLASS).define('X', p_240489_2_).pattern("###").pattern("#X#").pattern("###").group("stained_glass").unlockedBy("has_glass", has(Blocks.GLASS)).save(p_240489_0_);
    }

    private static void stainedGlassPaneFromStainedGlass(Consumer<FinishedRecipe> p_240490_0_, ItemLike p_240490_1_, ItemLike p_240490_2_) {
        shaped(p_240490_1_, 16).define('#', p_240490_2_).pattern("###").pattern("###").group("stained_glass_pane").unlockedBy("has_glass", has(p_240490_2_)).save(p_240490_0_);
    }

    private static void stainedGlassPaneFromGlassPaneAndDye(Consumer<FinishedRecipe> p_240491_0_, ItemLike p_240491_1_, ItemLike p_240491_2_) {
        String s = Registry.ITEM.getKey(p_240491_1_.asItem()).getPath();
        String s1 = Registry.ITEM.getKey(p_240491_2_.asItem()).getPath();
        shaped(p_240491_1_, 8).define('#', Blocks.GLASS_PANE).define('$', p_240491_2_).pattern("###").pattern("#$#").pattern("###").group("stained_glass_pane").unlockedBy("has_glass_pane", has(Blocks.GLASS_PANE)).unlockedBy("has_" + s1, has(p_240491_2_)).save(p_240491_0_, s + "_from_glass_pane");
    }

    private static void coloredTerracottaFromTerracottaAndDye(Consumer<FinishedRecipe> p_240492_0_, ItemLike p_240492_1_, ItemLike p_240492_2_) {
        shaped(p_240492_1_, 8).define('#', Blocks.TERRACOTTA).define('X', p_240492_2_).pattern("###").pattern("#X#").pattern("###").group("stained_terracotta").unlockedBy("has_terracotta", has(Blocks.TERRACOTTA)).save(p_240492_0_);
    }

    private static void concretePowder(Consumer<FinishedRecipe> p_240493_0_, ItemLike p_240493_1_, ItemLike p_240493_2_) {
        ShapelessRecipeBuilder.shapeless(p_240493_1_, 8).requires(p_240493_2_).requires(Blocks.SAND, 4).requires(Blocks.GRAVEL, 4).group("concrete_powder").unlockedBy("has_sand", has(Blocks.SAND)).unlockedBy("has_gravel", has(Blocks.GRAVEL)).save(p_240493_0_);
    }

    private static void cookRecipes(Consumer<FinishedRecipe> p_218445_0_, String p_218445_1_, SimpleCookingSerializer<?> p_218445_2_, int p_218445_3_) {
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.BEEF), Items.COOKED_BEEF, 0.35F, p_218445_3_, p_218445_2_).unlockedBy("has_beef", has(Items.BEEF)).save(p_218445_0_, "cooked_beef_from_" + p_218445_1_);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.CHICKEN), Items.COOKED_CHICKEN, 0.35F, p_218445_3_, p_218445_2_).unlockedBy("has_chicken", has(Items.CHICKEN)).save(p_218445_0_, "cooked_chicken_from_" + p_218445_1_);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.COD), Items.COOKED_COD, 0.35F, p_218445_3_, p_218445_2_).unlockedBy("has_cod", has(Items.COD)).save(p_218445_0_, "cooked_cod_from_" + p_218445_1_);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Blocks.KELP), Items.DRIED_KELP, 0.1F, p_218445_3_, p_218445_2_).unlockedBy("has_kelp", has(Blocks.KELP)).save(p_218445_0_, "dried_kelp_from_" + p_218445_1_);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.SALMON), Items.COOKED_SALMON, 0.35F, p_218445_3_, p_218445_2_).unlockedBy("has_salmon", has(Items.SALMON)).save(p_218445_0_, "cooked_salmon_from_" + p_218445_1_);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.MUTTON), Items.COOKED_MUTTON, 0.35F, p_218445_3_, p_218445_2_).unlockedBy("has_mutton", has(Items.MUTTON)).save(p_218445_0_, "cooked_mutton_from_" + p_218445_1_);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.PORKCHOP), Items.COOKED_PORKCHOP, 0.35F, p_218445_3_, p_218445_2_).unlockedBy("has_porkchop", has(Items.PORKCHOP)).save(p_218445_0_, "cooked_porkchop_from_" + p_218445_1_);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.POTATO), Items.BAKED_POTATO, 0.35F, p_218445_3_, p_218445_2_).unlockedBy("has_potato", has(Items.POTATO)).save(p_218445_0_, "baked_potato_from_" + p_218445_1_);
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.RABBIT), Items.COOKED_RABBIT, 0.35F, p_218445_3_, p_218445_2_).unlockedBy("has_rabbit", has(Items.RABBIT)).save(p_218445_0_, "cooked_rabbit_from_" + p_218445_1_);
    }

    protected static EnterBlockTrigger.TriggerInstance insideOf(Block p_200407_0_) {
        return new EnterBlockTrigger.TriggerInstance(EntityPredicate.Composite.ANY, p_200407_0_, StatePropertiesPredicate.ANY);
    }

    protected static InventoryChangeTrigger.TriggerInstance has(ItemLike p_200403_0_) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(p_200403_0_).build());
    }

    protected static InventoryChangeTrigger.TriggerInstance has(Tag<Item> p_200409_0_) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(p_200409_0_).build());
    }

    protected static InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... p_200405_0_) {
        return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, p_200405_0_);
    }
}
package com.sammy.malum.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class MalumSaplingBlock extends SaplingBlock
{
    public final Supplier<Feature<NoneFeatureConfiguration>> tree;
    public MalumSaplingBlock(Properties properties, Supplier<Feature<NoneFeatureConfiguration>> tree)
    {
        super(null, properties);
        this.tree = tree;
    }

    @Override
    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, Random rand)
    {
        if (state.getValue(STAGE) == 0)
        {
            level.setBlock(pos, state.cycle(STAGE), 4);
        }
        else
        {
            if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(level, rand, pos))
            {
                return;
            }
            tree.get().place(new FeaturePlaceContext(Optional.empty(), level, level.getChunkSource().getGenerator(), rand,pos, NoneFeatureConfiguration.INSTANCE));
        }
    }
    
}
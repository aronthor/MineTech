package me.aronth.minetechplus.world;

import java.util.Random;

import me.aronth.minetechplus.core.BlockHandler;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenOres implements IWorldGenerator{

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        int dimId = world.provider.dimensionId;
        if(dimId == 0)
            generateSurface(random, chunkX*16, chunkZ*16, world);
        if(dimId == 1)
            generateEnd(random, chunkX*16, chunkZ*16, world);
        if(dimId == -1)
            generateNether(random, chunkX*16, chunkZ*16, world);
    }
    
    public void generateSurface(Random rand, int x, int z, World w){
        for(int i = 0; i < 70; i++){
            (new WorldGenMinable(BlockHandler.blockOre.blockID, 0, 8, Block.stone.blockID)).generate(w, rand, x+rand.nextInt(16), 32+rand.nextInt(64), z+rand.nextInt(16));
        }
        for(int i = 0; i < 70; i++){
            (new WorldGenMinable(BlockHandler.blockOre.blockID, 1, 8, Block.stone.blockID)).generate(w, rand, x+rand.nextInt(16), rand.nextInt(64), z+rand.nextInt(16));
        }
    }
    
    public void generateNether(Random rand, int x, int z, World w){
        
    }
    
    public void generateEnd(Random rand, int x, int z, World w){
        
    }

}

package com.shnupbups.simplehorseshoes.data;

import com.shnupbups.simplehorseshoes.register.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.IRON_HORSESHOE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_HORSESHOE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DIAMOND_HORSESHOE, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHERITE_HORSESHOE, Models.GENERATED);
    }
}

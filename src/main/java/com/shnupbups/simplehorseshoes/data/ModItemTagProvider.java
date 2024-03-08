package com.shnupbups.simplehorseshoes.data;

import com.shnupbups.simplehorseshoes.register.ModItems;
import com.shnupbups.simplehorseshoes.register.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        this.getOrCreateTagBuilder(ModTags.HORSESHOES).add(
            ModItems.IRON_HORSESHOE, ModItems.GOLDEN_HORSESHOE, ModItems.DIAMOND_HORSESHOE, ModItems.NETHERITE_HORSESHOE
        );
        this.getOrCreateTagBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE).addTag(ModTags.HORSESHOES);
        this.getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED).add(ModItems.GOLDEN_HORSESHOE);
    }
}

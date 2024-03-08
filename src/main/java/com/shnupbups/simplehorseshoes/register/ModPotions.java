package com.shnupbups.simplehorseshoes.register;

import com.shnupbups.simplehorseshoes.SimpleHorseshoes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class ModPotions {
    public static final String LUCK_BASE_NAME = "luck";
    public static final String BAD_LUCK_BASE_NAME = "bad_luck";

    public static final String LONG_PREFIX = "long_";
    public static final String STRONG_PREFIX = "strong_";

    public static final int BASE_DURATION = 6000;
    public static final int LONG_DURATION = 10000;
    public static final int STRONG_DURATION = 3000;

    public static final RegistryEntry<Potion> LONG_LUCK = register(
        LONG_PREFIX + LUCK_BASE_NAME, new Potion(
            LUCK_BASE_NAME, createLongEffect(StatusEffects.LUCK)
        )
    );
    public static final RegistryEntry<Potion> STRONG_LUCK = register(
        STRONG_PREFIX + LUCK_BASE_NAME, new Potion(
            LUCK_BASE_NAME, createStrongEffect(StatusEffects.LUCK)
        )
    );

    public static final RegistryEntry<Potion> BAD_LUCK = register(
        BAD_LUCK_BASE_NAME, new Potion(
            BAD_LUCK_BASE_NAME, createBaseEffect(StatusEffects.UNLUCK)
        )
    );
    public static final RegistryEntry<Potion> LONG_BAD_LUCK = register(
        LONG_PREFIX + BAD_LUCK_BASE_NAME, new Potion(
            BAD_LUCK_BASE_NAME, createLongEffect(StatusEffects.UNLUCK)
        )
    );
    public static final RegistryEntry<Potion> STRONG_BAD_LUCK = register(
        STRONG_PREFIX + BAD_LUCK_BASE_NAME, new Potion(
            BAD_LUCK_BASE_NAME, createStrongEffect(StatusEffects.UNLUCK)
        )
    );

    public static void init() {
        registerBaseRecipe(ModItems.GOLDEN_HORSESHOE, Potions.LUCK);
        registerLongRecipe(Potions.LUCK, LONG_LUCK);
        registerStrongRecipe(Potions.LUCK, STRONG_LUCK);
        registerCorruptRecipe(Potions.LUCK, BAD_LUCK);
        registerLongRecipe(BAD_LUCK, LONG_BAD_LUCK);
        registerStrongRecipe(BAD_LUCK, STRONG_BAD_LUCK);
        registerCorruptRecipe(LONG_LUCK, LONG_BAD_LUCK);
        registerCorruptRecipe(STRONG_LUCK, STRONG_BAD_LUCK);
    }

    private static StatusEffectInstance createBaseEffect(RegistryEntry<StatusEffect> effect) {
        return new StatusEffectInstance(effect, BASE_DURATION);
    }

    private static StatusEffectInstance createLongEffect(RegistryEntry<StatusEffect> effect) {
        return new StatusEffectInstance(effect, LONG_DURATION);
    }

    private static StatusEffectInstance createStrongEffect(RegistryEntry<StatusEffect> effect) {
        return new StatusEffectInstance(effect, STRONG_DURATION, 1);
    }

    private static void registerBaseRecipe(ItemConvertible input, RegistryEntry<Potion> output) {
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, input.asItem(), output);
    }

    private static void registerLongRecipe(RegistryEntry<Potion> input, RegistryEntry<Potion> output) {
        BrewingRecipeRegistry.registerPotionRecipe(input, Items.REDSTONE, output);
    }

    private static void registerStrongRecipe(RegistryEntry<Potion> input, RegistryEntry<Potion> output) {
        BrewingRecipeRegistry.registerPotionRecipe(input, Items.GLOWSTONE_DUST, output);
    }

    private static void registerCorruptRecipe(RegistryEntry<Potion> input, RegistryEntry<Potion> output) {
        BrewingRecipeRegistry.registerPotionRecipe(input, Items.FERMENTED_SPIDER_EYE, output);
    }

    private static RegistryEntry<Potion> register(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, SimpleHorseshoes.id(name), potion);
    }
}

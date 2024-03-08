package com.shnupbups.simplehorseshoes.register;

import com.shnupbups.simplehorseshoes.SimpleHorseshoes;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {
    public static final TagKey<Item> HORSESHOES = TagKey.of(RegistryKeys.ITEM, SimpleHorseshoes.id("horseshoes"));
}

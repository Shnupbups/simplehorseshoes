package com.shnupbups.simplehorseshoes.register;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

public class ModItemGroupStuff {
    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((entries) -> {
            entries.addAfter(Items.DIAMOND_HORSE_ARMOR,
                ModItems.IRON_HORSESHOE,
                ModItems.GOLDEN_HORSESHOE,
                ModItems.DIAMOND_HORSESHOE,
                ModItems.NETHERITE_HORSESHOE
            );
        });
    }
}

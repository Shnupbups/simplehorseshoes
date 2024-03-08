package com.shnupbups.simplehorseshoes.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public interface Shoeable {
    Inventory getHorseshoeInventory();
    boolean hasHorseshoeSlot();
    boolean isHorseshoe(ItemStack stack);
    void equipHorseshoe(PlayerEntity player, ItemStack stack);
    boolean isWearingHorseshoe();
}

package com.shnupbups.simplehorseshoes.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.shnupbups.simplehorseshoes.item.HorseshoeItem;
import net.minecraft.client.gui.screen.ingame.SmithingScreen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SmithingScreen.class)
public class SmithingScreenMixin {
    @WrapOperation(method = "equipArmorStand(Lnet/minecraft/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ArmorStandEntity;equipStack(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;)V", ordinal = 1))
    public void modifyArmorStandSlot(ArmorStandEntity armorStand, EquipmentSlot slot, ItemStack stack, Operation<Void> original) {
        if(stack.getItem() instanceof HorseshoeItem) armorStand.equipStack(EquipmentSlot.OFFHAND, stack);
        else original.call(armorStand, slot, stack);
    }
}

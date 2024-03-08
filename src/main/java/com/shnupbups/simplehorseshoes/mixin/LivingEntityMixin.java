package com.shnupbups.simplehorseshoes.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.shnupbups.simplehorseshoes.item.HorseshoeItem;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyReturnValue(method = "getPreferredEquipmentSlot(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/entity/EquipmentSlot;", at = @At("RETURN"))
    private static EquipmentSlot preventEquippingHorseshoe(EquipmentSlot original, ItemStack stack) {
        if(stack.getItem() instanceof HorseshoeItem) {
            return EquipmentSlot.MAINHAND;
        } return original;
    }
}

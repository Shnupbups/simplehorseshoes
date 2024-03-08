package com.shnupbups.simplehorseshoes.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.shnupbups.simplehorseshoes.util.Shoeable;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.inventory.SingleStackInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractHorseEntity.class)
public abstract class AbstractHorseEntityMixin extends AnimalEntity implements InventoryChangedListener, RideableInventory, Tameable, JumpingMount, Saddleable, Shoeable {
    @Unique
    private final Inventory horseshoeInventory = new SingleStackInventory() {
        @Override
        public ItemStack getStack() {
            return AbstractHorseEntityMixin.this.getEquippedStack(EquipmentSlot.FEET);
        }

        @Override
        public void setStack(ItemStack stack) {
            AbstractHorseEntityMixin.this.equipStack(EquipmentSlot.FEET, stack);
        }

        @Override
        public void markDirty() {
        }

        @Override
        public boolean canPlayerUse(PlayerEntity player) {
            return player.getVehicle() == AbstractHorseEntityMixin.this || player.canInteractWithEntity(AbstractHorseEntityMixin.this, 4.0);
        }
    };

    protected AbstractHorseEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interactMob(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AbstractHorseEntity;hasArmorSlot()Z"), cancellable = true)
    public void equipOnInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir, @Local ItemStack itemStack) {
        if (this.hasHorseshoeSlot() && this.isHorseshoe(itemStack) && !this.isWearingHorseshoe()) {
            this.equipHorseshoe(player, itemStack);
            cir.setReturnValue(ActionResult.success(this.getWorld().isClient));
        }
    }

    @Override
    public final Inventory getHorseshoeInventory() {
        return horseshoeInventory;
    }

    @Override
    public boolean hasHorseshoeSlot() {
        return false;
    }

    @Override
    public boolean isHorseshoe(ItemStack stack) {
        return false;
    }

    @Override
    public void equipHorseshoe(PlayerEntity player, ItemStack stack) {
        if (this.isHorseshoe(stack)) {
            this.equipStack(EquipmentSlot.FEET, stack.copyWithCount(1));
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }
    }

    @Override
    public boolean isWearingHorseshoe() {
        return !this.getEquippedStack(EquipmentSlot.FEET).isEmpty();
    }
}

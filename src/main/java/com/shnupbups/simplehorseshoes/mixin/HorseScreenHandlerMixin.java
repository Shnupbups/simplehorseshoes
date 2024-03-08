package com.shnupbups.simplehorseshoes.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.shnupbups.simplehorseshoes.util.Shoeable;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.HorseScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HorseScreenHandler.class)
public abstract class HorseScreenHandlerMixin extends ScreenHandler {
    @Unique
    private Inventory horseshoeInventory;

    @Unique
    private int horseshoeSlotId;

    protected HorseScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/entity/passive/AbstractHorseEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/HorseScreenHandler;hasChest(Lnet/minecraft/entity/passive/AbstractHorseEntity;)Z"))
    public void injectSlot(int syncId, PlayerInventory playerInventory, Inventory inventory, AbstractHorseEntity entity, CallbackInfo ci) {
        Shoeable shoeable = (Shoeable)entity;
        this.horseshoeInventory = shoeable.getHorseshoeInventory();
        Slot slot = this.addSlot(new Slot(this.horseshoeInventory, 0, 8, 54) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return shoeable.isHorseshoe(stack);
            }

            @Override
            public boolean isEnabled() {
                return shoeable.hasHorseshoeSlot();
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });
        horseshoeSlotId = slot.id;
    }

    @ModifyReturnValue(method = "canUse(Lnet/minecraft/entity/player/PlayerEntity;)Z", at = @At("RETURN"))
    public boolean modifyCanUse(boolean original, PlayerEntity player) {
        return original && horseshoeInventory.canPlayerUse(player);
    }

    @Inject(method = "quickMove(Lnet/minecraft/entity/player/PlayerEntity;I)Lnet/minecraft/item/ItemStack;", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/inventory/Inventory;size()I"), cancellable = true)
    public void injectQuickMove(PlayerEntity player, int slot, CallbackInfoReturnable<ItemStack> cir, @Local(ordinal = 1) ItemStack stack) {
        if (this.getSlot(horseshoeSlotId).canInsert(stack) && !this.getSlot(horseshoeSlotId).hasStack()) {
            if (!this.insertItem(stack, horseshoeSlotId, horseshoeSlotId + 1, false)) {
                cir.setReturnValue(ItemStack.EMPTY);
            }
        }
    }

    @ModifyConstant(method = "quickMove(Lnet/minecraft/entity/player/PlayerEntity;I)Lnet/minecraft/item/ItemStack;", constant = @Constant(intValue = 1, ordinal = 0))
    public int modifyQuickMoveConstant(int value) {
        return value + 1;
    }
}

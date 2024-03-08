package com.shnupbups.simplehorseshoes.mixin;

import com.shnupbups.simplehorseshoes.item.HorseshoeItem;
import com.shnupbups.simplehorseshoes.util.Shoeable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.VariantHolder;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HorseEntity.class)
public abstract class HorseEntityMixin extends AbstractHorseEntity implements VariantHolder<HorseColor>, Shoeable {
    protected HorseEntityMixin(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean hasHorseshoeSlot() {
        return true;
    }

    @Override
    public boolean isHorseshoe(ItemStack stack) {
        return stack.getItem() instanceof HorseshoeItem;
    }
}

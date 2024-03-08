package com.shnupbups.simplehorseshoes.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.shnupbups.simplehorseshoes.SimpleHorseshoesClient;
import com.shnupbups.simplehorseshoes.util.Shoeable;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HorseScreen;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.HorseScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseScreen.class)
public abstract class HorseScreenMixin extends HandledScreen<HorseScreenHandler> {
    @Final
    @Shadow
    private AbstractHorseEntity entity;

    public HorseScreenMixin(HorseScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V", at = @At("TAIL"))
    public void injectSlotIcon(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci, @Local(ordinal = 2) int i, @Local(ordinal = 3) int j) {
        Shoeable shoeable = (Shoeable)this.entity;
        if (shoeable.hasHorseshoeSlot()) {
            context.drawGuiTexture(SimpleHorseshoesClient.HORSESHOE_SLOT_TEXTURE, i + 7, j + 53, 18, 18);
        }
    }
}

package com.shnupbups.simplehorseshoes.mixin.client;

import com.shnupbups.simplehorseshoes.render.HorseshoeFeatureRenderer;
import net.minecraft.client.render.entity.AbstractHorseEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HorseEntityRenderer;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.entity.passive.HorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseEntityRenderer.class)
public abstract class HorseEntityRendererMixin extends AbstractHorseEntityRenderer<HorseEntity, HorseEntityModel<HorseEntity>> {
    public HorseEntityRendererMixin(EntityRendererFactory.Context ctx, HorseEntityModel<HorseEntity> model, float scale) {
        super(ctx, model, scale);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRendererFactory$Context;)V", at = @At("TAIL"))
    public void injectFeature(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.addFeature(new HorseshoeFeatureRenderer(this, context.getModelLoader()));
    }
}

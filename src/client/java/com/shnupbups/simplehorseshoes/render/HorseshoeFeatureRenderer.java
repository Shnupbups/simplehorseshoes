package com.shnupbups.simplehorseshoes.render;

import com.shnupbups.simplehorseshoes.item.HorseshoeItem;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HorseshoeFeatureRenderer extends FeatureRenderer<HorseEntity, HorseEntityModel<HorseEntity>> {
    private final HorseEntityModel<HorseEntity> model;

    public HorseshoeFeatureRenderer(FeatureRendererContext<HorseEntity, HorseEntityModel<HorseEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new HorseEntityModel(loader.getModelPart(EntityModelLayers.HORSE_ARMOR));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, HorseEntity horseEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack stack = horseEntity.getEquippedStack(EquipmentSlot.FEET);
        Item item = stack.getItem();
        if (item instanceof HorseshoeItem horseshoeItem) {
            this.getContextModel().copyStateTo(this.model);
            this.model.animateModel(horseEntity, limbAngle, limbDistance, tickDelta);
            this.model.setAngles(horseEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

            VertexConsumer mainVertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(horseshoeItem.getEntityTexture()));
            this.model.render(matrices, mainVertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

            if(stack.hasGlint()) {
                VertexConsumer glintVertexConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorEntityGlint());
                this.model.render(matrices, glintVertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}

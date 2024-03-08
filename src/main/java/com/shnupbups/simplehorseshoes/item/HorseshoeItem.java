package com.shnupbups.simplehorseshoes.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.shnupbups.simplehorseshoes.SimpleHorseshoes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class HorseshoeItem extends ArmorItem {
    public static final UUID SPEED_MODIFIER_UUID = UUID.fromString("db10078c-293d-427c-86a3-a31f0b9f6415");

    private final Identifier entityTexture;

    public HorseshoeItem(RegistryEntry<ArmorMaterial> material, Item.Settings settings) {
        super(material, Type.BOOTS, settings);
        this.entityTexture = getEntityTexture(material);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, createSpeedModifier(), AttributeModifierSlot.FEET).build();
    }

    private static EntityAttributeModifier createSpeedModifier() {
        return new EntityAttributeModifier(SPEED_MODIFIER_UUID, "Horseshoe modifier", 0.1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    public static Identifier getEntityTexture(RegistryEntry<ArmorMaterial> material) {
        Identifier materialId = material.getKey().orElseThrow().getValue();
        if(materialId.getNamespace().equals(Identifier.DEFAULT_NAMESPACE)) {
            materialId = SimpleHorseshoes.id(materialId.getPath());
        }
        return materialId.withPath((path) -> "textures/entity/horse/shoes/horseshoes_" + path + ".png");
    }

    public Identifier getEntityTexture() {
        return this.entityTexture;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> map = super.getAttributeModifiers(slot);
        if(slot.equals(EquipmentSlot.FEET)) {
            map = new ImmutableMultimap.Builder<RegistryEntry<EntityAttribute>, EntityAttributeModifier>().putAll(map).put(EntityAttributes.GENERIC_MOVEMENT_SPEED, createSpeedModifier()).build();
        }
        return map;
    }
}

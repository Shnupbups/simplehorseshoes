package com.shnupbups.simplehorseshoes.register;

import com.shnupbups.simplehorseshoes.SimpleHorseshoes;
import com.shnupbups.simplehorseshoes.item.HorseshoeItem;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
    public static final Item IRON_HORSESHOE = new HorseshoeItem(ArmorMaterials.IRON, new Item.Settings().maxCount(1)/*.attributeModifiers(HorseshoeItem.createAttributeModifiers())*/);
    public static final Item GOLDEN_HORSESHOE = new HorseshoeItem(ArmorMaterials.GOLD, new Item.Settings().maxCount(1)/*.attributeModifiers(HorseshoeItem.createAttributeModifiers())*/);
    public static final Item DIAMOND_HORSESHOE = new HorseshoeItem(ArmorMaterials.DIAMOND, new Item.Settings().maxCount(1)/*.attributeModifiers(HorseshoeItem.createAttributeModifiers())*/);
    public static final Item NETHERITE_HORSESHOE = new HorseshoeItem(ArmorMaterials.NETHERITE, new Item.Settings().maxCount(1).fireproof()/*.attributeModifiers(HorseshoeItem.createAttributeModifiers())*/);

    public static void init() {
        Registry.register(Registries.ITEM, SimpleHorseshoes.id("iron_horseshoe"), IRON_HORSESHOE);
        Registry.register(Registries.ITEM, SimpleHorseshoes.id("golden_horseshoe"), GOLDEN_HORSESHOE);
        Registry.register(Registries.ITEM, SimpleHorseshoes.id("diamond_horseshoe"), DIAMOND_HORSESHOE);
        Registry.register(Registries.ITEM, SimpleHorseshoes.id("netherite_horseshoe"), NETHERITE_HORSESHOE);
    }
}

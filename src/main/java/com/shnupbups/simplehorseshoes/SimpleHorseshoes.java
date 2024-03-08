package com.shnupbups.simplehorseshoes;

import com.mojang.logging.LogUtils;
import com.shnupbups.simplehorseshoes.register.ModItemGroupStuff;
import com.shnupbups.simplehorseshoes.register.ModItems;
import com.shnupbups.simplehorseshoes.register.ModPotions;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;

public class SimpleHorseshoes implements ModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
	public static final String MOD_ID = "simplehorseshoes";

	@Override
	public void onInitialize() {
		ModItems.init();
		ModItemGroupStuff.init();
		ModPotions.init();
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
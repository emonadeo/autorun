package com.emonadeo.autorun;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class AutoRunModMenu implements ModMenuApi, ConfigScreenFactory<Screen> {

	@Override
	public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
		return this;
	}

	@Override
	public Screen create(Screen screen) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(screen)
				.setTitle(Component.translatable("title." + AutoRunMod.MODID + ".config"));

		ConfigEntryBuilder entryBuilder = builder.entryBuilder();
		ConfigCategory general = builder
				.getOrCreateCategory(Component.translatable("config." + AutoRunMod.MODID + ".general"));

		// Toogle Auto-Jump
		general.addEntry(entryBuilder
				.startBooleanToggle(
						Component.translatable(
								"config." + AutoRunMod.MODID + ".toggleAutoJump"),
						AutoRunMod.toggleAutoJump)
				.setDefaultValue(true)
				.setTooltip(Component.translatable(
						"config." + AutoRunMod.MODID + ".toggleAutoJump.description"))
				.setSaveConsumer((value) -> AutoRunMod.toggleAutoJump = value)
				.build());

		// Persist Auto-Run
		general.addEntry(entryBuilder
				.startBooleanToggle(
						Component.translatable(
								"config." + AutoRunMod.MODID + ".persistAutoRun"),
						AutoRunMod.persistAutoRun)
				.setDefaultValue(false)
				.setTooltip(Component.translatable(
						"config." + AutoRunMod.MODID + ".persistAutoRun.description"))
				.setSaveConsumer((value) -> AutoRunMod.persistAutoRun = value)
				.build());

		// Always Sprint
		general.addEntry(entryBuilder
				.startBooleanToggle(
						Component.translatable(
								"config." + AutoRunMod.MODID + ".alwaysSprint"),
						AutoRunMod.alwaysSprint)
				.setDefaultValue(false)
				.setTooltip(Component.translatable(
						"config." + AutoRunMod.MODID + ".alwaysSprint"))
				.setSaveConsumer((value) -> AutoRunMod.alwaysSprint = value)
				.build());

		return builder.setSavingRunnable(() -> {
			AutoRunMod.saveConfig(AutoRunMod.CFG_FILE);
			AutoRunMod.loadConfig(AutoRunMod.CFG_FILE);
		}).build();
	}
}

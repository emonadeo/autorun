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

		// Always Sprint
		general.addEntry(entryBuilder
				.startBooleanToggle(
						Component.translatable("config." + AutoRunMod.MODID + ".alwaysSprint"),
						AutoRunMod.configSprint)
				.setDefaultValue(false)
				.setTooltip(Component.translatable("config." + AutoRunMod.MODID + ".alwaysSprint.description"))
				.setSaveConsumer((value) -> AutoRunMod.configSprint = value)
				.build());

		// Persist Auto-Run
		general.addEntry(entryBuilder
				.startBooleanToggle(
						Component.translatable("config." + AutoRunMod.MODID + ".persistAutoRun"),
						AutoRunMod.configPersistAutoRun)
				.setDefaultValue(false)
				.setTooltip(Component.translatable("config." + AutoRunMod.MODID + ".persistAutoRun.description"))
				.setSaveConsumer((value) -> AutoRunMod.configPersistAutoRun = value)
				.build());

		// Show Message
		general.addEntry(entryBuilder
				.startBooleanToggle(
						Component.translatable("config." + AutoRunMod.MODID + ".showMessage"),
						AutoRunMod.configShowMessage)
				.setDefaultValue(true)
				.setTooltip(Component.translatable("config." + AutoRunMod.MODID + ".showMessage.description"))
				.setSaveConsumer((value) -> AutoRunMod.configShowMessage = value)
				.build());

		// Toggle Auto-Jump
		general.addEntry(entryBuilder
				.startBooleanToggle(Component.translatable("options.autoJump"), AutoRunMod.configAutoJump)
				.setDefaultValue(true)
				.setTooltip(Component.translatable("config." + AutoRunMod.MODID + ".autoJump.description"))
				.setSaveConsumer((value) -> AutoRunMod.configAutoJump = value)
				.build());

		return builder.setSavingRunnable(() -> {
			AutoRunMod.saveConfig(AutoRunMod.CFG_FILE);
			AutoRunMod.loadConfig(AutoRunMod.CFG_FILE);
		}).build();
	}
}

package com.emonadeo.autorun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Input;

public class AutoRunMod implements ClientModInitializer {

	public static final String MODID = "autorun";
	public static final File CFG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(),
			"autorun.properties");

	public static boolean toggleAutoJump = true;
	public static boolean persistAutoRun = false;
	public static boolean alwaysSprint = false;

	public static boolean forward = false;
	public static boolean backward = false;
	public static boolean left = false;
	public static boolean right = false;
	public static boolean sprint = false;

	private static boolean activating = false;
	private static boolean originalAutoJumpSetting = false;

	private static KeyMapping keyBinding;

	@Override
	public void onInitializeClient() {
		loadConfig(CFG_FILE);
		// Re-save so that new properties will appear in old config files
		saveConfig(CFG_FILE);

		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyMapping(
				"key.autorun.toggle",
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_O, // Default to 'o'
				"key.categories.movement" // Append movement category
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyBinding.consumeClick() && client.level != null) {
				if (forward || backward || left || right) {
					disableAutoRun(client);
				} else {
					enableAutoRun(client);
					activating = true;
				}
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (activating) {
				if ((forward && !client.options.keyUp.isDown())
						|| (backward && !client.options.keyDown.isDown())
						|| (left && !client.options.keyLeft.isDown())
						|| (right && !client.options.keyRight.isDown())) {
					activating = false;
				}
				return;
			}

			if ((forward || backward) && (client.options.keyUp.isDown() || client.options.keyDown.isDown())) {
				disableAutoRun(client);
			}
			if ((left || right) && (client.options.keyLeft.isDown() || client.options.keyRight.isDown())) {
				disableAutoRun(client);
			}
		});

		ClientEntityEvents.ENTITY_UNLOAD.register((entity, clientWorld) -> {
			if (entity instanceof LocalPlayer && !persistAutoRun) {
				disableAutoRun(Minecraft.getInstance());
			}
		});
	}

	private static void enableAutoRun(Minecraft client) {
		client.player.displayClientMessage(Component.literal("Activating Auto-Run"), false);

		if (toggleAutoJump) {
			originalAutoJumpSetting = client.options.autoJump().get();
			client.options.autoJump().set(true);
			client.options.broadcastOptions();
		}

		Input input = client.player.input.keyPresses;
		if (input.sprint() || alwaysSprint) {
			sprint = true;
		}
		if (!input.forward() && !input.backward() && !input.left() && !input.right()) {
			// Auto-Run forward if no movement key is pressed
			forward = true;
			return;
		}
		// At least 1 movement key is pressed
		if (input.forward()) {
			forward = true;
		}
		if (input.backward()) {
			backward = true;
		}
		if (input.left()) {
			left = true;
		}
		if (input.right()) {
			right = true;
		}
	}

	private static void disableAutoRun(Minecraft client) {
		client.player.displayClientMessage(Component.literal("Deactivating Auto-Run"), false);

		forward = false;
		backward = false;
		left = false;
		right = false;
		sprint = false;

		// Restore Auto-Jump
		if (toggleAutoJump) {
			client.options.autoJump().set(originalAutoJumpSetting);
			client.options.broadcastOptions();
		}
	}

	public static void loadConfig(File file) {
		try {
			Properties cfg = new Properties();
			if (!file.exists()) {
				saveConfig(file);
			}
			cfg.load(new FileInputStream(file));
			alwaysSprint = Boolean.parseBoolean(cfg.getProperty("alwaysSprint", "false"));
			persistAutoRun = Boolean.parseBoolean(cfg.getProperty("persistAutoRun", "false"));
			toggleAutoJump = Boolean.parseBoolean(cfg.getProperty("toggleAutoJump", "true"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveConfig(File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file, false);
			fos.write(("alwaysSprint=" + alwaysSprint + "\n").getBytes());
			fos.write(("persistAutoRun=" + persistAutoRun + "\n").getBytes());
			fos.write(("toggleAutoJump=" + toggleAutoJump + "\n").getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

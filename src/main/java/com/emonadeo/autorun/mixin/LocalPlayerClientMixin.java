package com.emonadeo.autorun.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.emonadeo.autorun.AutoRunMod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

@Environment(EnvType.CLIENT)
@Mixin(LocalPlayer.class)
public class LocalPlayerClientMixin {

	@Redirect(method = "aiStep()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;isDown()Z"))
	public boolean isSprinting(KeyMapping keySprint) {
		Minecraft client = Minecraft.getInstance();
		if (AutoRunMod.sprint && keySprint == client.options.keySprint) {
			return true;
		}
		return keySprint.isDown();
	}
}
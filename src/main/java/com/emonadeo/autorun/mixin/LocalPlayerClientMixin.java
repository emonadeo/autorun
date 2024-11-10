package com.emonadeo.autorun.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.emonadeo.autorun.AutoRunMod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

@Environment(EnvType.CLIENT)
@Mixin(LocalPlayer.class)
public class LocalPlayerClientMixin {

	@WrapOperation(method = "aiStep()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;isDown()Z"))
	public boolean wrapSprint(KeyMapping instance, Operation<Boolean> original) {
		Minecraft client = Minecraft.getInstance();
		if (AutoRunMod.sprint && instance == client.options.keySprint) {
			return true;
		}
		return original.call(instance);
	}
}
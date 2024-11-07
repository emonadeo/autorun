package com.emonadeo.autorun.mixin;

import com.emonadeo.autorun.AutoRunMod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(LocalPlayer.class)
public class LocalPlayerClientMixin {
	// effectively a conditional redirect,
	// only overriding other mods if the condition is met
	@WrapOperation(method = "aiStep()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;isDown()Z"))
	public boolean isSprinting(KeyMapping instance, Operation<Boolean> original) {
		Minecraft client = Minecraft.getInstance();
		if (AutoRunMod.sprint && instance == client.options.keySprint) {
			return true;
		}
		return original.call(instance);
	}
}
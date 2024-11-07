package com.emonadeo.autorun.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.emonadeo.autorun.AutoRunMod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Input;

@Environment(EnvType.CLIENT)
@Mixin(Input.class)
public class InputClientMixin {
	// In the standard mixin world,
	// a redirect implies that you want to stop all
	// other mods from touching anything relating to this
	// and crash the game if they try.
	// For mod compatibility, it is better to find a more specific injector
	@ModifyReturnValue(method = "forward", at = @At("TAIL"))
	public boolean forward(boolean original) {
		return original || AutoRunMod.forward;
	}

	@ModifyReturnValue(method = "backward", at = @At("TAIL"))
	public boolean backward(boolean original) {
		return original || AutoRunMod.backward;
	}

	@ModifyReturnValue(method = "right", at = @At("TAIL"))
	public boolean right(boolean original) {
		return original || AutoRunMod.right;
	}

	@ModifyReturnValue(method = "left", at = @At("TAIL"))
	public boolean left(boolean original) {
		return original || AutoRunMod.left;
	}
}

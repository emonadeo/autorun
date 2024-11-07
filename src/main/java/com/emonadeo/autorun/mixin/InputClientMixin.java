package com.emonadeo.autorun.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.emonadeo.autorun.AutoRunMod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Input;

@Environment(EnvType.CLIENT)
@Mixin(Input.class)
public class InputClientMixin {

	@Inject(method = "forward", at = @At("HEAD"), cancellable = true)
	public void forward(CallbackInfoReturnable<Boolean> info) {
		if (AutoRunMod.forward) {
			info.setReturnValue(true);
		}
	}

	@Inject(method = "backward", at = @At("HEAD"), cancellable = true)
	public void backward(CallbackInfoReturnable<Boolean> info) {
		if (AutoRunMod.backward) {
			info.setReturnValue(true);
		}
	}

	@Inject(method = "right", at = @At("HEAD"), cancellable = true)
	public void right(CallbackInfoReturnable<Boolean> info) {
		if (AutoRunMod.right) {
			info.setReturnValue(true);
		}
	}

	@Inject(method = "left", at = @At("HEAD"), cancellable = true)
	public void left(CallbackInfoReturnable<Boolean> info) {
		if (AutoRunMod.left) {
			info.setReturnValue(true);
		}
	}
}

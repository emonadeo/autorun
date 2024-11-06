package com.emonadeo.autorun.mixin.client;

import com.emonadeo.autorun.AutoRunMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.player.Input;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Environment(EnvType.CLIENT)
@Mixin(Input.class)
public class InputMixin {
    @Shadow
    private boolean forward;

    @Shadow
    private boolean backward;

    @Shadow
    private boolean left;

    @Shadow
    private boolean right;

    public boolean forward() {
        return forward || AutoRunMod.forward;
    }

    public boolean backward() {
        return backward || AutoRunMod.backward;
    }

    public boolean left() {
        return left || AutoRunMod.left;
    }

    public boolean right() {
        return right || AutoRunMod.right;
    }
}

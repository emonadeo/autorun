package com.emonadeo.autorun.mixin.client;

import com.emonadeo.autorun.AutoRunMod;
import com.mojang.authlib.GameProfile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(LocalPlayer.class)
public class LocalPlayerMixin extends AbstractClientPlayer {
    public LocalPlayerMixin(ClientLevel clientLevel, GameProfile gameProfile) {
        super(clientLevel, gameProfile);
    }

    @Override
    public boolean isSprinting() {
        if (AutoRunMod.sprint) {
            return true;
        }
        return super.isSprinting();
    }

    @Override
    public void setSprinting(boolean bl) {
        if (AutoRunMod.sprint) {
            // Cancel out `setSprinting(false)` calls
            // (e.g. when bumping into something or receiving damage)
            super.setSprinting(true);
            return;
        }
        super.setSprinting(bl);
    }
}
# Auto-Run

[![Modrinth](https://img.shields.io/modrinth/dt/2i7tg1Wv?logo=modrinth&label=Modrinth)](https://modrinth.com/mod/autorun)
[![CurseForge](https://img.shields.io/curseforge/dt/279429?logo=curseforge&label=CurseForge&color=orange)](https://www.curseforge.com/minecraft/mc-mods/autorun-fabric)
[![GitHub](https://img.shields.io/github/downloads/emonadeo/autorun/total?logo=github&label=GitHub&color=blue)](https://github.com/Emonadeo/autorun/releases)

Hands-free walking and sprinting in Minecraft

## Installation

You can download the Auto-Run mod on [GitHub](https://github.com/Emonadeo/autorun/releases), [CurseForge](https://www.curseforge.com/minecraft/mc-mods/autorun-fabric) or [Modrinth](https://modrinth.com/mod/autorun).

See <https://docs.fabricmc.net/players/installing-mods> on how to install mods.

### Dependencies

Install these mods alongside Auto-Run.

- [Fabric API](https://modrinth.com/mod/fabric-api) is **required**
- [Mod Menu](https://modrinth.com/mod/modmenu) and [Cloth Config API](https://modrinth.com/mod/cloth-config) are **optional**

### Cross-version support

When a new Minecraft update drops, most of the times this mod “just works”.
For example `autorun-mc1.21.2-v1.1.0.jar` also works in 1.21.3 and 1.21.4.

Consider the `mc1.21.2` in the tag as the minimum Minecraft version this mod runs on. Check the [Compatiblity on Modrinth](https://modrinth.com/mod/autorun/versions) or [Game Versions on CurseForge](https://www.curseforge.com/minecraft/mc-mods/autorun-fabric/files) to see which Auto-Run version is compatible with what Minecraft versions.

If you cannot find a compatible version it's worth trying out the latest version of Auto-Run. In the best case it just works. In the worst case Minecraft crashes when you try to launch it.

In that case please create a [GitHub Issue](https://github.com/emonadeo/autorun/issues/new) and I will either update the version compatibility chart or release a new version if needed.

## Usage

Press <kbd>V</kbd> to toggle Auto-Run.
If you hold a specific direction during activation, it will in that direction.
You can also hit the sprint key to sprint while auto-walking.

This keybind can be customized in the controls options.


> [!NOTE]
> In `mc26.1-v1.5.0` and before the default key was <kbd>O</kbd>.
> In `mc26.2-v1.6.0` this has been changed to <kbd>V</kbd> to not conflict with
> the new friend list introduced in 26.2.

## Disclaimer

Almost all localization is AI-generated. If you see any translation errors or
inaccuracies please report them in the related issue on GitHub: <https://github.com/emonadeo/autorun/issues/10>

This is the **ONLY** part of the mod that was created with AI assistance. Everything
else was either written by hand or in the case of third-party contributions (PRs)
manually reviewed.

## FAQ

### Can I use it in my modpack?

**Yes.**

### Do I need to install Auto-Run on the server?

**No.** In fact you should **not** install it on the server.
This is a **client-side only** mod and should only be installed on the client.

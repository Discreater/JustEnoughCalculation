[![][1]][3] [![][2]][3] [![][4]][5]

[中文](README_CN.md)

**Moved to original repo https://github.com/Towdium/JustEnoughCalculation/tree/1.7.10**.

**已迁移至原仓库 https://github.com/Towdium/JustEnoughCalculation/tree/1.7.10** 。

# Just Enough Calculation Mod

Hey, this is a small add-on to **NEI** (Not Enough Items), in
order to help you calculate the amount of resources to craft a specific amount of item. .

This might be a little confusing, here's an example:

- you want to know how many wood logs do you need to craft 40 stairs, simply load the recipe you want to use, and it will
tell you 13 logs should be enough, that's all. 

This mod is focused on calculation, so you might find it powerful and high performance in some complicated cases. Have fun!

For all the release files, description or videos, refer to
the [release page](https://minecraft.curseforge.com/projects/just-enough-calculation).

## Features

- **Client only, no default keybinding. Set it up according to yourself.**
- Support get catalyst in [NEI-GTNH-2.1](https://github.com/GTNewHorizons/NotEnoughItems)
- Infinite inputs, outputs and catalysts of recipe
- Math calculation
- Recipe can transferred from NEI recipe handler (recipe gui)
  - [x] vanilla
  - [x] gregtech
  - [x] Forestry
  - [x] Avaritia (hard to find the transfer button)
  - [ ] AE2
  - [ ] Others
- [x] Item from NEI item panel
- [ ] Fluid from NEI item panel (Now need to get fluid from labels picker)
- [x] OreDictionary recognize


## Usage

- **Setup keybinding**
  ![setup keybinding](docs/setup_keybinding.png)

- Main page
  ![main page](docs/main_page.png)
  
- General usage: see [here](https://github.com/Towdium/JustEnoughCalculation/issues/85)

## TODO

- add new button to transfer the recipe. 

## Known Issues

- [ ] **May crash** if you open the gui immediately after entering the world
- [ ] Fluid from NEI ItemPanel will be recognized as item
- [ ] Some OreDict items renders wrong with overlay (Now removed the overlay)
- [ ] GUI will cover the NEI's GUI like tooltip on item panel
- [ ] Fluid from recipe needs lots of adapters, see [Adapter](./src/main/java/me/towdium/jecalculation/nei/Adapter.java).

## Development

For run `gradle runClient`, you need copy the mods from `dev-mods/` to `run/mods`.

[1]: http://cf.way2muchnoise.eu/full_just-enough-calculation_downloads.svg

[2]: http://cf.way2muchnoise.eu/versions/just-enough-calculation.svg

[3]: https://minecraft.curseforge.com/projects/just-enough-calculation

[4]: https://img.shields.io/discord/517485644163973120.svg?logo=discord

[5]: https://discord.gg/M3fNfTW


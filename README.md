# ShiftRightCLick
_**Run configurable commands by Shift + Right-Clicking a player.**_

> ShiftRightClick is a recreation of [https://github.com/TnTVlogs/ShiftClickPlugin](https://github.com/TnTVlogs/ShiftClickPlugin)

---

### Description

**ShiftClickPlayer** is a lightweight and configurable plugin that lets players execute custom commands by **Shift + Right-Clicking another player**.

Perfect for opening player menus, moderation tools, trading systems, profile GUIs, or any command-based interaction.

> AI was used rewrite this plugin, but don't worry.

## Features

* ✔ Shift + Right-Click detection
* ✔ Execute one or multiple configurable commands
* ✔ Placeholder support:

  * `%player_clicker%`
  * `%player_clicked%`
* ✔ Configurable cooldown (ticks)
* ✔ Lightweight with no external dependencies

## Configuration

```yaml
messages:
  prefix: "&6ShiftClick &8>&r "
  no_permission: "&cYou don't have permission to do this"

config:
  #Cooldown in ticks
  cooldown: 60

  #Add or remove worlds
  enabled_worlds:
    - minecraft:overworld
    - minecraft:the_nether
    - minecraft:the_end

  #You can use variable %player_clicker% and %player_clicked%
  commands:
    - command1
    - command2

```

## Permission

```
shiftclick.use
```

## Placeholders

| Placeholder        | Description                                   |
| ------------------ | --------------------------------------------- |
| `%player_clicker%` | The player performing the Shift + Right-Click |
| `%player_clicked%` | The player being clicked                      |

## Requirements

* Spigot/Paper **1.21+**

Really useful and really lightweight.
## 📊 bStats
[![Plugin's Stats](https://bstats.org/signatures/bukkit/ShiftRightClick.svg)](https://bstats.org/plugin/bukkit/ShiftRightClick)

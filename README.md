# RPG Natural Regen

**Slow natural healing with combat-aware pacing.**

RPG Natural Regen replaces hunger-driven vanilla healing with configurable slow health recovery. It is designed for packs that want healing to feel closer to RPG recovery: steady, readable, and slower during combat.

## Default behavior

- Suppresses vanilla hunger-based natural regeneration while the mod is installed.
- Does not change the world's `naturalRegeneration` gamerule.
- Heals players for 1 health every 100 ticks while in combat.
- Doubles the heal amount outside combat by default.
- Uses Combat State to decide whether a player is in combat.
- Can optionally restore extra health immediately after configured foods are eaten. The default list is empty.

## Configuration

Config file:

```text
config/rpg_natural_regen-common.toml
```

Default values:

```toml
[regeneration]
suppressVanillaNaturalRegeneration = true
enableSlowRegeneration = true
healIntervalTicks = 100
healAmount = 1.0
outOfCombatMultiplier = 2.0

[instant_food_healing]
foods = []
```

Food-healing entries use `item_id=health`, for example:

```toml
[instant_food_healing]
foods = ["minecraft:apple=4.0"]
```

`4.0` health is two hearts.

## Requirements

- Minecraft 1.21.1
- NeoForge
- Combat State

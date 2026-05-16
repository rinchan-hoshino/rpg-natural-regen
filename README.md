# RPG Natural Regen

**Slow natural healing with combat-aware pacing.**

RPG Natural Regen replaces hunger-driven vanilla healing with configurable slow health recovery. It is designed for packs that want healing to feel closer to RPG recovery: steady, readable, and slower during combat.

## Default behavior

- Disables vanilla `naturalRegeneration` when the server starts.
- Heals players for 1 health every 100 ticks while in combat.
- Doubles the heal amount outside combat by default.
- Keeps the West Melon Field apple first-aid rule: a normal apple restores 4 extra health.
- Uses Combat State to decide whether a player is in combat.

## Configuration

Config file:

```text
config/rpg_natural_regen-common.toml
```

Default values:

```toml
[regeneration]
disableVanillaNaturalRegeneration = true
enableSlowRegeneration = true
healIntervalTicks = 100
healAmount = 1.0
outOfCombatMultiplier = 2.0

[first_aid]
enableAppleFirstAid = true
appleHealAmount = 4.0
```

## Requirements

- Minecraft 1.21.1
- NeoForge
- Combat State

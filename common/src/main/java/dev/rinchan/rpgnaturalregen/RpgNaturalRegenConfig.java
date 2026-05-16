package dev.rinchan.rpgnaturalregen;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class RpgNaturalRegenConfig {
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue suppressVanillaNaturalRegeneration;
    public static final ModConfigSpec.BooleanValue enableSlowRegeneration;
    public static final ModConfigSpec.IntValue healIntervalTicks;
    public static final ModConfigSpec.DoubleValue healAmount;
    public static final ModConfigSpec.DoubleValue outOfCombatMultiplier;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("regeneration");
        suppressVanillaNaturalRegeneration = builder
            .comment("Suppress vanilla hunger-based natural regeneration while this mod is installed. This does not change the naturalRegeneration gamerule stored in the world.")
            .define("suppressVanillaNaturalRegeneration", true);
        enableSlowRegeneration = builder
            .comment("Enable slow RPG-style health regeneration.")
            .define("enableSlowRegeneration", true);
        healIntervalTicks = builder
            .comment("Ticks between slow regeneration pulses. West Melon Field default: 100 ticks, or 5 seconds.")
            .defineInRange("healIntervalTicks", 100, 1, 20 * 60 * 60);
        healAmount = builder
            .comment("Health restored per pulse while in combat. 1.0 health = half a heart.")
            .defineInRange("healAmount", 1.0D, 0D, 1024D);
        outOfCombatMultiplier = builder
            .comment("Multiplier for slow regeneration when the player is not in combat.")
            .defineInRange("outOfCombatMultiplier", 2.0D, 0D, 1024D);
        builder.pop();

        SPEC = builder.build();
    }

    private RpgNaturalRegenConfig() {
    }
}

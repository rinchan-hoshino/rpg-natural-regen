package dev.rinchan.rpgnaturalregen;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class RpgNaturalRegenConfig {
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue disableVanillaNaturalRegeneration;
    public static final ModConfigSpec.BooleanValue enableSlowRegeneration;
    public static final ModConfigSpec.IntValue healIntervalTicks;
    public static final ModConfigSpec.DoubleValue healAmount;
    public static final ModConfigSpec.DoubleValue outOfCombatMultiplier;
    public static final ModConfigSpec.BooleanValue enableAppleFirstAid;
    public static final ModConfigSpec.DoubleValue appleHealAmount;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("regeneration");
        disableVanillaNaturalRegeneration = builder
            .comment("Set vanilla naturalRegeneration to false when the server starts so healing is controlled by this mod instead of hunger.")
            .define("disableVanillaNaturalRegeneration", true);
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

        builder.push("first_aid");
        enableAppleFirstAid = builder
            .comment("Keep the pack's apple first-aid behavior: eating a normal apple restores extra health.")
            .define("enableAppleFirstAid", true);
        appleHealAmount = builder
            .comment("Extra health restored by eating a normal apple. West Melon Field default: 4.0 health, or two hearts.")
            .defineInRange("appleHealAmount", 4.0D, 0D, 1024D);
        builder.pop();

        SPEC = builder.build();
    }

    private RpgNaturalRegenConfig() {
    }
}

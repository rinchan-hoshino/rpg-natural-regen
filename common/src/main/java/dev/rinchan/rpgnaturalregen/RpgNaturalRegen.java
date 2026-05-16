package dev.rinchan.rpgnaturalregen;

import dev.rinchan.combatstate.CombatStateApi;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;

public final class RpgNaturalRegen {
    public static final String MOD_ID = "rpg_natural_regen";

    private RpgNaturalRegen() {
    }

    public static boolean suppressVanillaNaturalRegeneration(GameRules rules, GameRules.Key<GameRules.BooleanValue> key) {
        if (key == GameRules.RULE_NATURAL_REGENERATION && RpgNaturalRegenConfig.suppressVanillaNaturalRegeneration.get()) {
            return false;
        }
        return rules.getBoolean(key);
    }

    public static void tickPlayer(ServerPlayer player) {
        if (!RpgNaturalRegenConfig.enableSlowRegeneration.get()) {
            return;
        }
        if (!player.isAlive() || player.isDeadOrDying()) {
            return;
        }
        int interval = Math.max(1, RpgNaturalRegenConfig.healIntervalTicks.get());
        if (player.server.getTickCount() % interval != 0) {
            return;
        }
        float amount = RpgNaturalRegenConfig.healAmount.get().floatValue();
        if (!CombatStateApi.isInCombat(player)) {
            amount *= RpgNaturalRegenConfig.outOfCombatMultiplier.get().floatValue();
        }
        heal(player, amount);
    }

    private static void heal(ServerPlayer player, float amount) {
        if (amount <= 0F || player.getHealth() >= player.getMaxHealth()) {
            return;
        }
        player.heal(amount);
    }
}

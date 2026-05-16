package dev.rinchan.rpgnaturalregen;

import dev.rinchan.combatstate.CombatStateApi;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;

public final class RpgNaturalRegen {
    public static final String MOD_ID = "rpg_natural_regen";

    private RpgNaturalRegen() {
    }

    public static void enforceNaturalRegeneration(MinecraftServer server) {
        if (RpgNaturalRegenConfig.disableVanillaNaturalRegeneration.get()) {
            server.getGameRules().getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, server);
        }
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

    public static void finishUsingItem(ServerPlayer player, ItemStack usedStack) {
        if (!RpgNaturalRegenConfig.enableAppleFirstAid.get() || !usedStack.is(Items.APPLE)) {
            return;
        }
        heal(player, RpgNaturalRegenConfig.appleHealAmount.get().floatValue());
    }

    private static void heal(ServerPlayer player, float amount) {
        if (amount <= 0F || player.getHealth() >= player.getMaxHealth()) {
            return;
        }
        player.heal(amount);
    }
}

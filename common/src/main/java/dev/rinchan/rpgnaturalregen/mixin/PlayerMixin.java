package dev.rinchan.rpgnaturalregen.mixin;

import dev.rinchan.rpgnaturalregen.RpgNaturalRegen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Redirect(
        method = "aiStep",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z")
    )
    private boolean rpgNaturalRegen$suppressPeacefulNaturalRegeneration(GameRules rules, GameRules.Key<GameRules.BooleanValue> key) {
        return RpgNaturalRegen.suppressVanillaNaturalRegeneration(rules, key);
    }
}

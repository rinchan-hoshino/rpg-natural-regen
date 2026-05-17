package dev.rinchan.rpgnaturalregen.neoforge;

import dev.rinchan.rpgnaturalregen.RpgNaturalRegen;
import dev.rinchan.rpgnaturalregen.RpgNaturalRegenConfig;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@Mod(RpgNaturalRegen.MOD_ID)
public class RpgNaturalRegenNeoForge {
    public RpgNaturalRegenNeoForge(IEventBus modBus) {
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, RpgNaturalRegenConfig.SPEC);
        NeoForge.EVENT_BUS.addListener(this::onPlayerTick);
        NeoForge.EVENT_BUS.addListener(this::onItemUseFinish);
    }

    private void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            RpgNaturalRegen.tickPlayer(player);
        }
    }

    private void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            RpgNaturalRegen.finishUsingItem(player, event.getItem());
        }
    }
}

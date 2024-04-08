package dev.maxoduke.mods.slimetorch.platform.services;

import dev.maxoduke.mods.slimetorch.networking.SeedPacket;
import net.minecraft.server.level.ServerPlayer;

public interface INetworkHelper
{
    void sendSeedToClient(ServerPlayer player, SeedPacket seedPacket);
}

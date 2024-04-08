package dev.maxoduke.mods.slimetorch.platform;

import dev.maxoduke.mods.slimetorch.FabricInitializer;
import dev.maxoduke.mods.slimetorch.networking.SeedPacket;
import dev.maxoduke.mods.slimetorch.platform.services.INetworkHelper;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;

public class FabricNetworkHelper implements INetworkHelper
{
    @Override
    public void sendSeedToClient(ServerPlayer player, SeedPacket seedPacket)
    {
        ServerPlayNetworking.send(player, FabricInitializer.SEED_NETWORK_CHANNEL, seedPacket.asBuf());
    }
}

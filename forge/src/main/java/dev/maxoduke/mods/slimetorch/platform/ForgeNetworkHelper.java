package dev.maxoduke.mods.slimetorch.platform;

import dev.maxoduke.mods.slimetorch.networking.NetworkHandler;
import dev.maxoduke.mods.slimetorch.networking.SeedPacket;
import dev.maxoduke.mods.slimetorch.platform.services.INetworkHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class ForgeNetworkHelper implements INetworkHelper
{
    @Override
    public void sendSeedToClient(ServerPlayer player, SeedPacket seedPacket)
    {
        NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), seedPacket);
    }
}

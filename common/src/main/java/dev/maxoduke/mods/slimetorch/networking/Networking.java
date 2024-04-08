package dev.maxoduke.mods.slimetorch.networking;

import dev.maxoduke.mods.slimetorch.SlimeTorch;
import dev.maxoduke.mods.slimetorch.platform.Services;
import net.minecraft.server.level.ServerPlayer;

public class Networking
{
    public static void sendSeedToClient(ServerPlayer serverPlayer, long seed)
    {
        Services.NETWORK.sendSeedToClient(serverPlayer, new SeedPacket(seed));
    }

    public static void clientDisconnected()
    {
        SlimeTorch.setSeed(null);
    }

    public static void seedReceivedFromServer(SeedPacket seedPacket)
    {
        SlimeTorch.setSeed(seedPacket.seed());
    }
}

package dev.maxoduke.mods.slimetorch.networking;

import dev.maxoduke.mods.slimetorch.SlimeTorch;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler
{
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE;

    static
    {
        INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(SlimeTorch.MOD_ID, "main"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .simpleChannel();
    }

    public static void register()
    {
        INSTANCE
            .messageBuilder(SeedPacket.class, 1, NetworkDirection.PLAY_TO_CLIENT)
            .encoder(SeedPacket::writeToBuf)
            .decoder(SeedPacket::fromBuf)
            .consumerMainThread((seedPacket, context) -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> Networking.seedReceivedFromServer(seedPacket)))
            .add();
    }
}

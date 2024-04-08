package dev.maxoduke.mods.slimetorch;

import dev.maxoduke.mods.slimetorch.networking.Networking;
import dev.maxoduke.mods.slimetorch.networking.SeedPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;

@SuppressWarnings("unused")
public class FabricInitializer implements ModInitializer, ClientModInitializer
{
    public static final ResourceLocation SEED_NETWORK_CHANNEL = new ResourceLocation(SlimeTorch.MOD_ID, "seed_channel");

    @Override
    public void onInitialize()
    {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((content) -> content.accept(SlimeTorch.ITEM));
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(SlimeTorch.MOD_ID, SlimeTorch.ITEM_NAME), SlimeTorch.ITEM);

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> Networking.sendSeedToClient(handler.player, server.getWorldData().worldGenOptions().seed()));
    }

    @Override
    public void onInitializeClient()
    {
        ItemProperties.register(SlimeTorch.ITEM, new ResourceLocation("lit"), new SlimeTorchItemPropertyFunction());

        ClientPlayNetworking.registerGlobalReceiver(SEED_NETWORK_CHANNEL, (client, handler, buf, responseSender) -> Networking.seedReceivedFromServer(SeedPacket.fromBuf(buf)));
        ClientPlayConnectionEvents.DISCONNECT.register(SEED_NETWORK_CHANNEL, (handler, client) -> Networking.clientDisconnected());
    }
}

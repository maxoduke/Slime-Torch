package dev.maxoduke.mods.slimetorch;

import dev.maxoduke.mods.slimetorch.networking.NetworkHandler;
import dev.maxoduke.mods.slimetorch.networking.Networking;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
@Mod(SlimeTorch.MOD_ID)
public class ForgeInitializer
{
    private static final DeferredRegister<Item> ITEMS;

    static
    {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SlimeTorch.MOD_ID);
        ITEMS.register(SlimeTorch.ITEM_NAME, () -> SlimeTorch.ITEM);
    }

    public ForgeInitializer()
    {
        NetworkHandler.register();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
            event.accept(SlimeTorch.ITEM);
    }

    @SuppressWarnings("DataFlowIssue")
    @SubscribeEvent
    public void playerJoined(PlayerEvent.PlayerLoggedInEvent event)
    {
        ServerPlayer serverPlayer = (ServerPlayer) event.getEntity();
        long seed = serverPlayer.getServer().getWorldData().worldGenOptions().seed();

        Networking.sendSeedToClient(serverPlayer, seed);
    }

    @Mod.EventBusSubscriber(modid = SlimeTorch.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemProperties.register(SlimeTorch.ITEM, new ResourceLocation("lit"), new SlimeTorchItemPropertyFunction());
        }
    }

    @Mod.EventBusSubscriber(modid = SlimeTorch.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientModNetworkEvents
    {
        @SubscribeEvent
        public static void clientDisconnected(ClientPlayerNetworkEvent.LoggingOut event)
        {
            Networking.clientDisconnected();
        }
    }
}
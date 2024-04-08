package dev.maxoduke.mods.slimetorch.platform;

import dev.maxoduke.mods.slimetorch.platform.services.INetworkHelper;

import java.util.ServiceLoader;

public class Services
{
    public static final INetworkHelper NETWORK = load(INetworkHelper.class);

    public static <T> T load(Class<T> type)
    {
        return ServiceLoader.load(type)
            .findFirst()
            .orElseThrow(() -> new NullPointerException("Failed to load service for " + type.getName()));
    }
}

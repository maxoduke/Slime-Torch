package dev.maxoduke.mods.slimetorch;

import net.minecraft.world.item.Item;

import javax.annotation.Nullable;

public class SlimeTorch
{
    public static final String MOD_ID = "slimetorch";
    public static final String ITEM_NAME = "slime_torch";

    public static final Item ITEM = new Item(new Item.Properties());

    private static Long seed = null;
    public static void setSeed(@Nullable Long seed) { SlimeTorch.seed = seed; }
    public static @Nullable Long getSeed() { return seed; }
}

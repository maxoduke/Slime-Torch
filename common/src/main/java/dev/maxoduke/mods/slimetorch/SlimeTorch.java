package dev.maxoduke.mods.slimetorch;

import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

public class SlimeTorch
{
    public static final String MOD_ID = "slimetorch";
    public static final String ITEM_NAME = "slime_torch";
    public static final String MOD_NAME = "Slime Torch";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final Item ITEM = new Item(new Item.Properties());

    private static Long seed = null;
    public static void setSeed(@Nullable Long seed) { SlimeTorch.seed = seed; }
    public static @Nullable Long getSeed() { return seed; }
}

package dev.maxoduke.mods.slimetorch.networking;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;

public record SeedPacket(long seed)
{
    public void writeToBuf(FriendlyByteBuf buf)
    {
        buf.writeLong(seed);
    }

    public FriendlyByteBuf asBuf()
    {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        writeToBuf(buf);

        return buf;
    }

    public static SeedPacket fromBuf(FriendlyByteBuf buf)
    {
        return new SeedPacket(buf.readLong());
    }
}

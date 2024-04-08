package dev.maxoduke.mods.slimetorch;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SlimeTorchItemPropertyFunction implements ClampedItemPropertyFunction
{
    @Override
    public float unclampedCall(@NotNull ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity livingEntity, int i)
    {
        Entity itemEntity = livingEntity != null ? livingEntity : itemStack.getEntityRepresentation();
        if (itemEntity == null)
            return 0.0f;

        Long seed = SlimeTorch.getSeed();
        if (seed == null)
            return 0.0f;

        ChunkPos chunkPos = itemEntity.chunkPosition();
        int chunkX = chunkPos.x;
        int chunkZ = chunkPos.z;

        boolean isSlimeChunk = WorldgenRandom.seedSlimeChunk(chunkX, chunkZ, seed, 987234911L).nextInt(10) == 0;
        return isSlimeChunk ? 1.0f : 0.0f;
    }
}

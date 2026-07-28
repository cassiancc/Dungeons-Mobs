package com.infamous.dungeons_libraries.capabilities.elite;

import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import static com.infamous.dungeons_libraries.entities.elite.EliteMobConfig.EMPTY_TEXTURE;

public class EliteMob {

    public static final Codec<EliteMob> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    // Up to 16 fields can be declared here
                    Codec.BOOL.optionalFieldOf("is_elite", false).forGetter(EliteMob::isElite),
                    Codec.BOOL.optionalFieldOf("has_spawned", false).forGetter(EliteMob::hasSpawned),
                    ResourceLocation.CODEC.optionalFieldOf("texture", EMPTY_TEXTURE).forGetter(EliteMob::getTexture)
            )
            .apply(instance, (e, f, g)->new EliteMob(e, f, g)));
    private ResourceLocation texture;
    private boolean isElite;
    private boolean hasSpawned;

    public EliteMob(boolean isElite, boolean hasSpawned, ResourceLocation texture) {
        this.isElite = isElite;
        this.hasSpawned = hasSpawned;
        this.texture = texture;
    }

    public boolean isElite() {
        return isElite;
    }

    public void setElite(boolean elite) {
        isElite = elite;
    }

    public boolean hasSpawned() {
        return hasSpawned;
    }

    public void setHasSpawned(boolean hasSpawned) {
        this.hasSpawned = hasSpawned;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("isElite", isElite);
        nbt.putBoolean("hasSpawned", hasSpawned);
        if (texture != null) {
            nbt.putString("texture", texture.toString());
        }
        return nbt;
    }

    public void deserializeNBT(CompoundTag tag) {
        isElite = tag.getBoolean("isElite");
        hasSpawned = tag.getBoolean("hasSpawned");
        if (tag.contains("texture")) {
            texture = GeneralUtil.mcLoc(tag.getString("texture"));
        } else {
            texture = EMPTY_TEXTURE;
        }
    }

}

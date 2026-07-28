package com.infamous.dungeons_libraries.network.materials;

import com.infamous.dungeons_libraries.items.materials.weapon.DungeonsWeaponMaterial;
import com.infamous.dungeons_libraries.items.materials.weapon.WeaponMaterials;
import com.infamous.dungeons_libraries.network.gearconfig.ArmorGearConfigSyncPacket;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.infamous.dungeons_libraries.items.GearConfigReloadListener.reloadAllItems;

public class WeaponMaterialSyncPacket implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<WeaponMaterialSyncPacket> TYPE = new CustomPacketPayload.Type<>(GeneralUtil.librariesLoc("weapon_material_config_sync"));

    private static final Codec<Map<ResourceLocation, Tier>> MAPPER =
            Codec.unboundedMap(ResourceLocation.CODEC, DungeonsWeaponMaterial.CODEC);

    public static final StreamCodec<ByteBuf, WeaponMaterialSyncPacket> STREAM_CODEC =
            StreamCodec.composite(
                    // Stream codec and getter pair
                    ByteBufCodecs.fromCodec(MAPPER), WeaponMaterialSyncPacket::data,
                    WeaponMaterialSyncPacket::new
            );

    public final Map<ResourceLocation, Tier> data;

    public Map<ResourceLocation, Tier> data() {
        return data;
    }

    public WeaponMaterialSyncPacket(Map<ResourceLocation, Tier> data) {
        this.data = data.entrySet().stream().filter(entry -> entry.getValue() instanceof DungeonsWeaponMaterial).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeNbt((CompoundTag) (MAPPER.encodeStart(NbtOps.INSTANCE, this.data).result().orElse(new CompoundTag())));
    }

    public static WeaponMaterialSyncPacket decode(FriendlyByteBuf buffer) {
        return new WeaponMaterialSyncPacket(MAPPER.parse(NbtOps.INSTANCE, buffer.readNbt()).result().orElse(new HashMap<>()));
    }

    public void onPacketReceived(IPayloadContext context) {
        context.enqueueWork(this::handlePacketOnMainThread);
    }

    private void handlePacketOnMainThread() {
        WeaponMaterials.WEAPON_MATERIALS.setData(this.data);
        reloadAllItems();
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

package com.infamous.dungeons_libraries.network.materials;

import com.infamous.dungeons_libraries.items.materials.armor.DungeonsArmorMaterial;
import com.infamous.dungeons_libraries.items.materials.armor.DungeonsArmorMaterials;
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
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.infamous.dungeons_libraries.items.GearConfigReloadListener.reloadAllItems;

public class ArmorMaterialSyncPacket implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ArmorMaterialSyncPacket> TYPE = new CustomPacketPayload.Type<>(GeneralUtil.librariesLoc("armor_material_config_sync"));
    private static final Codec<Map<ResourceLocation, ArmorMaterial>> MAPPER =
            Codec.unboundedMap(ResourceLocation.CODEC, DungeonsArmorMaterial.CODEC);
    public static final StreamCodec<ByteBuf, ArmorMaterialSyncPacket> STREAM_CODEC =
            StreamCodec.composite(
                    // Stream codec and getter pair
                    ByteBufCodecs.fromCodec(MAPPER), ArmorMaterialSyncPacket::data,
                    ArmorMaterialSyncPacket::new
            );

    public final Map<ResourceLocation, ArmorMaterial> data;

    public Map<ResourceLocation, ArmorMaterial> data() {
        return data;
    }

    public ArmorMaterialSyncPacket(Map<ResourceLocation, ArmorMaterial> data) {
        this.data = data.entrySet().stream().filter(entry -> entry.getValue() instanceof DungeonsArmorMaterial).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeNbt((CompoundTag) (MAPPER.encodeStart(NbtOps.INSTANCE, this.data).result().orElse(new CompoundTag())));
    }

    public static ArmorMaterialSyncPacket decode(FriendlyByteBuf buffer) {
        return new ArmorMaterialSyncPacket(MAPPER.parse(NbtOps.INSTANCE, buffer.readNbt()).result().orElse(new HashMap<>()));
    }

    public void onPacketReceived(IPayloadContext context) {
        context.enqueueWork(this::handlePacketOnMainThread);
    }

    private void handlePacketOnMainThread() {
        DungeonsArmorMaterials.ARMOR_MATERIALS.setData(this.data);
        reloadAllItems();
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

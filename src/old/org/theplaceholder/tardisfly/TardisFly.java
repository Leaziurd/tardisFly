package org.theplaceholder.tardisfly;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.constants.TardisConstants;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.items.TardisPartItem;
import net.tardis.mod.tileentities.ConsoleTile;

import net.tardis.mod.tileentities.exteriors.*;
import org.theplaceholder.tardisfly.cap.Capabilities;
import org.theplaceholder.tardisfly.cap.fly.IPlayerTardisFly;

@Mod("tardisfly")
public class TardisFly {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "tardisfly");
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "tardisfly");

    public static final RegistryObject<Item> FLY_ENGINE = ITEMS.register("fly_engine", () -> new TardisPartItem(TardisConstants.Part.PartType.UPGRADE, false, true));

    public TardisFly() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();


    }

    public static String getTardisIDFromPlayer(PlayerEntity player){
        return player.getCapability(Capabilities.PLAYER_TARDIS_FLY).map(IPlayerTardisFly::getTardisID).orElse("0");
    }

    public static ConsoleTile getConsoleFromPlayer(PlayerEntity player){
        return TardisHelper.getConsoleInWorld(player.getServer().getLevel(WorldHelper.getWorldKeyFromRL(new ResourceLocation("tardis", getTardisIDFromPlayer(player))))).orElse(null);
    }

    public static ExteriorTile getExteriorFromPlayer(PlayerEntity player){
        return getConsoleFromPlayer(player).getOrFindExteriorTile().orElse(null);
    }
}
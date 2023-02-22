package org.theplaceholder.tardisfly.utils;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.player.PlayerEntity;
import net.tardis.mod.client.models.exteriors.ModernPoliceBoxExteriorModel;
import net.tardis.mod.client.models.exteriors.PoliceBoxExteriorModel;
import net.tardis.mod.client.renderers.exteriors.*;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.tileentities.exteriors.*;
import org.lwjgl.system.CallbackI;
import org.theplaceholder.tardisfly.cap.Capabilities;

public class ExteriorsIDs {

    public static int getExteriorID(ConsoleTile console){
        ExteriorTile exterior = console.getOrFindExteriorTile().orElse(null);

        if(exterior instanceof ClockExteriorTile)
            return 0;
        if(exterior instanceof SteampunkExteriorTile)
            return 1;
        if(exterior instanceof TrunkExteriorTile)
            return 2;
        if(exterior instanceof TelephoneExteriorTile)
            return 3;
        if(exterior instanceof PoliceBoxExteriorTile)
            return 4;
        if(exterior instanceof FortuneExteriorTile)
            return 5;
        if(exterior instanceof ModernPoliceBoxExteriorTile)
            return 6;
        if(exterior instanceof SafeExteriorTile)
            return 7;
        if(exterior instanceof TTCapsuleExteriorTile)
            return 8;
        if(exterior instanceof TT2020ExteriorTile)
            return 9;
        if(exterior instanceof JapanExteriorTile)
            return 10;
        if (exterior instanceof ApertureExteriorTile)
            return 11;
        if (exterior instanceof DisguiseExteriorTile)
            return 12;
        return -1;
    }

    public static void render(MatrixStack stack, IRenderTypeBuffer buffer, int packedLight, float partialTicks, int id){
            if(id == 0)
                new ClockExteriorRenderer(null).renderExterior(new ClockExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 1)
                new SteamExteriorRenderer(null).renderExterior(new SteampunkExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 2)
                new TrunkExteriorRenderer(null).renderExterior(new TrunkExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 3)
                new TelephoneExteriorRenderer(null).renderExterior(new TelephoneExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 4)
                new PoliceBoxExteriorRenderer(null).renderExterior(new PoliceBoxExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 5)
                new FortuneExteriorRenderer(null).renderExterior(new FortuneExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 6)
                new ModernPoliceBoxExteriorRenderer(null).renderExterior(new ModernPoliceBoxExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 7)
                new SafeExteriorRenderer(null).renderExterior(new SafeExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 8)
                new TTCapsuleExteriorRenderer(null).renderExterior(new TTCapsuleExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 9)
                new TT2020CapsuleExteriorRenderer(null).renderExterior(new TT2020ExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 10)
                new JapanExteriorRenderer(null).renderExterior(new JapanExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 11)
                new ApertureExteriorRenderer(null).renderExterior(new ApertureExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
            if(id == 12)
                new DisguiseExteriorTileRenderer(null).renderExterior(new DisguiseExteriorTile(), partialTicks, stack, buffer, packedLight, 1, 1f);
    }
}

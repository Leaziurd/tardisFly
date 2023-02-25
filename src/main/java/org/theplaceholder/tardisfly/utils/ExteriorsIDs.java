package org.theplaceholder.tardisfly.utils;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Quaternion;
import net.tardis.mod.client.TRenderTypes;
import net.tardis.mod.client.models.exteriors.ModernPoliceBoxExteriorModel;
import net.tardis.mod.client.models.exteriors.PoliceBoxExteriorModel;
import net.tardis.mod.client.renderers.exteriors.*;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.tileentities.exteriors.*;
import org.lwjgl.system.CallbackI;
import org.theplaceholder.tardisfly.cap.Capabilities;
import org.theplaceholder.tardisfly.client.ClientVars;
import org.theplaceholder.tardisfly.interfaces.ExteriorTileMixinInterface;
import org.theplaceholder.tardisfly.mixins.tardis.ExteriorTileMixin;

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

    public static void render(String uuid, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight, float partialTicks, int id, boolean isOnGround){
        switch (id){
            case 0:
                new ClockExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new ClockExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 1:
                new SteamExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new SteampunkExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 2:
                new TrunkExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new TrunkExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 3:
                new TelephoneExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new TelephoneExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 4:
                new PoliceBoxExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new PoliceBoxExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 5:
                new FortuneExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new FortuneExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 6:
                new ModernPoliceBoxExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new ModernPoliceBoxExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 7:
                new SafeExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new SafeExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 8:
                translateAndRotate(uuid, stack, 1.8f, isOnGround);
                new TTCapsuleExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new TTCapsuleExteriorTile(), partialTicks, stack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 9:
                new TT2020CapsuleExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new TT2020ExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 10:
                new JapanExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new JapanExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 11:
                new ApertureExteriorRenderer(TileEntityRendererDispatcher.instance).renderExterior(new ApertureExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
            case 12:
                new DisguiseExteriorTileRenderer(TileEntityRendererDispatcher.instance).renderExterior(new DisguiseExteriorTile(), partialTicks, stack, buffer, packedLight,  OverlayTexture.NO_OVERLAY, 1f);
                break;
        }
    }

    public static void translateAndRotate(String uuid, MatrixStack stack, float up, boolean isOnGround){
        if (ClientVars.rotationMapLastUpdated > 0)
            ClientVars.playerRotationMap.put(uuid, ClientVars.playerRotationMap.get(uuid) + 1.5f);

        ClientVars.rotationMapLastUpdated++;

        stack.translate(0, up, 0);
        if(!isOnGround) {
            translateFloating(stack);
        }
        else {
            stack.mulPose(new Quaternion(0, ClientVars.playerRotationMap.get(uuid), 0, true));
        }
    }

    public static void translateFloating(MatrixStack matrix){
        matrix.translate(0, Math.cos(Minecraft.getInstance().level.getGameTime() * 0.05) * 0.25, 0);
    }

}

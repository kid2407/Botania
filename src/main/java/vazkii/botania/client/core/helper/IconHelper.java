/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 14, 2014, 5:28:21 PM (GMT)]
 */
package vazkii.botania.client.core.helper;

import com.google.common.base.Function;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ITransformation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.lib.LibMisc;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public final class IconHelper {

	public static TextureAtlasSprite forName(TextureMap ir, String name) {
		return ir.registerSprite(new ResourceLocation(LibResources.PREFIX_MOD, name));
	}

	public static TextureAtlasSprite forName(TextureMap ir, String name, String dir) {
		return ir.registerSprite(new ResourceLocation(LibResources.PREFIX_MOD, dir + "/" + name));
	}

	public static TextureAtlasSprite forBlock(TextureMap ir, Block block) {
		return forName(ir, block.getUnlocalizedName().replaceAll("tile\\.", ""));
	}

	public static TextureAtlasSprite forBlock(TextureMap ir, Block block, int i) {
		return forBlock(ir, block, Integer.toString(i));
	}

	public static TextureAtlasSprite forBlock(TextureMap ir, Block block, int i, String dir) {
		return forBlock(ir, block, Integer.toString(i), dir);
	}

	public static TextureAtlasSprite forBlock(TextureMap ir, Block block, String s) {
		return forName(ir, block.getUnlocalizedName().replaceAll("tile\\.", "") + s);
	}

	public static TextureAtlasSprite forBlock(TextureMap ir, Block block, String s, String dir) {
		return forName(ir, block.getUnlocalizedName().replaceAll("tile\\.", "") + s, dir);
	}

	public static TextureAtlasSprite forItem(TextureMap ir, Item item) {
		return forName(ir, item.getUnlocalizedName().replaceAll("item\\.", ""));
	}

	public static TextureAtlasSprite forItem(TextureMap ir, Item item, int i) {
		return forItem(ir, item, Integer.toString(i));
	}

	public static TextureAtlasSprite forItem(TextureMap ir, Item item, String s) {
		return forName(ir, item.getUnlocalizedName().replaceAll("item\\.", "") + s);
	}

	/**
	 * Load and bake an arbitrary model
	 */
	public static IFlexibleBakedModel loadAndBakeArbitraryModel(ResourceLocation location) {

		try {
			IModel unbaked = ModelLoaderRegistry.getModel(location);
			return unbaked.bake(unbaked.getDefaultState(), DefaultVertexFormats.ITEM, new Function<ResourceLocation, TextureAtlasSprite>() {
				@Override
				public TextureAtlasSprite apply(ResourceLocation input) {
					return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(input.toString());
				}
			});
		} catch (IOException ex) {
			return null;
		}

	}

}
package com.suppergerrie2.sdrones.entities.rendering.models;

import javax.annotation.Nullable;

import com.suppergerrie2.sdrones.entities.EntityBasicDrone;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;

/**
 * HaulerDrone - suppergerrie2
 * Created using Tabula 7.0.0
 */
public class ModelDrone extends ModelBase {
	float itemOffset = 0.5f;

	public void renderInventory(Entity entity) {
		GlStateManager.pushMatrix();

		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(0.2, 1.7, -0.1);
		GlStateManager.rotate(180, 0, 0, 1);
		GlStateManager.rotate((float) (Math.sin(entity.ticksExisted%20/20f*Math.PI*2)*10f)-90, 1, 0, 0);
		GlStateManager.rotate(270, 0, 1, 0);

		ItemStack weapon = ((EntityBasicDrone)entity).getTool();

		if(!weapon.isEmpty()){
			renderItemStack(weapon, entity.world, (EntityLivingBase) entity);
		}

		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();

		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.rotate(180, 0, 0, 1);
		GlStateManager.translate(0, -1.5 + Math.sin(entity.ticksExisted%50/50f*Math.PI*2)*0.1f, 0);
		
		ItemStack[] stacks = ((EntityBasicDrone)entity).getItemStacksInDrone();
		for(int i = 0; i < stacks.length; i++) {
			ItemStack stack = stacks[i];
			if(stack==null||stack.isEmpty()) {
				continue;
			}
			
			for(int j = 0; j < Math.max(1, Math.min(5, stack.getCount())); j++) {
				GlStateManager.translate(0,itemOffset,0);
				
				renderItemStack(stack, entity.world, (EntityLivingBase) entity);
			}
		}

		GlStateManager.popMatrix();
	}

	public void renderItemStack(ItemStack stack, World world, @Nullable EntityLivingBase entity) {
		GlStateManager.pushMatrix();

		IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, world, entity);
		model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);

		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);


		GlStateManager.popMatrix();
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

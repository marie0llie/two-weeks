package gay.marie_the.twoweeks.mixin;

import com.mojang.blaze3d.glfw.Window;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.MutableText;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.analysis.Value;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
@Mixin(InGameHud.class)
public abstract class HotBarMixin {

	// WHY THE FUCK DIDNT YOU COMMENT THIS THE FIRST TIME

	// move everything right
	@ModifyVariable(method = "renderHotbar", at = @At(value = "STORE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/gui/GuiGraphics;)V"))
	private int moveEverythingRight(int x) {
		return MinecraftClient.getInstance().getWindow().getScaledWidth() - 116;
	}

	// Move Selector Up
	@ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V", ordinal = 1), index = 6)
	private int inject(int value) {

		return value + 2;
	}

	// move hotbar up
	@ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"), index = 2)
	private int moveHotbarUp(int value) {
		return value - 18;
	}

	// move rendered items up
	@ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(Lnet/minecraft/client/gui/GuiGraphics;IIFLnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;I)V"), index = 2)
	private int iteminject(int value) {
		// items
		return value - 18;
	}

	// xp level number (move down)

	// note: I HAVE NO IDEA WHAT THE XP STUFF IS DOING
	@ModifyVariable(method = "renderExperienceBar", at = @At(value = "STORE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/gui/GuiGraphics;I)V"), index = 6)
	private int fuckthisxptext(int value) {
		return value + 23;
	}



	// xp bar (move down)
	/*
	@ModifyVariable(method = "renderExperienceBar", at = @At(value = "STORE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/gui/GuiGraphics;I)V"), index = 3)
	private int injectxp(int value) {
		// xp bar
		return value + 23;
	}

	 */

	@ModifyVariable(method = "renderStatusBars", at = @At(value = "STORE"), ordinal = 5)
	private int fuck_the_u(int u) {

		return u + 2000;
	}

	// move held item tooltip to the right ( and down )

	@ModifyVariable(method="renderHeldItemTooltip", at = @At(value = "STORE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/gui/GuiGraphics;)V"), ordinal = 1)
	int moveTooltipLeft(int value) {
		return (value * 2) - 210;
	}
	@ModifyVariable(method="renderHeldItemTooltip", at = @At(value = "STORE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/gui/GuiGraphics;)V"), ordinal = 2)
	private int moveTooltipDown(int value) {
		return value + 32;
	}
}

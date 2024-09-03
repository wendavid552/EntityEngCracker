package club.mcams.entityrngcracker.mixins.translations;

import club.mcams.entityrngcracker.translations.CarpetExtensionTranslations;
import club.mcams.entityrngcracker.translations.ServerPlayerEntityWithClientLanguage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.BaseText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//#if MC >= 12002
//$$ import net.minecraft.network.packet.c2s.common.SyncedClientOptions;
//#else
import net.minecraft.network.packet.c2s.play.ClientSettingsC2SPacket;
//#endif

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements ServerPlayerEntityWithClientLanguage
{
	private String clientLanguage$CME = "en_US";

	@Inject(
			//#if MC >= 12002
			//$$ method = "setClientOptions",
			//#else
			method = "setClientSettings",
			//#endif
			at = @At("HEAD")
	)
	private void recordClientLanguage(
			//#if MC >= 12002
			//$$ SyncedClientOptions settings,
			//#else
			ClientSettingsC2SPacket packet,
			//#endif
			CallbackInfo ci)
	{
		this.clientLanguage$CME =
				//#if MC >= 12002
				//$$ settings.language();
				//#elseif MC >= 11800
				//$$ packet.language();
				//#else
				((ClientSettingsC2SPacketAccessor)packet).getLanguage$CME();
				//#endif
	}

	@Override
	public String getClientLanguage$CME()
	{
		return this.clientLanguage$CME;
	}
	/**
	 * This handle all TISCM translation on chat messages
	 */
	@ModifyVariable(
			method = {
					//#if MC >= 11901
					//$$ "sendMessageToClient",
					//#elseif MC >= 11600
					//$$ "sendMessage(Lnet/minecraft/text/Text;Z)V",
					//$$ "sendMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V",
					//#else
					"addChatMessage",
					"sendChatMessage",
					//#endif
			},
			at = @At("HEAD"),
			argsOnly = true
	)
	private Text applyTISCarpetTranslationToChatMessage(Text message)
	{
		if (message instanceof BaseText)
		{
			message = CarpetExtensionTranslations.translate((BaseText)message, (ServerPlayerEntity)(Object)this);
		}
		return message;
	}
}

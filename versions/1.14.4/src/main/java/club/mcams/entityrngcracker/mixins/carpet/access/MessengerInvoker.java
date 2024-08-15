/*
 * This file is part of the EntityRngCracker project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2024  WenDavid and contributors
 *
 * EntityRngCracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EntityRngCracker is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with EntityRngCracker.  If not, see <https://www.gnu.org/licenses/>.
 */

package club.mcams.entityrngcracker.mixins.carpet.access;

import carpet.utils.Messenger;
import club.mcams.entityrngcracker.utils.ModIds;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.text.BaseText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Restriction(require = @Condition(value = ModIds.minecraft, versionPredicates = "<1.15"))
@Mixin(Messenger.class)
public interface MessengerInvoker
{
	@Invoker(value = "_applyStyleToTextComponent", remap = false)
	static BaseText call_applyStyleToTextComponent(BaseText comp, String style)
	{
		return null;
	}
}
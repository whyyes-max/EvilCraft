package org.cyclops.evilcraft.api.broom;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.cyclops.cyclopscore.init.IRegistry;

import java.util.Collection;
import java.util.Map;

/**
 * Registry for broom parts.
 * @author rubensworks
 */
public interface IBroomPartRegistry extends IRegistry {

    /**
     * Register a new broom part.
     * @param part The part.
     * @param <P> The type of part.
     * @return The registered part.
     */
    public <P extends IBroomPart> P registerPart(P part);

    /**
     * Assign a part item to the given part.
     * @param part The part.
     * @param item The item-form of this part.
     * @param <P> The type of part.
     */
    public <P extends IBroomPart> void registerPartItem(P part, ItemStack item);

    /**
     * Assign base modifiers to the given part.
     * @param modifiers The modifiers.
     * @param part The part.
     * @param <P> The type of part.
     */
    public <P extends IBroomPart> void registerBaseModifiers(Map<BroomModifier, Float> modifiers, P part);

    /**
     * Assign base modifier to the given part.
     * @param modifier The modifier.
     * @param modifierValue The modifier value.
     * @param part The part.
     * @param <P> The type of part.
     */
    public <P extends IBroomPart> void registerBaseModifiers(BroomModifier modifier, float modifierValue, P part);

    /**
     * Get the base modifiers for the given part.
     * @param part The part.
     * @param <P> The part type.
     * @return The base modifiers.
     */
    public <P extends IBroomPart> Map<BroomModifier, Float> getBaseModifiersFromPart(P part);

    /**
     * Get the broom base modifiers from the given itemstack.
     * @param broomStack The broom item stack
     * @return The broom base modifiers.
     */
    public Map<BroomModifier, Float> getBaseModifiersFromBroom(ItemStack broomStack);

    /**
     * Get the item-form of the given part.
     * @param part The part.
     * @param <P> The part type.
     * @return The item-form or null.
     */
    public <P extends IBroomPart> ItemStack getItemFromPart(P part);

    /**
     * Get the part of the given item-form.
     * @param item The item-form of this part.
     * @param <P> The part type.
     * @return The part or null.
     */
    public <P extends IBroomPart> P getPartFromItem(ItemStack item);

    /**
     * @return All broom parts.
     */
    public Collection<IBroomPart> getParts();

    /**
     * Get the part from the given id.
     * @param partId The unique part id.
     * @return The corresponding part or null.
     */
    public IBroomPart getPart(ResourceLocation partId);

    /**
     * @param type The type of parts to retrieve.
     * @return All broom parts of the given type..
     */
    public Collection<IBroomPart> getParts(IBroomPart.BroomPartType type);

    /**
     * Register a model resource location for the given part.
     * @param part The part
     * @param modelLocation The model resource location.
     */
    @SideOnly(Side.CLIENT)
    public void registerPartModel(IBroomPart part, ResourceLocation modelLocation);

    /**
     * Get the model resource location of the given part.
     * @param part The part.
     * @return The model resource location.
     */
    @SideOnly(Side.CLIENT)
    public ResourceLocation getPartModel(IBroomPart part);

    /**
     * Get all registered model resource locations for the parts.
     * @return All model resource locations.
     */
    @SideOnly(Side.CLIENT)
    public Collection<ResourceLocation> getPartModels();

    /**
     * Get the broom parts in the given itemstack.
     * @param broomStack The broom item stack
     * @return The broom parts.
     */
    public Collection<IBroomPart> getBroomParts(ItemStack broomStack);

    /**
     * Apply the given broom parts to the given itemstack.
     * @param broomStack The broom item stack
     * @param broomParts The broom parts.
     */
    public void setBroomParts(ItemStack broomStack, Collection<IBroomPart> broomParts);

}
package evilcraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import evilcraft.api.config.ExtendedConfig;
import evilcraft.blocks.BloodInfuser;
import evilcraft.blocks.BloodInfuserConfig;
import evilcraft.blocks.DarkBlock;
import evilcraft.blocks.DarkBlockConfig;
import evilcraft.fluids.Blood;
import evilcraft.fluids.BloodConfig;
import evilcraft.items.BloodInfusionCore;
import evilcraft.items.BloodInfusionCoreConfig;
import evilcraft.items.BloodPearlOfTeleportation;
import evilcraft.items.BloodPearlOfTeleportationConfig;
import evilcraft.items.ContainedFlux;
import evilcraft.items.ContainedFluxConfig;
import evilcraft.items.DarkGem;
import evilcraft.items.DarkGemConfig;
import evilcraft.items.DarkPowerGem;
import evilcraft.items.DarkPowerGemConfig;
import evilcraft.items.HardenedBloodShard;
import evilcraft.items.HardenedBloodShardConfig;
import evilcraft.items.WeatherContainer;
import evilcraft.items.WeatherContainerConfig;

/**
 * Holder class of all the recipes.
 */
public class Recipes {
    
    public static boolean isItemEnabled(Class<? extends ExtendedConfig> config) {
        try {
            return ((ExtendedConfig)config.getField("_instance").get(null)).isEnabled();
        } catch (NullPointerException e1) {
            return false;
        } catch (IllegalArgumentException e2) {
        	return false;
        } catch (IllegalAccessException e3) {
        	return false;
        } catch (NoSuchFieldException e3) {
        	return false;
        } catch (SecurityException e4) {
        	return false;
        }
    }
    
    public static void registerRecipes() {
        // 9 DarkGems -> 1 DarkBlock
        if(isItemEnabled(DarkGemConfig.class) && isItemEnabled(DarkBlockConfig.class)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(DarkBlock.getInstance(), true,
                    new Object[]{
                    "GGG",
                    "GGG",
                    "GGG",
                    Character.valueOf('G'), DarkGemConfig._instance.getOreDictionaryId()})
            );
        }
        // 1 DarkBlock -> 9 DarkGems
        if(isItemEnabled(DarkGemConfig.class) && isItemEnabled(DarkBlockConfig.class)) {
            GameRegistry.addShapelessRecipe(new ItemStack(DarkGem.getInstance(), 9),
                    new ItemStack(DarkBlock.getInstance())
            );
        }
        // Weather Container
        if(isItemEnabled(WeatherContainerConfig.class) && isItemEnabled(ContainedFluxConfig.class)) {
            GameRegistry.addRecipe(new ItemStack(WeatherContainer.getInstance()),
                    " G ",
                    " P ",
                    " S ",
                    'G', new ItemStack(ContainedFlux.getInstance()),
                    'P', new ItemStack(Item.glassBottle),
                    'S', new ItemStack(Item.sugar)
            );
        }
        // Blood Pearl of Teleportation
        if(isItemEnabled(BloodPearlOfTeleportationConfig.class) && isItemEnabled(ContainedFluxConfig.class)) {
            GameRegistry.addRecipe(new ItemStack(BloodPearlOfTeleportation.getInstance()),
                    "EGE",
                    "GEG",
                    "EGE",
                    'G', new ItemStack(ContainedFlux.getInstance()),
                    'E', new ItemStack(Item.enderPearl)
            );
        }
        // Blood Infusion Core
        if(isItemEnabled(BloodInfusionCoreConfig.class) && isItemEnabled(HardenedBloodShardConfig.class) && isItemEnabled(DarkPowerGemConfig.class)) {
            GameRegistry.addRecipe(new ItemStack(BloodInfusionCore.getInstance()),
                    "SSS",
                    "SGS",
                    "SSS",
                    'S', new ItemStack(HardenedBloodShard.getInstance()),
                    'G', new ItemStack(DarkPowerGem.getInstance())
            );
        }
        // Blood Infuser
        if(isItemEnabled(BloodInfusionCoreConfig.class) && isItemEnabled(BloodInfuserConfig.class)) {
            GameRegistry.addRecipe(new ShapedOreRecipe(BloodInfuser.getInstance(), true, 
                    new Object[]{
                    "CCC",
                    "CIC",
                    "CCC",
                    'C', "cobblestone",
                    'I', new ItemStack(BloodInfusionCore.getInstance())})
            );
        }
        
        registerCustomRecipes();
    }
    
    public static void registerCustomRecipes() {
        if(isItemEnabled(DarkGemConfig.class) && isItemEnabled(DarkPowerGemConfig.class) && isItemEnabled(BloodConfig.class)) {
            CustomRecipeRegistry.put(new CustomRecipe(
                            new ItemStack(DarkGem.getInstance()),
                            new FluidStack(Blood.getInstance(), FluidContainerRegistry.BUCKET_VOLUME / 4),
                            BloodInfuser.getInstance(),
                            20
                        ),
                    new ItemStack(DarkPowerGem.getInstance()
            ));
        }
    }
}

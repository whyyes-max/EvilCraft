package evilcraft.item;

import org.cyclops.cyclopscore.config.extendedconfig.ExtendedConfig;
import org.cyclops.cyclopscore.config.extendedconfig.ItemConfig;
import evilcraft.entity.item.EntityLightningGrenade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Pearl that spawns lightning on collision.
 * @author rubensworks
 *
 */
public class LightningGrenade extends AbstractGrenade {
    
    private static LightningGrenade _instance = null;
    
    /**
     * Get the unique instance.
     * @return The instance.
     */
    public static LightningGrenade getInstance() {
        return _instance;
    }

    public LightningGrenade(ExtendedConfig<ItemConfig> eConfig) {
        super(eConfig);
    }

    @Override
    protected EntityThrowable getThrowableEntity(ItemStack itemStack, World world, EntityPlayer player) {
        return new EntityLightningGrenade(world, player);
    }
}

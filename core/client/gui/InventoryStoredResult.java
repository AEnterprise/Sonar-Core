package sonar.core.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;

public class InventoryStoredResult extends InventoryCraftResult
{
	IInventory module;
	public int offset;

    public InventoryStoredResult(IInventory module)
    {
        this.module = module;
    }
    
    public InventoryStoredResult(IInventory module, int offset)
    {
        this.module = module;
        this.offset = offset;
    }

    @Override
    public int getSizeInventory ()
    {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot (int par1)
    {
        return module.getStackInSlot(0+offset);
    }

    public String getInvName ()
    {
        return "Result";
    }

    public boolean isInvNameLocalized ()
    {
        return false;
    }

    @Override
    public ItemStack decrStackSize (int par1, int par2)
    {
        ItemStack stack = module.getStackInSlot(0+offset);
        if (stack != null)
        {
            ItemStack itemstack = stack;
            module.setInventorySlotContents(0+offset, null);
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing (int par1)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents (int par1, ItemStack par2ItemStack)
    {
    	module.setInventorySlotContents(0+offset, par2ItemStack);
    }

    @Override
    public int getInventoryStackLimit ()
    {
        return 64;
    }

    @Override
    public void markDirty ()
    {
    }

    @Override
    public boolean isUseableByPlayer (EntityPlayer player)
    {
        return true;
    }

    public void openChest ()
    {
    }

    public void closeChest ()
    {
    }

    public boolean isStackValidForSlot (int par1, ItemStack par2ItemStack)
    {
        return true;
    }
}

package sonar.core.inventory;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import sonar.core.network.PacketTileSync;
import sonar.core.network.SonarPackets;
import sonar.core.utils.ISyncTile;
import sonar.core.utils.helpers.NBTHelper;

public abstract class ContainerSync extends Container {

	public ISyncTile sync;
	public TileEntity tile;

	public ContainerSync(ISyncTile sync, TileEntity tile) {
		this.sync = sync;
		this.tile = tile;

	}

	public ContainerSync(TileEntity tile) {
		if (tile instanceof ISyncTile) {
			sync = (ISyncTile) tile;
		}
		this.tile = tile;
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		if (sync != null) {
			if (crafters != null) {
				NBTTagCompound syncData = new NBTTagCompound();
				sync.writeData(syncData, NBTHelper.SyncType.SYNC);
				for (Object o : crafters) {
					if (o != null && o instanceof EntityPlayerMP) {
						SonarPackets.network.sendTo(new PacketTileSync(tile.xCoord, tile.yCoord, tile.zCoord, syncData), (EntityPlayerMP) o);
					}
				}

			}

		}
	}
}

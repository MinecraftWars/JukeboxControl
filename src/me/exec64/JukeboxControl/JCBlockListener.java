package me.exec64.JukeboxControl;

import org.bukkit.block.Block;
import org.bukkit.block.Jukebox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class JCBlockListener implements Listener {
	public JukeboxControl plugin;
	
	public JCBlockListener(JukeboxControl instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockRedstoneChange(BlockRedstoneEvent event) {		

		Block src = event.getBlock();
		
		//Check any adjacent blocks that could power this.
		checkJukebox(src.getRelative(0, 0, 1));
		checkJukebox(src.getRelative(0, 0, -1));
		checkJukebox(src.getRelative(1, 0, 0));
		checkJukebox(src.getRelative(-1, 0, 0));
		checkJukebox(src.getRelative(0, -1, 0));
	}
	
	private void checkJukebox( Block block ) {
		
		if( block.getState() instanceof Jukebox ) {
			Jukebox jb = null;
			
			try {
				jb = (Jukebox) block.getState();
			} catch(ClassCastException e) {
				plugin.getLogger().warning("Failed to cast block to jukebox.");
				return;
			}
			finally {
			}
			
			//We were not powered, so we're turning on now.
			if(!block.isBlockPowered()) {
				if(jb.isPlaying()) {
					//Right now jukeboxes claim to be playing, even if the song is over.
					//It's presumably a bug with bukkit. All I can do is tell the
					//jukebox to restart the current song on redstone signal.
					jb.setPlaying(jb.getPlaying());
				}
			}
			
		}
	}
}

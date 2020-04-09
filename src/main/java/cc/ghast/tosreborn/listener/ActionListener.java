package cc.ghast.tosreborn.listener;

import cc.ghast.tosreborn.TOSReborn;
import cc.ghast.tosreborn.api.utils.chat.Chat;
import cc.ghast.tosreborn.managers.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

/**
 * @author Ghast
 * @since 07-Apr-20
 */

// ALL CREDITS TO https://www.spigotmc.org/resources/tos.100/, https://github.com/YoFuzzy3/ToS/tree/master/src
public class ActionListener implements Listener {
    private TOSReborn plugin;

    public ActionListener(TOSReborn plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.Move.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.Move.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.Talk.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.Talk.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.BlockInteract.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.BlockInteract.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.EntityInteract.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.EntityInteract.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.BlockPlace.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.BlockPlace.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.BlockBreak.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.BlockBreak.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            if(ConfigManager.getConfig().getBoolean("Options.InventoryClick.Deny")){
                if(!plugin.getStorageManager().computeIfAbsent(event.getWhoClicked().getUniqueId()).isAgreed()){
                    event.setCancelled(true);
                    if(ConfigManager.getConfig().getBoolean("Options.InventoryClick.Message")){
                        ((Player) event.getWhoClicked()).sendMessage(Chat.translate(ConfigManager.getConfig()
                                .getString("Messages.DenyMessage")));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.ItemPickup.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.ItemPickup.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.ItemDrop.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.ItemDrop.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.ItemConsume.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.ItemConsume.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.ItemHeld.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.ItemHeld.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event){
        if(event.getEntity().getShooter() instanceof Player){
            if(ConfigManager.getConfig().getBoolean("Options.ProjectileLaunch.Deny")){
                if(!plugin.getStorageManager().computeIfAbsent(((Player) event.getEntity().getShooter()).getUniqueId()).isAgreed()){
                    event.setCancelled(true);
                    if(ConfigManager.getConfig().getBoolean("Options.ProjectileLaunch.Message")){
                        ((Player) event.getEntity().getShooter()).sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            if(ConfigManager.getConfig().getBoolean("Options.Attack.Deny")){
                if(plugin.getStorageManager().computeIfAbsent(((Player) event.getDamager()).getUniqueId()).isAgreed()){
                    event.setCancelled(true);
                    if(ConfigManager.getConfig().getBoolean("Options.Attack.Message")){
                        ((Player) event.getDamager()).sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            if(ConfigManager.getConfig().getBoolean("Options.Damage.Deny")){
                if(plugin.getStorageManager().computeIfAbsent(((Player) event.getEntity()).getUniqueId()).isAgreed()){
                    event.setCancelled(true);
                    if(ConfigManager.getConfig().getBoolean("Options.Damage.Message")){
                        ((Player) event.getEntity()).sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.BucketEmpty.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.BucketEmpty.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.BucketFill.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.BucketFill.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.ToggleSneak.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.ToggleSneak.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerToggleSprint(PlayerToggleSprintEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.ToggleSprint.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.ToggleSprint.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.ExpChange.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setAmount(0);
                if(ConfigManager.getConfig().getBoolean("Options.ExpChange.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event){
        if(ConfigManager.getConfig().getBoolean("Options.PortalUsage.Deny")){
            if(!plugin.getStorageManager().getCachedPlayer(event.getPlayer().getUniqueId()).isAgreed()){
                event.setCancelled(true);
                if(ConfigManager.getConfig().getBoolean("Options.PortalUsage.Message")){
                    event.getPlayer().sendMessage(Chat.translate(ConfigManager.getConfig().getString("Messages.DenyMessage")));
                }
            }
        }
    }
}

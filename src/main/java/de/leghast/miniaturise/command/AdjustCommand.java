package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.ui.Interface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdjustCommand implements CommandExecutor {

    private Miniaturise main;


    public AdjustCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(!main.getSettingsManager().hasAdjusterSettings(player.getUniqueId())){
                main.getSettingsManager().addAdjusterSettings(player.getUniqueId());
            }
            new Interface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getLastPage());
            return true;
        }
        return false;
    }

    public Miniaturise getMain(){
        return main;
    }
}

package net.verdox.colorffa.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Setter
@AllArgsConstructor
public class ColoredBlock {
    private Location location;
    private Player player;
    private Material material;
}

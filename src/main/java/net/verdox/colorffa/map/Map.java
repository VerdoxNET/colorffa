package net.verdox.colorffa.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Map {
    private String name;
    private Location spawn;
    private int spawnHeight;
    private int deathHeight;
    private Material defaultMaterial;
    private String[] authors;
}

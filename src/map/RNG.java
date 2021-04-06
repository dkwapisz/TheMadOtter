package map;

import javafx.scene.layout.Pane;
import model.block.*;
import model.enemy.*;
import model.item.*;
import model.item.guns.*;

import java.util.ArrayList;
import java.util.Random;

public class RNG {

    private final Pane layer;
    private BitMapLoader bitMap;
    private final int floorId;
    private final int centerRoom; // id pomiszczenia środkowego
    private final Random random;
    //private ArrayList<BitMapLoader> usedBitMaps = new ArrayList<>();

    public RNG(int centerRoom, int floorId, int roomId, Pane layer) {
        this.layer = layer;
        this.floorId = floorId;
        this.centerRoom = centerRoom;
        this.random = new Random();
        loadBitMap(roomId);
    }

    private void loadBitMap(int roomId) {
        if(roomId != centerRoom) {
            int randCol = random.nextInt(2);
            int randRow = random.nextInt(2);
            bitMap = new BitMapLoader(randCol, randRow); // nie podajemy współrzędnych bitmapy, tylko w której kolumnie i rzędzie występuje (liczone od 0)
        }
    }

    public ArrayList<Item> itemsGenerator(int roomId) {
        ArrayList<Item> items = new ArrayList<>();
        if(floorId == 1) {
            items.add(new Uzi(100, 100, layer));
            items.add(new Ak47(100, 200, layer));
            items.add(new SniperRifle(100, 300, layer));
            items.add(new Deagle(100, 400, layer));
            items.add(new PlasmaGun(100, 500, layer));
            items.add(new Shotgun(100, 600, layer));
            items.add(new RocketLauncher(100, 700, layer));
            items.add(new Fish(700,100, layer));
            items.add(new SmallFish(600,100, layer));
            items.add(new Coin(700,200, layer));
            items.add(new Sign(700,300, layer, "hello whats up"));
            items.add(new Sign(600,300, layer, "JD JD JD"));
            items.add(new Dolar(700,400, layer));
            items.add(new Bomb(700,500, layer));
            items.add(new BombPack(700,600, layer));
        } else if (floorId == 2) {
            items.add(new M16(200, 100, layer));
            items.add(new Mp5(200, 200, layer));
            items.add(new Glock(200, 300, layer));
            items.add(new LaserGun(200, 400, layer));
            items.add(new Scar(200, 500, layer));
            items.add(new Stg44(200, 600, layer));
            items.add(new Fish(700,100, layer));
        }

        if(roomId != (centerRoom)) {
            for (Item item : items) {
                item.removeFromLayer();
            }
        }

        return items;
    }

    public ArrayList<Block> blockGenerator(int roomId) {
        ArrayList<Block> blocks = new ArrayList<>();

        for(int i = 0; i < bitMap.getSolidBlocksLoc().size(); i++) {
            blocks.add(new SolidBlock(bitMap.getSolidBlocksLoc().get(i)[0], bitMap.getSolidBlocksLoc().get(i)[1], layer));
        }

        for(int i = 0; i < bitMap.getSoftBlocksLoc().size(); i++) {
            blocks.add(new SoftBlock(bitMap.getSoftBlocksLoc().get(i)[0], bitMap.getSoftBlocksLoc().get(i)[1], layer));
        }

        for(int i = 0; i < bitMap.getSpikeBlockLoc().size(); i++) {
            blocks.add(new SpikeBlock(bitMap.getSpikeBlockLoc().get(i)[0], bitMap.getSpikeBlockLoc().get(i)[1], layer));
        }

        if(roomId != (centerRoom)) {
            for (Block block : blocks) {
                block.removeFromLayer();
            }
        }

        return blocks;
    }

    public ArrayList<Enemy> enemiesGenerator(int roomId){
        ArrayList<Enemy> enemies = new ArrayList<>();
        if(roomId == 7) {
            enemies.add(new Bat(500, 500, layer));
            enemies.add(new Enemy5(300, 500, layer));
        }
        if(roomId == 11) {
            enemies.add(new Enemy1(500, 500, layer));
            enemies.add(new Enemy2(300, 500, layer));
            enemies.add(new Enemy3(600, 300, layer));
            enemies.add(new Turret(200, 300, layer));
            enemies.add(new Slime(400,500, layer,"SlimeKing"));

        }
        if(roomId == 13) {
            enemies.add(new Slime(400,500, layer,"SlimeKing"));
        }
        if(roomId == 17) {
            enemies.add(new Diglet(500, 500, layer, bitMap.getEnemySpotLoc()));
            enemies.add(new Enemy7(300, 500, layer, bitMap.getEnemySpotLoc()));
        }
        if(roomId == 18) {
            enemies.add(new Turret(500, 500, layer));
            enemies.add(new Snake(500, 300, layer));
            enemies.add(new Wasp(100, 700, layer));
            enemies.add(new Fly(360, 700, layer));
            enemies.add(new Crab(360, 700, layer));
        }

        return enemies;
    }

}

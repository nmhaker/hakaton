package bot;

import java.util.ArrayList;
import constants.ItemType;
import constants.MapSize;
import constants.WeaponType;
import models.Game;
import models.Tile;
import models.items.Item;
import models.Player;
import commands.enums.Direction;
import models.items.shops.MetalShop;
import models.items.shops.StoneShop;
import models.items.shops.WoodShop;
import models.weapons.ArrowWeapon;
import models.weapons.SwordWeapon;
import models.weapons.Weapon;

import static constants.ItemType.*;

public class Helpers {

    public static Game game;

    public static int playerID;

    public static int getPlayerID() {
        return playerID;
    }

    public static void setPlayerID(int playerID) {
        Helpers.playerID = playerID;
    }




    public static void SetGame(Game g){
       game = g;
    }

    public static ArrayList<Tile> GetStores(){

        ArrayList<Tile> stores = new ArrayList<>();
        for (Tile[] tile1 : game.result.map.tiles) {
            for (Tile tile2 : tile1) {
                if(tile2.shop) stores.add(tile2);
            }
        }
        return stores;
    }

    public static ArrayList<Tile> GetBuildings(){

        ArrayList<Tile> buildings = new ArrayList<>();
        Player me = returnMe();
        for (Item item : me.buildings) {
            buildings.add(game.result.map.tiles[item.y][item.x]);
        }
        return buildings;
    }

    public static ArrayList<Tile> GetStoresForUpperLeftPlayer(){

        ArrayList<Tile> shortenedList = new ArrayList<>();

        ArrayList<Tile> stores = GetStores();
        for (Tile tile : stores) {
            if(UpperLeftDistance(tile) < LowerRightDistance(tile)) shortenedList.add(tile);
        }
        return shortenedList;
    }

    public static ArrayList<Tile> GetStoresForLoweRigthPlayer(){
        ArrayList<Tile> shortenedList = new ArrayList<>();

        ArrayList<Tile> stores = GetStores();
        for (Tile tile : stores) {
            if(UpperLeftDistance(tile) > LowerRightDistance(tile)) shortenedList.add(tile);
        }
        return shortenedList;
    }

    public static Tile GetNearestStoreForUpperLeftPlayer(String storeType){

        if(storeType != ItemType.METAL_SHOP && storeType != ItemType.WOOD_SHOP && storeType != ItemType.STONE_SHOP){
            return null;
        }
        ArrayList<Tile> stores = GetStores();
        double nearestDistance = 25, currentDistance;
        Tile nearestStore = null;

        for (Tile tile : stores) {
            currentDistance = UpperLeftDistance(tile);
            if( tile.item.GetItemType().equals(storeType) && currentDistance < nearestDistance) {
                nearestDistance = currentDistance;
                nearestStore = tile;
            }
        }
        return nearestStore;
    }

    public static Tile GetNearestStoreForLowerRightPlayer(String storeType){

        if(storeType != ItemType.METAL_SHOP && storeType != ItemType.WOOD_SHOP && storeType != ItemType.STONE_SHOP){
            return null;
        }
        ArrayList<Tile> stores = GetStores();
        double nearestDistance = 25, currentDistance;
        Tile nearestStore = null;

        for (Tile tile : stores) {
            currentDistance = LowerRightDistance(tile);
            if( tile.item.GetItemType().equals(storeType) && currentDistance < nearestDistance) {
                nearestDistance = currentDistance;
                nearestStore = tile;
            }
        }
        return nearestStore;
    }

    public static Tile GetNearestItem(String tileType){
        double minDist = 25, currentDistance;
        Tile nearestTile = null;
        ArrayList<Tile> stores;
        if(!tileType.equals(WOOD_SHOP) && !tileType.equals(METAL_SHOP) &&!tileType.equals(STONE_SHOP)){
            stores = GetBuildings();
        } else{
            stores = GetStores();
        }
        for (Tile tile : stores) {
            currentDistance = RelativeDistance(tile);
            if( tile.item.GetItemType().equals(tileType) && currentDistance < minDist) {
                minDist = currentDistance;
                nearestTile = tile;
            }
        }
        return nearestTile;

    }

    public static Item GetNearestEnemyBuilding() {
        Player enemy = returnEnemy();
        if (enemy.buildings.length == 0) return null;

        double minDistance = Double.MAX_VALUE;
        int minInd = -1;
        for(int i = 0; i < enemy.buildings.length; i++)
        {
            Tile tile = new Tile();
            tile.item = enemy.buildings[i];
            double currentDistance = RelativeDistance(tile);
            if (currentDistance < minDistance)
                minDistance= currentDistance;
                minInd = i;
        }

        return enemy.buildings[minInd];
    }

    public static double UpperLeftDistance(Tile tile){
        return Math.sqrt((Math.pow((0-tile.item.x), 2))+(Math.pow((0-tile.item.y), 2)));
    }

    public static double LowerRightDistance(Tile tile){
        return Math.sqrt((Math.pow((MapSize.WIDTH_LAST_INDEX -tile.item.x), 2))+(Math.pow((MapSize.HEIGHT_LAST_INDEX-tile.item.y), 2)));
    }

    public static double RelativeDistance(Tile tile){
        int x =  returnMe().x;
        int y =  returnMe().y;
        return Math.sqrt((Math.pow((x-tile.item.x), 2))+(Math.pow((y-tile.item.y), 2)));
    }

    public static double DistanceToEnemy(){
        int x =  returnMe().x;
        int y =  returnMe().y;
        int eX =  returnEnemy().x;
        int eY =  returnEnemy().y;
        return Math.sqrt((Math.pow((x-eX), 2))+(Math.pow((y-eY), 2)));
    }

    public static boolean nextToItem(String itemType) {
       Player player = game.result.player1.id == playerID ? game.result.player1 : game.result.player2;
       Tile[][] tiles = game.result.map.tiles;

       int playerX = player.x;
       int playerY = player.y;

       if (itemType.equals(METAL_SHOP)) {
           if( playerX-1 >= 0 && tiles[playerY][playerX-1].item != null && tiles[playerY][playerX-1].item.GetItemType().equals(METAL_SHOP)) return true;
           if( playerX+1 <= 24 && tiles[playerY][playerX+1].item != null && tiles[playerY][playerX+1].item.GetItemType().equals(METAL_SHOP)) return true;
           if( playerY-1 >= 0 && tiles[playerY-1][playerX].item != null && tiles[playerY-1][playerX].item.GetItemType().equals(METAL_SHOP)) return true;
           if( playerY+1 <= 19 && tiles[playerY+1][playerX].item != null && tiles[playerY+1][playerX].item.GetItemType().equals(METAL_SHOP)) return true;
       } else if (itemType.equals(STONE_SHOP)) {
           if( playerX-1 >= 0 && tiles[playerY][playerX-1].item != null && tiles[playerY][playerX-1].item.GetItemType().equals(STONE_SHOP)) return true;
           if( playerX+1 <= 24 && tiles[playerY][playerX+1].item != null && tiles[playerY][playerX+1].item.GetItemType().equals(STONE_SHOP)) return true;
           if( playerY-1 >= 0 && tiles[playerY-1][playerX].item != null && tiles[playerY-1][playerX].item.GetItemType().equals(STONE_SHOP)) return true;
           if( playerY+1 <= 19 && tiles[playerY+1][playerX].item != null && tiles[playerY+1][playerX].item.GetItemType().equals(STONE_SHOP)) return true;
       } else if (itemType.equals(WOOD_SHOP)) {
           if( playerX-1 >= 0 && tiles[playerY][playerX-1].item != null && tiles[playerY][playerX-1].item.GetItemType().equals(WOOD_SHOP)) return true;
           if( playerX+1 <= 24 && tiles[playerY][playerX+1].item != null && tiles[playerY][playerX+1].item.GetItemType().equals(WOOD_SHOP)) return true;
           if( playerY-1 >= 0 && tiles[playerY-1][playerX].item != null && tiles[playerY-1][playerX].item.GetItemType().equals(WOOD_SHOP)) return true;
           if( playerY+1 <= 19 && tiles[playerY+1][playerX].item != null && tiles[playerY+1][playerX].item.GetItemType().equals(WOOD_SHOP)) return true;
       }else if (itemType.equals(HOUSE)) {
           if( playerX-1 >= 0 && tiles[playerY][playerX-1].item != null && tiles[playerY][playerX-1].item.GetItemType().equals(HOUSE)) return true;
           if( playerX+1 <= 24 && tiles[playerY][playerX+1].item != null && tiles[playerY][playerX+1].item.GetItemType().equals(HOUSE)) return true;
           if( playerY-1 >= 0 && tiles[playerY-1][playerX].item != null && tiles[playerY-1][playerX].item.GetItemType().equals(HOUSE)) return true;
           if( playerY+1 <= 19 && tiles[playerY+1][playerX].item != null && tiles[playerY+1][playerX].item.GetItemType().equals(HOUSE)) return true;
       }else if (itemType.equals(FORTRESS)) {
           if( playerX-1 >= 0 && tiles[playerY][playerX-1].item != null && tiles[playerY][playerX-1].item.GetItemType().equals(FORTRESS)) return true;
           if( playerX+1 <= 24 && tiles[playerY][playerX+1].item != null && tiles[playerY][playerX+1].item.GetItemType().equals(FORTRESS)) return true;
           if( playerY-1 >= 0 && tiles[playerY-1][playerX].item != null && tiles[playerY-1][playerX].item.GetItemType().equals(FORTRESS)) return true;
           if( playerY+1 <= 19 && tiles[playerY+1][playerX].item != null && tiles[playerY+1][playerX].item.GetItemType().equals(FORTRESS)) return true;
       }
       return false;
    }

    public static Direction directionTo(String itemType) {
        Player player = game.result.player1.id == playerID ? game.result.player1 : game.result.player2;
        Tile[][] tiles = game.result.map.tiles;

        int playerX = player.x;
        int playerY = player.y;

        if (itemType.equals(METAL_SHOP)) {
            if( playerX-1 >= 0 && tiles[playerY][playerX-1].item != null && tiles[playerY][playerX-1].item.GetItemType().equals(METAL_SHOP)) return Direction.LEFT;
            if( playerX+1 <= 24 && tiles[playerY][playerX+1].item != null && tiles[playerY][playerX+1].item.GetItemType().equals(METAL_SHOP)) return Direction.RIGHT;
            if( playerY-1 >= 0 && tiles[playerY-1][playerX].item != null && tiles[playerY-1][playerX].item.GetItemType().equals(METAL_SHOP)) return Direction.UP;
            if( playerY+1 <= 19 && tiles[playerY+1][playerX].item != null && tiles[playerY+1][playerX].item.GetItemType().equals(METAL_SHOP)) return Direction.DOWN;
        } else if (itemType.equals(STONE_SHOP)) {
            if( playerX-1 >= 0 && tiles[playerY][playerX-1].item != null && tiles[playerY][playerX-1].item.GetItemType().equals(STONE_SHOP)) return Direction.LEFT;
            if( playerX+1 <= 24 && tiles[playerY][playerX+1].item != null && tiles[playerY][playerX+1].item.GetItemType().equals(STONE_SHOP)) return Direction.RIGHT;
            if( playerY-1 >= 0 && tiles[playerY-1][playerX].item != null && tiles[playerY-1][playerX].item.GetItemType().equals(STONE_SHOP)) return Direction.UP;
            if( playerY+1 <= 19 && tiles[playerY+1][playerX].item != null && tiles[playerY+1][playerX].item.GetItemType().equals(STONE_SHOP)) return Direction.DOWN;
        } else if (itemType.equals(WOOD_SHOP)) {
            if( playerX-1 >= 0 && tiles[playerY][playerX-1].item != null && tiles[playerY][playerX-1].item.GetItemType().equals(WOOD_SHOP)) return Direction.LEFT;
            if( playerX+1 <= 24 && tiles[playerY][playerX+1].item != null && tiles[playerY][playerX+1].item.GetItemType().equals(WOOD_SHOP)) return Direction.RIGHT;
            if( playerY-1 >= 0 && tiles[playerY-1][playerX].item != null && tiles[playerY-1][playerX].item.GetItemType().equals(WOOD_SHOP)) return Direction.UP;
            if( playerY+1 <= 19 && tiles[playerY+1][playerX].item != null && tiles[playerY+1][playerX].item.GetItemType().equals(WOOD_SHOP)) return Direction.DOWN;
        }else if (itemType.equals(HOUSE)) {
            if( playerX-1 >= 0 && tiles[playerY][playerX-1].item != null && tiles[playerY][playerX-1].item.GetItemType().equals(HOUSE)) return Direction.LEFT;
            if( playerX+1 <= 24 && tiles[playerY][playerX+1].item != null && tiles[playerY][playerX+1].item.GetItemType().equals(HOUSE)) return Direction.RIGHT;
            if( playerY-1 >= 0 && tiles[playerY-1][playerX].item != null && tiles[playerY-1][playerX].item.GetItemType().equals(HOUSE)) return Direction.UP;
            if( playerY+1 <= 19 && tiles[playerY+1][playerX].item != null && tiles[playerY+1][playerX].item.GetItemType().equals(HOUSE)) return Direction.DOWN;
        }else if (itemType.equals(FORTRESS)) {
            if( playerX-1 >= 0 && tiles[playerY][playerX-1].item != null && tiles[playerY][playerX-1].item.GetItemType().equals(FORTRESS)) return Direction.LEFT;
            if( playerX+1 <= 24 && tiles[playerY][playerX+1].item != null && tiles[playerY][playerX+1].item.GetItemType().equals(FORTRESS)) return Direction.RIGHT;
            if( playerY-1 >= 0 && tiles[playerY-1][playerX].item != null && tiles[playerY-1][playerX].item.GetItemType().equals(FORTRESS)) return Direction.UP;
            if( playerY+1 <= 19 && tiles[playerY+1][playerX].item != null && tiles[playerY+1][playerX].item.GetItemType().equals(FORTRESS)) return Direction.DOWN;
        }else {
            System.out.println("Greska, nije nigde oko tebe!");
        }
        return Direction.DOWN;
    }

    public static Direction GetFreeTileDirection(){
        int x =  returnMe().x;
        int y =  returnMe().y;

        if(x + 1 < 25 && PathHelper.freeField(game.result.map.tiles, y, x+1)) return Direction.RIGHT;
        else if(x - 1 >= 0 && PathHelper.freeField(game.result.map.tiles, y, x-1)) return Direction.LEFT;
        else if(y + 1 < 20 && PathHelper.freeField(game.result.map.tiles, y+1, x)) return Direction.DOWN;
        else if(y - 1 >= 0 && PathHelper.freeField(game.result.map.tiles, y-1, x)) return Direction.UP;
        return null;
    }
    public static Player returnMe(){
        return game.result.player1.id == playerID ? game.result.player1 : game.result.player2;
    }

    public static Player returnEnemy(){
        return game.result.player1.id != playerID ? game.result.player1 : game.result.player2;
    }

    public static boolean EnemyReachableWithSword() {
        Player me = returnMe();
        Player enemy = returnEnemy();

        if (me.x == enemy.x && ((me.y == enemy.y - 1) || (me.y == enemy.y + 1))) {
            return true;
        }

        if (me.y == enemy.y && ((me.x == enemy.x - 1) || (me.x == enemy.x + 1))) {
            return true;
        }

        return false;
    }

    public static boolean EnemyBuildingReachableBySword(Item attackingBuilding) {
        Player me = returnMe();

        if (me.x == attackingBuilding.x && ((me.y == attackingBuilding.y - 1) || (me.y == attackingBuilding.y + 1))) {
            return true;
        }

        if (me.y == attackingBuilding.y && ((me.x == attackingBuilding.x - 1) || (me.x == attackingBuilding.x + 1))) {
            return true;
        }

        return false;
    }

    public static long getEnemyDistance() {

        Player player = game.result.player1.id == playerID ? game.result.player1 : game.result.player2;
        Player playerOther = game.result.player1.id != playerID ? game.result.player1 : game.result.player2;

        int player1_x = player.x;
        int player1_y = player.y;

        int player2_x = playerOther.x;
        int player2_y = playerOther.y;

        return Math.round(Math.sqrt((Math.pow((player1_x - player2_x), 2)) + (Math.pow((player1_y - player2_y), 2))));

    }
    public static int GetNumberOfWood(){
        return returnMe().resources.WOOD;
    }


    public static int GetNumberOfStone(){
        return returnMe().resources.STONE;
    }
    public static int GetNumberOfMetal(){
        return returnMe().resources.METAL;
    }

    public static boolean EnemyReachableWithArrows() {
        Player me = returnMe();
        Player enemy = returnEnemy();

        return false;
    }


    public static int getEnemyDistanceInSteps() {
        Player me = returnMe();
        Player enemy = returnEnemy();

        return Math.abs(me.x - enemy.x) + Math.abs(me.y - enemy.y);
    }

    public static int getEnemyAttackingPower() {
        Player enemy = returnEnemy();
        int power = 0;

        power += getWeaponPower(enemy.weapon1);
        power += getWeaponPower(enemy.weapon2);
        return power;
    }

    public static int getMyAttackingPower() {
        Player me = returnMe();
        int power = 0;

        power += getWeaponPower(me.weapon1);
        power += getWeaponPower(me.weapon2);
        return power;
    }


    public static int getWeaponPower(Weapon weapon) {
        if (weapon != null && WeaponType.SWORD == weapon.type) {
            SwordWeapon sword = (SwordWeapon)weapon;
            return sword.swings * 10;
        }

        if (weapon != null && WeaponType.ARROW == weapon.type) {
            ArrowWeapon arrow = (ArrowWeapon)weapon;
            return arrow.num_of_arrows * 5;
        }

        return 0;
    }

    public static boolean assessEnemyDanger() {
        double distance = getEnemyDistance();

        if (distance <= 4) {
            if (getEnemyAttackingPower() >= 50) {
                return true;
            }
        }

        return false;
    }

    public static boolean doesNearestShopHasResources(String tileType, int numOfResources) {
        double minDist = 25, currentDistance;
        Tile nearestTile = null;
        ArrayList<Tile> stores;
        if(!tileType.equals(WOOD_SHOP) && !tileType.equals(METAL_SHOP) &&!tileType.equals(STONE_SHOP)){
            System.out.println("GRESKA Zahtevan NON-STORE objekat");
            //System.exit(1);
            return false;
        }
        stores = GetStores();
        for (Tile tile : stores) {
            currentDistance = RelativeDistance(tile);
            if( tile.item.GetItemType().equals(tileType) && currentDistance < minDist) {
                minDist = currentDistance;
                nearestTile = tile;
            }
        }
        if(tileType.equals(WOOD_SHOP)){
            WoodShop ws = (WoodShop)nearestTile.item;
            if(ws.value == numOfResources) return true;
            else return false;
        }else if(tileType.equals(METAL_SHOP)){
            MetalShop ms = (MetalShop)nearestTile.item;
            if(ms.value == numOfResources) return true;
            else return false;
        }else if(tileType.equals(STONE_SHOP)) {
            StoneShop sh = (StoneShop)nearestTile.item;
            if(sh.value == numOfResources) return true;
            else return false;
        }
        System.out.println("Neka greska velika: 420: doesNearestShopHasResources");
        return false;
    }


}

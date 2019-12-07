package bot;

import constants.ItemType;
import constants.MapSize;
import models.Game;
import models.Tile;
import models.items.Item;

import java.util.ArrayList;

public class Helpers {

    public static Game game;

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
            if( tile.item.itemType.equals(storeType) && currentDistance < nearestDistance) {
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
            if( tile.item.itemType.equals(storeType) && currentDistance < nearestDistance) {
                nearestDistance = currentDistance;
                nearestStore = tile;
            }
        }
        return nearestStore;
    }

    public static double UpperLeftDistance(Tile tile){
        return Math.sqrt((Math.pow((0-tile.item.x), 2))+(Math.pow((0-tile.item.x), 2)));
    }

    public static double LowerRightDistance(Tile tile){
        return Math.sqrt((Math.pow((MapSize.HEIGHT_LAST_INDEX -tile.item.x), 2))+(Math.pow((MapSize.WIDTH_LAST_INDEX-tile.item.x), 2)));
    }
}

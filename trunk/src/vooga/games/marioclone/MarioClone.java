package vooga.games.marioclone;

import java.io.IOException;
import java.util.List;

import vooga.engine.resource.ResourceHandler;
import vooga.games.marioclone.tiles.Tile;

public class MarioClone {
	public static void main(String[] args) throws IOException {
		ResourceHandler.loadFile("resourcelist.txt");
		System.out.println("loaded resource list");
		TileMap m = new TileMap("testmap.txt");
		System.out.println("loaded tiles");
		List<Tile> tiles = m.getTiles();
	}
}

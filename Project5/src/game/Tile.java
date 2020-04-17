package game;

public class Tile {
	public static Tile tiles[] = new Tile[255];
	public static byte not = 0;

	public static final Tile BG = new Tile("bg.jpg");
	public static final Tile FLOOR = new Tile("testImage.jpg").setSolid();

	private byte id;
	private boolean solid;
	private String texture;

	public Tile(String texture) {
		this.id = not;
		not++;
		this.texture = texture;
		this.solid = false;
		if (tiles[id] != null)
			throw new IllegalStateException("Tiles at [" + id + "] is already being used!");
		tiles[id] = this;
	}

	public Tile setSolid() {
		this.solid = true;
		return this;
	}

	public boolean isSolid() {
		return solid;
	}

	public byte getId() {
		return id;
	}

	public String getTexture() {
		return texture;
	}
}
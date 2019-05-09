public class Multiplier extends GameElement {

    public Multiplier(int x, int y, int width, int height, Game game) {
        super(x, y, width, height, game);
    }

    public void addToMultiplier(){

    }

    @Override
    public void selfDestroy() {
        this.game.destroy(this);
    }
}

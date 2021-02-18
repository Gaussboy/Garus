import gaussboy.sw.modes.GameMode;

public class Test {

    @org.junit.Test
    public void testToFailMove() {
        try {
            GameMode gamemode = new GameMode();
            gamemode.start();
            gamemode.makeMove("a2 b8");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
    @org.junit.Test
    public void testToTrueMove() {
        GameMode gamemode = new GameMode();
        gamemode.makeMove("a1 b2");
        System.out.println("Nice");
    }
}

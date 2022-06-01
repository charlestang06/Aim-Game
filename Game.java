/**
 * @author Charles Tang Game.java provides the interface for all versions of
 *         games
 */
public interface Game {
	public void starterScreen();

	public void endScreen();

	public void createWindow();

	public void play();

	default public void wait(int tenth_of_seconds) {
		try {
			for (int i = 0; i < tenth_of_seconds; i++) {
				Thread.sleep(100);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

package Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.github.nlydroid.coveapp.CoveApplication;

public class DoorScreen extends RobotScreen {
  private CoveApplication coveApplication;
  private Music bgMusic;

  public DoorScreen(CoveApplication coveApplication) {
    super(coveApplication, "DoorRoom/DoorRoom.tmx");
    this.coveApplication = coveApplication;

    bgMusic = Gdx.audio.newMusic(Gdx.files.internal("WanderingHearts.mp3"));
    bgMusic.setLooping(true);
    bgMusic.setVolume(0.3f);
  }

  public void handleInput(){
    super.handleInput();
    Array<RectangleMapObject> rects = getMap().getLayers().get(7).getObjects().getByType(RectangleMapObject.class);
    for (int i = 0; i < rects.size; i++){
      Rectangle door = rects.get(i).getRectangle();
      if ((getRobot().getBound().overlaps(door))){
        if (!getKnocking().isPlaying()){
          getKnocking().play();
        }
        if ((Gdx.input.isKeyPressed(Input.Keys.ENTER) ||
            (Gdx.input.isKeyPressed(Input.Keys.SPACE)))){
          getOut().play(0.5f);
          bgMusic.stop();
          coveApplication.setScreen(new QuestionScreen(coveApplication, i));
          dispose();
        }
      }
    }
  }

  @Override
  public void show() {
    super.show();
    bgMusic.play();
  }

  @Override
  public void render(float delta) {
    super.render(delta);
  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {
    bgMusic.dispose();
  }
}

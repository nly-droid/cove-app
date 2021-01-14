package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.github.nlydroid.coveapp.CoveApplication;

public class AnswerScreen extends RobotScreen {
  private CoveApplication coveApplication;
  private Array<String> answers;
  private int ID;
  private Label text;
  private Music bgMusic;

  public AnswerScreen(CoveApplication coveApplication, int ID) {
    super(coveApplication, "QuestionRoom/QuestionRoom.tmx");
    this.coveApplication = coveApplication;
    this.ID = ID;

    // Credits: Kim Lightyear
    bgMusic = Gdx.audio.newMusic(Gdx.files.internal("illusion.mp3"));
    bgMusic.setLooping(true);
    bgMusic.play();
    bgMusic.setVolume(0.3f);
  }

  public void handleInput(){
    super.handleInput();

    Array<RectangleMapObject> rects = getMap().getLayers().get(6).getObjects().getByType(RectangleMapObject.class);
    Rectangle questionDoor = rects.get(1).getRectangle();
    Rectangle mainDoor = rects.get(0).getRectangle();

    if ((getRobot().getBound().overlaps(questionDoor))) {
      if (!getKnocking().isPlaying()) {
        getKnocking().play();
      }
      if ((Gdx.input.isKeyPressed(Input.Keys.ENTER) ||
          (Gdx.input.isKeyPressed(Input.Keys.SPACE)))) {
        getOut().play(0.5f);
        bgMusic.stop();
        coveApplication.setScreen(new QuestionScreen(coveApplication, ID));
        dispose();
      }
    }

    if ((getRobot().getBound().overlaps(mainDoor))) {
      if (!getKnocking().isPlaying()) {
        getKnocking().play();
      }
      if ((Gdx.input.isKeyPressed(Input.Keys.ENTER) ||
          (Gdx.input.isKeyPressed(Input.Keys.SPACE)))) {
        getOut().play(0.5f);
        bgMusic.stop();
        coveApplication.setScreen(new DoorScreen(coveApplication));
        dispose();
      }
    }
  }

  @Override
  public void show() {
    super.show();
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

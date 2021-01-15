package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.github.nlydroid.coveapp.CoveApplication;

public class AnswerScreen extends RobotScreen {
  private CoveApplication coveApplication;
  private int ID;
  private Label answer;
  private Music bgMusic;

  public AnswerScreen(CoveApplication coveApplication, int ID) {
    super(coveApplication, "AnswerRoom/AnswerRoom.tmx");
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

    Array<RectangleMapObject> rects = getMap().getLayers().get(7).getObjects().getByType(RectangleMapObject.class);
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
        if (ID >= CoveApplication.numQuestion){
          coveApplication.setScreen(new AnswerScreen(coveApplication, ID
              - CoveApplication.numQuestion));
        }
        else {
          coveApplication.setScreen(new QuestionScreen(coveApplication, ID));
        }
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
        if (ID >= CoveApplication.numQuestion){
          coveApplication.setScreen(new DoorScreen(coveApplication));
        }
        else {
          coveApplication.setScreen(new AnswerScreen(coveApplication, ID
              + CoveApplication.numQuestion));
        }
        dispose();
      }
    }
  }

  public Label.LabelStyle generateAnswerLabelStyle(){
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto/Roboto-Light.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    parameter.size = 25;
    parameter.color = Color.BLACK;
    BitmapFont font = generator.generateFont(parameter);
    generator.dispose();
    Label.LabelStyle style = new Label.LabelStyle();
    style.font = font;
    return style;
  }

  @Override
  public void show() {
    super.show();
    Table table = new Table();
    table.setFillParent(true);
    table.top();

    Label question = new Label(coveApplication.answers.get(ID), generateAnswerLabelStyle());
    question.setWrap(true);
    table.add(question).padTop(50).width(768).center();
    table.row();
    getStage().addActor(table);
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

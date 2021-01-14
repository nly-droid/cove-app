package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.nlydroid.coveapp.CoveApplication;

public class MainMenuScreen implements Screen {
  private CoveApplication coveApplication;
  private Texture bg;
  private OrthographicCamera camera;
  private Viewport viewport;
  private Stage stage;
  private Texture buttonTextureUp;
  private Texture buttonTextureDown;
  private Music bgMusic;
  private Sound click;
  private Texture soundPlay;
  private Texture soundStop;

  public MainMenuScreen(CoveApplication app) {
    this.coveApplication = app;
    // Credit: Ciera Elizabeth Hoover [SugarMoonWitch]/ http://sugarmoonwitch.com/
    bg = new Texture(Gdx.files.internal("ocean.png"));
    camera = new OrthographicCamera();
    viewport = new FitViewport(CoveApplication.WORLD_WIDTH, CoveApplication.WORLD_HEIGHT, camera);
    viewport.apply();
    stage = new Stage(viewport, coveApplication.batch);
    buttonTextureUp = new Texture(Gdx.files.internal("up.png"));
    buttonTextureDown = new Texture(Gdx.files.internal("down.png"));

    bgMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.wav"));
    bgMusic.setLooping(true);
    bgMusic.play();
    bgMusic.setVolume(1);
    click = Gdx.audio.newSound(Gdx.files.internal("Sound/click1.ogg"));
    // Credit: Sound On Off Images: Aaron Burke (itmatters.mobi)
    soundPlay = new Texture(Gdx.files.internal("soundOnWhite.png"));
    soundStop = new Texture(Gdx.files.internal("soundOffWhite.png"));
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(stage);
    Table table = new Table();
    table.setFillParent(true);
    table.top();

    Label title = new Label("NHI LY'S COVE APPLICATION", generateTitleLabelStyle());
    title.setHeight(viewport.getWorldHeight());

    TextButton startButton = generateTextButton("START");
    startButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y){
        click.play(0.2f);
        bgMusic.stop();
        coveApplication.setScreen(new DoorScreen(coveApplication));
        dispose();
      }
    });
    TextButton exitButton = generateTextButton("EXIT");
    exitButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y){
        click.play(0.2f);
        Gdx.app.exit();
      }
    });
    final ImageButton soundButton = generateSoundButton();
    soundButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        bgMusic.play();
        if (soundButton.isChecked()){
          bgMusic.pause();
        }
      }
    });

    table.add(soundButton).align(Align.left).padTop(20);
    table.row();
    table.add(title).pad(60);
    table.row();
    table.add(startButton).pad(30).width(150);
    table.row();
    table.add(exitButton).pad(30).width(150);

    stage.addActor(table);
  }

  private Label.LabelStyle generateTitleLabelStyle(){
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Piedra/Piedra-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    parameter.size = 60;
    parameter.color = Color.WHITE;
    BitmapFont font = generator.generateFont(parameter);
    generator.dispose();
    Label.LabelStyle style = new Label.LabelStyle();
    style.font = font;
    return style;
  }

  private ImageButton generateSoundButton(){
    Drawable play = new TextureRegionDrawable(new TextureRegion(soundPlay));
    Drawable stop = new TextureRegionDrawable(new TextureRegion(soundStop));
    ImageButton sound = new ImageButton(play, play, stop);
    return sound;
  }

  private TextButton generateTextButton(String text){
    TextButton button = new TextButton(text, generateButtonSyle());
    button.getLabelCell().pad(10);
    return button;
  }

  private TextButton.TextButtonStyle generateButtonSyle(){
    Drawable up = new TextureRegionDrawable(new TextureRegion(buttonTextureUp));
    Drawable down = new TextureRegionDrawable(new TextureRegion(buttonTextureDown));
    TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
    style.up = up;
    style.down = down;

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto/Roboto-Medium.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    parameter.size = 30;
    parameter.color = Color.BLACK;
    BitmapFont font = generator.generateFont(parameter);
    generator.dispose();

    style.font = font;
    return style;
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0,0,0,1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.getBatch().begin();
    stage.getBatch().draw(bg, 0, 0,
        CoveApplication.WORLD_WIDTH,CoveApplication.WORLD_WIDTH);
    stage.getBatch().end();
    stage.act();
    stage.draw();
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
    bg.dispose();
    stage.dispose();
    buttonTextureUp.dispose();
    buttonTextureDown.dispose();
    bgMusic.dispose();
    click.dispose();
    soundPlay.dispose();
    soundStop.dispose();
  }
}

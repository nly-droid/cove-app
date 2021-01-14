package Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.nlydroid.coveapp.CoveApplication;
import com.github.nlydroid.coveapp.Robot;


public class DoorScreen implements Screen {
  private CoveApplication coveApplication;
  private TmxMapLoader mapLoader;
  private TiledMap map;
  private OrthogonalTiledMapRenderer renderer;
  private OrthographicCamera camera;
  private Viewport viewport;
  private Stage stage;
  private Robot robot;
  private Music bgMusic;
  private Vector3 mapDimension;
  private Texture soundPlay;
  private Texture soundStop;

  public DoorScreen(CoveApplication coveApplication) {
    mapLoader = new TmxMapLoader();
    map = mapLoader.load("DoorRoom/DoorRoom.tmx");
    renderer = new OrthogonalTiledMapRenderer(map);
    MapProperties prop = map.getProperties();
    mapDimension = new Vector3(prop.get("width", Integer.class) * prop.get("tilewidth", Integer.class),
        prop.get("height", Integer.class) * prop.get("tileheight", Integer.class), 0);

    this.coveApplication = coveApplication;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, CoveApplication.WORLD_WIDTH, CoveApplication.WORLD_HEIGHT);
    viewport = new FitViewport(CoveApplication.WORLD_WIDTH, CoveApplication.WORLD_HEIGHT, camera);
    stage = new Stage(viewport, coveApplication.batch);

    renderer.setView(camera);

    robot = new Robot(0, 210);

    bgMusic = Gdx.audio.newMusic(Gdx.files.internal("WanderingHearts.mp3"));
    bgMusic.setLooping(true);
    bgMusic.play();
    bgMusic.setVolume(0.3f);
  }

  public void handleInput(){
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      robot.moveRight(Gdx.graphics.getDeltaTime());
      if (!(robot.getDirection() == Robot.RIGHT)){
        robot.switchDirection(Robot.RIGHT);
      }
      if (robot.getX() >= (mapDimension.x - (robot.getWidth() * robot.getScaleX()))){
        robot.setX((mapDimension.x - (robot.getWidth() * robot.getScaleX())));
      }
    }
    else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
      robot.moveLeft(Gdx.graphics.getDeltaTime());
      if (robot.getX() < 0){
        robot.setX(0);
      }
      if (!(robot.getDirection() == Robot.LEFT)){
        robot.switchDirection(Robot.LEFT);
      }
    }
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(stage);
    stage.addActor(robot);
  }

  @Override
  public void render(float delta) {
    camera.update();

    Gdx.gl.glClearColor(0,1,0,1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    handleInput();
    viewport.apply();
    camera.position.set(robot.getX() + camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    if (camera.position.x > (mapDimension.x - (camera.viewportWidth / 2f))){
      camera.position.x = (mapDimension.x - (camera.viewportWidth / 2f));
    }
    camera.update();


    renderer.render();
    renderer.setView(camera);

    stage.act(Gdx.graphics.getDeltaTime());
    stage.getBatch().setProjectionMatrix(camera.combined);
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
    map.dispose();
    renderer.dispose();
    stage.dispose();
    robot.dispose();
    bgMusic.dispose();
    soundPlay.dispose();
    soundStop.dispose();
  }
}

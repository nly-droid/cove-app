package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.nlydroid.coveapp.CoveApplication;

public class QuestionScreen implements Screen {
  private CoveApplication coveApplication;
  private Array<String> questions;
  private Viewport viewport;
  private Stage stage;
  private OrthographicCamera camera;

  public QuestionScreen(CoveApplication coveApplication, int ID) {
    this.coveApplication = coveApplication;

    camera = new OrthographicCamera();
    viewport = new FitViewport(CoveApplication.WORLD_WIDTH, CoveApplication.WORLD_HEIGHT, camera);
    viewport.apply();
    stage = new Stage(viewport, coveApplication.batch);
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0,0,1,1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

  }
}

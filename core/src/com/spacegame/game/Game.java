package com.spacegame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.spacegame.game.controller.GameController;
import com.spacegame.game.model.GameModel;
import com.spacegame.game.view.GameView;

/**
 * Класс игры
 * @author Nikita
 */
public class Game extends ApplicationAdapter {
    
    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;
    public static Rectangle AREA = new Rectangle(-75, -75, WIDTH + 150, HEIGHT + 150);
    
    private GameModel model; // игровая модель
    private GameController controller; // контроллер
    private GameView view; // отображение

    @Override
    public void create () {
        
        model = new GameModel();
        controller = new GameController(model);
        view = new GameView(controller);
        
        view.spritesInitialization();
    }

    @Override
    public void render () {
        
        controller.update(Gdx.graphics.getDeltaTime());
        view.render();
    }

    @Override
    public void dispose () {
        Gdx.input.setInputProcessor(null);
        view.dispose();
    }
}

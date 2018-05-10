/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static com.spacegame.game.Game.HEIGHT;
import static com.spacegame.game.Game.WIDTH;
import com.spacegame.game.GameState;
import com.spacegame.game.controller.GameController;
import com.spacegame.game.model.GameObject;
import com.spacegame.game.model.TypeObject;
import java.util.ArrayList;

/**
 *
 * @author Никита
 */
public class GameView {
    
    private SpriteBatch batch; // общий спрайт на все окно
    private OrthographicCamera camera; // камера
    private BitmapFont infoFont; // информационная панель 
    private BitmapFont gameFont; // игровая панель(только во время игры)
    private Texture background; // фона
    private Texture player; // игрок
    private Texture missilePlayer; // ракета игрока
    private Texture missileEnemy; // ракета врага
    private Texture enemyShipOneMissile; // враг с одной ракетой
    private Texture enemyShipMoreMissile; // враг с 3мя ракетами
    private Texture enemyShipHealth; // враг с большим ХП
    private Texture healthBonus; // бонус в виде доп жизни
    private Texture weaponBonus; // бонус в виде 3х оружий
    
    
    
    private GameController controller; // контроллер
    
    public GameView(GameController controller) {
        this.controller = controller;
    }
    
    /**
     * Иициализация всех спрайтов
     */
    public void spritesInitialization() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("background.png"));
        player = new Texture(Gdx.files.internal("ship.png"));
        missilePlayer = new Texture(Gdx.files.internal("missilePlayer.png"));
        missileEnemy = new Texture(Gdx.files.internal("missileEnemy.png"));
        enemyShipOneMissile = new Texture(Gdx.files.internal("oneMissileEnemy.png"));
        enemyShipMoreMissile = new Texture(Gdx.files.internal("moreMissileEnemy.png"));
        enemyShipHealth = new Texture(Gdx.files.internal("healthEnemy.png"));
        healthBonus = new Texture(Gdx.files.internal("healthBonus.png"));
        weaponBonus = new Texture(Gdx.files.internal("weaponBonus.png"));
        
        infoFont = new BitmapFont();
        infoFont.setColor(Color.WHITE);
        infoFont.getData().setScale(2, 2);
        
        gameFont = new BitmapFont();
        gameFont.setColor(Color.WHITE);
        
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.translate(WIDTH / 2, HEIGHT / 2);
        camera.update();
    }
    
    /**
     * Рендеринг всех игровых объектов на поле
     */
    public void render() {
        
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        
        GameState.State state = GameState.getInstance().getCurrentState();
        if (state == GameState.State.PlayGame || state == GameState.State.PauseGame) {
            ArrayList<GameObject> objects = controller.getGameObjects();
            gameFont.draw(batch, "Score:" + controller.getScore() + ". Health:" + controller.getHealth() + ". Downed ships:" + controller.countDownedShips(), 0, 539);
            for (GameObject object : objects) {
                if (object.getType() == TypeObject.PLAYER) {
                    batch.draw(player, object.getRectangle().x, object.getRectangle().y);
                } else if (object.getType() == TypeObject.MISSILEPLAYER) {
                    batch.draw(missilePlayer, object.getRectangle().x, object.getRectangle().y);
                } else if(object.getType() == TypeObject.MISSILEENEMY) {
                    batch.draw(missileEnemy, object.getRectangle().x, object.getRectangle().y);
                } else if (object.getType() == TypeObject.ENEMYSHIPONEMISSILE) {
                    batch.draw(enemyShipOneMissile, object.getRectangle().x, object.getRectangle().y);
                } else if (object.getType() == TypeObject.ENEMYSHIPMOREMISSILE) {
                    batch.draw(enemyShipMoreMissile, object.getRectangle().x, object.getRectangle().y);
                } else if (object.getType() == TypeObject.ENEMYHEALTH) {
                    batch.draw(enemyShipHealth, object.getRectangle().x, object.getRectangle().y);
                } else if (object.getType() == TypeObject.HEALTHBONUS) {
                    batch.draw(healthBonus, object.getRectangle().x, object.getRectangle().y);
                } else if (object.getType() == TypeObject.WEAPONBONUS) {
                    batch.draw(weaponBonus, object.getRectangle().x, object.getRectangle().y);
                }
            }
            
            if (state == GameState.State.PauseGame) {
                infoFont.draw(batch, "PAUSE", 380, (float) 340);
            }
            
        } else if(state == GameState.State.GameOver) {
            infoFont.draw(batch, "GAME OVER", 380, (float) 340);
            infoFont.draw(batch, "YOU SCORE:" + controller.getScore(), 360, (float) 300);
            infoFont.draw(batch, "PRESS M TO MENU", 330, (float) 220);
        } else if (state == GameState.State.Menu) {
            infoFont.draw(batch, "PRESS P TO PLAY", 330, (float) 340);
            infoFont.draw(batch, "PRESS B TO BOT", 330, (float) 300);
        }
        
        batch.end();
    }
    
    public void dispose() {
        batch.dispose();
        background.dispose();
        infoFont.dispose();
        gameFont.dispose();
        background.dispose();
        player.dispose();
        missilePlayer.dispose();
        missileEnemy.dispose();
        enemyShipOneMissile.dispose();
        enemyShipMoreMissile.dispose();
        enemyShipHealth.dispose();
        healthBonus.dispose();
        weaponBonus.dispose();
    }
}

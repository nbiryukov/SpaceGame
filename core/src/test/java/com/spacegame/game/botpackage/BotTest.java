/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.spacegame.game.botpackage;

import com.badlogic.gdx.math.Rectangle;
import com.spacegame.game.Game;
import com.spacegame.game.botpackage.Bot;
import com.spacegame.game.model.EnemyShip;
import com.spacegame.game.model.GameAPI;
import com.spacegame.game.model.GameObject;
import com.spacegame.game.model.TypeObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikita
 */
public class BotTest {
    
    private final int WIDTH = 102;
    private final int HEIGHT = 34;

    @Test
    public void testGetDirectionDOWN() {
        System.out.println("getDirection down");
        Rectangle recBot = new Rectangle(0, Game.HEIGHT / 2, WIDTH, HEIGHT);
        GameObject nearestObject = new EnemyShip(TypeObject.ENEMYSHIPONEMISSILE, 250);
        nearestObject.setRectangle(new Rectangle(Game.WIDTH, 200, 42, 40));

        Bot instance = new Bot();
        Bot.Direction expResult = Bot.Direction.DOWN;
        Bot.Direction result = instance.getDirection(recBot, nearestObject);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDirectionUP() {
        System.out.println("getDirection up");
        Rectangle recBot = new Rectangle(0, Game.HEIGHT / 2, WIDTH, HEIGHT);
        GameObject nearestObject = new EnemyShip(TypeObject.ENEMYSHIPONEMISSILE, 250);
        nearestObject.setRectangle(new Rectangle(Game.WIDTH, 400, 42, 40));

        Bot instance = new Bot();
        Bot.Direction expResult = Bot.Direction.UP;
        Bot.Direction result = instance.getDirection(recBot, nearestObject);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDirectionNONE() {
        System.out.println("getDirection none");
        Rectangle recBot = new Rectangle(0, Game.HEIGHT / 2, WIDTH, HEIGHT);
        GameObject nearestObject = new EnemyShip(TypeObject.ENEMYSHIPONEMISSILE, 250);
        nearestObject.setRectangle(new Rectangle(Game.WIDTH, Game.HEIGHT / 2 - 1, 42, 40));

        Bot instance = new Bot();
        Bot.Direction expResult = Bot.Direction.NONE;
        Bot.Direction result = instance.getDirection(recBot, nearestObject);
        assertEquals(expResult, result);
    }

}

package pl.polsl.bol.krzysztof.lab2.test;

import java.awt.Point;
import javafx.scene.input.KeyCode;
import static org.hamcrest.core.Is.is;
import org.junit.Before;
import org.junit.Test;
import pl.polsl.bol.krzysztof.lab2.models.Game;
import static org.junit.Assert.*;
import pl.polsl.bol.krzysztof.lab2.models.Directions;

/**
 * Test case of Game methods.
 *
 * @author Krzysztof Ból
 * @version 1.0
 */
public class GameTest {

    /**
     * Game object used in tests.
     */
    private Game game;

    /**
     * Method that is run before tests. Creates new instance of Game.
     */
    @Before
    public void setUp() {
        game = new Game(100, 100);
    }

    /**
     * Test of update method, of class Game, when collision with borders
     * occured.
     */
    @Test
    public void testUpdateWhenCollisionWithBorders() {
        //given
        game.startGame();
        game.getSnake().getSnakeBody().get(0).setPosition(new Point(100, 100));
        //when
        game.update();
        //then
        assertTrue("IsGameOver should be true.", game.getIsGameOver());
    }

    /**
     * Test of update method, of class Game, when snake's head is out of canvas.
     */
    @Test
    public void testUpdateWhenOutOfCanvas() {
        //given
        game.startGame();
        game.getSnake().getSnakeBody().get(0).setPosition(new Point(1000, 1000));
        //when
        game.update();
        //then
        assertTrue("IsGameOver should be true.", game.getIsGameOver());
    }

    /**
     * Test of update method, of class Game, when collision with borders didn't
     * occure.
     */
    @Test
    public void testUpdateWhenNoCollisionWithBorders() {
        //given
        game.startGame();
        //when
        game.update();
        //then
        assertFalse("IsGameOver should be false.", game.getIsGameOver());
    }

    /**
     * Test of update method, of class Game, when collision with food occured.
     */
    @Test
    public void testUpdateWhenCollisionWithFood() {
        //given
        game.startGame();
        Point newPosition = new Point(50, 50);
        game.getFood().setPosition(newPosition);
        game.getSnake().getSnakeBody().get(0).setPosition(newPosition);
        //when
        game.update();
        //then
        assertThat("Snake lenght should be 1.", game.getSnake().getSnakeBody().size(), is(2));
        assertFalse("IsGameOver should be false.", game.getIsGameOver());
        assertNotSame("Food position should be different than newPostion.", newPosition, game.getFood().getPosition());
    }

    /**
     * Test of update method, of class Game, when collision with food didn't
     * occure.
     */
    @Test
    public void testUpdateWhenNoCollisionWithFood() {
        //given
        game.startGame();
        Point newPosition = new Point(50, 50);
        game.getFood().setPosition(newPosition);
        game.getSnake().getSnakeBody().get(0).setPosition(new Point(70, 70));
        //when
        game.update();
        //then
        assertThat("Snake length should be 1.", game.getSnake().getSnakeBody().size(), is(1));
        assertSame("Food position shouldn't change.", newPosition, game.getFood().getPosition());
        assertFalse("IsGameOver should be false.", game.getIsGameOver());
    }

    /**
     * Test of update method, of class Game, when collision with snake's body
     * occured.
     */
    @Test
    public void testUpdateCollisionWithSnakeBody() {
        //given
        game = new Game(1000, 1000);
        game.startGame();
        game.getSnake().getSnakeBody().get(0).setPosition(new Point(50, 50));
        game.getSnake().addNewBodyPart();
        game.getSnake().addNewBodyPart();
        game.getSnake().addNewBodyPart();
        game.getSnake().addNewBodyPart();
        game.getSnake().setDirection(Directions.RIGHT);
        game.getSnake().move();
        game.getSnake().setDirection(Directions.DOWN);
        game.getSnake().move();
        game.getSnake().setDirection(Directions.LEFT);
        game.getSnake().move();
        game.getSnake().setDirection(Directions.UP);
        game.getSnake().move();
        //when
        game.update();
        //then
        assertTrue("IsGameOver should be true.", game.getIsGameOver());
    }

    /**
     * Test of processDirectionChange method, of class Game, when the direction
     * is up.
     */
    @Test
    public void testProcessUpDirectionChange() {
        //given
        game.startGame();
        //when
        game.processDirectionChange(KeyCode.UP);
        //then
        assertThat("Snake direction should be Directions.Up.",
                game.getSnake().getDirection(), is(Directions.UP));
    }

    /**
     * Test of processDirectionChange method, of class Game, when the direction
     * is down.
     */
    @Test
    public void testProcessDownDirectionChange() {
        //given
        game.startGame();
        //when
        game.processDirectionChange(KeyCode.DOWN);
        //then
        assertThat("Snake direction should be Directions.Up.",
                game.getSnake().getDirection(), is(Directions.DOWN));
    }

    /**
     * Test of processDirectionChange method, of class Game, when the direction
     * is left.
     */
    @Test
    public void testProcessLeftDirectionChange() {
        //given
        game.startGame();
        //when
        game.processDirectionChange(KeyCode.LEFT);
        //then
        assertThat("Snake direction should be Directions.Up.",
                game.getSnake().getDirection(), is(Directions.LEFT));
    }

    /**
     * Test of processDirectionChange method, of class Game, when the direction
     * is right.
     */
    @Test
    public void testProcessRightDirectionChange() {
        //given
        game.startGame();
        //when
        game.processDirectionChange(KeyCode.RIGHT);
        //then
        assertThat("Snake direction should be Directions.RIGHT.",
                game.getSnake().getDirection(), is(Directions.RIGHT));
    }

    /**
     * Test of startGame method, of class Game.
     */
    @Test
    public void testStartGame() {
        //given
        final int canvasHeight = 200;
        final int canvasWidth = 200;
        game = new Game(canvasWidth, canvasHeight);
        //when
        game.startGame();
        //then
        assertFalse("IsGameOver should be false.", game.getIsGameOver());
        assertThat("Player's score should be 0.", game.getPlayer().getScore(), is(0));
        assertNotNull("Snake shouln't be null.", game.getSnake());
        assertNotNull("Food shouln't be null.", game.getFood());
        assertTrue("The x-position of food should be greater than or equal to 0 and less than canvas width",
                game.getFood().getPosition().x >= 0 && game.getFood().getPosition().x < canvasWidth);
        assertTrue("The y-position of food should be greater than or equal to 0 and less than canvas width",
                game.getFood().getPosition().y >= 0 && game.getFood().getPosition().y < canvasHeight);
    }
}

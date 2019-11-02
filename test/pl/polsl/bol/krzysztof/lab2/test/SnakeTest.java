package pl.polsl.bol.krzysztof.lab2.test;

import org.junit.Before;
import org.junit.Test;
import pl.polsl.bol.krzysztof.lab2.models.Directions;
import pl.polsl.bol.krzysztof.lab2.models.Snake;
import static org.junit.Assert.*;

/**
 * Test case of Snake methods.
 *
 * @author Krzysztof Ból
 * @version 1.0
 */
public class SnakeTest {

    /**
     * Snake object used in tests.
     */
    private Snake snake;

    /**
     * Method that is run before tests. Creates new instance of Snake.
     */
    @Before
    public void setUp() {
        snake = new Snake(100, 100);
    }

    /**
     * Test of addNewBodyPart method, of class Snake.
     */
    @Test
    public void testAddNewBodyPart() {
        //given - snake with length = 1 created in setUp method
        //when
        snake.addNewBodyPart();
        snake.addNewBodyPart();
        snake.addNewBodyPart();
        //then
        assertEquals("Snake length should be 4.", 4, snake.getSnakeBody().size());
    }

    /**
     * Test of move method, of class Snake, when the direction is right.
     */
    @Test
    public void testMoveWithRightDirection() {
        //given 
        snake.setDirection(Directions.RIGHT);
        //when
        snake.move();
        //then
        assertEquals("Snake's head x-position should be 120.", 120, snake.getSnakeBody().get(0).getPosition().x);
        assertEquals("Snake's head y-position should be 100.", 100, snake.getSnakeBody().get(0).getPosition().y);
    }

    /**
     * Test of move method, of class Snake, when the direction is left.
     */
    @Test
    public void testMoveWithLeftDirection() {
        //given 
        snake.setDirection(Directions.LEFT);
        //when
        snake.move();
        //then
        assertEquals("Snake's head x-position should be 80.", 80, snake.getSnakeBody().get(0).getPosition().x);
        assertEquals("Snake's head y-position should be 100.", 100, snake.getSnakeBody().get(0).getPosition().y);
    }

    /**
     * Test of move method, of class Snake, when the direction is up.
     */
    @Test
    public void testMoveWithUpDirection() {
        //given 
        snake.setDirection(Directions.UP);
        //when
        snake.move();
        //then
        assertEquals("Snake's head x-position should be 100.", 100, snake.getSnakeBody().get(0).getPosition().x);
        assertEquals("Snake's head y-position should be 80.", 80, snake.getSnakeBody().get(0).getPosition().y);
    }

    /**
     * Test of move method, of class Snake, when the direction is down.
     */
    @Test
    public void testMoveWithDownDirection() {
        //given 
        snake.setDirection(Directions.DOWN);
        //when
        snake.move();
        //then
        assertEquals("Snake's head x-position should be 100.", 100, snake.getSnakeBody().get(0).getPosition().x);
        assertEquals("Snake's head y-position should be 120.", 120, snake.getSnakeBody().get(0).getPosition().y);
    }

    /**
     * Test of move method, of class Snake, when the direction is none.
     */
    @Test
    public void testMoveWithNoneDirection() {
        //given 
        snake.setDirection(Directions.NONE);
        //when
        snake.move();
        //then
        assertEquals("Snake's head x-position should be 100.", 100, snake.getSnakeBody().get(0).getPosition().x);
        assertEquals("Snake's head y-position should be 100.", 100, snake.getSnakeBody().get(0).getPosition().y);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     *
     */
    private Snake snake;
     /**
     * Test of addNewBodyPart method, of class Snake.
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
        snake.addNewBodyPart();
        assertEquals("Snake lenght should be 2.", 2, snake.getSnakeBody().size());
    }

    /**
     * Test of move method, of class Snake.
     */
   @Test
    public void testMoveRight() {
        //given 
        snake.setDirection(Directions.RIGHT);
        //when
        snake.move();
        //then
        assertEquals("Snake's head x-position should be 120.", 120, snake.getSnakeBody().get(0).getPosition().x);
        assertEquals("Snake's head y-position should be 100.", 100, snake.getSnakeBody().get(0).getPosition().y);
    }
    
    /**
     * Test of move method, of class Snake.
     */
    @Test
    public void testMoveLeft() {
        //given 
        snake.setDirection(Directions.LEFT);
        //when
        snake.move();
        //then
        assertEquals("Snake's head x-position should be 80.", 80, snake.getSnakeBody().get(0).getPosition().x);
        assertEquals("Snake's head y-position should be 100.", 100, snake.getSnakeBody().get(0).getPosition().y);
    }
    
    /**
     * Test of move method, of class Snake.
     */
    @Test
    public void testMoveUp() {
        //given 
        snake.setDirection(Directions.UP);
        //when
        snake.move();
        //then
        assertEquals("Snake's head x-position should be 100.", 100, snake.getSnakeBody().get(0).getPosition().x);
        assertEquals("Snake's head y-position should be 80.", 80, snake.getSnakeBody().get(0).getPosition().y); 
    }
    
    /**
     * Test of move method, of class Snake.
     */
    @Test
    public void testMoveDown() {
        //given 
        snake.setDirection(Directions.DOWN);
        //when
        snake.move();
        //then
        assertEquals("Snake's head x-position should be 100.", 100, snake.getSnakeBody().get(0).getPosition().x);
        assertEquals("Snake's head y-position should be 120.", 120, snake.getSnakeBody().get(0).getPosition().y); 
    } 
}

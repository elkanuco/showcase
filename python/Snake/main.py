from turtle import Screen
import time
from snake import Snake
from food import Food
from scoreboard import Scoreboard

screen = Screen()
screen.setup(width=600, height=600)
screen.bgcolor("black")
screen.title("not to brag but back in the day I did this on an oscilloscope ☺")
screen.tracer(0)
snake = Snake()
food = Food()
score_board = Scoreboard()
screen.listen()
screen.onkey(snake.up, "Up")
screen.onkey(snake.down, "Down")
screen.onkey(snake.left, "Left")
screen.onkey(snake.right, "Right")
game_is_on = True
game_is_over = False


def reset():
    global snake
    global food
    global score_board
    global game_is_over
    snake.reset()
    score_board.reset_score()
    time.sleep(0.5)
    food.refresh()
    game_is_over = False


def stop_game(x=None, y=None):
    global game_is_on
    global game_is_over
    game_is_on = False
    game_is_over = True
    score_board.game_over()
    screen.exitonclick()


def game_over():
    global game_is_over
    game_is_over = True
    score_board.game_over()


screen.onclick(stop_game)
screen.onkey(reset, "space")
while game_is_on:
    screen.update()
    time.sleep(0.1)
    if not game_is_over:
        snake.move()
        if snake.head.distance(food) < 15:
            snake.extend()
            food.refresh()
            score_board.increase_score()
        if snake.head.xcor() > 260 or snake.head.xcor() < -260 or snake.head.ycor() > 260 or snake.head.ycor() < -260:
            game_over()
        for segment in snake.segments[1:]:
            if snake.head.distance(segment) < 10:
                game_over()

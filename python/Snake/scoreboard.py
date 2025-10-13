from turtle import Turtle

ALIGNMENT = "center"
FONT = ("Courier", 24, "normal")


def load_high_score():
    with open("high_score", mode="a+") as high_score_file:
        high_score_file.seek(0)  # Move to start to read content
        value = high_score_file.read()
        if value != "":
            return int(value)
        else:
            return 0


class Scoreboard(Turtle):

    def __init__(self):
        super().__init__()
        self.score = 0
        self.high_score = load_high_score()

        self.color("white")
        self.penup()
        self.update_scoreboard()
        self.hideturtle()

    def save_high_score(self):
        with open("high_score", mode="w") as high_score_file:
            high_score_file.write(str(self.high_score))

    def update_scoreboard(self):
        self.clear()
        self.goto(0, 260)
        self.write(f'Score: {self.score} High score : {self.high_score}', align=ALIGNMENT, font=FONT)

    def game_over(self):
        self.goto(0, 0)
        self.write('GAME OVER', align=ALIGNMENT, font=FONT)
        self.goto(0, -260)
        self.write('HIT SPACE TO RESET', align=ALIGNMENT, font=FONT)
        self.high_score = max(self.score, self.high_score)
        self.save_high_score()

    def reset_score(self):
        self.high_score=max(self.score, self.high_score)
        self.save_high_score()
        self.score = 0
        self.update_scoreboard()

    def increase_score(self):
        self.score += 1
        self.update_scoreboard()

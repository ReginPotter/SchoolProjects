from Player import Player
from Game import Game

class Main:
    print("This is the start of the project")

    print("This project is to test if Regin can understand python and if he can apply higher level abilities")

    print("He will try using GUIs later")
    print("Lets start.")
    print("\n")
    prompt = "Please enter an integer "

    while True:
        try:
            userInput = int(input(prompt))
        except ValueError:
            print("Please input an Integer\n")
            continue
        if userInput == 0:
            p1 = Player("Iron Man", 50, 20)
            p2 = Player("Thanos", 100, 20)
            print(p1, p2, sep=' ')
            while p1.getHealth() > 0 and p2.getHealth() > 0:
                p1.hit(p2)
                p2.hit(p1)
                print(p1, p2, sep=' ')

        elif userInput == 1:
            game = Game()
            print(game)

        else:
            print("You havent chosen anything special")
            break
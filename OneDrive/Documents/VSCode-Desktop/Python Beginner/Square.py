class Square:
    def __init__(self, name):
        self.sides = 4
        self.name = name

    def __str__(self):
        return self.name + " is a Square. Sides = %s" % (self.sides)
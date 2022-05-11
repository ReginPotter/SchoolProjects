import Square

class Rectangle(Square.Square):
    def __init__(self, name):
        super().__init__(name)

    def __str__(self):
        return self.name + " is a Rectangle. There are %s sides" % (self.sides)
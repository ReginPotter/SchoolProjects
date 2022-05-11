class Bird:

    # This initializes the class item and lets the code change any of the variables
    def __init__(self, says="Chirp", gender="Male", name="Steve"):
        self.says = says
        self.gender = gender
        self.name = name

    # This makes the bird "fly"
    def fly(self):
        print("Bird flies")

    # This prints out all useful information about the bird
    def __str__(self):
        return self.name + " is a " + self.gender + " and says " + self.says
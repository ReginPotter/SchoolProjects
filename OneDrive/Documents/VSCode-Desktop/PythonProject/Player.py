import random

class Player:

    def __init__ (self, name, health, maxDamage):
        self.name = name
        self.health = health
        self.maxDamage =maxDamage

    def setName(self, name):
        self.name = name

    def getName(self):
        return self.name

    def setHealth(self, health):
        self.health = health

    def getHealth(self):
        return self.health

    def setMaxDamage(self, maxDamage):
        self.maxDamage = maxDamage

    def getMaxDamage(self):
        return self.maxDamage

    def hit (self, other):
        rdm = random.randint(1, self.maxDamage)

        other.setHealth(other.getHealth()-rdm)

    def __str__(self):
        return f'{self.name} ({self.health})'
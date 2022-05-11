from Bird import Bird
from Rectangle import Rectangle
from Square import Square
"""
a = 'xxcaazz'
b = 'xxbaaz'
count = 0
ans = 0
for x in range(len(a)-2):
    if a[x]==b[x] and a[x+1]==b[x+1]:
        ans += 1
print(f"{ans} = ans")
"""

# Figuring out print statements
print("Welcome to Python")
print()

# Working with integers / doubles
a = 3
b = 3.5

# Working with if/else statements
if a>b:
    print("a > b")
    print("a = ", a)
    print("b = ", b)
elif b>a:
    print("b > a")
    print(f"a = {a}")
    print(f"b = {b}")
else:
    print("b = a")

    # in order to print like this use % only for integers and/or doubles
    print("a = %s\nb = %s" % (a, b))
print()

# Working with files and printing out lines
with open("test.txt") as f:
    lines = f.readlines()

count = 0
print("Start")
for line in lines:
    count += 1
    print(f'line {count}: {line}')


print()
# Printing out individual words in a file
with open('test.txt') as f:
    for line in f:
        line = line.rstrip("\n")
        line = line.split(' ')

        # This prints out each individual work in a given line and then ends gives an enter at the end
        for i in range(0, len(line)):
            print(line[i], end=" ")
        print()

print()

# This creates a new Square object and prints the number of sides and name of the object
newSquare = Square("square")
sides = newSquare.sides
print(sides)
print(newSquare.name)
print(newSquare)

print()
# This creates two bird objects and changes the variables for one of the birds and prints out the necessary info
b1 = Bird()
b2 = Bird("AAAH", "NB", "Orange")

print(b1)
print()
print(b2)
print()

r1 = Rectangle("Jerald")
print(r1)
print()

# input() reads in input from the user
input = input("please enter a string ")
print(f"You entered {input}")

# any variable can be any type so be careful what you do with the variable
print(f"a = {a}")
print(f"a = {a+6}")
a = "hello"
print(f"a = {a}")
# print(f"a = {a+6}")

"""
# You can create a class inside the main class
class Square:
    sides = 4
    name = "Square"
"""
from faker import Faker
from faker.providers import address
from faker.providers import company
from random import randint
import sys

fake = Faker()
fake.add_provider(address)
fake.add_provider(company)


def generate_shelters(x, filename):
    shelter_data = {}
    for i in range(0, x):
        shelter_data[i] = {}
        shelter_data[i]['name'] = fake.company()
        shelter_data[i]['city'] = fake.city()
        shelter_data[i]['street'] = fake.street_name()
        shelter_data[i]['phone'] = str(fake.phone_number())

    try:
        with open(filename, 'w') as f:
            for key in shelter_data.keys():
                for value in shelter_data[key].values():
                    if "," in value:
                        f.write('"' + value + '", ')
                    else:
                        f.write(value + ", ")
                f.write("\n")

    except IOError:
        print("Error writing to CSV")


def generate_animals(x, filename):
    animal_data = {}
    for i in range(0, x):
        animal_data[i] = {}
        animal_data[i]['TypeID'] = randint(1, 7)
        animal_data[i]['name'] = fake.first_name()
        animal_data[i]['color'] = fake.safe_color_name()
        animal_data[i]['age'] = randint(0, 5)
        animal_data[i]['gender'] = randint(0, 1)
        animal_data[i]['weight'] = randint(1, 15)
        animal_data[i]['price'] = randint(100, 420)
        animal_data[i]['breed'] = randint(0, 10)
        animal_data[i]['shelterID'] = randint(1, 20)

    try:
        with open(filename, 'w') as f:
            for key in animal_data.keys():
                for value in animal_data[key].values():
                    f.write(str(value) + ',')
                f.write("\n")
    except IOError:
        print("Error writing to CSV")


generate_shelters(20, 'shelters.csv')
try:
    generate_animals(int(sys.argv[1]), sys.argv[2])
except IndexError:
    print("Error: program requires 2 command line arguments: \n"
          "1) The number of fake animal records that are to be created.\n"
          "2) The csv file these records will be output to.")
except ValueError:
    print("Error: first command line argument must be an integer");
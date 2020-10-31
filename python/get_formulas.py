import sys

from py.polynom_creator import PolynomCreator


if __name__ == '__main__':
    vector = sys.argv[1:]
    e = PolynomCreator(vector).find_polynomial()
    print(e)

import sys

from py.polynom_creator import PolynomCreator


if __name__ == '__main__':
    path = sys.argv[1]
    with open(path) as fin:
        vector = [line for line in fin]
    polynomial = PolynomCreator(vector).find_polynomial()
    print(polynomial)

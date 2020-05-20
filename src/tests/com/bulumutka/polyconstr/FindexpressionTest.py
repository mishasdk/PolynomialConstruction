import unittest
import sympy as sp

import com.bulumutka.polyconstr.FindExpression as ex

path = '../../../../../data/'


class GraphTestCase(unittest.TestCase):
    def test_k3_graph(self):
        v = ex.read_vector(path + 'K3_graph_vector.txt')
        expected_formulas = ex.find_k_graph(3, ["sqrt(2)", "sqrt(3)", "sqrt(5)"])
        actual_formulas = ex.find_polynomial(v)
        print('Expected:', expected_formulas)
        print('Actual:', actual_formulas)

    # def test_k4_graph(self):
    #     v = ex.read_vector(path + 'K4_graph_vector.txt')
    #     expected_formulas = ex.find_k_graph(4, [1, 1, 1, 1, 1, 1])
    #     actual_formulas = ex.find_polynomial(v)
    #
    #     T = sp.symbols('T')
    #     print('Expected:', expected_formulas)
    #     print('Actual:', actual_formulas)
    #
    # # def test_k5_graph(self):
    # #     v = ex.read_vector(path + 'K5_graph_vector.txt')
    # #     expected_formulas = ex.find_k_graph(5, [1 for i in range(10)])
    # #     actual_formulas = ex.find_polynomial(v)
    # #     print('Expected:', expected_formulas)
    # #     print('Actual:', actual_formulas)
    #
    # def test_cycle_3(self):
    #     v = ex.read_vector(path + 'cycle_3_vector.txt')
    #     expected_formulas = ex.find_cycle_graph(3, [1, 1, 1])
    #     actual_formulas = ex.find_polynomial(v)
    #     print('Expected:', expected_formulas)
    #     print('Actual:', actual_formulas)
    #
    # def test_cycle_4(self):
    #     v = ex.read_vector(path + 'cycle_4_vector.txt')
    #     expected_formulas = ex.find_cycle_graph(4, [1, 1, 1, 1])
    #     actual_formulas = ex.find_polynomial(v)
    #     print('Expected:', expected_formulas)
    #     print('Actual:', actual_formulas)
    #
    # def test_cycle_5(self):
    #     v = ex.read_vector(path + 'cycle_5_vector.txt')
    #     expected_formulas = ex.find_cycle_graph(5, [1, 1, 1, 1, 1])
    #     actual_formulas = ex.find_polynomial(v)
    #     print('Expected:', expected_formulas)
    #     print('Actual:', actual_formulas)


if __name__ == '__main__':
    unittest.main()

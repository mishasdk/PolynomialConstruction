import unittest

import com.bulumutka.polyconstr.FindExpression as ex

path = '../../../../../data/'


class GraphTestCase(unittest.TestCase):
    def test_k3_graph(self):
        v = ex.read_vector(path + 'K3_graph_vector.txt')
        expected_formulas = ex.find_k_graph(3, [1, 1, 1])
        print('Expected:', expected_formulas)
        actual_formulas = ex.find_polynomial(v)
        print('Actual:', actual_formulas)
        print('Expected:', expected_formulas)

    def test_k4_graph(self):
        v = ex.read_vector(path + 'K4_graph_vector.txt')
        expected_formulas = ex.find_k_graph(4, [1, 1, 1, 1, 1, 1])
        actual_formulas = ex.find_polynomial(v)
        print('Expected:', expected_formulas)
        print('Actual:', actual_formulas)


if __name__ == '__main__':
    unittest.main()

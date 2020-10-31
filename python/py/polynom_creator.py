import numpy as np
import sympy as sp
import time

from sympy.parsing.sympy_parser import parse_expr


class PolynomCreator:
    def __init__(self, vector):
        self.vector = vector

    def find_polynomial(self):
        # First term R'(T)
        T = sp.symbols('T')
        sub_graph_number = int(self.vector[2])
        index = 3
        first_term = 0
        for _ in range(sub_graph_number):
            vertex_number = int(self.vector[index])
            index += 1
            edges_number = int(self.vector[index])
            index += 1
            edges_weights = [parse_expr(self.vector[index + j]) * 2 for j in range(edges_number)]
            index += edges_number
            for _ in range(vertex_number):
                sub = int(self.vector[index])
                index += 1
                if sub == 0:
                    continue
                args_number = int(self.vector[index])
                index += 1
                sum1 = 0
                for _ in range(args_number):
                    set_size = int(self.vector[index])
                    index += 1
                    argument = sum([parse_expr(self.vector[index + i]) for i in range(set_size)])
                    index += set_size
                    sum1 += self.bernoulli_barns(argument, edges_weights)
                first_term += sp.sympify(sum1 * sub)
        # Second term R''(T)
        sub_graph_number = int(self.vector[index])
        index += 1
        second_term = 0
        for _ in range(sub_graph_number):
            vertex_number = int(self.vector[index])
            index += 1
            if vertex_number == 0:
                continue
            edges_number = int(self.vector[index])
            index += 1
            edges_weights = [parse_expr(self.vector[index + j]) * 2 for j in range(edges_number)]
            index += edges_number
            for _ in range(vertex_number):
                j_weight = parse_expr(self.vector[index])
                index += 1
                args_number = int(self.vector[index])
                index += 1
                sum1 = 0
                edges_weights.remove(j_weight * 2)
                for _ in range(args_number):
                    set_size = int(self.vector[index])
                    index += 1
                    argument = sum([parse_expr(self.vector[index + i]) - j_weight for i in range(set_size)])
                    index += set_size
                    sum1 += self.bernoulli_barns(argument, edges_weights)
                edges_weights.append(j_weight * 2)
                second_term += sum1
        assert (index == len(self.vector))
        return sp.collect(sp.expand(first_term + second_term), T)

    @staticmethod
    def bernoulli_barns(S, t):
        T = sp.symbols('T')
        k = len(t)
        expr = 1 / np.prod(t) * (
                    (T + S) ** k / sp.factorial(k) - np.sum(t) / 2 * (T + S) ** (k - 1) / sp.factorial(k - 1))
        expr -= expr.subs(T, 0)
        return sp.expand(expr)

    @staticmethod
    def format_time(seconds):
        return time.strftime('%H:%M:%S', time.gmtime(seconds))

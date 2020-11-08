import numpy as np
import sympy as sp
import time

from sympy.parsing.sympy_parser import parse_expr


class PolynomCreator:
    def __init__(self, vector):
        self.vector = vector
        self.log_period = len(vector) // 100
        self.start = 0

    def find_polynomial(self):
        # First term R'(T)
        self.start = time.time()
        T = sp.symbols('T')
        sub_graph_number = int(self.vector[2])
        index = 3
        first_term = 0
        log_counter = 0
        for _ in range(sub_graph_number):
            log_counter = self.try_log(log_counter, index)

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
            log_counter = self.try_log(log_counter, index)

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
        print(f'progress: {len(self.vector)} / {len(self.vector)}')
        return sp.collect(sp.expand(first_term + second_term), T)

    def try_log(self, log_counter, index):
        if log_counter == self.log_period:
            print(f'progress: {index} / {len(self.vector)},'
                  f' time: {self.format_time(time.time() - self.start)}')
            return 0
        else:
            return log_counter + 1

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
        seconds = round(seconds)
        minutes = seconds // 60
        hours = minutes // 60
        print(minutes, hours)
        return time.strftime('%s:%s:%s' % (hours, minutes % 60, seconds % 60))

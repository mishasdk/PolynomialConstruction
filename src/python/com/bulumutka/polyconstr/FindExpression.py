import numpy as np
import sympy as sp
from time import time
from sympy.parsing.sympy_parser import parse_expr


def find_polynomial(vector):
    # First term R'(T)
    start = time()
    T = sp.symbols('T')
    sub_graph_number = int(vector[2])
    index = 3
    first_term = 0
    for _ in range(sub_graph_number):
        edges_number = int(vector[index])
        index += 1
        edges_weights = [parse_expr(vector[index + j]) * 2 for j in range(edges_number)]
        index += edges_number
        vertex_number = int(vector[index])
        index += 1
        for _ in range(vertex_number):
            sub = int(vector[index])
            index += 1
            args_number = int(vector[index])
            index += 1
            sum1 = 0
            for _ in range(args_number):
                set_size = int(vector[index])
                index += 1
                argument = sum([parse_expr(vector[index + i]) for i in range(set_size)])
                index += set_size
                sum1 += bernoulli_barns(argument, edges_weights)
            first_term += sp.sympify(sum1 * sub)
    # Second term R''(T)
    sub_graph_number = int(vector[index])
    index += 1
    second_term = 0
    for _ in range(sub_graph_number):
        edges_number = int(vector[index])
        index += 1
        edges_weights = [parse_expr(vector[index + j]) * 2 for j in range(edges_number)]
        index += edges_number
        vertex_number = int(vector[index])
        index += 1
        for _ in range(vertex_number):
            j_weight = parse_expr(vector[index])
            index += 1
            args_number = int(vector[index])
            index += 1
            sum1 = 0
            edges_weights.remove(j_weight * 2)
            for _ in range(args_number):
                set_size = int(vector[index])
                index += 1
                argument = sum([parse_expr(vector[index + i]) - j_weight for i in range(set_size)])
                index += set_size
                sum1 += bernoulli_barns(argument, edges_weights)
            edges_weights.append(j_weight * 2)
            second_term += sum1
    assert (index == len(vector))
    print('time =', time() - start)
    return sp.collect(sp.expand(first_term + second_term), T)


def find_k_graph(n, t):
    t = list(map(parse_expr, t))
    n = int(n)
    m = len(t)
    T = sp.symbols('T')
    expr = T ** (m - 1) * sum(t) / 2 ** (n - 2) / sp.factorial(m - 1) / np.prod(t)
    expr += T ** (m - 2) * double_sum(t) / 2 ** (n - 2) / sp.factorial(m - 2) / np.prod(t)
    return sp.collect(sp.expand(expr), T)


def bernoulli_barns(S, t):
    T = sp.symbols('T')
    k = len(t)
    expr = 1 / np.prod(t) * ((T + S) ** k / sp.factorial(k) - np.sum(t) / 2 * (T + S) ** (k - 1) / sp.factorial(k - 1))
    expr -= expr.subs(T, 0)
    return sp.expand(expr)


def find_cycle_graph(n, t):
    t = list(map(parse_expr, t))
    T = sp.symbols('T')
    expr = T ** (n - 1) * sum(t) / (2 ** (n - 2)) / sp.factorial(n - 1) / np.prod(t)
    expr += double_sum(t) * T ** (n - 2) / 2 ** (n - 2) / sp.factorial(n - 2) / np.prod(t)
    return sp.collect(sp.expand(expr), T)


def double_sum(a):
    sum1 = 0
    for i in range(len(a)):
        for j in range(i + 1, len(a)):
            sum1 += a[i] * a[j]
    return sum1


def unpack_vector(pathname):
    return find_polynomial(read_vector(pathname))


def calculate_polynomial(expression, x):
    T = sp.sybols('T')
    return expression.subs(T, x)


def read_vector(pathname):
    vector = []
    with open(pathname, 'r') as reader:
        vector = [str(line) for line in reader]

    return vector

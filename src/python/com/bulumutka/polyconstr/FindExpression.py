import sympy as sp
import com.bulumutka.polyconstr.BernoulliBarnsPolynomial as pol


def find_polynomial(vector):
    n = vector[0]
    m = vector[1]
    polynom = pol.BernoulliBarnsPolynomial(m)
    # First term R'(T)
    sub_graph_number = vector[3]
    index = 4
    first_term = 0
    for _ in range(sub_graph_number):
        edges_number = vector[index]
        index += 1
        edges_weights = [vector[index + j] * 2 for j in range(edges_number)]
        index += edges_number
        vertex_number = vector[index]
        index += 1
        for _ in range(vertex_number):
            sub = vector[index]
            index += 1
            args_number = vector[index]
            index += 1
            args = [vector[index + j] for j in range(args_number)]
            index += args_number
            sum = 0
            for i in range(args_number):
                sum += polynom.get_polynomial(args[i], edges_weights)
            sum *= sub
            first_term += sum

    #Second term R''(T)
    sub_graph_number = vector[index]
    index += 1
    second_term = 0
    for _ in range(sub_graph_number):
        edges_number = vector[index]
        index += 1
        edges_weights = [vector[index + j] * 2 for j in range(edges_number)]
        index += edges_number
        vertex_number = vector[index]
        index += 1
        for _ in range(vertex_number):
            args_number = vector[index]
            index += 1
            args = [vector[index + j] for j in range(args_number)]
            index += args_number
            sum = 0
            for i in range(args_number):
                sum += polynom.get_polynomial(args[i], edges_weights)
            sum *= sub
            second_term += sum

    return first_term + second_term

def read_vector(pathname):
    vector = []
    with open(pathname, 'r') as reader:
        vector = list(map(int, reader.readline().split()))
    return vector
import sympy as sp
import com.bulumutka.polyconstr.FindExpression as expr


def unpack_vector(pathname):
    return expr.find_polynomial(expr.read_vector(pathname))


def calculate_polynomial(expression, x):
    T = sp.sybols('T')
    return expression.subs(T, x)


if __name__ == '__main__':
    pass
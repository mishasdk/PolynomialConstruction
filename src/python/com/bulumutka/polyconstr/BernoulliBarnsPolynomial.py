import sympy as sp


class BernoulliBarnsPolynomial:
    def __init__(self, max_edges_number):
        self._max_edges_number = max_edges_number
        self._build_factorial_table()

    def _build_factorial_table(self):
        factorials = [1 for i in range(0, self._max_edges_number)]
        power = 1
        for i in range(1, self._max_edges_number):
            power *= i
            factorials[i] = power
        self._factorials = factorials

    def get_polynomial(self, S, args):
        T = sp.symbols('T')
        k = len(args)
        pow = BernoulliBarnsPolynomial._get_pow(args)
        sum = BernoulliBarnsPolynomial._get_sum(args)
        expr = 1 / (pow) * ((T + S) ** k / self._factorials[k] + 1 / 2 * (sum) * (T + S) ** (k - 1) / self._factorials[k - 1])
        return sp.expand(expr)

    @staticmethod
    def _get_pow(args):
        pow = 1
        for a in args:
            pow *= a
        return pow

    @staticmethod
    def _get_sum(args):
        sum = 0
        for a in args:
            sum += a
        return sum

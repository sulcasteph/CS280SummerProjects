class Complex:
    def __init__(self, r = 0, i = 0):
        self.r = r
        self.i = i
        
    
    # operation for addtion (+)
    def __add__(self, otherObj):
        if isinstance(otherObj, Complex): # checks if the other object is a complex number
            return Complex(self.r + otherObj.r, self.i + otherObj.i)
        return Complex(self.r + otherObj, self.i)
    
    # oepration for reflected addtion 
    def __radd__(self, otherObj):
        return self + otherObj
    
    
    # operatino for subtraction (-)
    def __sub__(self, otherObj):
        # checks if other object is complec
        if isinstance(otherObj, Complex):
            return Complex(self.r - otherObj.r, self.i - otherObj.i)
        return Complex(self.r - otherObj, self.i)
    
    # operation for reflected subtraction
    def __rsub__(self, otherObj):
        return Complex(otherObj - self.r, -self.i)
    
    
    # operation for multiplication (*)
    def __mul__(self, otherObj):
        if isinstance(otherObj, Complex):
            return Complex(self.r * otherObj.r - self.i * otherObj.i, self.r * otherObj.i + self.i * otherObj.r)
        return Complex(self.r * otherObj, self.i * otherObj)

    # operation for reflected multiplication
    def __rmul__(self, otherObj):
        return self * otherObj

    # operation for true division (/)
    def __truediv__(self, otherObj):
        if isinstance(otherObj, Complex):
            denom = otherObj.r**2 + otherObj.i**2
            return Complex((self.r * otherObj.r + self.i * otherObj.i) / denom, (self.i * otherObj.r - self.r * otherObj.i) / denom)
        return Complex(self.r / otherObj, self.i / otherObj)

    # operation for reflected true division
    def __rtruediv__(self, otherObj):
        denom = self.r**2 + self.i**2
        return Complex((otherObj * self.r) / denom, (-otherObj * self.i) / denom)
    
    # operation for string representation 
    def __str__(self):
        return f"({self.r} + {self.i}i)"
    
    
# main method
if __name__ == '__main__':
    c1 = Complex(3, 4)
    c2 = Complex(1, -3)
    x = 3.0
    
    print(f"{c1} + {c2} = {c1 + c2}")
    print(f"{c1} - {c2} = {c1 - c2}")
    print(f"{c1} * {c2} = {c1 * c2}")
    print(f"{c1} / {c2} = {c1 / c2}")
    
    print(f"{c1} + {x} = {c1 + x}")
    print(f"{c1} + {x} = {c1 + x}")
    print(f"{c1} * {x} = {c1 * x}")
    print(f"{c1} / {x} = {c1 / x}")
    
    print(f"{x} + {c1} = {x + c1}")
    print(f"{x} - {c1} = {x - c1}")
    print(f"{x} * {c1} = {x * c1}")
    print(f"{x} / {c1} = {x / c1}")
